package com.dfbz.service;

import com.dfbz.entity.Dept;
import com.dfbz.entity.User;

import java.util.List;

/**
 * @author Chen
 * @description
 * @date 2019/12/7 18
 */
public interface DeptService extends IService<Dept> {


    /*
    * 查询所有部门 和部门下面的所有用户数量   和用户信息
    * */
    List<Dept> selectByCondition();

    List<User> selectUser(long did);
}
