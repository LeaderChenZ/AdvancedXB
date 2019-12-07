package com.dfbz.controller;

import com.dfbz.entity.User;
import com.dfbz.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Chen
 * @description
 * @date 2019/12/7 12
 */
@RestController
@RequestMapping("xbjy/user")
public class UserController {
    @Autowired
   private UserService service;

    @RequestMapping("list")
    PageInfo<User> list(@RequestBody Map<String, Object> params) {
        return service.selectByCondition(params);
    }

    @RequestMapping("selectByUId")
    public User selectByUId(long id){
        return service.selectByUId(id);
    }

    @RequestMapping("selectFocus")
    public PageInfo<User> selectFocus(@RequestBody Map<String, Object> focus){
        return service.selectFocus(focus);
    }

}
