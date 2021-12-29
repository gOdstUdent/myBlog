package com.myblog.login.service.impl;

import com.myblog.login.Dao.ILoginDao;
import com.myblog.login.domain.User;
import com.myblog.login.service.IMailService;
import com.myblog.login.service.IloginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class loginService implements IloginService {
    @Autowired
    private IMailService mailService;
    @Autowired
    private ILoginDao loginDao;
    @Override
    public String register(User user) {
        loginDao.register(user);
        String code = user.getCode();
        log.warn("code:"+code);
        //主题
        String subject = "来自yan哥网站的激活邮件";
        //user/checkCode?code=code(激活码)是我们点击邮件链接之后根据激活码查询用户，如果存在说明一致，将用户状态修改为“1”激活
        //上面的激活码发送到用户注册邮箱
        //注意:此处的链接地址,是项目内部地址,如果我们没有正式的服务器地址,暂时无法从qq邮箱中跳转到我们自己项目的激活页面
        String context = "<a href=\"http://192.168.30.108:8763/user/checkCode?code="+code+"\">激活请点击:"+code+"</a>";
        //发送激活邮件
        mailService.sendHtmlMail (user.getUserEmail(),subject,context);
        return null;
    }

    @Override
    public User getUserByName(String name) {

        return loginDao.getUserByName(name);
    }
}
