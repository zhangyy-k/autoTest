package object.webSys.login;

import org.openqa.selenium.By;

/**
 * web端登陆页面-页面元素对象
 *@Author zhangyy
 *@Date 2017-4-17 16:26
 */
public class WebLoginObject {

    //首页登录标签
    public static By loginAIndex = By.xpath(".//*[text()='登录']");

    //用户名文本框
    public static By usernameText = By.id("userName");
    //密码文本框
    public static By passwordText = By.id("password");
    //验证码
    public static By codeText = By.id("vcode");
    //登陆按钮
    public static By loginButton = By.id("loginBtn");

    //登录成功 校验方式  “.//*[text()='我的中心']”显示
    public static By myCenterText = By.xpath(".//*[text()='我的中心']");

    //登录错误提示dialog
    public static By errorDialog = By.id("layui-layer3");
    //错误信息提示 确定按钮
    public static By errorDialogButton = By.xpath(".//*[@id='layui-layer3']/div[3]/a");





}
