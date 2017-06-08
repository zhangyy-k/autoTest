package task.webSys.authExam.superman;


import com.glodon.autoframework.actions.MyActions;
import com.glodon.autoframework.logger.LoggerControler;
import com.glodon.autoframework.tools.DateFormat;
import com.glodon.autoframework.tools.MyAssert;
import object.webSys.authExam.superman.CertificateExamSuperObject;
import object.webSys.authExam.superman.PublishExamResultSuperObject;
import object.webSys.index.IndexObject;
import object.webSys.menu.MenuObject;
import org.openqa.selenium.WebDriver;
import task.webSys.login.WebLoginTask;
import task.webSys.menu.superman.MenuSuperTask;

import java.util.Date;
import java.util.Map;

import static com.glodon.autoframework.tools.DateFormat.DEFAULT_DATE_FORMAT;

/**
 * 管理员-发布考试结果-业务逻辑处理
 *@Author zhangyy
 *@Date 2017-6-5 14:24
 */
public class PublishExamResultSuperTask {
    static final LoggerControler log = LoggerControler.getLogger(PublishExamResultSuperTask.class);


    /**
     * 管理员-发布考试结果
     *@Author zhangyy
     *@Date 2017-6-6 15:29
     * @param supermanUser 管理员账号
     * @param password 管理员账号密码
     * @param authExamName 认证考试名称
     * @param stuUsersEditScore 学生账号及修改成绩
     * @param driver 当前浏览器对象
     */
    public static void publishExamResult(String supermanUser, String password,
                                         String authExamName, Map<String,Double > stuUsersEditScore, WebDriver driver) throws InterruptedException{

        //管理员-登录-点击左侧“证书管理-发布考试结果”子菜单，进入考试列表
        MenuSuperTask.enterPublishiExamResultMenu(supermanUser,password,driver);
        //点击 认证考试-未发布列表中  查看成绩按钮
        MyActions.click(PublishExamResultSuperObject.getlookScoreButton(authExamName),driver);
        //控制权移交给新页面
        MyActions.toWindow(driver);
        //根据考生手机号 修改考试成绩
        if(stuUsersEditScore != null){
            for (Map.Entry<String,Double> entry : stuUsersEditScore.entrySet()){
                PublishExamResultSuperTask.editExamScore(entry.getKey(),entry.getValue(),driver);
            }
        }else{
            log.info("老师发布考试结果-学生修改成绩集合为空");
        }

        //点击 发布级别  按钮
        MyActions.click(PublishExamResultSuperObject.publishButton,driver);
        MyActions.click(PublishExamResultSuperObject.publishButtonDailog_ok,driver);
        Thread.sleep(2000);
        //判断 发布级别成功提示框 是否存在
        boolean b = MyActions.isDisplayed(PublishExamResultSuperObject.publishiSuccessDailog,driver);
        log.info("管理员发布级别成功提示框是否存在："+String.valueOf(b));
        MyAssert.assertTrue(b);
        //点击 成功对话框 确定按钮
        MyActions.click(PublishExamResultSuperObject.publishButtonSuccessDailog_ok,driver);
        driver.quit();
    }

    /**
     * 修改考试成绩
     *@Author zhangyy
     *@Date 2017-6-5 14:24
     * @param mobil 考生手机号
     * @param newScore 修改后分数
     * @param driver 当前浏览器对象
     */
    public static void editExamScore(String mobil, double newScore, WebDriver driver) throws InterruptedException {
        //在列表中 根据考生手机号 定位修改按钮
        MyActions.click(PublishExamResultSuperObject.getEditScoreButton(mobil),driver);
        //在弹框中 输入修改后分数
        MyActions.sendText(PublishExamResultSuperObject.newScoreText,String.valueOf(newScore),driver);
        //点击弹框中 确定按钮
        MyActions.click(PublishExamResultSuperObject.editScoreDailog_ok,driver);
        //点击修改成功提示框 确定按钮
        Thread.sleep(2000);
        MyActions.click(PublishExamResultSuperObject.editScoreSuccessDailog_ok,driver);
    }
}
