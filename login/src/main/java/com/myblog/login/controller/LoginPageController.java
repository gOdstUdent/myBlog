package com.myblog.login.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class LoginPageController {

    @GetMapping(value = "/login", produces = MediaType.TEXT_HTML_VALUE)
    @ResponseBody
    public String loginPage() {
        return "<!DOCTYPE html>"
                + "<html lang=\"zh-CN\">"
                + "<head>"
                + "<meta charset=\"UTF-8\">"
                + "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">"
                + "<title>登录</title>"
                + "<style>"
                + "body{margin:0;font-family:Arial,sans-serif;background:#f4f6f8;display:flex;align-items:center;justify-content:center;height:100vh;}"
                + ".card{width:320px;background:#fff;padding:24px;border-radius:8px;box-shadow:0 8px 20px rgba(0,0,0,.08);}"
                + "h1{margin:0 0 16px;font-size:20px;text-align:center;color:#222;}"
                + "label{display:block;margin-bottom:12px;color:#444;font-size:14px;}"
                + "input{width:100%;box-sizing:border-box;padding:10px;border:1px solid #d6dbe1;border-radius:6px;margin-top:6px;}"
                + "button{width:100%;border:none;background:#1677ff;color:#fff;padding:10px;border-radius:6px;cursor:pointer;}"
                + "button:hover{background:#0958d9;}"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class=\"card\">"
                + "<h1>用户登录</h1>"
                + "<form method=\"post\" action=\"/login\">"
                + "<label>账号<input type=\"text\" name=\"username\" placeholder=\"请输入账号\" required></label>"
                + "<label>密码<input type=\"password\" name=\"password\" placeholder=\"请输入密码\" required></label>"
                + "<button type=\"submit\">登录</button>"
                + "</form>"
                + "</div>"
                + "</body>"
                + "</html>";
    }
}
