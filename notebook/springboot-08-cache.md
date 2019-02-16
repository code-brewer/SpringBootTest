原理讲解：

1、自动配置类：CacheAutoConfiguration

@Import(CacheConfigurationImportSelector.class)

2、selectImports:

```java
static class CacheConfigurationImportSelector implements ImportSelector {

   @Override
   public String[] selectImports(AnnotationMetadata importingClassMetadata) {
      CacheType[] types = CacheType.values();
      String[] imports = new String[types.length];
      for (int i = 0; i < types.length; i++) {
         imports[i] = CacheConfigurations.getConfigurationClass(types[i]);
      }
      return imports;
   }
}
```


imports： 缓存配置类

1.  "org.springframework.boot.autoconfigure.cache.GenericCacheConfiguration"
2. "org.springframework.boot.autoconfigure.cache.JCacheCacheConfiguration"
3. "org.springframework.boot.autoconfigure.cache.EhCacheCacheConfiguration"
4. "org.springframework.boot.autoconfigure.cache.HazelcastCacheConfiguration"
5. "org.springframework.boot.autoconfigure.cache.InfinispanCacheConfiguration"
6. "org.springframework.boot.autoconfigure.cache.CouchbaseCacheConfiguration"
7. "org.springframework.boot.autoconfigure.cache.RedisCacheConfiguration"
8. "org.springframework.boot.autoconfigure.cache.CaffeineCacheConfiguration"
9. "org.springframework.boot.autoconfigure.cache.SimpleCacheConfiguration"
10. "org.springframework.boot.autoconfigure.cache.NoOpCacheConfiguration"

3、哪个配置类生效？
SimpleCacheConfiguration.class

```
给容器中注入一个缓存管理器ConcurrentMapCacheManager
@Bean
public ConcurrentMapCacheManager cacheManager() {
   ConcurrentMapCacheManager cacheManager = new ConcurrentMapCacheManager();
   List<String> cacheNames = this.cacheProperties.getCacheNames();
   if (!cacheNames.isEmpty()) {
      cacheManager.setCacheNames(cacheNames);
   }
   return this.customizerInvoker.customize(cacheManager);
}
```

4、ConcurrentMapCacheManager

```java
@Override
@Nullable
public Cache getCache(String name) {
   Cache cache = this.cacheMap.get(name);
   if (cache == null && this.dynamic) {
      synchronized (this.cacheMap) {
         cache = this.cacheMap.get(name);
         if (cache == null) {
            cache = createConcurrentMapCache(name);
            this.cacheMap.put(name, cache);
         }
      }
   }
   return cache;
}
```

### 运行流程：

```java
public Cache getCache(String name) {
   Cache cache = this.cacheMap.get(name);
   if (cache == null && this.dynamic) {
      synchronized (this.cacheMap) {
         cache = this.cacheMap.get(name);
         if (cache == null) {
            cache = createConcurrentMapCache(name);
            this.cacheMap.put(name, cache);
         }
      }
   }
   return cache;
}
```

1、service类中的方法（目标方法）运行之前，先去查询cache（缓存组件），按照cacheName指定的名字获取；

CacheManager先获取相应的缓存（public Cache getCache(String name)），如

2、ConcurrentMapCache

```java
@Override
@Nullable
protected Object lookup(Object key) {
   return this.store.get(key);
}
```

去ConcurrentMapCache中查找相应的缓存cache，使用一个key，默认是目标方法的参数

lookup(Object key)

**key是按照某种策略生成出来的，默认是使用keyGenerator生成的**

```
SimpleKeyGenerator生成key的默认策略：
如果没有参数：key=new SimpleKey();
如果有一个参数：key=参数的值
如果有多个参数：key=new SimpleKey(params);
```

```java
public abstract class CacheAspectSupport extends AbstractCacheInvoker

SimpleKeyGenerator implements KeyGenerator 
public static Object generateKey(Object... params) {
		if (params.length == 0) {
			return SimpleKey.EMPTY;
		}
		if (params.length == 1) {
			Object param = params[0];
			if (param != null && !param.getClass().isArray()) {
				return param;
			}
		}
		return new SimpleKey(params);
	}
```

3、如果没有查到缓存，调用目标方法

4、将目标方法返回结果放进缓存中

```java
private final ConcurrentMap<String, Cache> cacheMap = new ConcurrentHashMap<>(16);
```

```java
if (cache == null) {
   cache = createConcurrentMapCache(name);
   this.cacheMap.put(name, cache);
}
```



@Cacheable标注的方法执行之前，先检查缓存中有没有这个数据,如果没有就运行并将结果放入缓存，以后再来调用就可以直接使用缓存中

核心：

1、使用cacheManager(ConcurrentMapCacheManager)按照名字得到ConcurrentMapCache组件

2、key使用keyGenerator生成的，默认是SimplekeyGenerator