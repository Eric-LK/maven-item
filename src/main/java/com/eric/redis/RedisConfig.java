package com.eric.redis;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * @author liuBing
 */
@Configuration
public class RedisConfig
{

    @Bean("str-json-template")
    public RedisTemplate<String, String> redisTemplate(
            RedisConnectionFactory redisConnectionFactory)
    {
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        FastJson2JsonRedisSerializer<Object> redisObjectSerializer = new FastJson2JsonRedisSerializer<>(
                Object.class);

        RedisTemplate<String, String> template = new RedisTemplate<>();
        template.setConnectionFactory(redisConnectionFactory);
        template.setKeySerializer(stringRedisSerializer);
        template.setValueSerializer(redisObjectSerializer);
        return template;
    }
}