package com.github.charlesvhe.springcloud.practice.consumer.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

/**
 * Created by charles on 2017/5/25.
 */
@RestController
@RequestMapping("/test")
public class TestController {

    @Autowired
    private RestTemplate restTemplate;

    @RequestMapping(method = RequestMethod.GET)
    public String test() {
        String result = restTemplate.getForObject("http://provider/user", String.class);
        return result;
    }
}
