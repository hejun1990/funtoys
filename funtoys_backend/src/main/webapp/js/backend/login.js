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
                window.location.href = base_url + "/main/index";
            } else {
                layer.msg(result.error_info, {icon: 5});
            }
        } else {
            layer.msg("系统异常！", {icon: 5});
        }
    });
}