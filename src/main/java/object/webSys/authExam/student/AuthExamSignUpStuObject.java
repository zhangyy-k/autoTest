package object.webSys.authExam.student;

import org.openqa.selenium.By;

/**
 * 学生-认证考试-认证报名-页面元素对象
 * Created by Administrator on 2017-6-2.
 */
public class AuthExamSignUpStuObject {

    //定向页签
    public static By directExamTab = By.xpath(".//*[@id='j_slideico']/li[1]");

    //统招页签
    public static By entranExamTab = By.xpath(".//*[@id='j_slideico']/li[2]");

    //报名列表中 我要报名  按钮
    public static By getSignUpButton(String examName){
        return By.xpath(".//*[contains(text(),'"+examName+"')]/parent::dd/parent::dl/following-sibling::div/p/a");
    }

    //详情页面中 我要报名 按钮
    public static By signUpButtonDetail = By.id("signUp");

    //报名 修改 按钮
    public static By editSignUpButton = By.xpath(".//*[text()='修改']");

    //姓名
    public static By signUpName = By.id("fullname");
    //性别
    public static By sexBoy = By.xpath(".//*[@id='genderTD']/input[1]");  //男
    public static By sexGirl = By.xpath(".//*[@id='genderTD']/input[2]"); //女
    //联系电话
    public static By signUpMobile = By.id("mobile");
    //身份证
    public static By signUpIdentityCard = By.id("identityCard");
    //毕业时间
    public static By signUpGraduationDate = By.xpath(".//*[@id='graduationDateTD']/input");
    //所属单位
    public static By signUpCompany = By.name("company");

    //修改后  确定 按钮
    public static By OKSignUp = By.xpath(".//*[text()='确定']");
    //弹出 照片确认 提示框
    public static By imageSureDialog = By.xpath(".//*[contains(text(),'请确认照片为标准一寸蓝底证件照')]");
    //点击确定后弹框中  确定 按钮
    public static By dailog_ok_superman = By.xpath(".//a[@class='layui-layer-btn0']");

    //提交报名  按钮
    public static By submitSignUp = By.xpath(".//*[text()='提交报名信息']");

    //支付成功提示
    public static By paySucess = By.xpath(".//*[@id='contentDetail']/p[1]");
}
