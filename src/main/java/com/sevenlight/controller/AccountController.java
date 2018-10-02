package com.sevenlight.controller;

import com.sevenlight.entity.UserInfo;
import com.sevenlight.entity.WxLoginInfo;
import com.sevenlight.util.HttpUtil;
import com.sevenlight.util.ResponseUtil;
import okhttp3.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("wx/auth")
public class AccountController {
    //private $authApi = 'https://open.weixin.qq.com/connect/oauth2/authorize';
    //private $authAccessTokenApi = 'https://api.weixin.qq.com/sns/oauth2/access_token';
    //private $authAccessTokenApi4XCX = 'https://api.weixin.qq.com/sns/jscode2session';
    //private $userInfoApi = 'https://api.weixin.qq.com/sns/userinfo';
    //private $jsTicketApi = 'https://api.weixin.qq.com/cgi-bin/ticket/getticket';
    //private $accessTokenApi = 'https://api.weixin.qq.com/cgi-bin/token';
    //private $unifiedApi = 'https://api.mch.weixin.qq.com/pay/unifiedorder';

    /*$url = $this->authAccessTokenApi;
    $querys = array(
      'appid'  => $this->appId,
            'secret' => $this->appSecret,
            'grant_type' => 'authorization_code'
            );
    if ($this->platform && $this->platform == Platform::WX_XCX) {
        $querys['js_code'] = $code;
        $url = $this->authAccessTokenApi4XCX;
    } else {
        $querys['code'] = $code;
    }*/



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
        //调用微信接口获取openid和sessionKey
        String url = "https://api.weixin.qq.com/sns/jscode2session";
        Map<String, String> params = new HashMap<>();
        params.put("js_code", code);
        Response res = HttpUtil.okHttpGet(url, params);
        log.info("result is {}",res);
        //通过openId和sessionKey获取用户信息或判断用户是否已注册


        return "success";
    }
}
