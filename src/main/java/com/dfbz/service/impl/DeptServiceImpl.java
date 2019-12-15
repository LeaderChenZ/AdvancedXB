package com.dfbz.service.impl;

import com.dfbz.dao.DeptMapper;
import com.dfbz.entity.Dept;
import com.dfbz.entity.User;
import com.dfbz.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @author Chen
 * @description
 * @date 2019/12/7 18
 */
@Service
@Transactional
public class DeptServiceImpl extends IServiceImpl<Dept> implements DeptService {

    @Autowired
    private DeptMapper deptMapper;


    /*
     * 查询所有部门 和部门下面的所有用户数量   和用户信息
     * */
    @Override
    public List<Dept> selectByCondition() {
        List<Dept> dept = deptMapper.selectDept();
        for (Dept dept1 : dept) {
            List<User> users = deptMapper.selectByUName(dept1.getId());
            dept1.setUser(users);
        }
        return dept;
    }

    /*
     * 查询部门下所有的用户
     * */
    @Override
    public List<User> selectUser(long did) {
        return deptMapper.selectByUName(did);
    }

    /*
     * */
    @Override
    public Dept selectDeptAll(String did) {
        return deptMapper.selectDept1(did);
    }

}
