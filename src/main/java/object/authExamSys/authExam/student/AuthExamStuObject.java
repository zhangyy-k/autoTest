package object.authExamSys.authExam.student;

import org.openqa.selenium.By;

/**
 * 认证考试系统-学生-认证考试-页面元素对象
 *@Author zhangyy
 *@Date 2017-6-5 10:05
 */
public class AuthExamStuObject {

    //考试列表是否有数据
    public static By authExamDataRows = By.xpath(".//*[contains(text(),'表中数据为空')]");

    //根据考试名称  查询考试列表是否有该考试
    public static By authExamRow(String examName){
        return By.xpath(".//*[contains(text(),'"+examName+"')]");
    }

    //考试列表中 进入考试按钮
    public static By getEnterExamButton(String examName){
        return By.xpath(".//*[contains(text(),'"+examName+"')]/following-sibling::td[4]/a");
    }

    //倒计时p标签
    public static By countDownText = By.xpath(".//*[contains(text(),'倒计时')]");
    //倒计时页面  开始考试  按钮
    public static By examStartButton = By.id("btn1");

    //试卷名称
    public static By paperName = By.xpath(".//*[@id='question_view_1_1']/div/div/div/div[1]/lable");
    //考试页面 附件列表
    public static By attachmentTRows =By.xpath(".//*[@id='question_view_1_1']/div/div/div/div[2]/dl/dd");
    //根据附件列表 index 获取a标签
    public static By attachmentButton(int index){
        return By.xpath(".//*[@id='question_view_1_1']/div/div/div/div[2]/dl/dd["+index+"]/p/a");
    }
    //附件下载 提示框
    public static By downLoadDailog = By.id("alertTitle1");
    //提示框 确定 按钮
    public static By downLoadDailog_ok_button = By.xpath(".//*[contanis(text(),'确定')]");
    //获取附件下载地址
    public static By downLoadURL = By.xpath(".//*[@id='alertMessage']/textarea");

    //退出考试 按钮
    public static By exitExamButton = By.xpath(".//body/nav/div/div[2]/button[1]");
    //保存
    public static By saveExamButton = By.xpath(".//body/nav/div/div[2]/button[2]");
    //交卷
    public static By handExamButton = By.xpath(".//body/nav/div/div[2]/button[3]");

    //已经汇总成功，直接交卷  按钮
    public static By handExamDailogButton = By.id("btnSubmitAlertConfirm1");

    //交卷异常  提示框
    public static By handExamSucessfulExceptionDailog = By.id("examSuccessModal");
    //交卷异常 信息提示对话框 确定按钮
    public static By handExamSucessfulExceptionOkButton = By.id("examSuccessConfirm");

}
