package com.sevenlight.controller;

import com.sevenlight.entity.UserInfo;
import com.sevenlight.entity.WxLoginInfo;
import com.sevenlight.util.ResponseUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("wx/auth")
public class AccountController {
    private static final Logger log = LoggerFactory.getLogger(AccountController.class);
    @RequestMapping("/wxlogin")
    public Object wxLogin(@RequestBody WxLoginInfo wxLoginInfo, HttpServletRequest request) {

        String code = wxLoginInfo.getCode();
        UserInfo userInfo = wxLoginInfo.getUserInfo();
        if(code == null || userInfo == null) {
            return ResponseUtil.error(401, "参数不正确");
            //参数不正确
        }

        String sessionKey = null;
        String openId = null;


        return "success";
    }
}