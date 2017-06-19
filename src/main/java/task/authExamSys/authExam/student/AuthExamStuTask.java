package task.authExamSys.authExam.student;

import com.glodon.autoframework.actions.MyActions;
import com.glodon.autoframework.element.MyFind;
import com.glodon.autoframework.logger.LoggerControler;
import com.glodon.autoframework.tools.DateFormat;
import com.glodon.autoframework.tools.MyAssert;
import com.glodon.autoframework.tools.MyDataBase;
import object.authExamSys.authExam.student.AuthExamStuObject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import task.authExamSys.login.AuthExamLoginTask;
import task.webSys.menu.student.MenuStuTask;
import task.webSys.menu.superman.MenuSuperTask;

import java.util.Date;
import java.util.List;

import static com.glodon.autoframework.tools.DateFormat.DEFAULT_DATE_FORMAT;

/**
 * 认证考试系统-学生-认证考试-业务逻辑处理
 *@Author zhangyy
 *@Date 2017-6-6 15:06
 */
public class AuthExamStuTask {
    static final LoggerControler log = LoggerControler.getLogger(AuthExamStuTask.class);
    /**
     * 考试-登录认证考试系统-进入考试
     *@Author zhangyy
     *@Date 2017-6-6 15:07
     * @param stuUserMobil 学生登录账号
     * @param password 学生登录密码
     * @param authExamName 认证考试名称
     * @param driver 当前浏览器对象
     */
    public static void enterAuthExam(String stuUserMobil, String password,
                                     String authExamName,WebDriver driver) throws InterruptedException{
        //学生-登录-认证考试系统
        AuthExamLoginTask.authExamLogin(stuUserMobil,password,driver);
        //点击进入考试
        By enterExamButton = AuthExamStuObject.getEnterExamButton(authExamName);
        MyActions.click(enterExamButton,driver);

        //判断考试是否已经开始
        By examStartButton = AuthExamStuObject.examStartButton;//倒计时页面 开始考试 按钮
        boolean b = MyActions.isDisplayed(examStartButton,driver);
        log.info("是否需要等待考试开始："+b);
        if(b){
            //等待 考试倒计时
            long sleepTime = -1;
            sleepTime = enterExamCountDown(driver);
            log.info("距离考试开始还有"+String.valueOf(sleepTime)+"毫秒，请等待……");
            Thread.sleep(sleepTime);
            log.info("考试开始");
            MyActions.click(examStartButton,driver);
        }else
            log.info("考试已经开始");

        //附件下载

        //交卷
        Thread.sleep(5000);
        handExam(stuUserMobil,driver);
        //检验 已经交卷
        Thread.sleep(5000);
        isHandExam_Y(stuUserMobil,authExamName,driver);
    }

    /**
     * 考试详情页面  附件下载
     *@Author zhangyy
     *@Date 2017-6-16 16:49
     */
    public static void attachementDownLoad(WebDriver driver) throws InterruptedException{
        List<WebElement> attachementRows = MyFind.findElements(AuthExamStuObject.attachmentTRows,driver);
        int attaementCouts = attachementRows.size();
        log.info("考试页面附件个数为："+attaementCouts);
        for(int i = 1;i < attaementCouts + 1;i++){
            //获取附件按钮 点击
            MyActions.click(AuthExamStuObject.attachmentButton(i),driver);
            //
            Thread.sleep(5000);
            boolean b = MyActions.isDisplayed(AuthExamStuObject.downLoadDailog,driver);
            log.info("附件下载提示对话框是否存在："+b);
            Thread.sleep(5000);
            //获取下载地址
            String downLoadURL = MyActions.getText(AuthExamStuObject.downLoadURL,driver);
            log.info("附件的下载地址为："+downLoadURL);

//            MyActions.click(AuthExamStuObject.downLoadDailog_ok_button,driver);
            Thread.sleep(5000);

        }
    }

    /**
     * 未打开算量软件，交卷
     *@Author zhangyy
     *@Date 2017-6-9 13:41
     * @param stuUserMobil 学生登录账号
     * @param driver 当前浏览器对象
     *
     */
    public static void handExam(String stuUserMobil,WebDriver driver) throws InterruptedException{
        //点击交卷
        MyActions.click(AuthExamStuObject.handExamButton,driver);
        //直接交卷按钮
        Thread.sleep(5000);
        MyActions.click(AuthExamStuObject.handExamDailogButton,driver);
        //异常提示 确定按钮
        Thread.sleep(8000);
        //判断 交卷成功 提示框 是否存在
        boolean b = MyActions.isDisplayed(AuthExamStuObject.handExamSucessfulExceptionDailog,driver);
        log.info("学生:"+stuUserMobil+"交卷成功提示框是否存在："+String.valueOf(b));
        MyAssert.assertTrue(b);
        //点击 提示框 确定按钮
        MyActions.click(AuthExamStuObject.handExamSucessfulExceptionOkButton,driver);
    }

    /**
     * 检验点 已经交卷(考试列表  进入考试 变为 已经交卷；且不可用)
     *@Author zhangyy
     *@Date 2017-6-9 13:44
     * @param stuUserMobil 学生登录账号
     * @param authExamName 认证考试名称
     * @param driver 当前浏览器对象
     */
    public static void isHandExam_Y(String stuUserMobil,String authExamName,WebDriver driver){
        //返回考试列表  进入考试 变为 已经交卷；且不可用
        //判断考试是否结束
        By enterExamButton = AuthExamStuObject.getEnterExamButton(authExamName);
        boolean enterExamButtonIsD = MyActions.isDisplayed(enterExamButton,driver);
        log.info("学生:"+stuUserMobil+"考试列表是否显示该考试："+String.valueOf(enterExamButtonIsD));
        if(enterExamButtonIsD){
            String enterExamText = MyActions.getText(enterExamButton,driver);
            //判断进入考试 按钮是否可用(点击已经交卷按钮，停留在当前页面)
            MyActions.click(enterExamButton,driver);
            boolean enterExamButtonIsCurrentPage = MyActions.isDisplayed(enterExamButton,driver);
            log.info("学生:"+stuUserMobil+"进入考试按钮改为："+enterExamText+";点击进入考试按钮是否停留当前页面："+String.valueOf(enterExamButtonIsCurrentPage));
            //判断 进入考试 变为 已经交卷
            MyAssert.assertInclude(enterExamText,"已经交卷",driver);
            //判断 进入考试 按钮不可用
            MyAssert.assertTrue(enterExamButtonIsCurrentPage);
        }else{
            log.info("该考试已经结束，不在考试列表显示！");
        }
    }

    /**
     * 获取认证考试开始时间和结束时间
     *@Author zhangyy
     *@Date 2017-6-6 15:12
     * @param authExamName 认证考试名称
     */
    public static List<Object[]> getAuthExamTime(String authExamName){
        MyDataBase myDataBase = new MyDataBase();
        String sql = " SELECT a.start_time,a.end_time from au_exam_info a where a.title = '"+authExamName+"' ";
        List<Object[]> list = myDataBase.selectData(sql);
        return list;
    }

    /**
     * 检验点 考试列表没有该场考试
     *@Author zhangyy
     *@Date 2017-6-8 13:52
     */
    public static void authExamIsDisplay(String stuUserMobil, String password,
                                         String authExamName,WebDriver driver) throws InterruptedException{
        //学生-登录-认证考试系统
        AuthExamLoginTask.authExamLogin(stuUserMobil,password,driver);
        //判断 该考试是否 显示
        boolean b = MyActions.isDisplayed(AuthExamStuObject.authExamRow(authExamName),driver);
        log.info("学生："+stuUserMobil+"；登录考试-考试列表中是否存在该场考试："+String.valueOf(b));
        MyAssert.assertFalse(b);
    }

    /**
     * 考试进入考试，判断倒计时
     *@Author zhangyy
     *@Date 2017-6-9 13:30
     */
    public static long enterExamCountDown(WebDriver driver) throws InterruptedException{
        return (long)MyActions.executeJS("return residue",driver);
    }


}
