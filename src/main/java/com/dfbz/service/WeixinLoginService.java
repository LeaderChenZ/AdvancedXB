package com.dfbz.service;

import com.alibaba.fastjson.JSONObject;

public interface WeixinLoginService {

    /*
     *  获取AccessToken、openid等数据
     *
     * */
    JSONObject getAuthInfo(String code, String appSecret, String appid);

    /*
     * 获取用户的基本信息
     * */
    JSONObject getUserInfo(JSONObject authInfo);
}
