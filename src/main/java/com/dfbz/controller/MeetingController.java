package com.dfbz.controller;

import com.dfbz.entity.Dept;
import com.dfbz.entity.Meeting;
import com.dfbz.entity.Result;
import com.dfbz.entity.User;
import com.dfbz.service.DeptService;
import com.dfbz.service.MeetingService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
    PageInfo<Meeting> index(@RequestBody Map<String, Object> params) {
        return service.selectByCondition(params);
    }

    /*
     * 发布会议
     * */
    @RequestMapping("insertMeeting")
    public Result insertMeeting(@RequestBody Map<String, Object> meeting) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Dept dept = deptService.selectDeptAll(meeting.get("deptId").toString());
        Result result = new Result();
        Meeting m = new Meeting();
        m.setStatus(0);
        m.setPublishDate(new Date());
        m.setDeptName(dept.getName());
        m.setDeptId(dept.getId());
        m.setStartTime(sdf.parse(meeting.get("startTime").toString()));
        m.setStartTime(sdf.parse(meeting.get("endTime").toString()));
        m.setMakeUser("[" + meeting.get("makeUser") + "]");
        m.setTitle(meeting.get("title").toString());
        m.setContent(meeting.get("content").toString());
        int i = service.insert(m);
        if (i > 0) {
            result.setSuccess(true);
            result.setMsg("发布成功！");
        }
        return result;
    }

    /*
     * 根据id查询会议具体信息
     * */
    @RequestMapping("selectDetail")
    Meeting selectDetail(long mid) {
        return service.selectDetail(mid);
    }

    /*
     * 参加会议
     * */
    @RequestMapping("addMeeting")
    Result addMeeting(long mid, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Result result = new Result();
        int i = service.addMeeting(user.getId(), mid);
        if (i > 0) {
            result.setSuccess(true);
            result.setMsg("参加成功！");
        }
        return result;
    }

    /*
     * 退出会议
     * */
    @RequestMapping("deleteMeeting")
    Result deleteMeeting(long mid, HttpSession session) {
        User user = (User) session.getAttribute("user");
        Result result = new Result();
        int i = service.deleteMeeting(user.getId(), mid);
        if (i > 0) {
            result.setSuccess(true);
            result.setMsg("退出成功！");
        }
        return result;
    }


}
