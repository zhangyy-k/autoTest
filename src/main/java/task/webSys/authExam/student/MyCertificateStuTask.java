package task.webSys.authExam.student;

import com.glodon.autoframework.actions.MyActions;
import com.glodon.autoframework.element.MyFind;
import com.glodon.autoframework.logger.LoggerControler;
import com.glodon.autoframework.tools.MyAssert;
import object.webSys.authExam.student.MyCertificateStuObject;
import object.webSys.index.IndexObject;
import object.webSys.menu.MenuObject;
import org.openqa.selenium.WebDriver;
import task.webSys.login.WebLoginTask;
import task.webSys.menu.student.MenuStuTask;

/**
 * 学生-认证考试-我的证书-业务逻辑处理
 *@Author zhangyy
 *@Date 2017-6-6 15:43
 */
public class MyCertificateStuTask {
    static final LoggerControler log = LoggerControler.getLogger(MyCertificateStuTask.class);

    /**
     * 学生-登录-认证考试-查看我的证书
     *@Author zhangyy
     *@Date 2017-6-6 15:44
     * @param stuUserMobil 学生登录账号
     * @param password 学生登录密码
     * @param certificateCode 证书编号
     * @param driver 当前浏览器对象
     */
    public static void lookMyCertificate(String stuUserMobil, String password,
                                         String certificateCode , WebDriver driver) throws InterruptedException{
        //学生-登录-点击左侧“认证考试-我的证书”子菜单，进入考试列表
        MenuStuTask.enterMyCertificateMenu(stuUserMobil,password,driver);
        //根据 证书编号 查询证书是否显示
        boolean b = MyActions.isDisplayed(MyCertificateStuObject.getCertificateText(certificateCode),driver);
        log.info("学生:"+stuUserMobil+"的证书是否显示："+String.valueOf(b)+";编号为:"+certificateCode);
        MyAssert.assertTrue(b);
    }

    /**
     * 学生-测评认证-证书查询
     *@Author zhangyy
     *@Date 2017-6-6 15:44
     * @param stuName 学生姓名
     * @param certificateCode 证书编号
     * @param driver 当前浏览器对象
     */
    public static void searcheMyCertificate(String stuName,String certificateCode , WebDriver driver) throws InterruptedException{
        //主页-顶部“测评认证”菜单，鼠标移动
        MyActions.mouseRemove(MyFind.findElement(IndexObject.topMenuCprzButton,driver),driver);
        //点击“主页-测评认证-证书查询”按钮
        MyActions.click(IndexObject.topItemMenuSerachCertificateButton,driver);
        //输入证书编号查询信息
//        MyActions.sendText(IndexObject.userName,stuName,driver);//输入姓名
        MyActions.sendText(IndexObject.identityText,stuName,driver);//输入姓名
        MyActions.sendText(IndexObject.certificaetCode,certificateCode,driver);//输入证书编号
        MyActions.click(IndexObject.searcheButton,driver);//点击 查询 按钮
        //根据 证书编号 查询证书是否显示
        boolean b = MyActions.isDisplayed(IndexObject.getSearchResutl(certificateCode),driver);
        log.info("学生:"+stuName+"的证书是否显示："+String.valueOf(b)+";编号为:"+certificateCode);
        MyAssert.assertTrue(b);
    }
}
