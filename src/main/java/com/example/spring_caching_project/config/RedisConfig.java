package com.example.spring_caching_project.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisStandaloneConfiguration;
import org.springframework.data.redis.connection.jedis.JedisClientConfiguration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.JdkSerializationRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

@Configuration
@EnableCaching
public class RedisConfig {
    @Value("${spring.redis.host}")
private  String redis_hostName;
    @Value("${spring.redis.port}")
private int redis_portName;
    @Bean
    JedisConnectionFactory jedisConnectionFactory() {
        RedisStandaloneConfiguration standaloneConfiguration=new RedisStandaloneConfiguration(redis_hostName,redis_portName);
        JedisClientConfiguration jedisClientConfiguration= JedisClientConfiguration.builder().build();
        JedisConnectionFactory connectionFactory=new JedisConnectionFactory(standaloneConfiguration,jedisClientConfiguration);
        return connectionFactory;
    }

    @Bean
    public RedisTemplate<String, Object> redisTemplate() {
        RedisTemplate<String,Object>template=new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashKeySerializer(new JdkSerializationRedisSerializer());
        template.setHashValueSerializer(new JdkSerializationRedisSerializer());

//        template.setEnableTransactionSupport(true);
        template.afterPropertiesSet();
        template.setConnectionFactory(jedisConnectionFactory());
        return  template;
    }
}
