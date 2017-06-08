package task.webSys.login;

import com.glodon.autoframework.actions.MyActions;
import object.webSys.login.WebLoginObject;
import org.openqa.selenium.WebDriver;

/**
 * 登录-逻辑处理
 *@Author zhangyy
 *@Date 2017-4-24 10:53
 */
public class WebLoginTask {

    /**
     * web端-登陆方法
     *@Author zhangyy
     *@Date 2017-4-17 16:30
     * @param userName 登录用户名
     * @param passWord 登录用户名密码
     * @param driver 当前打开的浏览器对象
     */
    public static void webLogin(String userName,String passWord, WebDriver driver){
        //点击首页登录标签
        MyActions.click(WebLoginObject.loginAIndex, driver);
        //输入用户名
        MyActions.sendText(WebLoginObject.usernameText,userName, driver);
        //输入密码
        MyActions.sendText(WebLoginObject.passwordText,passWord, driver);
        //输入验证码
        MyActions.sendText(WebLoginObject.codeText,"123456", driver);
        //点击登录
        MyActions.click(WebLoginObject.loginButton, driver);
    }

}
