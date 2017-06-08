package task.webSys.authExam.student;

import com.glodon.autoframework.actions.MyActions;
import com.glodon.autoframework.logger.LoggerControler;
import com.glodon.autoframework.tools.MyAssert;
import object.webSys.authExam.student.MyAuthExamStuObject;
import object.webSys.index.IndexObject;
import object.webSys.menu.MenuObject;
import org.openqa.selenium.WebDriver;
import task.webSys.login.WebLoginTask;
import task.webSys.menu.student.MenuStuTask;
import task.webSys.menu.superman.MenuSuperTask;
import task.webSys.menu.teacher.MenuTeaTask;

/**
 * 学生-认证考试-我的考试-业务逻辑处理
 *@Author zhangyy
 *@Date 2017-6-8 9:10
 */
public class MyAuthExamStuTask {
    static final LoggerControler log = LoggerControler.getLogger(MyAuthExamStuTask.class);

    /**
     * 学生-我的考试-检验没有该场考试
     *@Author zhangyy
     *@Date 2017-6-8 9:20
     * @param stuUserMobil 学生登录账号
     * @param password 学生登录密码
     * @param authExamName 认证考试名称
     * @param driver 当前浏览器对象
     */
    public static void lookMyExam(String stuUserMobil, String password,
                                  String authExamName , WebDriver driver) throws InterruptedException{
        //学生-登录-点击左侧“认证考试-我的考试”子菜单，进入考试列表
        MenuStuTask.enterMyAuthExamMenu(stuUserMobil,password,driver);
        //判断 我的考试列表 是否显示 该场考试
        boolean b = MyActions.isDisplayed(MyAuthExamStuObject.getExamRow(authExamName),driver);
        log.info("学生："+stuUserMobil+"-我的考试列表中是否显示'"+authExamName+"'该场考试:"+String.valueOf(b));
        MyAssert.assertFalse(b);
        driver.quit();
    }

}
