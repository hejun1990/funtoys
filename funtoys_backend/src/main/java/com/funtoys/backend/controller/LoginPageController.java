package com.funtoys.backend.controller;

import com.funtoys.service.domain.generation.AccountInfo;
import com.funtoys.service.inter.AccountInfoService;
import com.funtoys.service.utils.MD5;
import com.funtoys.service.utils.RandomCode;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
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
     * @param account     账号
     * @param password    密码
     * @param accountType 账号类型
     * @param request     HttpServletRequest
     * @return 检查信息
     */
    @RequestMapping(value = "/checklogin", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> checkLogin(@RequestParam("account") String account, @RequestParam("password") String password,
                                   @RequestParam("accountType") Integer accountType, HttpServletRequest request) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> record = new HashMap<>();
        record.put("isDel", 0);
        record.put("account", account);
        record.put("accountType", accountType);
        List<AccountInfo> accountInfos = accountInfoService.selectPageByConditionNoOrderBy(record, null);
        if (accountInfos != null && !accountInfos.isEmpty()) {
            AccountInfo accountInfo = accountInfos.get(0);
            String randomCode = accountInfo.getRandomCode();
            if (StringUtils.isNotEmpty(randomCode)) {
                String md5_pwd = getMD5Pwd(password, randomCode);
                record.put("password", md5_pwd);
                accountInfos = accountInfoService.selectPageByConditionNoOrderBy(record, null);
                if (accountInfos != null && !accountInfos.isEmpty()) {
                    accountInfo = accountInfos.get(0);
                    // 验证成功，用户信息保存到session
                    HttpSession session = request.getSession();
                    session.setAttribute("accountInfo", accountInfo);
                    result.put("msg", "success");
                } else {
                    result.put("msg", "error");
                    result.put("error_info", "用户名或密码错误！");
                }
            } else {
                result.put("msg", "error");
                result.put("error_info", "用户登录码异常！");
            }
        } else { // 用户名不存在
            result.put("msg", "error");
            result.put("error_info", "用户名或密码错误！");
        }
        return result;
    }

    /**
     * 跳转到注册页面
     */
    @RequestMapping("/registerpage")
    public String gotoRegisterPage() {
        return "backend/register";
    }


    /**
     * 检查账号是否已经注册册
     *
     * @param account     账号
     * @param accountType 账号类型
     * @return 检查结果
     */
    @RequestMapping(value = "/checkaccount", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> checkAccount(@RequestParam("account") String account, @RequestParam("accountType") Integer accountType) {
        Map<String, Object> result = new HashMap<>();
        Map<String, Object> record = new HashMap<>();
        record.put("account", account);
        record.put("accountType", accountType);
        List<AccountInfo> accountInfos = accountInfoService.selectPageByConditionNoOrderBy(record, null);
        if (accountInfos != null && !accountInfos.isEmpty()) {
            // 账号已经被注册
            result.put("msg", "fail");
        } else {
            // 账号可以注册
            result.put("msg", "success");
        }
        return result;
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public
    @ResponseBody
    Map<String, Object> register(@RequestParam("account") String account, @RequestParam("password") String password,
                                 @RequestParam("accountType") Integer accountType) {
        Map<String, Object> result = new HashMap<>();
        // 随机码
        String randomCode = RandomCode.registerCode();
        // 重新加密
        String md5_pwd = getMD5Pwd(password, randomCode);
        AccountInfo accountInfo = new AccountInfo();
        accountInfo.setAccount(account);
        accountInfo.setPassword(md5_pwd);
        accountInfo.setAccountType(accountType);
        accountInfo.setRandomCode(randomCode);
        boolean success = accountInfoService.insertSelective(accountInfo);
        if (success) {
            result.put("msg", "success");
        } else {
            result.put("msg", "fail");
        }
        return result;
    }

    /**
     * 返回符合系统规则的加密密码
     *
     * @param password   原始密码
     * @param randomCode 随机码
     * @return 新加密密码
     */
    private String getMD5Pwd(String password, String randomCode) {
        if (randomCode == null) {
            randomCode = RandomCode.registerCode();
        }
        // 将已经加密一次的密码，在第8个字符处插入随机码
        StringBuffer new_pwd = new StringBuffer(password);
        if (password.length() > 8) {
            new_pwd.insert(8, randomCode);
        } else {
            new_pwd.append(randomCode);
        }
        // 第二次加密
        return MD5.Encoder(new_pwd.toString());
    }

}
