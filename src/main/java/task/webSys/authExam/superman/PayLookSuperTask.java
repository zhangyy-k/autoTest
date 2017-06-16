package task.webSys.authExam.superman;

import com.glodon.autoframework.actions.MyActions;
import com.glodon.autoframework.logger.LoggerControler;
import com.glodon.autoframework.tools.MyAssert;
import object.webSys.authExam.superman.PayLookSuperObject;
import org.openqa.selenium.WebDriver;
import task.webSys.menu.superman.MenuSuperTask;

/**
 * 老师-认证考试-缴费查看-业务逻辑处理
 *@Author zhangyy
 *@Date 2017-6-13 9:51
 */
public class PayLookSuperTask {
    static final LoggerControler log = LoggerControler.getLogger(PayLookSuperTask.class);

    /**
     * 管理员-认证考试-缴费查看-确认收到汇款
     *@Author zhangyy
     *@Date 2017-6-13 9:52
     * @param supermanUser 管理员账号
     * @param password 管理员账号密码
     * @param authExamName 认证考试名称
     * @param driver 当前浏览器对象
     */
    public static void sureGetPay(String supermanUser, String password,
                                  String authExamName, WebDriver driver) throws InterruptedException{
        //管理员-登录-点击左侧“认证考试-缴费查看”子菜单，进入考试列表
        MenuSuperTask.payLookMenu(supermanUser,password,driver);
        //根据 考试名称 获取 查看 按钮点击
        MyActions.click(PayLookSuperObject.getLookPayDetailButton(authExamName),driver);
        //控制权 转交
        MyActions.toWindow(driver);
        //点击 确认收到汇款 按钮
        MyActions.click(PayLookSuperObject.sureGetPayButton,driver);
        //输入 管理员登录密码
        MyActions.sendText(PayLookSuperObject.upwdText,password,driver);
        //点击 确定按钮
        Thread.sleep(1000);
        MyActions.click(PayLookSuperObject.sureGetPayDailog_yes_button,driver);

        //判断 收款成功 提示框 是否存在
        boolean b = MyActions.isDisplayed(PayLookSuperObject.sureGetPaySuccessDailog,driver);
        log.info("考试："+authExamName+";管理员确认收款是否成功："+b);
        MyAssert.assertTrue(b);
        //点击 成功提示框 确定按钮
        MyActions.click(PayLookSuperObject.sureGetPaySuccessDailog_yes_button,driver);

        //判断 确认收到汇款”按钮变为“已收到汇款”
        boolean getPayedButton = MyActions.isDisplayed(PayLookSuperObject.getPayedButton,driver);
        log.info("已收到汇款按钮是否显示："+b);
        MyAssert.assertTrue(getPayedButton);

        //判断 本场考试从未完成支付考试列表中调整到已完成支付列表中
        //控制权 转交
        MyActions.toWindow(driver);
        //点击 统招类-已完成支付tab
        MyActions.click(PayLookSuperObject.tongPayTab,driver);
        //根据考试名称 获取考试行  是否在列表中
        Thread.sleep(1000);
        b = MyActions.isDisplayed(PayLookSuperObject.getPayedAuthExamRow(authExamName),driver);
        log.info("考试："+authExamName+";是否在已支付列表中："+b);
        MyAssert.assertTrue(b);
    }

}
