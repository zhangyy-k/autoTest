package task.authExamSys.login;

import com.glodon.autoframework.actions.MyActions;
import object.authExamSys.login.AuthExamLoginObject;
import object.webSys.authExam.superman.AuthExamLookSuperObjcet;
import org.openqa.selenium.WebDriver;
import sun.awt.windows.ThemeReader;

/**
 * 考试系统-登录-业务逻辑处理
 *@Author zhangyy
 *@Date 2017-6-5 9:48
 */
public class AuthExamLoginTask {
    /**
     * 考试系统端-登录方法
     *@Author zhangyy
     *@Date 2017-6-5 9:43
     * @param userName 登录用户名
     * @param passWord 登录用户名密码
     * @param driver 当前打开的浏览器对象
     */
    public static void authExamLogin(String userName,String passWord, WebDriver driver) throws InterruptedException{
        Thread.sleep(5000);
        //点击断网提示
        MyActions.click(AuthExamLoginObject.netTrip,driver);
        Thread.sleep(3000);
        //点击 登录页面  提示框
        MyActions.click(AuthExamLoginObject.loginDialog,driver);
        Thread.sleep(3000);
        //输入用户名
        MyActions.sendText(AuthExamLoginObject.userNameText,userName,driver);
        //输入密码
        MyActions.sendText(AuthExamLoginObject.passWordText,passWord,driver);
        //输入验证码
        MyActions.sendText(AuthExamLoginObject.vcodeText,"123",driver);
        //点击登录
        MyActions.click(AuthExamLoginObject.loginBindButton,driver);
    }
}
