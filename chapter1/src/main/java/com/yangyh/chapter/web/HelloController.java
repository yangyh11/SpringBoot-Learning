package com.yangyh.chapter.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: yangyh
 * @create: 2019-03-21 11:27
 **/
@RestController
public class HelloController {


    @RequestMapping("/hello")
    public String hello(){

        return "hello world";
    }
}
