package com.dfbz.controller;


import com.alibaba.fastjson.JSONObject;
import com.dfbz.util.AuthUrlUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Properties;

@WebServlet("/wx_login")
public class WXController extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        //登录成功后的验证码
        String code = req.getParameter("code");

        Properties prop = new Properties();
        prop.load(this.getClass().getClassLoader().getResourceAsStream("wxconfig.properties"));

        String appid = prop.getProperty("wx.AppID");
        String AppSecret = prop.getProperty("wx.AppSecret");
        String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                + appid +
                "&secret=" + AppSecret +
                "&code=" + code +
                "&grant_type=authorization_code";
// 获取AccessToken、openid等数据
        JSONObject authInfo = AuthUrlUtil.getUserInfo(url);
        System.out.println("authInfo: " + authInfo);
        url = "https://api.weixin.qq.com/sns/userinfo?access_token=" +
                authInfo.getString("access_token") +
                "&openid=" + authInfo.getString("openid");
        JSONObject userInfo = AuthUrlUtil.getUserInfo(url);
        System.out.println("userInfo: " + userInfo);

        resp.sendRedirect("/html/home.html");

    }
}
