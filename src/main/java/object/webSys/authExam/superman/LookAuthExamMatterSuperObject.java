package object.webSys.authExam.superman;

import org.openqa.selenium.By;

/**
 * 管理员-认证考试-查看考试情况-页面元素对象
 *@Author zhangyy
 *@Date 2017-6-7 14:23
 */
public class LookAuthExamMatterSuperObject {
    //正在进行的考试tab
    public static By examingTab = By.xpath(".//*[@id='examtab']/li[1]");
    //已经结束的考试tab
    public static By examEndTab = By.xpath(".//*[@id='examtab']/li[2]");
    //考试延时审核tab
    public static By delayExamingTab = By.xpath(".//*[@id='examtab']/li[3]");

    //认证考试tab
    public static By authExamTab = By.xpath(".//*[@id='examTab1']/li[1]");
    //认证大赛tab
    public static By authMathTab = By.xpath(".//*[@id='examTab1']/li[2]");

    ////////////////////////////////////////////////////正在进行的考试-认证考试//////////////////////////////////////////

    //正在进行的考试列表 查看 按钮
    public static By getLookExamingButton(String authExamName){
        return By.xpath(".//*[contains(text(),'"+authExamName+"')]/parent::td/following-sibling::td[6]/input");
    }

    //考生详情列表 是否有数据
    public static By noEnterExamStuDataText = By.xpath(".//*[contains(text(),'未找到结果')]");
    //考生详情列表行数
    public static By enterExamStuTRCounts = By.xpath(".//*[@id='examingStuList']/table/tbody/tr");
    //获取 考生详情列表 某行手机号
    public static By getEnterExamStuTRMobil(int index) {
        return By.xpath(".//*[@id='examingStuList']/table/tbody/tr["+index+"]/td[4]");
    }
    //获取 考生详情列表 某行成绩
    public static By getEnterExamStuTRScore(int index) {
        return By.xpath(".//*[@id='examingStuList']/table/tbody/tr["+index+"]/td[6]");
    }
    //获取 考生详情列表 某行交卷时间
    public static By getEnterExamStuTRHandTime(int index) {
        return By.xpath(".//*[@id='examingStuList']/table/tbody/tr["+index+"]/td[7]");
    }

    ///////////////////////////////////////////////////////////已结束的考试/////////////////////////////////////////

    //已结束的考试列表 查看 按钮
    public static By getLookExamEndButton(String authExamName){
        return By.xpath(".//*[contains(text(),'"+authExamName+"')]/parent::td/following-sibling::td[7]/input");
    }

    ///////////////////////查看详情页面
    //未通过考试tab
    public static By unpassTab = By.xpath(".//*[@id='examtab']/li[1]");
    //已通过考试tab
    public static By passTab = By.xpath(".//*[@id='examtab']/li[2]");

    //未通过考生详情列表行数
    public static By unpassStuTRCounts = By.xpath(".//*[@id='unpassedExamList']/table/tbody/tr");
    //未通过考生详情列表  获取 考生 某行考试姓名
    public static By getunpassStuTRName(int index) {
        return By.xpath(".//*[@id='unpassedExamList']/table/tbody/tr["+index+"]/td[2]");
    }
    //未通过考生详情列表  获取 考生 某行成绩
    public static By getunpassStuTRScore(int index) {
        return By.xpath(".//*[@id='unpassedExamList']/table/tbody/tr["+index+"]/td[5]");
    }
    //未通过考生详情列表  获取 考生 某行考试结果
    public static By getunpassStuTRResult(int index) {
        return By.xpath(".//*[@id='unpassedExamList']/table/tbody/tr["+index+"]/td[7]");
    }

    //通过考生详情列表行数
    public static By passStuTRCounts = By.xpath(".//*[@id='passedExamList']/table/tbody/tr");
    //通过考生详情列表  获取 考生 某行考试姓名
    public static By getpassStuTRName(int index) {
        return By.xpath(".//*[@id='passedExamList']/table/tbody/tr["+index+"]/td[2]");
    }
    //通过考生详情列表  获取 考生 某行成绩
    public static By getpassStuTRScore(int index) {
        return By.xpath(".//*[@id='passedExamList']/table/tbody/tr["+index+"]/td[5]");
    }
    //通过考生详情列表  获取 考生 某行考试级别
    public static By getpassStuTRLevel(int index) {
        return By.xpath(".//*[@id='passedExamList']/table/tbody/tr["+index+"]/td[6]");
    }
}
