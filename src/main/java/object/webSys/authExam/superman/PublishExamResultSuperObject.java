package object.webSys.authExam.superman;

import org.apache.xmlbeans.impl.xb.xsdschema.Public;
import org.openqa.selenium.By;

/**
 * 管理员-认证考试-发布考试结果-页面元素对象
 *@Author zhangyy
 *@Date 2017-6-5 13:56
 */
public class PublishExamResultSuperObject {

    //认证考试-页签
    public static By authExamTab = By.xpath(".//*[@id='examTab0']/li[1]");
    //认证大赛-页签
    public static By authMathTab = By.xpath(".//*[@id='examTab0']/li[2]");
    //未发布
    public static By noPublishTab = By.xpath(".//*[@id='examTab1']/li[1]");
    //已发布
    public static By yesPublishTab = By.xpath(".//*[@id='examTab1']/li[2]");

    //列表中   查看成绩 按钮
    public static By getlookScoreButton(String examName){
        return By.xpath(".//*[contains(text(),'"+examName+"')]/parent::td/following-sibling::td[6]/input");
    }

    //详情页面  发布级别按钮
    public static By publishButton = By.xpath(".//*[@id='examResultForm']/div[1]/span[1]");
    //确定发布级别提示框  确定按钮
    public static By publishButtonDailog_ok = By.xpath(".//a[@class='layui-layer-btn0']");
    //发布级别 成功提示对话框
    public static By publishiSuccessDailog = By.xpath(".//*[contains(text(),'发布级别成功')]");
    //发布级别成功提示 确定按钮
    public static By publishButtonSuccessDailog_ok = By.xpath(".//a[@class='layui-layer-btn0']");

    //修改考试成绩 修改按钮
    public static By getEditScoreButton(String userMobil){
        return By.xpath(".//*[contains(text(),'"+userMobil+"')]/following-sibling::td[5]/a");
    }
    //弹框中 修改分数  输入框
    public static By newScoreText = By.name("newScore");
    //弹框中 确定 按钮
    public static By editScoreDailog_ok = By.xpath(".//a[@class='layui-layer-btn0']");
    //修改成功提示 确定按钮
    public static By editScoreSuccessDailog_ok = By.xpath(".//a[@class='layui-layer-btn0']");

}
