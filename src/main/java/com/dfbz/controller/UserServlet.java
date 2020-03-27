package com.dfbz.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.net.URLEncoder;
import java.util.Properties;


@WebServlet("/xbjy/wx/*")
public class UserServlet extends HttpServlet {
    // 处理微信回调
    public void wx_login(HttpServletRequest request, HttpServletResponse response) throws Exception {


        // 加载配置文件
        Properties prop=new Properties();
        prop.load(this.getClass().getClassLoader().getResourceAsStream("config.properties"));

        String appid = prop.getProperty("wx.AppID");

        //微信授权成功后的回调地址
        String redirect_uri = prop.getProperty("wx.redirect_uri");

        //Step1：获取Authorization Code
        String url = "https://open.weixin.qq.com/connect/qrconnect?response_type=code"+
                "&appid=" + appid +
                "&redirect_uri=" + URLEncoder.encode(redirect_uri) +
                "&scope=snsapi_login";

        // 重定向到微信登录指定的地址进行微信登录授权,授权成功后返回code
        response.sendRedirect(url);
    }
}
