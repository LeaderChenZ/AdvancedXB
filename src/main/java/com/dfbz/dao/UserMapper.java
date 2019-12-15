package com.dfbz.dao;

import com.dfbz.entity.User;
import org.apache.ibatis.annotations.*;
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


    /*
    * 添加关注人
    * */
    @Insert("INSERT INTO `xb`.`userfocus`(`user_id`, `user_focus_id`) VALUES (#{uid}, #{fid})")
    int insertFacus(@Param("uid")long uid,@Param("fid")long fid);


    /*
    * 删除关注人
    * */
    @Delete("DELETE " +
            "from " +
            "userfocus " +
            "where " +
            "user_focus_id = #{fid}  and  user_id=#{uid}")
    int deleteFocus(@Param("uid")long uid,@Param("fid")long fid);


    /*
    * 查询是否已关注的人
    * */
    @Select("SELECT  " +
            "  count( 1 )   " +
            "FROM  " +
            "  userfocus   " +
            "WHERE  " +
            "  user_focus_id =#{fid} and user_id=#{id}")
    int selectFocusCount(@Param("id") Integer id,@Param("fid") Integer fid);


    /*
    * 验证是否已存在的用户名
    * */
    @Select("SELECT " +
            " COUNT( 0 ) count  " +
            "FROM " +
            " `user`  " +
            "WHERE " +
            " username = #{username}")
    int selectByName(String username);
    /*
    * 增加用户
    * */
    @Insert("INSERT into `user`(username,`password`,email,register_time) " +
            "values( #{username} ,#{password}, #{email} ,NOW())")
    int insertUser(User user);

    /*
    * 修改密码
    * */
    @Update("UPDATE `user`set `password` = #{password} where email = #{email}")
    int updatePassword(@Param("password") String password ,@Param("email")String email);

}