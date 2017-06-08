package task.webSys.authExam.teacher;

import com.glodon.autoframework.actions.MyActions;
import com.glodon.autoframework.element.MyFind;
import com.glodon.autoframework.logger.LoggerControler;
import com.glodon.autoframework.tools.DateFormat;
import com.glodon.autoframework.tools.MyAssert;
import entity.webSys.AuthExam;
import object.webSys.authExam.teacher.AddAuthExamTeaObject;
import object.webSys.index.IndexObject;
import object.webSys.menu.MenuObject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import task.webSys.login.WebLoginTask;
import task.webSys.menu.teacher.MenuTeaTask;

/**
 * 老师-认证考试-发布考试信息-业务逻辑处理
 *@Author zhangyy
 *@Date 2017-4-24 10:54
 */
public class AddAuthExamTeaTask {
    static final LoggerControler log = LoggerControler.getLogger(AddAuthExamTeaTask.class);

    /**
     * 老师-登录-新建认证考试（线上统招）
     *@Author zhangyy
     *@Date 2017-6-6 14:37
     * @param teacherUser 老师登录账号
     * @param password 老师账号密码
     * @param authExam 认证考试实体对象
     * @param driver 当前浏览器对象
     */
    public static void publishAuthExam(String teacherUser,String password,
                                       AuthExam authExam,WebDriver driver) throws InterruptedException{
        //老师-登录-点击左侧"认证考试-发布考试信息"子菜单，进入考试列表
        MenuTeaTask.enterPublishiExamMenu(teacherUser,password,driver);
        //新建考试
        AddAuthExamTeaTask.addAuthExam(authExam,driver);
        driver.quit();
    }


    /**
     * 新建认证考试
     *@Author zhangyy
     *@Date 2017-4-24 10:51
     */
    public static void addAuthExam(AuthExam authExam, WebDriver driver) throws InterruptedException {

        //点击新建考试
        MyActions.click(AddAuthExamTeaObject.addExamButton,driver);
        //控制权转交至新建页面
        MyActions.toWindow(driver);
        //考试类型
        if(authExam.getExamType().contains("定向"))
            MyActions.click(AddAuthExamTeaObject.examTyep_Ding, driver);//定向招生
        else if(authExam.getExamType().contains("统招"))
            MyActions.click(AddAuthExamTeaObject.examTyep_Tong, driver);//统一招
        //考试名称
        MyActions.sendText(AddAuthExamTeaObject.examNameText, authExam.getExamName(), driver);
        //考试级别
        MyActions.click(AddAuthExamTeaObject.examLevel, driver);
        //考试专业
        WebElement element = MyFind.findElement(AddAuthExamTeaObject.examSubjectZy,driver);
        Select select = new Select(element);
        select.selectByVisibleText(authExam.getExamSubjectZy());
        //考试科目
//        element = MyFind.findElement(AddAuthExamTeaObject.examSubjectId);
//        select = new Select(element);
//        select.selectByVisibleText(authExam.getExamSubjectId());
        //考试时间
          //去掉时间输入框的js限制
        MyActions.executeJS("document.getElementById(\"_startTime\").setAttribute(\"value\",'"+authExam.getStartTime()+"')",driver);
        MyActions.executeJS("document.getElementById(\"_endTime\").setAttribute(\"value\",'"+authExam.getEndTime()+"')",driver);

        //考场容量
        MyActions.sendText(AddAuthExamTeaObject.examCapacity,authExam.getExamCapacity(), driver);
        //考试地点
        MyActions.sendText(AddAuthExamTeaObject.examAddress,authExam.getExamAddress(), driver);

        //统招线下 缴费信息
        if(authExam.getExamType().equals("统招线下")){
            MyActions.sendText(AddAuthExamTeaObject.pay_contacts,authExam.getPay_contacts(), driver);//缴费联系人
            MyActions.sendText(AddAuthExamTeaObject.pay_mobile,String.valueOf(DateFormat.time()/100), driver);//联系人电话
            MyActions.sendText(AddAuthExamTeaObject.pay_address,authExam.getPay_address(), driver);//考生缴费地址
            //缴费截止时间
            MyActions.executeJS("document.getElementsByName(\"pay_endTime\")[0].setAttribute(\"value\",'"+authExam.getPay_endTime()+"')",driver);
        }

        //考试说明
            //控制权转交给富文本编辑框
            driver.switchTo().frame(MyFind.findElement(AddAuthExamTeaObject.remarkContainer,driver));
            //document.getElementsByTagName('body')[0]可以获取富本文编辑框的编辑区域对象
        MyActions.executeJS("document.getElementsByTagName('body')[0].innerHTML='"+authExam.getRemarkContainer()+"'",driver);
        driver.switchTo().defaultContent();

        //拖动滚动条到页面底部
         MyActions.pageBottom(driver);

        //照片管理   选择默认照片
//        MyActions.click(AddAuthExamTeaObject.uploadImgRadio_one);

        //提交
        Thread.sleep(1000);
        MyActions.click(AddAuthExamTeaObject.submitButton, driver);
        //判断提交成功的提示框 是否存在
        boolean b = MyActions.isDisplayed(AddAuthExamTeaObject.submitDailog,driver);
        log.info("老师-新建认证考试-提交成功提示框是否显示："+String.valueOf(b));
        MyAssert.assertTrue(b);
        //点击确定
        MyActions.click(AddAuthExamTeaObject.okButton, driver);
    }
}
