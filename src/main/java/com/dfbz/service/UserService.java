package com.dfbz.service;

import com.dfbz.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface UserService extends IService<User> {
    PageInfo<User> selectByCondition(Map<String, Object> params);

    User selectByUId(long id);

    PageInfo<User> selectFocus(Map<String, Object> params);
}
