package com.dfbz.service.impl;

import com.dfbz.dao.MeetingMapper;
import com.dfbz.entity.Meeting;
import com.dfbz.service.MeetingService;
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
 * @description
 * @date 2019/12/7 18
 */
@Service
@Transactional
public class MeetingServiceImpl extends IServiceImpl<Meeting> implements MeetingService {
    @Autowired
    MeetingMapper mapper;


    /*
     * 分页查询  模糊查询和会议状态查询
     * */
    @Override
    public PageInfo<Meeting> selectByCondition(Map<String, Object> params) {
        if (StringUtils.isEmpty(params.get("pageNum"))) {
            params.put("pageNum", 1);
        }

        if (StringUtils.isEmpty(params.get("pageSize"))) {
            params.put("pageSize", 3);
        }

        PageHelper.startPage((Integer) params.get("pageNum"), (Integer) params.get("pageSize"));

        //拼接sql   调用mapper方法
        Example example = new Example(Meeting.class);
        Example.Criteria criteria = example.createCriteria();
        if (!StringUtils.isEmpty(params.get("title"))) {
            criteria.andLike("title", "%" + params.get("title") + "%");
//            criteria.andEqualTo("realName", params.get("realName"));
        }
        if (!StringUtils.isEmpty(params.get("status"))) {
            criteria.andEqualTo("status", params.get("status"));
        }

        List<Meeting> qualifications = mapper.selectByExample(example);

        return new PageInfo<Meeting>(qualifications);
    }

    /*
     * 根据ID查询会议信息
     * */
    @Override
    public Meeting selectDetail(long id) {
        Meeting meeting = mapper.selectDetail(id);

        String[] split = meeting.getMakeUser().split(",");
        meeting.setGetUser(split);//应到人数

        int l1 = split.length;
        int arrivals = mapper.selectGetUser(id); //实到人数
        meeting.setArrivals(arrivals);
        int late = l1 - arrivals;
        meeting.setLate(late);
        return meeting;
    }
    /*
    * 参加会议
    * */
    @Override
    public  int addMeeting(long uid,long mid){
        return mapper.addMeeting(uid,mid);
    }

    /*
     * 退出会议
     * */
    @Override
    public  int deleteMeeting(long uid,long mid){
        return mapper.deleteMeeting(uid,mid);
    }


}
