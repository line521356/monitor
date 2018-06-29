package com.dongfang.monitor.utils;

import com.alibaba.fastjson.JSONObject;

public class GlobalConstant {

    public final static String USER_SESSION_KEY = "user_session_key";

    public final static String ADMIN_ACCOUNT = "admin";

    public static JSONObject constructResponse(Integer code,String msg,Object data){
        JSONObject res = new JSONObject();
        res.put("code",code);
        res.put("msg",msg);
        res.put("data",data);
        return res;
    }

}
