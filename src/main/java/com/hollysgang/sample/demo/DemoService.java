package com.hollysgang.sample.demo;

import com.hollysgang.sample.redis.RedisHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DemoService {

    private final RedisHandler redisHandler;

    public void demoRedisSet(String key, String value){
        redisHandler.executeOperation(()-> redisHandler.getValueOperations().set(key, value));
    }

    public String demoRedisGet(String key){
        return (String) redisHandler.getValueOperations().get(key);
    }
}
