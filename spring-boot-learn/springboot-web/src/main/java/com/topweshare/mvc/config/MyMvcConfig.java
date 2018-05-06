package com.topweshare.mvc.config;

import com.topweshare.mvc.converter.MyMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import java.util.List;

/**
 * All Rights Reserved !!!
 *
 * WebMvcConfigurerAdapter(过时)
 */
@Configuration
@EnableWebMvc
@ComponentScan("com.topweshare.mvc")
public class MyMvcConfig implements WebMvcConfigurer {
    @Bean
    public InternalResourceViewResolver viewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("/WEB-INF/classes/views/");
        viewResolver.setSuffix(".jsp");
        viewResolver.setViewClass(JstlView.class);

        return viewResolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {

        registry.addResourceHandler("/assets/**").addResourceLocations("classpath:/assets/");

    }


    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/converter").setViewName("/converter");
    }

    /**
     * 配置自定义的HttpMessageConverter 的Bean ，在Spring MVC 里注册HttpMessageConverter有两个方法：
     * 1、configureMessageConverters ：重载会覆盖掉Spring MVC 默认注册的多个HttpMessageConverter
     * 2、extendMessageConverters ：仅添加一个自定义的HttpMessageConverter ，不覆盖默认注册的HttpMessageConverter
     * 在这里重写extendMessageConverters
     */
    @Override
    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {

        converters.add(converter());

    }

    @Bean
    public MyMessageConverter converter() {
        return new MyMessageConverter();
    }


}
