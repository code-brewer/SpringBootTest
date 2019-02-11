package com.wac.springboot.springboot02config.bean;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.Email;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 将配置文件中配置的每一个属性的值映射到这个组件中
 * @ConfigurationProperties：告诉SpringBoot将本类中的所有属性和配置文件中相关的配置进行绑定
 *      prefix = "person": 配置文件中哪个下面的所有属性进行一一映射
 * 只有这个组件是容器中的组件，才能使用容器提供的@ConfigurationProperties功能
 * 默认从全局配置文件中获取值
 *
 * 如果将所有的配置都添加到全局配置文件application.properties或者application.yml中时,
 * 肯定会过于繁杂
 * 使用@PropertySource(value = {"classpath:person.properties"})指定配置文件
 * (注意当全局配置文件与person.properties中都有person时 以全局配置文件中的为准)
 *
 */
@Data
@PropertySource(value = {"classpath:person.properties"})
@Component //声明此类是容器中的一个组件bean
@ConfigurationProperties(prefix = "person") //配置文件绑定
@Validated //数据校验

public class Person {


    /*
     * <bean id="Person" class="---">
     *      <property name="lastName" value="字面量/${key}从环境变量中获取/#{SpEL}" />
     * </bean>
     */

    /*
    当@ConfigurationProperties和@value同时存在时 以前者为准
    @Email数据校验 只有在使用@ConfigurationProperties情况下才有效
     */

    /**
     * 当application.properties和application.yml同时配置相同的属性时
     * 默认使用application.properties中的值
     */

    //@Value("${person.last-name}")
    private String lastName;
    //@Value("#{3*6}")
    private Integer age;
    private Boolean boss;
    private Date birth;
    private Map<String,Object> maps;
    private List<Object> lists;
    private Dog dog;
    @Email
    //Field error in object 'person' on field 'email': rejected value [1515476572]
    private String email;

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public Boolean getBoss() {
        return boss;
    }

    public void setBoss(Boolean boss) {
        this.boss = boss;
    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public Map<String, Object> getMaps() {
        return maps;
    }

    public void setMaps(Map<String, Object> maps) {
        this.maps = maps;
    }

    public List<Object> getLists() {
        return lists;
    }

    public void setLists(List<Object> lists) {
        this.lists = lists;
    }

    public Dog getDog() {
        return dog;
    }

    public void setDog(Dog dog) {
        this.dog = dog;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
