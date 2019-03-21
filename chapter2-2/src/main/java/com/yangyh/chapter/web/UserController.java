package com.yangyh.chapter.web;

import com.yangyh.chapter.entity.User;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @author: yangyh
 * @create: 2019-03-21 16:20
 **/
@RestController
@RequestMapping("/user")
public class UserController {

    /**创建线程安全的Map**/
    Map<Long, User> users = Collections.synchronizedMap(new HashMap<>());

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public List<User> getUserList(){

        //处理"/user/"的get请求，用来获取用户列表
        List<User> userList = new ArrayList<>(users.values());
        return userList;

    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String saveUser(@ModelAttribute User user){

        //处理"/user/"的post请求，用来创建用户
        users.put(user.getId(), user);
        return "success";

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    public User getUser(@PathVariable Long id){

        //处理"/user/{id}"的get请求，用来获取id对应的用户信息
        User user = users.get(id);
        return user;

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public String updateUser(@PathVariable Long id,@ModelAttribute User user){

        //处理"/user/{id}"的put请求，用来更新id对应的用户信息
        users.put(id, user);
        return "success";

    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public String delUser(@PathVariable Long id){

        //处理"/user/{id}"的put请求，用来删除id对应的用户信息
        users.remove(id);
        return "success";

    }


}
