package com.hollysgang.sample.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/demo")
public class DemoController {
    @Autowired
    DemoService demoService;

    @GetMapping("/set")
    public void set(@RequestParam("key") String key, @RequestParam("val") String val){
        demoService.demoRedisSet(key, val);
    }

    @GetMapping("/get")
    public String get(@RequestParam("key") String key){
        return demoService.demoRedisGet(key);
    }
}
