package object.webSys.menu;

import org.openqa.selenium.By;

/**
 * web端登录成功-我的中心-左侧菜单-页面元素对象
 *@Author zhangyy
 *@Date 2017-4-24 10:53
 */
public class MenuObject {
    //一级菜单
    public static By item_authExam = By.xpath(".//*[text()='认证考试']");
    public static By item_certificate = By.xpath(".//*[text()='证书管理']");

    //二级菜单

    //老师角色：认证考试-发布考试信息
    public static By menuItem_publishExamInfo_teacher = By.xpath(".//*[text()='发布考试信息']");

    //管理员角色：认证考试-考试查看
    public static By menuItem_examLook_superman = By.xpath(".//*[text()='考试查看']");
    //管理员角色：认证考试-发布考试结果
    public static By menuItem_publishExamResult_superman = By.xpath(".//*[text()='发布考试结果']");
    //管理员角色：认证考试-发布考试结果
    public static By menuItem_lookExamMatter_superman = By.xpath(".//*[text()='查看考试情况']");
    //管理员角色：证书管理-证书颁发
    public static By menuItem_certificateExam_superman = By.xpath(".//*[text()='证书颁发']");

    //学生角色：认证考试-认证报名
    public static By menuItem_examSignUp_student = By.xpath(".//*[text()='认证报名']");
    //学生角色：认证考试-我的考试
    public static By menuItem_myExam_student = By.xpath(".//*[text()='我的考试']");
    //学生角色：认证考试-我的证书
    public static By menuItem_certificate_student = By.xpath(".//*[text()='我的证书']");
}
