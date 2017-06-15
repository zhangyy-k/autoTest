package task.webSys.authExam.superman;

import com.glodon.autoframework.actions.MyActions;
import com.glodon.autoframework.logger.LoggerControler;
import com.glodon.autoframework.tools.MyAssert;
import object.webSys.authExam.superman.AuthExamLookSuperObjcet;
import object.webSys.index.IndexObject;
import object.webSys.menu.MenuObject;
import org.openqa.selenium.WebDriver;
import task.webSys.login.WebLoginTask;
import task.webSys.menu.superman.MenuSuperTask;

/**
 * 管理员-认证考试-考试查看-业务逻辑处理
 *@Author zhangyy
 *@Date 2017-6-6 14:50
 */
public class AuthExamLookSuperTask {
    static final LoggerControler log = LoggerControler.getLogger(AuthExamLookSuperTask.class);

    /**
     * 管理员审核认证考试(通过)
     *@Author zhangyy
     *@Date 2017-6-6 14:51
     * @param supermanUser 管理员账号
     * @param password 管理员账号密码
     * @param authExamName 认证考试名称
     * @param driver 当前浏览器对象
     */
    public static void passAuthExam(String supermanUser, String password,
                                    String authExamName, WebDriver driver) throws InterruptedException{
        //管理员-登录-点击左侧“认证考试-考试查看”子菜单，进入考试列表
        MenuSuperTask.enterAuthExamLookMenu(supermanUser,password,driver);
        //点击“通过”按钮
        MyActions.click(AuthExamLookSuperObjcet.getPublishiButton_superman(authExamName),driver);
        MyActions.click(AuthExamLookSuperObjcet.dailog_yes_superman,driver);
        //判断审核成功 提示框 是否显示
        boolean b = MyActions.isDisplayed(AuthExamLookSuperObjcet.daiog_sucessful,driver);
        log.info("管理员-审核-认证考试-审核成功提示框是否显示："+String.valueOf(b));
        MyAssert.assertTrue(b);
        MyActions.click(AuthExamLookSuperObjcet.dailog_ok_superman,driver);
    }
}
