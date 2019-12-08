package com.dfbz.controller;

import com.dfbz.entity.Result;
import com.dfbz.entity.User;
import com.dfbz.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author Chen
 * @description
 * @date 2019/12/8 12
 */
@RestController
@RequestMapping("xbjy/login")
public class LoginController {

    @Autowired
    UserService userService;

    /*
     * 1.校验验证码
     * 2.校验登录账号密码
     * 3.将用户信息放入状态管理、将用户的访问资源放入状态管理
     * 4.将用户登录情况返回页面
    * */
    @RequestMapping("")
    public Result login(@RequestBody Map<String,Object> params, HttpSession session){
        Result result = new Result();
        if (params.containsKey("code")&&!StringUtils.isEmpty(params.get("code"))){
            if (session.getAttribute("checkCode").equals(params.get("code"))){
                if(params.containsKey("username")&&!StringUtils.isEmpty(params.get("username"))
                        &&params.containsKey("password")&&!StringUtils.isEmpty(params.get("password"))){

                    //校验账号密码
                    User user  = new User();
                    String username= (String) params.get("username");
                    String password = (String)params.get("password");
                    user.setUsername(username);
                    user.setPassword(password);
                    User loginUser = userService.selectOne(user);
                    if (loginUser!=null){
                        result.setSuccess(true);
                        result.setMsg("登录成功！");
                        HashMap<String,Object> map = new HashMap();
                        map.put("username",username);
                        map.put("id",loginUser.getId());
                        result.setObj(map);

                        //将用户信息放入session
                        session.setAttribute("user",user);
                    }

                }
            }
        }
        return result;
    }

}
