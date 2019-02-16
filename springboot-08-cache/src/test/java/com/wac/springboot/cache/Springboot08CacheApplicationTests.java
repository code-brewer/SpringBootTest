package com.wac.springboot.cache;

import com.wac.springboot.cache.domain.Employee;
import com.wac.springboot.cache.mapper.EmployeeMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot08CacheApplicationTests {

    @Autowired
    EmployeeMapper employeeMapper;

    @Autowired
    StringRedisTemplate stringRedisTemplate;
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    RedisTemplate empRedisTemplate;
    @Test
    public void test1(){
        //stringRedisTemplate.opsForValue().append("msg","wangancheng");
    }
    @Test
    public void test2(){
        Employee employee = employeeMapper.getEmpById(1);
        redisTemplate.opsForValue().set("emp",employee);
        //以json的形式存入Redis中
        empRedisTemplate.opsForValue().set("emp",employee);
    }


    @Test
    public void contextLoads() {
        Employee employee = employeeMapper.getEmpById(1);
        System.out.println(employee.toString());
    }

}