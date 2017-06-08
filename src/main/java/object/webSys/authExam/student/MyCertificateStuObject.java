package object.webSys.authExam.student;

import org.openqa.selenium.By;

/**
 * 学生-认证考试-我的证书-页面元素对象
 *@Author zhangyy
 *@Date 2017-6-5 16:29
 */
public class MyCertificateStuObject {

    //根据证书编号 获取证书行
    public static By getCertificateText(String certificateCode){
        return By.xpath(".//td[contains(text(),'"+certificateCode+"')]");
    }
}
