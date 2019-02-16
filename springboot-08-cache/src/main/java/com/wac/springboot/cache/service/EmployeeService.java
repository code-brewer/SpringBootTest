package com.wac.springboot.cache.service;

import com.wac.springboot.cache.domain.Employee;
import com.wac.springboot.cache.mapper.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.*;
import org.springframework.stereotype.Service;

@CacheConfig()
@Service
@SuppressWarnings("all")
public class EmployeeService {

    @Autowired
    private EmployeeMapper employeeMapper;


    /**
     * 将方法的运行结果进行缓存，以后再要相同的数据，直接从缓存中获取
     * @Cacheable:
     *  cacheNames:指定缓存管理器CacheManager
     *      CacheManager管理多个Cache组件的，对缓存的真正的CRUD操作在缓存管理器中
     *      每一个cacheMangaer有唯一的名
     *  key：缓存数据使用的key；可以用它来指定  默认是使用方法参数
     *      SpEL：
     *  keyGenerator:key的生成器，可以自己指定key的生成器
     *      key和keyGenerator 二选一使用
     *  cacheManager：指定缓存管理器 或者选择缓存解析器CacheSovler
     *  conditon：指定符合条件的情况下才缓存
     *  ubless:否定缓存 当指定条件为true 不缓存 与conditon相反
     *      可以获取结果来判定
     *      ubless
     *  sync：是否使用异步模式
     *
     * @param id
     * @return Employee
     */
    //@Cacheable(cacheNames = "emp",keyGenerator = "myKeyGenerator",condition = "#a0>0")
    @Cacheable(cacheNames = "emp",key = "#id")
    public Employee getEmpById(Integer id){
        System.out.println("查询"+id+"号员工");
        return employeeMapper.getEmpById(id);
    }

    /**
     * 测试步骤：
     * 1、查询1号员工：查到的结果会放在缓存中
     *      key：#id 1  value：Employee对象
     * 2、以后查询还是之前的结果
     * 3、更新1号员工：
     *      将方法的返回值也放进缓存中
     *      key:(默认是参数) 值：返回的Employee对象
     * value = "emp",key = "#employee.id"
     * value = "emp",key = "#result.id"
     *      @cacheable的key不能用result
     * @param employee
     * @return
     */
    //@CachePut(value = "emp",key = "#employee.id")
    @CachePut(value = "emp",key = "#result.id")
    public Employee update(Employee employee){
        System.out.println("更新缓存中"+employee.getdId()+"号员工");
        employeeMapper.updateEmp(employee);
        return employee;
    }

    /**
     * key:指定要删除缓存的key
     * allEntries：清理缓存中所有的缓存
     * beforeInvocation = false :清理是否在方法执行之前执行费
     * @param id
     */
    @CacheEvict(value = "emp",key = "#id",allEntries = false)
    public void deleteEmpById(Integer id){
        System.out.println("删除缓存中"+id+"号员工");
        employeeMapper.deleteEmpById(id);
    }

    /**
     * 测试方法：
     * http://localhost:8080/emp/lastname/wang
     * 运行之后，会有三个缓存
     * http://localhost:8080/emp/1
     * @param lastName
     * @return
     */
    @Caching(
            cacheable = {
                    @Cacheable(value = "emp",key = "#lastName")
            },
            put = {
                    @CachePut(value = "emp",key = "#result.email"),
                    @CachePut(value = "emp",key = "#result.id")
            }
    )
    public Employee getEmpByLastName(String lastName){
        return employeeMapper.getEmpByLastName(lastName);
    }


    //@cacheConfig 抽去缓存的公共配置



}
