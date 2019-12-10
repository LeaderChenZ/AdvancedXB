package com.dfbz.controller;

import com.dfbz.entity.Result;
import com.dfbz.entity.User;
import com.dfbz.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.Map;

/**
 * @author Chen
 * @description
 * @date 2019/12/7 12
 */
@RestController
@RequestMapping("xbjy/user")
public class UserController {
    @Autowired
   private UserService service;

    @RequestMapping("list")
    PageInfo<User> list(@RequestBody Map<String, Object> params,HttpSession session) {
        User user = (User) session.getAttribute("user");
        params.put("id", user.getId());
        return service.selectByCondition(params);
    }

    @RequestMapping("selectByUId")
    public User selectByUId(long id){
        return service.selectByUId(id);
    }

    @RequestMapping("selectFocus")
    public PageInfo<User> selectFocus(@RequestBody Map<String, Object> focus){
        return service.selectFocus(focus);
    }


    //根据前端传过来的check布尔值来确定删除还是添加
    @RequestMapping("chooseFocus")
    public Result chooseFocus(long fid, String checked, HttpSession session){
        User user = (User) session.getAttribute("user");
        Result result  = new Result();
        if ("true".equals(checked)){
            int i = service.insertFacus(user.getId(), fid);
            if (i>0)
            {
                result.setSuccess(true);
                result.setMsg("关注成功！");
            }
        }else if ("false".equals(checked)){
            int i = service.deleteFacus(user.getId(), fid);
            if (i>0)
            {
                result.setSuccess(true);
                result.setMsg("取消关注成功！");
            }
        }

        return result;
    }


}
