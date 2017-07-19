// 刷新图片验证码
function resetRandomCode() {
    var time_now = new Date().getTime();
    $(".img-rounded").attr("src", base_url + "/login/randomcodeimg?date=" + time_now);
}

// 注册
function register() {
    var account = $("#account").val();
    if (account == null || account.length == 0) {
        layer.tips("请输入用户名！", "#account", {
            tips: [2, '#FF3300']
        });
        return false;
    }

    var ret = /^[a-zA-Z][a-zA-Z0-9_]{5,20}$/;
    if (!ret.test(account)) {
        layer.tips("用户名须由英文字母（A-Z/a-z）、数字（0-9）和下划线（_）组成，以英文字母开头，长度为5-20位。", "#account", {
            tips: [2, '#FF3300'],
            time: 6000
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
        layer.tips("密码长度不应少于6位！", "#password", {
            tips: [2, '#FF3300']
        });
        return false;
    }

    var randomcode = $("#randomcode").val();
    if (randomcode == null || randomcode.length == 0) {
        layer.tips("请输入验证码！", "#randomcode", {
            tips: [1, '#FF3300']
        });
        return false;
    }

    var checkAccount = true; // 账号是否可以注册标识
    var system_error = false; // ajax返回值异常标识
    // 验证账号是否可以注册
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

    var checkRandoCode = true; // 验证码是否正确标识
    // 检查验证码是否正确
    $.ajaxSettings.async = false; // 设置ajax同步
    $.post(base_url + "/login/checkrandomcode", {
        randomCode: randomcode
    }, function (result) {
        if (result != null) {
            if (result != "right") {
                checkRandoCode = false;
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
    if(!checkRandoCode) {
        layer.tips("验证码不正确！", "#randomcode", {
            tips: [1, '#FF3300']
        });
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