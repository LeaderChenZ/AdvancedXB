package com.dfbz.service.impl;

import com.dfbz.dao.UserMapper;
import com.dfbz.entity.User;
import com.dfbz.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;
import java.util.Map;

/**
 * @author Chen
 * @description 用户实现层
 * @date 2019/12/7 11
 */
@Service
@Transactional
public class UserServiceImpl extends IServiceImpl<User> implements UserService  {

    @Autowired
    private UserMapper mapper;

    /*
    * 分页查询和搜索模糊查询
    * */
    @Override
    public PageInfo<User> selectByCondition(Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("pageNum"))) {
            params.put("pageNum", 1);
        }

        if (StringUtils.isEmpty(params.get("pageSize"))) {
            params.put("pageSize", 5);
        }

        PageHelper.startPage((Integer) params.get("pageNum"), (Integer) params.get("pageSize"));

        //拼接sql   调用mapper方法
        Example example = new Example(User.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(params.get("realName"))) {
            criteria.andLike("realName","%"+params.get("realName")+"%");
//            criteria.andEqualTo("realName", params.get("realName"));
        }

        List<User> qualifications = mapper.selectByExample(example);
        if (!StringUtils.isEmpty(params.get("id"))){
            Integer uid = (Integer) params.get("id");
            //设置查到的用户是否为登录账号用户的关注人
            for (User user : qualifications) {
                int i = mapper.selectFocusCount(uid, user.getId());
                user.setMark(i);
            }
        }

        return new PageInfo<User>(qualifications);
    }


    /*
    * 根据用户ID查询用户信息
    * */
    @Override
    public User selectByUId(long id){
        return mapper.selectByPrimaryKey(id);
    }

    /*
    * 根据用户ID查询用户关注人数
    * 自连接查询
    * */
    @Override
    public PageInfo<User> selectFocus(Map<String, Object> params){
        if (StringUtils.isEmpty(params.get("pageNum"))) {
            params.put("pageNum", 1);
        }

        if (StringUtils.isEmpty(params.get("pageSize"))) {
            params.put("pageSize", 3);
        }

        PageHelper.startPage((Integer) params.get("pageNum"), (Integer) params.get("pageSize"));

        long uid = 1;
        List<User> users = mapper.selectFocus(uid);
        return new PageInfo<User>(users);
    }

    /*
    * 添加关注
    * */
    @Override
    public int insertFacus(long uid, long fid){
        return mapper.insertFacus(uid,fid);
    }

    /*
       取消关注
     */
    @Override
    public int deleteFacus(long uid, long fid){
        return mapper.deleteFocus(uid,fid);
    }


}

