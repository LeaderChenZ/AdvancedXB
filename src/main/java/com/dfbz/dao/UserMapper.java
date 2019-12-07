package com.dfbz.dao;

import com.dfbz.entity.User;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface UserMapper extends Mapper<User> {

    @Select(
            "select u.* from  " +
                    "`user` u,userfocus  uf  " +
                    "where  " +
                    "u.id = uf.user_focus_id  " +
                    "and  " +
                    "uf.user_id = #{uid}"
    )
    List<User> selectFocus(long uid);

}