package object.authExamSys.login;

import org.openqa.selenium.By;

/**
 * 考试系统-登录-业务对象
 *@Author zhangyy
 *@Date 2017-6-5 9:52
 */
public class AuthExamLoginObject {

    //登录文本框
    public static By userNameText = By.id("username");
    //密码输入框
    public static By passWordText = By.id("password");
    //验证码输入框
    public static By vcodeText = By.id("vcode");
    //登录按钮
    public static By loginBindButton = By.id("loginBind");
}
