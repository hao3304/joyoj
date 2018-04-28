package com.sjtu.server.vo;

import org.json.JSONObject;

public class JSONResult {

    public static String fillResponseString(Integer status, String message, Object result) {
        JSONObject jsonObject = new JSONObject(){{
            put("status", status);
            put("message", message);
            put("result", result);
        }};
        return  jsonObject.toString();
    }
}

