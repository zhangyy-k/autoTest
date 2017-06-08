package object.webSys.authExam.student;

import org.openqa.selenium.By;

/**
 * 学生-认证考试-我的考试-页面元素对象
 *@Author zhangyy
 *@Date 2017-6-8 9:11
 */
public class MyAuthExamStuObject {

    //未结束考试tab
    public static By examingTab = By.xpath(".//*[@id='examtab']/li[1]");
    //已结束考试tab
    public static By endExamTab = By.xpath(".//*[@id='examtab']/li[2]");

    //没有考试记录
    public static By noExamData = By.xpath(".//*[contains(text(),'未检索到记录')]");

    //根据考试名称 获取考试行
    public static By getExamRow(String authExamName){
        return By.xpath(".//*[contains(text(),'"+authExamName+"')]");
    }
}
