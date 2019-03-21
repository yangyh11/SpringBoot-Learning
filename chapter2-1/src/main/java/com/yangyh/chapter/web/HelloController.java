package com.yangyh.chapter.web;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @description:
 * @author: yangyh
 * @create: 2019-03-21 14:10
 **/
@RestController
public class HelloController {

    @Value("${com.yangyh.blog.name}")
    private String name;

    @Value("${com.yangyh.blog.title}")
    private String title;

    @Value("${com.yangyh.blog.desc}")
    private String desc;

    @Value("${random.int}")
    private String random;


    @RequestMapping("/hello")
    public Map hello(){

        Map<String, String> map = new HashMap<>();

        map.put("name", name);
        map.put("title", title);
        map.put("description", desc);
        map.put("random", random);

        return map;
    }
}
