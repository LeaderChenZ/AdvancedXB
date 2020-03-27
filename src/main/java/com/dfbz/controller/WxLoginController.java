package com.dfbz.controller;


import com.alibaba.fastjson.JSONObject;
import com.dfbz.entity.User;
import com.dfbz.service.UserService;
import com.dfbz.service.WeixinLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.net.URLEncoder;


/*
 * 微信扫码登录
 * */
@RestController
public class WxLoginController {

    @Value("${wx.AppID}")
    private String appid;

    @Value("${wx.redirect_uri}")
    private String uri;

    @Value("${wx.AppSecret}")
    private String appSecret;


    @Autowired
    private WeixinLoginService wxService;

    @Autowired
    private UserService userService;

    @RequestMapping("/wxloginURl")
    public ModelAndView wxloginURl() {
        System.out.println(appid + "--" + uri);
        ModelAndView modelAndView = new ModelAndView();
        //Step1：获取Authorization Code
        String url = "https://open.weixin.qq.com/connect/qrconnect?response_type=code" +
                "&appid=" + appid +
                "&redirect_uri=" + URLEncoder.encode(uri) +
                "&scope=snsapi_login";
        modelAndView.setViewName("redirect:" + url);
        return modelAndView;
    }


    @RequestMapping("/wx_login")
    public ModelAndView wxLogin(HttpServletRequest request) {
        ModelAndView m = new ModelAndView();
        String code = request.getParameter("code");
        JSONObject authInfo = wxService.getAuthInfo(code, appSecret, appid);
        JSONObject userInfo = wxService.getUserInfo(authInfo);

        User user = new User();
        user.setGender(userInfo.getString("sex"));

        int i = userService.insertUser(user);
        if(i>0)
        {
            m.setViewName("redirect:/html/home.html");
        }else{
            m.setViewName("rederect:forget.html");
        }
        return m;
    }

}
