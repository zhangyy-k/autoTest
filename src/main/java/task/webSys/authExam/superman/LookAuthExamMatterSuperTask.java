package task.webSys.authExam.superman;

import com.glodon.autoframework.actions.MyActions;
import com.glodon.autoframework.element.MyFind;
import com.glodon.autoframework.logger.LoggerControler;
import com.glodon.autoframework.tools.MyAssert;
import object.webSys.authExam.superman.CertificateExamSuperObject;
import object.webSys.authExam.superman.LookAuthExamMatterSuperObject;
import object.webSys.authExam.superman.PublishExamResultSuperObject;
import object.webSys.index.IndexObject;
import object.webSys.menu.MenuObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import task.webSys.login.WebLoginTask;
import task.webSys.menu.superman.MenuSuperTask;

import java.util.List;

/**
 * 管理员-认证考试-查看考试情况-业务逻辑处理
 *@Author zhangyy
 *@Date 2017-6-7 14:23
 */
public class LookAuthExamMatterSuperTask {
    static final LoggerControler log = LoggerControler.getLogger(LookAuthExamMatterSuperTask.class);

    /**
     * 管理员-查看考试情况
     *@Author zhangyy
     *@Date 2017-6-7 14:49
     * @param supermanUser 管理员账号
     * @param password 管理员账号密码
     * @param authExamName 认证考试名称
     * @param driver 当前浏览器对象
     */
    public static void lookExamMatter(String supermanUser, String password,
                                      String authExamName, WebDriver driver) throws InterruptedException{

        //管理员-登录-点击左侧“证书管理-查看考试情况”子菜单，进入考试列表
        MenuSuperTask.enterLookExamMatterMenu(supermanUser,password,driver);
        //点击 认证考试-正在进行考试-认证考试列表中  查看按钮
        MyActions.click(LookAuthExamMatterSuperObject.getLookExamButton(authExamName),driver);
        //控制权移交给新页面
        MyActions.toWindow(driver);

        //获取已经参加考试的考生列表
        Thread.sleep(5000);
        List<WebElement> list = MyFind.findElements(LookAuthExamMatterSuperObject.enterExamStuTRCounts,driver);
        int enterExamStuCounts = list.size();
        log.info("已经参加考试的学生数："+String.valueOf(enterExamStuCounts));
        for(int i = 1;i < enterExamStuCounts + 1;i++){
            //获取考生手机号
            String stuMobil = MyActions.getText(LookAuthExamMatterSuperObject.getEnterExamStuTRMobil(i),driver);
            //获取考生成绩
            String stuScore = MyActions.getText(LookAuthExamMatterSuperObject.getEnterExamStuTRScore(i),driver);
            //获取考生交卷时间
            String stuHandTime = MyActions.getText(LookAuthExamMatterSuperObject.getEnterExamStuTRHandTime(i),driver);
            log.info("学生："+stuMobil+"的考试成绩为："+stuScore+";交卷时间为："+stuHandTime);
            MyAssert.assertEquals(stuScore,"0.00");
        }
        driver.quit();
    }
}
