package object.webSys.authExam.teacher;

import org.openqa.selenium.By;

/**
 * 老师-认证考试-报名缴费管理-页面元素对象
 *@Author zhangyy
 *@Date 2017-6-12 20:37
 */
public class SignUpPayTeaObject {

    //未完成缴费
    public static By noPayTab = By.xpath(".//*[@id='examtab']/li[1]");
    //已完成缴费
    public static By payedTab = By.xpath(".//*[@id='examtab']/li[2]");
    //已完成缴费
    public static By forGlodonFormTab = By.xpath(".//*[@id='examtab']/li[3]");

    ///////////////////////////////////////////////////未完成缴费tab//////////////////////////////////////////////////////////////
    //根据考试名称，获取 缴费明细 按钮
    public static By getPayDetailButton(String authExamName){
        return By.xpath(".//*[contains(text(),'"+authExamName+"')]/parent::td/following-sibling::td[8]/a");
    }

    //缴费明细 详情页面
    public static By noPayData = By.xpath(".//*[contains(text(),'未找到结果')]");

    //考试基本信息  缴费截止日期
    public static By authExamPayEndTime = By.xpath(".//*[contains(text(),'缴费截止时间')]/parent::tr/parent::thead/following-sibling::tbody/tr/td[10]");


    //获取缴费详情 缴费学生rows
    public static By payStuRows = By.xpath(".//*[@id='viewApplyList']/table/tbody/tr");
    //根据rows的index获取 学生名称
    public static By getStuName(int index){
        return By.xpath(".//*[@id='viewApplyList']/table/tbody/tr["+index+"]/td[2]");
    }
    //根据rows的index获取 缴费状态
    public static By getPayState(int index){
        return By.xpath(".//*[@id='viewApplyList']/table/tbody/tr["+index+"]/td[6]");
    }
    //根据rows的index获取 确认缴费按钮
    public static By getSurePayButton(int index){
        return By.xpath(".//*[@id='viewApplyList']/table/tbody/tr["+index+"]/td[8]/a");
    }
    //根据rows的index获取 退费按钮
    public static By getReturnPayButton(int index){
        return By.xpath(".//*[@id='viewApplyList']/table/tbody/tr["+index+"]/td[8]/a");
    }

    //确认该考生已缴费提示框 确定按钮
    public static By surePayDailog_ok_button = By.xpath(".//a[@class='layui-layer-btn0']");
    //缴费成功 提示框
    public static By paySuccessDailog = By.xpath(".//*[contains(text(),'缴费成功')]");
    //缴费成功 提示框 确认按钮
    public static By paySuccessDailog_ok_button = By.xpath(".//a[@class='layui-layer-btn0']");

    //线上支付 按钮
    public static By onLineButton = By.xpath(".//*[contains(text(),'线上支付')]");
    //线下支付 按钮
    public static By underLineButton = By.xpath(".//*[contains(text(),'线下支付')]");


    ///////////////////////////////////////////////////需给广联达汇款tab//////////////////////////////////////////////////////////////
    //根据考试名称 获取 线上支付 按钮
    public static By getOnLinePayButton(String authExamName){
        return By.xpath(".//*[contains(text(),'"+authExamName+"')]/parent::td/following-sibling::td[8]/a[1]");
    }
    //根据考试名称 获取 线下支付 按钮
    public static By getUnderLinePayButton(String authExamName){
        return By.xpath(".//*[contains(text(),'"+authExamName+"')]/parent::td/following-sibling::td[8]/a[2]");
    }

    //根据考试名称 获取 已支付 文本
    public static By getPayedText(String authExamName){
        return By.xpath(".//*[contains(text(),'"+authExamName+"')]/parent::td/following-sibling::td[8]");
    }

    //汇款页面 汇款人姓名
    public static By payNameText = By.name("payName");
    //汇款页面 汇款单位
    public static By payCompanyText = By.name("payCompany");
    //汇款页面 汇款单号
    public static By payIdText = By.name("payId");
    //汇款页面 汇款日期
    public static String payTimeText = "payTime";
    //汇款页面 汇款照片
    public static String payImageID = "attrName";
    public static By payImageText = By.id("attrName");
    //提交按钮
    public static By payForGlodonSubmitBtn = By.xpath(".//*[contains(text(),'提交信息')]");

    //汇款成功 提示框
    public static By payForGlodonSuccessDailog = By.xpath(".//*[contains(text(),'保存成功')]");
    //汇款成功 提示框 确认按钮
    public static By payForGlodonSuccessDailog_ok_button = By.xpath(".//a[@class='layui-layer-btn0']");
}
