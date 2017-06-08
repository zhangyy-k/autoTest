package task.webSys.menu.superman;

import com.glodon.autoframework.actions.MyActions;
import object.webSys.index.IndexObject;
import object.webSys.menu.MenuObject;
import org.openqa.selenium.WebDriver;
import task.webSys.login.WebLoginTask;

/**
 * 管理员-菜单-业务逻辑处理
 *@Author zhangyy
 *@Date 2017-6-8 9:47
 */
public class MenuSuperTask {

    /**
     * 管理员-登录-点击左侧“认证考试-考试查看”子菜单，进入考试列表
     *@Author zhangyy
     *@Date 2017-6-8 9:47
     * @param supermanUser 管理员账号
     * @param password 管理员账号密码
     * @param driver 当前浏览器对象
     */
    public static void enterAuthExamLookMenu(String supermanUser, String password,WebDriver driver) throws InterruptedException{
        //管理员-登录
        WebLoginTask.webLogin(supermanUser,password,driver);
        //进入"我的中心"
        MyActions.click(IndexObject.myCenterButton, driver);
        //点击左侧"认证考试"菜单
        MyActions.click(MenuObject.item_authExam, driver);
        //点击左侧“认证考试-考试查看”子菜单，进入考试列表
        Thread.sleep(1000);
        MyActions.click(MenuObject.menuItem_examLook_superman,driver);
    }

    /**
     * 管理员-登录-点击左侧“证书管理-证书颁发”子菜单，进入考试列表
     *@Author zhangyy
     *@Date 2017-6-8 9:47
     * @param supermanUser 管理员账号
     * @param password 管理员账号密码
     * @param driver 当前浏览器对象
     */
    public static void enterCertificatExamMenu(String supermanUser, String password,WebDriver driver) throws InterruptedException{
        //管理员-登录
        WebLoginTask.webLogin(supermanUser,password,driver);
        //进入"我的中心"
        MyActions.click(IndexObject.myCenterButton, driver);
        //点击左侧"证书管理"菜单
        MyActions.click(MenuObject.item_certificate, driver);
        //点击左侧“证书管理-证书颁发”子菜单，进入考试列表
        Thread.sleep(1000);
        MyActions.click(MenuObject.menuItem_certificateExam_superman,driver);
    }

    /**
     * 管理员-登录-点击左侧“证书管理-查看考试情况”子菜单，进入考试列表
     *@Author zhangyy
     *@Date 2017-6-8 9:47
     * @param supermanUser 管理员账号
     * @param password 管理员账号密码
     * @param driver 当前浏览器对象
     */
    public static void enterLookExamMatterMenu(String supermanUser, String password,WebDriver driver) throws InterruptedException{
        //管理员-登录
        WebLoginTask.webLogin(supermanUser,password,driver);
        //进入"我的中心"
        MyActions.click(IndexObject.myCenterButton, driver);
        //点击左侧"认证考试"菜单
        MyActions.click(MenuObject.item_authExam, driver);
        //点击左侧“认证考试-查看考试情况”子菜单，进入考试列表
        Thread.sleep(1000);
        MyActions.click(MenuObject.menuItem_lookExamMatter_superman,driver);
    }

    /**
     * 管理员-登录-点击左侧“证书管理-发布考试结果”子菜单，进入考试列表
     *@Author zhangyy
     *@Date 2017-6-8 9:47
     * @param supermanUser 管理员账号
     * @param password 管理员账号密码
     * @param driver 当前浏览器对象
     */
    public static void enterPublishiExamResultMenu(String supermanUser, String password,WebDriver driver) throws InterruptedException{
        WebLoginTask.webLogin(supermanUser,password,driver);
        //进入"我的中心"
        MyActions.click(IndexObject.myCenterButton, driver);
        //点击左侧"认证考试"菜单
        MyActions.click(MenuObject.item_authExam, driver);
        //点击左侧“认证考试-发布考试结果”子菜单，进入考试列表
        Thread.sleep(1000);
        MyActions.click(MenuObject.menuItem_publishExamResult_superman,driver);
    }
}
