package com.baidu.utils;

import com.baidu.service.DictionaryService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class SpringContextHolder implements ApplicationContextAware {

    private static ApplicationContext applicationContext;
    public static DictionaryService dictionaryService;
    public static JdbcTemplate jdbcTemplate;
    public static Environment environment;


    @Override
    public void setApplicationContext(ApplicationContext applicationContext)
            throws BeansException {
        SpringContextHolder.applicationContext = applicationContext;
        dictionaryService = applicationContext.getBean(DictionaryService.class);
        jdbcTemplate = applicationContext.getBean(JdbcTemplate.class);
        environment = applicationContext.getEnvironment();

    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @SuppressWarnings("unchecked")
    public static <T> T getBean(String name) {
        return (T) applicationContext.getBean(name);
    }

    public static <T> T getBean(Class<T> clazz) {
        Map<String, T> beanMaps = applicationContext.getBeansOfType(clazz);
        if (beanMaps != null && !beanMaps.isEmpty()) {
            return (T) beanMaps.values().iterator().next();
        } else {
            return null;
        }
    }

    public static String getProperty(String name) {
        return environment.getProperty(name);
    }

    public static String getProperty(String name, String defaultValue) {
        return environment.getProperty(name, defaultValue);
    }


}
