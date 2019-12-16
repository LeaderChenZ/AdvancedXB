package com.dfbz.service;

import com.dfbz.entity.Meeting;
import com.github.pagehelper.PageInfo;

import java.util.Map;

/**
 * @author Chen
 * @description
 * @date 2019/12/7 18
 */
public interface MeetingService extends IService<Meeting> {


    PageInfo<Meeting> selectByCondition(Map<String, Object> params);

    /*
    * 根据ID查询会议信息
    * */
    Meeting selectDetail(long id);

    /*
    * 参加会议
    * */
    int addMeeting(long uid, long mid);

    /*
     * 退出会议
     * */
    int deleteMeeting(long uid, long mid);
}
