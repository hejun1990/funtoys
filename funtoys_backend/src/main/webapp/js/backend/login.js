// 初始化
function initLogin() {
    var account_store = $.cookie("account");
    if (account_store != null && account_store.length > 0) {
        $("#account").val(account_store);
    }
}

//登录
function login() {
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
    var encrypt_pwd = b64_md5(password);
    $.post(base_url + "/login/checklogin", {
        account: account,
        password: encrypt_pwd,
        accountType: 2 // 商家
    }, function (result) {
        if (result != null) {
            result = $.parseJSON(result);
            if (result.msg == "success") {
                saveAccount(account);
                window.location.href = base_url + "/main/index";
            } else {
                layer.msg(result.error_info, {icon: 5});
            }
        } else {
            layer.msg("系统异常！", {icon: 5});
        }
    });
}

// 保存登录的用户名
function saveAccount(account) {
    var rememberMe = $(".checkbox-inline").children("input[type=checkbox]");
    if ($(rememberMe).is(':checked')) {
        var account_store = $.cookie("account");
        if (account_store == null || account_store.length == 0 || account_store != account) {
            $.cookie(
                "account",
                account,
                {
                    expires: 3, // 保存3天
                    path: "/"
                }
            );
        }
    }
}

initLogin();