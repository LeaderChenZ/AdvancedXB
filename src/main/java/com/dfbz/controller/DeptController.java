package com.dfbz.controller;

import com.dfbz.entity.Dept;
import com.dfbz.entity.User;
import com.dfbz.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Chen
 * @description
 * @date 2019/12/7 12
 */
@RestController
@RequestMapping("xbjy/dept")
public class DeptController {
    @Autowired
    private DeptService service;

    /*
    * 查询所有部门 和部门下面的所有用户数量   和用户信息
    * */
    @RequestMapping("index")
   public List<Dept> index(){
        return service.selectByCondition();
    }
    /*
     * 查询部门下所有的用户
     * */
    @RequestMapping("selectUser")
    public List<User> selectUser(long did) {
        return service.selectUser(did);
    }


}
