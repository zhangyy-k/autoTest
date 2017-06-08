package task.webSys.menu.student;

import com.glodon.autoframework.actions.MyActions;
import object.webSys.authExam.student.AuthExamSignUpStuObject;
import object.webSys.index.IndexObject;
import object.webSys.menu.MenuObject;
import org.openqa.selenium.WebDriver;
import task.webSys.login.WebLoginTask;

/**
 * 学生-菜单-业务逻辑处理
 *@Author zhangyy
 *@Date 2017-6-8 9:58
 */
public class MenuStuTask {

    /**
     * 登录-点击-点击左侧“认证考试-我的考试”子菜单，进入考试列表
     *@Author zhangyy
     *@Date 2017-6-8 9:37
     * @param stuUserMobil 学生登录账号
     * @param password 学生登录密码
     * @param driver 当前浏览器对象
     */
    public static void enterMyAuthExamMenu(String stuUserMobil, String password, WebDriver driver) throws InterruptedException{
        //学生-登录
        WebLoginTask.webLogin(stuUserMobil,password,driver);
        //进入"我的中心"
        MyActions.click(IndexObject.myCenterButton, driver);
        //点击左侧"认证考试"菜单
        MyActions.click(MenuObject.item_authExam, driver);
        //点击左侧“认证考试-我的考试”子菜单，进入考试列表
        Thread.sleep(1000);
        MyActions.click(MenuObject.menuItem_myExam_student,driver);
    }

    /**
     * 登录-点击-点击左侧“认证考试-我的证书”子菜单，进入考试列表
     *@Author zhangyy
     *@Date 2017-6-8 9:37
     * @param stuUserMobil 学生登录账号
     * @param password 学生登录密码
     * @param driver 当前浏览器对象
     */
    public static void enterMyCertificateMenu(String stuUserMobil, String password, WebDriver driver) throws InterruptedException{
        //学生-登录
        WebLoginTask.webLogin(stuUserMobil,password,driver);
        //进入"我的中心"
        MyActions.click(IndexObject.myCenterButton, driver);
        //点击左侧"认证考试"菜单
        MyActions.click(MenuObject.item_authExam, driver);
        //点击左侧“认证考试-我的证书”子菜单，进入考试列表
        Thread.sleep(1000);
        MyActions.click(MenuObject.menuItem_certificate_student,driver);
    }

    /**
     * 登录-点击-点击左侧“认证考试-认证报名”子菜单，进入报名页面-点击我要报名，进入详情报名页面
     *@Author zhangyy
     *@Date 2017-6-8 9:37
     * @param stuUserMobil 学生登录账号
     * @param password 学生登录密码
     * @param authExamName 认证考试名称
     * @param driver 当前浏览器对象
     */
    public static void signUpMenu(String stuUserMobil, String password,String authExamName ,WebDriver driver) throws InterruptedException{
        //学生-登录
        WebLoginTask.webLogin(stuUserMobil,password,driver);
        //进入"我的中心"
        MyActions.click(IndexObject.myCenterButton, driver);
        //点击左侧"认证考试"菜单
        MyActions.click(MenuObject.item_authExam, driver);
        //点击左侧“认证考试-认证报名”子菜单，进入考试列表
        Thread.sleep(1000);
        MyActions.click(MenuObject.menuItem_examSignUp_student,driver);
        //点击“认证报名列表-统招”页签
        MyActions.click(AuthExamSignUpStuObject.entranExamTab,driver);
        //点击考试列表-我要报名 按钮
        MyActions.click(AuthExamSignUpStuObject.getSignUpButton(authExamName),driver);
        //点击考试详情页面-我要报名 按钮
        MyActions.click(AuthExamSignUpStuObject.signUpButtonDetail,driver);
    }
}
