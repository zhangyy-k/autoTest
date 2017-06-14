package task.webSys.authExam.teacher;

import com.glodon.autoframework.actions.MyActions;
import com.glodon.autoframework.element.MyFind;
import com.glodon.autoframework.logger.LoggerControler;
import com.glodon.autoframework.tools.MyAssert;
import entity.webSys.AuthExam;
import entity.webSys.PayForGlodonInfo;
import object.webSys.authExam.teacher.SignUpPayTeaObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import task.webSys.login.WebLoginTask;
import task.webSys.menu.teacher.MenuTeaTask;

import java.util.List;

/**
 * 老师-认证考试-报名缴费管理-业务逻辑处理
 *@Author zhangyy
 *@Date 2017-6-13 8:36
 */
public class SignUpPayTeaTask {
    static final LoggerControler log = LoggerControler.getLogger(SignUpPayTeaTask.class);

    /**
     * 老师-确认学生缴费
     *@Author zhangyy
     *@Date 2017-6-13 8:36
     * @param teacherUser 老师登录账号
     * @param password 老师账号密码
     * @param authExamName 认证考试名称
     * @param driver 当前浏览器对象
     */
    public static void sureStuPay(String teacherUser, String password,
                                  String authExamName, WebDriver driver) throws InterruptedException{
        //老师-登录-点击左侧"认证考试-报名缴费管理"子菜单，进入考试列表
        MenuTeaTask.signUpPayMenu(teacherUser,password,driver);
        //点击 缴费明细 按钮
        MyActions.click(SignUpPayTeaObject.getPayDetailButton(authExamName),driver);
        //控制权移交
        MyActions.toWindow(driver);
        Thread.sleep(2000);
        //获取缴费学生详情 列表
        List<WebElement> list = MyFind.findElements(SignUpPayTeaObject.payStuRows,driver);
        int counts = list.size();
        log.info("需确认缴费学生共有："+counts+"个");
        for(int i = 1;i < counts + 1;i++){
            //学生名称
            String stuName = MyActions.getText(SignUpPayTeaObject.getStuName(i),driver);

            //根据row的index 获取 确认缴费 按钮
            MyActions.click(SignUpPayTeaObject.getSurePayButton(i),driver);
            //点击确认缴费 提示框 确定按钮
            MyActions.click(SignUpPayTeaObject.surePayDailog_ok_button,driver);
            //判断 确认缴费成功提示框  是否存在
            boolean b = MyActions.isDisplayed(SignUpPayTeaObject.paySuccessDailog,driver);
            log.info("学生："+stuName+"确认缴费成功提示框是否存在："+b);
            MyAssert.assertTrue(b);
            //点击 成功提示框 确定按钮
            MyActions.click(SignUpPayTeaObject.paySuccessDailog_ok_button,driver);

            Thread.sleep(1000);
            //缴费状态
            String payState = MyActions.getText(SignUpPayTeaObject.getPayState(i),driver);
            log.info("学生："+stuName+"的缴费状态为："+payState);
            MyAssert.assertInclude(payState,"已缴费",driver);
        }
        driver.quit();
    }

    /**
     * 老师-给广联达汇款
     *@Author zhangyy
     *@Date 2017-6-13 9:02
     * @param teacherUser 老师登录账号
     * @param password 老师账号密码
     * @param authExamName 认证考试名称
     * @param payForGlodonInfo 汇款信息实体对象
     * @param driver 当前浏览器对象
     */
    public static void payForGlodon(String teacherUser, String password,
                                    String authExamName, PayForGlodonInfo payForGlodonInfo,
                                    WebDriver driver) throws InterruptedException{
        //老师-登录-点击左侧"认证考试-报名缴费管理"子菜单，进入考试列表
        MenuTeaTask.signUpPayMenu(teacherUser,password,driver);
        //点击 需给广联达汇款的考试 tab页
        Thread.sleep(1000);
        MyActions.click(SignUpPayTeaObject.forGlodonFormTab,driver);
        //根据考试名称 获取 线下支付 按钮 点击
        MyActions.click(SignUpPayTeaObject.getUnderLinePayButton(authExamName),driver);
        //控制权移交
        MyActions.toWindow(driver);
        //填写汇款信息
        addPayForGlodonInfo(payForGlodonInfo,driver);

        //判断填写 保存成功 是否存在
        boolean b = MyActions.isDisplayed(SignUpPayTeaObject.payForGlodonSuccessDailog,driver);
        log.info("考试："+authExamName+";填写汇款信息保存是否成功："+b);
        MyAssert.assertTrue(b);
        //点击 保存成功 确定 按钮
        MyActions.click(SignUpPayTeaObject.payForGlodonSuccessDailog_ok_button,driver);


        //跳转到考试支付页，判断线上、线下支付按钮置灰
        String currentURL = "authentication/system/examRegister/viewExamPayDetail";
        MyActions.click(SignUpPayTeaObject.onLineButton,driver);
        String onLineBtnClickURL = MyActions.getCurrentUrl(driver);
        log.info("点击线上支付后页面为："+onLineBtnClickURL);

        MyActions.click(SignUpPayTeaObject.underLineButton,driver);
        String underLineBtnClickURL = MyActions.getCurrentUrl(driver);
        log.info("点击线下支付后页面为："+underLineBtnClickURL);

        MyAssert.assertInclude(onLineBtnClickURL,currentURL,driver);
        MyAssert.assertInclude(underLineBtnClickURL,currentURL,driver);

        //跳转到考试支付页，判断退费按钮置灰
        //获取缴费学生详情 列表
        List<WebElement> list = MyFind.findElements(SignUpPayTeaObject.payStuRows,driver);
        int counts = list.size();
        for(int i = 1;i < counts + 1;i++){
            //根据row的index 获取 退费 按钮 是否可用
            MyActions.click(SignUpPayTeaObject.getReturnPayButton(i),driver);
            String returnBtnClickURL = MyActions.getCurrentUrl(driver);
            log.info("点击退费按钮后页面为："+returnBtnClickURL);
            MyAssert.assertInclude(returnBtnClickURL,currentURL,driver);
        }

        //需给广联达汇款的考试列表中本场考试变为已支付
        //控制权移交
        MyActions.toWindow(driver);
        //刷新页面
        MyActions.refresh(driver);
        //点击 需给广联达汇款的考试 tab页
        Thread.sleep(1000);
        MyActions.click(SignUpPayTeaObject.forGlodonFormTab,driver);
        //根据考试名称 获取 已支付 是否存在
        Thread.sleep(1000);
        String payedText = MyActions.getText(SignUpPayTeaObject.getPayedText(authExamName),driver);
        log.info("已支付是否为："+payedText);
        MyAssert.assertEquals(payedText,"已支付");

        driver.quit();
    }
    
    /**
     * 填写汇款信息
     *@Author zhangyy
     *@Date 2017-6-13 9:38
     */
    public static void addPayForGlodonInfo(PayForGlodonInfo payForGlodonInfo, WebDriver driver){
        //汇款人姓名
        MyActions.sendText(SignUpPayTeaObject.payNameText,payForGlodonInfo.getPayName(),driver);
        //汇款单位
        MyActions.sendText(SignUpPayTeaObject.payCompanyText,payForGlodonInfo.getPayCompany(),driver);
        //汇款单号
        MyActions.sendText(SignUpPayTeaObject.payIdText,payForGlodonInfo.getPayId(),driver);
        //汇款日期
        MyActions.executeJS("document.getElementsByName('"+SignUpPayTeaObject.payTimeText+"')[0].setAttribute(\"value\",'"+payForGlodonInfo.getPayTime()+"')",driver);
        //汇款照片
        MyActions.executeJS("document.getElementById('"+SignUpPayTeaObject.payImageID+"').removeAttribute('readOnly')",driver);
        MyActions.sendText(SignUpPayTeaObject.payImageText,payForGlodonInfo.getPayImage(),driver);
        //提交信息
        MyActions.click(SignUpPayTeaObject.payForGlodonSubmitBtn,driver);

    }
}
