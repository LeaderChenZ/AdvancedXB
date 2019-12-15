package com.dfbz.controller;

import com.dfbz.entity.Result;
import com.dfbz.entity.User;
import com.dfbz.service.UserService;
import com.dfbz.util.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
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
                        user.setId(loginUser.getId());
                        user.setRealName(loginUser.getRealName());
                        //将用户信息放入session
                        session.setAttribute("user",user);
                    }

                }
            }
        }
        return result;
    }

    /*
    * 注册
    * */
    @RequestMapping("register")
    public Result register(@RequestBody Map<String,Object> params){
        Result result = new Result();
        String email = (String) params.get("email");
        String username = (String) params.get("username");
        String password = (String) params.get("password");
        String password2 = (String) params.get("password2");
        if (password.equals(password2)){
            int integer = userService.selectByName(username);
            if (integer==1){
                result.setMsg("账号已存在");
            }else {
                User user =new User();
                user.setUsername(username);
                user.setPassword(password);
                user.setEmail(email);
                userService.insertUser(user);
                result.setMsg("注册成功");
                result.setSuccess(true);
            }
        }else {
            result.setMsg("密码不一致");
        }
        return result;
    }

    /*
    * 发送邮箱验证码
    * */
    /**
     * 发送邮箱
     * @return
     */
    @RequestMapping("send")
    public Result send(@RequestBody Map<String, Object> params, HttpServletRequest request){
        HttpSession session = request.getSession();
        //516767841@qq.com,1024
        String email= (String) params.get("email");
        int code=(int)(Math.random()*9000)+1000;
        session.setAttribute("code",code);
        Email.send(email,code);
//        System.out.println(email);
//        System.out.println(code);
//        System.out.println(session.getAttribute("code"));
        Result result = new Result();
        result.setMsg("发送成功");
        result.setSuccess(true);
        return result;
    }
    /**
     * 修改密码
     * @param params
     * @return
     */
    @RequestMapping("forget")
    public Result forget(@RequestBody Map<String, Object> params,HttpServletRequest request){
        Result result = new Result();
        HttpSession session = request.getSession();
        Integer code1 = (Integer) session.getAttribute("code");//邮箱验证码
        String code2 = code1.toString();
        String email = (String) params.get("email");//邮箱
        String code = (String) params.get("code");//验证码
        String password = (String) params.get("password");//新密码
        session.setAttribute("code","");
        if (code2.equals(code)){
            int i= userService.updatePassword(password,email);
            if (i==1){
                result.setMsg("修改成功");
                result.setSuccess(true);
            }else {
                result.setMsg("修改失败");
                result.setSuccess(false);
            }
        }
        return result;
    }

}
