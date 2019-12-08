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
}
