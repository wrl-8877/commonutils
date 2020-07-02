package com.example.mall.config;

import org.springframework.amqp.core.*;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.CacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
public class SeckillConfigure {

    @Autowired
    AmqpAdmin amqpAdmin;

    @Bean
    public CacheManager cacheManager(RedisConnectionFactory factory) {
        //键序列化器
        RedisSerializer<String> redisSerializer = new StringRedisSerializer();
        //设置CacheManager的值序列化方式为json序列化，加入@Class属性
        GenericJackson2JsonRedisSerializer jsonRedisSerializer = new GenericJackson2JsonRedisSerializer();

        // 配置序列化（解决乱码的问题）
        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(redisSerializer))
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(jsonRedisSerializer))
                .disableCachingNullValues(); //禁用缓存空值，不缓存null校验

        RedisCacheManager cacheManager = RedisCacheManager.builder(factory)
                .cacheDefaults(config)
                .build();
        return cacheManager;
    }

    @Bean
    public MessageConverter messageConverter(){
        return new Jackson2JsonMessageConverter();
    }

    @Bean(name = "pay.exchage")
    public Exchange exchange(){
        return new DirectExchange("pay.exchage",true,false,null);
    }

    @Bean(name = "pay.queue")
    public Queue queue(){
        return new Queue("pay.queue",true,false,false,null);
    }

    @Bean(name = "pay.binding")
    public Binding binding(){
        return new Binding("pay.queue", Binding.DestinationType.QUEUE,
                "pay.exchage","pay.binding",null);
    }
}
