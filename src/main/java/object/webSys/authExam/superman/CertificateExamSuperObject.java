package object.webSys.authExam.superman;

import org.openqa.selenium.By;

/**
 * 管理员-证书管理-证书颁发-页面元素对象
 *@Author zhangyy
 *@Date 2017-6-5 16:01
 */
public class CertificateExamSuperObject {

    //未颁发
    public static By noCertificateTab = By.xpath(".//*[@id='examtab']/li[1]");
    //已颁发
    public static By yesCertificateTab = By.xpath(".//*[@id='examtab']/li[2]");

    //列表中   查看详情 按钮
    public static By getlookDatailButton(String examName){
        return By.xpath(".//*[contains(text(),'"+examName+"')]/following-sibling::td[5]/input");
    }

    //生成证书 按钮
    public static By certificateButton = By.xpath(".//*[contains(text(),'生成证书')]");
    //证书生成一次提示框
    public static By certtificatDailog = By.xpath(".//*[contains(text(),'证书只能生成一次，确定现在要生成证书？')]");
    //确认生成证书提示框 确定按钮
    public static By certificateDailog_yes = By.xpath(".//a[@class='layui-layer-btn0']");
    //确认生成证书提示框 取消按钮
    public static By certificateDailog_no = By.xpath(".//a[@class='layui-layer-btn1']");

    //考生详情列表 是否有数据
    public static By noPassStuDataText = By.xpath(".//*[contains(text(),'未找到结果')]");
    //考生详情列表行数
    public static By passStuTRCounts = By.xpath(".//*[@id='passExamUserList']/table/tbody/tr");
    //获取 考生详情列表 某行姓名
    public static By getPassStrTRName(int index) {
        return By.xpath(".//*[@id='passExamUserList']/table/tbody/tr["+index+"]/td[2]");
    }
    //获取 考生详情列表 某行手机号
    public static By getPassStrTRMobil(int index) {
        return By.xpath(".//*[@id='passExamUserList']/table/tbody/tr["+index+"]/td[3]");
    }
    //获取 考生详情列表 某行证书编号
    public static By getPassStrTRCertificateCode(int index) {
        return By.xpath(".//*[@id='passExamUserList']/table/tbody/tr["+index+"]/td[8]");
    }
    //根据考生手机号 获取证书编号
    public static By getCertificateCodeText(String userMobil) {
        return By.xpath(".//*[contains(text(),'"+userMobil+"')]/following-sibling::td[5]");
    }
}
