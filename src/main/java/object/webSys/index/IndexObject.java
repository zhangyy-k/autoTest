package object.webSys.index;

import org.openqa.selenium.By;

/**
 * 登录成功-index页面元素对象
 *@Author zhangyy
 *@Date 2017-4-24 10:52
 */
public class IndexObject {

    //头部菜单栏

    public static By myCenterButton = By.xpath(".//*[text()='我的中心']");//index页面，"我的中心"菜单
    public static By topMenuCprzButton = By.xpath(".//*[text()='测评认证']");//index页面，顶部菜单-测评认证

    public static By topItemMenuSerachCertificateButton = By.xpath(".//*[contains(text(),'证书查询')]");//index页面，顶部菜单-测评认证-证书查询

    //证书查询 输入信息
    public static By identityText = By.name("identity");//身份证号
    public static By userName = By.name("userName");//姓名
    public static By certificaetCode = By.name("number");//证书编号
    public static By searcheButton = By.xpath(".//*[contains(text(),'查 询')]");//查询按钮

    public static By noCertificateTrip = By.xpath(".//*[contains(text(),'抱歉！暂未查到此证书信息！')]");//没有证书信息
    //根据 证书编号 查询 是否有查询结果
    public static By getSearchResutl(String certificateCode){
        return By.xpath(".//*[contains(text(),'"+certificateCode+"')]");
    }


}
