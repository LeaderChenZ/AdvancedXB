package com.dfbz.dao;

import com.dfbz.entity.Dept;
import com.dfbz.entity.User;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.common.Mapper;

import java.util.List;

public interface DeptMapper extends Mapper<Dept> {
    
    
    @Select("SELECT " +
            "d.*, " +
            "a.count  " +
            "FROM " +
            "dept d " +
            "LEFT JOIN (SELECT count( dept_id ) count, dept_id FROM `user` GROUP BY dept_id ) a  " +
            "ON d.id = a.dept_id")
    List<Dept> selectDept();

    @Select("select  " +
            "u.*  " +
            "from  " +
            "`user` u ,dept d " +
            "where " +
            "u.dept_id = d.id " +
            "and " +
            "d.id=#{did}")
    List<User> selectByUName(long did);
}