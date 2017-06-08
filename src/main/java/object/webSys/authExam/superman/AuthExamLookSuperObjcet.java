package object.webSys.authExam.superman;

import org.openqa.selenium.By;

/**
 * 管理员-认证考试-考试查看-页面元素对象
 *@Author zhangyy
 *@Date 2017-6-5 16:02
 */
public class AuthExamLookSuperObjcet {
    //考试列表中 审核按钮
    public static By getPublishiButton_superman(String examName){
        return By.xpath(".//*[contains(text(),'"+examName+"')]/parent::td/following-sibling::td[5]/input[2]");
    }

    //弹框中  是
    public static By dailog_yes_superman = By.xpath(".//a[@class='layui-layer-btn0']");
    //弹框中  否
    public static By dailog_no_superman = By.xpath(".//a[@class='layui-layer-btn1']");

    //审核成功 提示框
    public static By daiog_sucessful = By.xpath(".//*[contains(text(),'审核成功')]");
    //点击是后  确定
    public static By dailog_ok_superman = By.xpath(".//a[@class='layui-layer-btn0']");

}
