package object.webSys.authExam.superman;

import org.openqa.selenium.By;

/**
 * 老师-认证考试-缴费查看-页面元素对象
 *@Author zhangyy
 *@Date 2017-6-12 21:36
 */
public class PayLookSuperObject {

    ///////////////////////////////////////////////////统招类tab//////////////////////////////////////////////////////////////
    //统招类
    public static By tongFormTab = By.xpath(".//*[@id='examtab']/li[1]");
    //未支付
    public static By tongNoPayTab = By.xpath(".//*[@id='tylevel']/ul/li[1]");
    //已支付
    public static By tongPayTab = By.xpath(".//*[@id='tylevel']/ul/li[2]");

    ///////////统招-未支付////////////////

    //根据考试名称获取 查看 按钮
    public static By getLookPayDetailButton(String authExamName){
        return By.xpath(".//*[contains(text(),'"+authExamName+"')]/parent::td/following-sibling::td[10]/a");
    }

    //汇款详情页  确认收到汇款 按钮
    public static By sureGetPayButton = By.xpath(".//*[contains(text(),'确认收到汇款')]");
    //确认收到汇款 提示框
    public static By sureGetPayDailog = By.xpath(".//*[contains(text(),'确认已经收到该校汇款？')]");
    //弹出框 中 输入管理员密码
    public static By upwdText = By.id("upwd");
    //弹出框 确定按钮
    public static By sureGetPayDailog_yes_button = By.xpath(".//a[@class='layui-layer-btn0']");
    //弹出框 取消按钮
    public static By sureGetPayDailog_no_button = By.xpath(".//a[@class='layui-layer-btn1']");

    //确认收款成功 提示框
    public static By sureGetPaySuccessDailog = By.xpath(".//*[contains(text(),'确认收款成功')]");
    //确认收款成功 提示框 确定按钮
    public static By sureGetPaySuccessDailog_yes_button = By.xpath(".//a[@class='layui-layer-btn0']");

    //已收到汇款 按钮
    public static By getPayedButton = By.xpath(".//*[contains(text(),'已收到汇款')]");

    ///////////统招-已完成支付////////////////
    //考试名称行
    public static By getPayedAuthExamRow(String authExamName){
        return By.xpath(".//*[contains(text(),'"+authExamName+"')]");
    }

    ///////////////////////////////////////////////////定向类tab//////////////////////////////////////////////////////////////

    //定向类
    public static By dingFormTab = By.xpath(".//*[@id='examtab']/li[2]");
    //支付中
    public static By dingPayingTab = By.xpath(".//*[@id='dxlevel']/ul/li[1]");
    //支付结束
    public static By dingPayedTab = By.xpath(".//*[@id='dxlevel']/ul/li[2]");


    ///////////////////////////////////////////////////大赛考试tab//////////////////////////////////////////////////////////////

    //大赛考试
    public static By dsFormTab = By.xpath(".//*[@id='examtab']/li[3]");
}
