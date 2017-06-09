package task.webSys.authExam.student;

import com.glodon.autoframework.actions.MyActions;
import com.glodon.autoframework.logger.LoggerControler;
import com.glodon.autoframework.tools.MyAssert;
import entity.webSys.SignUpInfo;
import object.webSys.authExam.student.AuthExamSignUpStuObject;
import object.webSys.index.IndexObject;
import object.webSys.menu.MenuObject;
import org.openqa.selenium.WebDriver;
import task.webSys.login.WebLoginTask;
import task.webSys.menu.student.MenuStuTask;

import static com.glodon.autoframework.tools.MyDataBase.updateData;

/**
 * 学生-报名缴费-业务逻辑处理
 *@Author zhangyy
 *@Date 2017-6-5 9:07
 */
public class SignUpStuTask {
    static final LoggerControler log = LoggerControler.getLogger(SignUpStuTask.class);

    /**
     * 学生报名
     *@Author zhangyy
     *@Date 2017-6-6 14:57
     * @param stuUserMobil 学生登录账号
     * @param password 学生登录密码
     * @param authExamName 认证考试名称
     * @param signUpInfo 学生报名信息
     * @param driver 当前浏览器对象
     */
    public static void signUp(String stuUserMobil, String password, String authExamName, SignUpInfo signUpInfo, WebDriver driver) throws InterruptedException{
        //学生-登录-点击左侧“认证考试-认证报名”子菜单，进入报名页面-点击我要报名，进入详情报名页面
        MenuStuTask.signUpMenu(stuUserMobil,password,authExamName,driver);
        //修改报名信息
        editSignUpInfo(signUpInfo,driver);
        Thread.sleep(2000);
        //点击 提交报名信息 按钮
        MyActions.click(AuthExamSignUpStuObject.submitSignUp,driver);
        //判断 支付成功页面  是否跳转
        boolean b = MyActions.isDisplayed(AuthExamSignUpStuObject.paySucess,driver);
        log.info("学生:"+stuUserMobil+"-报名缴费成功提示是否跳转："+String.valueOf(b));
        MyAssert.assertTrue(b);
        driver.quit();
    }

    /**
     * 修改报名信息
     *@Author zhangyy
     *@Date 2017-6-7 16:56
     */
    public static void editSignUpInfo(SignUpInfo signUpInfo,WebDriver driver){
        //点击 修改 按钮
        MyActions.click(AuthExamSignUpStuObject.editSignUpButton,driver);
        //学生姓名
        MyActions.sendText(AuthExamSignUpStuObject.signUpName,signUpInfo.getStuName(),driver);
        //性别
        if(signUpInfo.getSex().equals("男"))
            MyActions.click(AuthExamSignUpStuObject.sexBoy,driver);
        else if(signUpInfo.getSex().equals("女"))
            MyActions.click(AuthExamSignUpStuObject.sexGirl,driver);
        //联系电话
        MyActions.sendText(AuthExamSignUpStuObject.signUpMobile,signUpInfo.getMobil(),driver);
        //身份证号
        MyActions.sendText(AuthExamSignUpStuObject.signUpIdentityCard,signUpInfo.getIdentity(),driver);
        //毕业时间
        MyActions.executeJS("document.getElementsByName(\"graduationDate\")[0].setAttribute(\"value\",'" + signUpInfo.getGraduationDate() + "')",driver);
        //所属单位
        MyActions.sendText(AuthExamSignUpStuObject.signUpCompany,signUpInfo.getCompany(),driver);
        //点击 确定 按钮
        MyActions.click(AuthExamSignUpStuObject.OKSignUp,driver);
        //判断 照片确认提示框 是否存在
        boolean b = MyActions.isDisplayed(AuthExamSignUpStuObject.imageSureDialog,driver);
        log.info("学生:"+signUpInfo.getMobil()+"报名照片提示框是否存在："+String.valueOf(b));
        MyAssert.assertTrue(b);
        //点击 提示框  确认按钮
        MyActions.click(AuthExamSignUpStuObject.dailog_ok_superman,driver);
    }


    /**
     * 更新缴费状态（更新为已缴费）
     *@Author zhangyy
     *@Date 2017-6-5 9:08
     * @param authExamName  考试名称
     * @param mobil 考生手机号
     */
    public static void updataSignUpState(String authExamName,String mobil){
        int result = 0;
        String sql = " UPDATE au_register d,au_exam_order e set d.is_pay = 1, e.is_pay = 1 " +
              " WHERE d.order_id = e.id AND d.id in ( " +
              " SELECT c.id FROM " +
              " (SELECT b.id from au_register_info a LEFT JOIN au_register b on a.id = b.register_no " +
              " where a.exam_name = '"+authExamName+"' and a.mobile = '"+mobil+"') c) ";
        try{
            result = updateData(sql);
            log.info("更新学生:"+mobil+"缴费状态影响行数"+result);
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
