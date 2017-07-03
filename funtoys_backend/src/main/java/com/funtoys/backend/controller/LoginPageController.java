package com.funtoys.backend.controller;

import com.funtoys.service.domain.generation.AccountInfo;
import com.funtoys.service.inter.AccountInfoService;
import com.funtoys.service.utils.MD5;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by hejun on 2017/7/3.
 */
@Controller
@RequestMapping("/login")
public class LoginPageController {
    private static Logger logger = LoggerFactory.getLogger(LoginPageController.class);

    @Autowired
    private AccountInfoService accountInfoService;

    /**
     * 跳转到登录页面
     */
    @RequestMapping("/page")
    public String gotoLoginPage() {
        return "backend/login";
    }

    /**
     * 检查登录信息
     *
     * @return 检查信息
     */
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> checkLogin(@RequestParam("account") String account, @RequestParam("password") String password,
                                   Map<String, Object> result, HttpServletRequest request) {
        Map<String, Object> record = new HashMap<>();
        record.put("isDel", 0);
        record.put("account", account);
        List<AccountInfo> accountInfos = accountInfoService.selectPageByConditionNoOrderBy(record, null);
        if (accountInfos != null && !accountInfos.isEmpty()) {
            AccountInfo accountInfo = accountInfos.get(0);
            String randomCode = accountInfo.getRandomCode();
            if (StringUtils.isNotEmpty(randomCode)) {
                // 将已经加密一次的密码，在第24个字符处插入随机码
                StringBuffer new_pwd = new StringBuffer(password);
                if (password.length() > 24) {
                    new_pwd.insert(24, randomCode);
                } else {
                    new_pwd.append(randomCode);
                }
                // 第二次加密
                String md5_pwd = MD5.Encoder(new_pwd.toString());
                record.put("password", md5_pwd);
                accountInfos = accountInfoService.selectPageByConditionNoOrderBy(record, null);
                if(accountInfos != null && !accountInfos.isEmpty()) {
                    accountInfo = accountInfos.get(0);
                    // 验证成功，用户信息保存到session
                    HttpSession session = request.getSession();
                    session.setAttribute("accountInfo", accountInfo);
                    result.put("msg", "success");
                }
            } else {
                result.put("msg", "error");
                result.put("error_info", "用户登录码异常！");
            }
        } else { // 用户名不存在在
            result.put("msg", "error");
            result.put("error_info", "用户名或密码错误！");
        }
        return result;
    }
}
