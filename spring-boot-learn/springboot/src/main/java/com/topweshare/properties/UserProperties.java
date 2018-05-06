package com.topweshare.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

/**
 * Created by mongoding on 2017/4/18.
 */


@Configuration
@PropertySource(value = "classpath:test.properties")
@ConfigurationProperties(prefix = "com.topweshare.user")
public class UserProperties {
    private String name;
    @Max(150)
    @Min(0)
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
