package task.webSys.menu.teacher;

import com.glodon.autoframework.actions.MyActions;
import object.webSys.index.IndexObject;
import object.webSys.menu.MenuObject;
import org.openqa.selenium.WebDriver;
import task.webSys.login.WebLoginTask;

/**
 * 老师-菜单-业务逻辑处理
 *@Author zhangyy
 *@Date 2017-6-8 9:37
 */
public class MenuTeaTask {

    /**
     * 登录-点击左侧"认证考试-发布考试信息"子菜单，进入考试列表
     *@Author zhangyy
     *@Date 2017-6-8 9:37
     * @param teacherUser 老师登录账号
     * @param password 老师账号密码
     * @param driver 当前浏览器对象
     */
    public static void enterPublishiExamMenu(String teacherUser,String password,WebDriver driver) throws InterruptedException{
        //老师-登录
        WebLoginTask.webLogin(teacherUser,password, driver);
        //进入"我的中心"
        MyActions.click(IndexObject.myCenterButton, driver);
        //点击左侧"认证考试"菜单
        MyActions.click(MenuObject.item_authExam, driver);
        //点击左侧"认证考试-发布考试信息"子菜单，进入考试列表
        Thread.sleep(1000);
        MyActions.click(MenuObject.menuItem_publishExamInfo_teacher, driver);
    }
}
