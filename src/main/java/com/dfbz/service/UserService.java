package com.dfbz.service;

import com.dfbz.entity.User;
import com.github.pagehelper.PageInfo;

import java.util.Map;

public interface UserService extends IService<User> {
    PageInfo<User> selectByCondition(Map<String, Object> params);

    User selectByUId(long id);

    PageInfo<User> selectFocus(Map<String, Object> params);


    /*
     * 添加关注
     * */
    int insertFacus(long uid, long fid);

    /*
   取消关注
  */
    int deleteFacus(long uid, long fid);

    /*
     * 验证是否已存在的用户名
     * */
    int selectByName(String username);

    /*
     * 添加用户
     * */
    int insertUser(User user);

    /*
    * 修改密码
    * */
    int updatePassword(String password, String userName);
}
