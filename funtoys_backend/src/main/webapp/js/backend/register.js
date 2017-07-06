function register() {
    var account = $("#account").val();
    if (account == null || account.length == 0) {
        layer.tips("请输入用户名！", "#account", {
            tips: [2, '#FF3300']
        });
        return false;
    }
    var password = $("#password").val();
    if (password == null || password.length == 0) {
        layer.tips("请输入密码！", "#password", {
            tips: [2, '#FF3300']
        });
        return false;
    }

    if (password.length < 6) {
        layer.tips("密码长度不能小于6位！", "#password", {
            tips: [2, '#FF3300']
        });
        return false;
    }

    var checkAccount = true; // 账号是否可以注册
    var system_error = false;

    $.ajaxSettings.async = false; // 设置ajax同步
    $.post(base_url + "/login/checkaccount", {
        account: account,
        accountType: 2 // 商家
    }, function (result) {
        if (result != null) {
            result = $.parseJSON(result);
            if (result.msg != "success") {
                checkAccount = false;
            }
        } else {
            system_error = true;
        }
    });
    $.ajaxSettings.async = true; // 设置ajax异步

    if (system_error) {
        layer.msg("系统异常！", {icon: 5});
        return false;
    }

    if (!checkAccount) {
        layer.msg("账号已被注册！", {icon: 5});
        return false;
    }

    var encrypt_pwd = b64_md5(password);
    $.post(base_url + "/login/register", {
        account: account,
        password: encrypt_pwd,
        accountType: 2 // 商家
    }, function (result) {
        if (result != null) {
            result = $.parseJSON(result);
            if (result.msg == "success") {
                layer.msg("注册成功！", {icon: 6});
                window.location.href = base_url + "/login/page";
            } else {
                layer.msg("注册失败！", {icon: 5});
            }
        } else {
            layer.msg("系统异常！", {icon: 5});
        }
    });
}