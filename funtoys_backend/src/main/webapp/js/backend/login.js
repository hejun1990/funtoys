var base = "backend";

function login() {
    var account = $("#account").val();
    if (account == null || account.length == 0) {
        alert("请输入用户名！");
        return false;
    }
    var password = $("#password").val();
    if (password == null || password.length == 0) {
        alert("请输入密码！");
        return false;
    }
    var encrypt_pwd = b64_md5(password);
    $.post(base + "/login/check", {account: account, password: encrypt_pwd}, function (result) {
        if(result != null) {
            if(result.msg == "success") {
                window.location.href = base + "/main/index";
            }
        }
    });

}