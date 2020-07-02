package com.example.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

@Component
public class SpringUtlis implements BeanFactoryPostProcessor {

    /**
     * spring应用上下文环境
     */
    private static ConfigurableListableBeanFactory beanFactory;

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        SpringUtlis.beanFactory = beanFactory;
    }

    public static <T> T getBean(String name){
        return (T) beanFactory.getBean(name);
    }

    public static <T> T getBean(Class<T> cls){
        T result = beanFactory.getBean(cls);
        return result;
    }

    public static boolean containsBean(String name){
        return beanFactory.containsBean(name);
    }

    public static boolean isSingleton(String name){
        return beanFactory.isSingleton(name);
    }


}
