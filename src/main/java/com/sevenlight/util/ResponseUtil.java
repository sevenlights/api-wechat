package com.sevenlight.util;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by zhangweihong on 2018/9/27.
 */
public class ResponseUtil {
    public static Object success() {
        Map<String, Object> obj = new HashMap<>();
        obj.put("errno", 0);
        obj.put("errmsg", "success");
        return obj;
    }

    public static Object success(Object data) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("errno", 0);
        obj.put("errmsg", "success");
        obj.put("data", data);
        return obj;
    }

    public static Object success(String errmsg, Object data) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("errno", 0);
        obj.put("errmsg", errmsg);
        obj.put("data", data);
        return obj;
    }

    public static Object error() {
        Map<String, Object> obj = new HashMap<>();
        obj.put("errno", -1);
        obj.put("errmsg", "error");
        return obj;
    }

    public static Object error(int errno, String errmsg) {
        Map<String, Object> obj = new HashMap<>();
        obj.put("errno", errno);
        obj.put("errmsg", errmsg);
        return obj;
    }
}
