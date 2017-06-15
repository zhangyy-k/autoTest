package task.webSys.authExam.superman;

import com.glodon.autoframework.actions.MyActions;
import com.glodon.autoframework.element.MyFind;
import com.glodon.autoframework.logger.LoggerControler;
import com.glodon.autoframework.tools.MyAssert;
import object.webSys.authExam.superman.CertificateExamSuperObject;
import object.webSys.index.IndexObject;
import object.webSys.menu.MenuObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import task.webSys.login.WebLoginTask;
import task.webSys.menu.superman.MenuSuperTask;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 管理员-证书管理-证书颁发-业务逻辑处理
 *@Author zhangyy
 *@Date 2017-6-6 15:41
 */
public class CertificateExamSuperTask {
    static final LoggerControler log = LoggerControler.getLogger(CertificateExamSuperTask.class);
    /**
     * 管理员-颁发证书
     *@Author zhangyy
     *@Date 2017-6-6 15:38
     * @param supermanUser 管理员账号
     * @param password 管理员账号密码
     * @param authExamName 认证考试名称
     * @param driver 当前浏览器对象
     */
    public static Map<String,String> certificateExam(String supermanUser, String password,
                                                     String authExamName, WebDriver driver) throws InterruptedException{
        //返回通过考试的考生手机号和证书编号
        Map<String,String> certificodes = new HashMap<>();
        //管理员-登录-点击左侧“证书管理-证书颁发”子菜单，进入考试列表
        MenuSuperTask.enterCertificatExamMenu(supermanUser,password,driver);
        //点击 未颁发列表中  查看详情按钮
        MyActions.click(CertificateExamSuperObject.getlookDatailButton(authExamName),driver);
        //控制权移交给新页面
        MyActions.toWindow(driver);
        //点击 生成证书按钮
        MyActions.click(CertificateExamSuperObject.certificateButton,driver);
        Thread.sleep(2000);
        //判断 证书生成一次 提示框 是否存在
        boolean b = MyActions.isDisplayed(CertificateExamSuperObject.certtificatDailog,driver);
        log.info("证书生成一次提示框是否存在："+String.valueOf(b));
        MyAssert.assertTrue(b);
        //点击 提示框中 确定按钮
        MyActions.click(CertificateExamSuperObject.certificateDailog_yes,driver);
        Thread.sleep(5000);
        //获取通过考生的手机号和证书编号
        //判断是否有通过的学生
//        b = MyActions.isDisplayed(CertificateExamSuperObject.noPassStuDataText,driver);
//        if(!b) {
            List<WebElement> list = MyFind.findElements(CertificateExamSuperObject.passStuTRCounts, driver);
            int passStuCounts = list.size();
            for (int i = 1; i < passStuCounts + 1; i++) {
                //获取学生手机号
                String stuMobil = MyActions.getText(CertificateExamSuperObject.getPassStrTRMobil(i), driver);
                //获取学生证书编号
                String stuCertificateCode = MyActions.getText(CertificateExamSuperObject.getPassStrTRCertificateCode(i), driver);
                certificodes.put(stuMobil, stuCertificateCode);
                log.info("学生：" + stuMobil + "的证书编号为：" + stuCertificateCode);
            }
//        }else{
//            log.info("该场考试没有合格的考生！");
//        }

        return certificodes;
    }
}
