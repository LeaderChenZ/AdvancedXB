package com.dfbz.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.dfbz.service.WeixinLoginService;
import com.dfbz.util.AuthUrlUtil;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.URLEncoder;
import java.util.Properties;

@Service
public class WeixinLoginServiceImpl implements WeixinLoginService {


    @Value("${wx.AppID}")
    private String appid;

    @Value("${wx.redirect_uri}")
    private String uri;


    /*
     *  获取AccessToken、openid等数据
     *
     * */
    @Override
    public JSONObject getAuthInfo(String code, String appSecret, String appid) {

        if (code != null) {
            String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="
                    + appid +
                    "&secret=" + appSecret +
                    "&code=" + code +
                    "&grant_type=authorization_code";
            JSONObject authInfo = AuthUrlUtil.getUserInfo(url);
            return authInfo;
        }
        return null;

    }

    /*
     * 获取用户的基本信息
     * */
    @Override
    public JSONObject getUserInfo(JSONObject authInfo) {
        if (authInfo != null) {
            String url = "https://api.weixin.qq.com/sns/userinfo?access_token=" +
                    authInfo.getString("access_token") +
                    "&openid=" + authInfo.getString("openid");
            return AuthUrlUtil.getUserInfo(url);
        } else {
            return null;
        }
    }

}
