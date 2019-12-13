package com.dfbz.controller;

import com.dfbz.entity.Meeting;
import com.dfbz.service.DeptService;
import com.dfbz.service.MeetingService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @author Chen
 * @description
 * @date 2019/12/7 12
 */
@RestController
@RequestMapping("xbjy/meeting")
public class MeetingController {
    @Autowired
    private MeetingService service;

    @Autowired
    private DeptService deptService;
    /*
    * 分页查询  模糊查询  会议状态查询
    * */
    @RequestMapping("index")
    PageInfo<Meeting> index(@RequestBody Map<String,Object> params) {
        return service.selectByCondition(params);
    }






}
