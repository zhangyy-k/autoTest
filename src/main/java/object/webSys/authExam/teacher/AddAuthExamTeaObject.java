package object.webSys.authExam.teacher;

import org.openqa.selenium.By;

/**
 * 老师-认证考试-发布考试信息-新建考试-页面元素对象
 *@Author zhangyy
 *@Date 2017-4-24 10:51
 */
public class AddAuthExamTeaObject {

    //发布考试信息列表-新建考试按钮
    public static By addExamButton = By.xpath(".//*[@handler='addView']");


    //考试类型
    public static By examTyep_Ding = By.xpath(".//*[@value='DIRECTIONAL']");//定向招生类
    public static By examTyep_Tong = By.xpath(".//*[@value='UNIFIED']");//统一招生类

    //考试信息
    public static By examNameText = By.xpath(".//*[@name='title']"); //考试名称
    public static By examNameTextTrip = By.xpath(".//*[@name='title']/following-sibling::span");//考试名称 输入提示
    public static By examLevel = By.xpath(".//*[@id='level']/input"); //考试级别
    public static By examSubjectZy = By.name("subjectZyId");//考试专业
    public static By examSubjectId = By.name("subjectId");//考试科目
    public static String examStartTime = "_startTime";//考试时间 开始时间
    public static String examEndTime = "_endTime";//考试时间  结束时间
    public static By examCapacity = By.id("capacity");//考场容量
    public static By examAddress = By.xpath(".//*[@name='address']");//考试地点
    public static By examAddressTrip = By.xpath(".//*[@name='address']/following-sibling::span");//考试地点 输入提示

    //缴费信息
    public static By pay_contacts = By.name("pay_contacts");//缴费联系人
    public static By pay_mobile = By.name("pay_mobile");//联系人电话
    public static By pay_address = By.name("pay_address");//考生缴费地址
    public static String pay_endTime = "pay_endTime";//缴费截止时间
    public static By pay_endTimeInput = By.name("pay_endTime");//缴费截止时间

    //其他信息
    public static By remarkContainer = By.className("ke-edit-iframe");//考试说明
    public static By uploadImgRadio = By.xpath(".//*[@name='checkImg']");//照片管理  单选按钮  根据name定位，会得到2个
    public static By uploadImgRadio_one = By.xpath(".//*[@id='examInfoFormId']/ul/li[4]/div/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[1]/input");
    public static By uploadImgRadio_two = By.xpath(".//*[@id='examInfoFormId']/ul/li[4]/div/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td[2]/input");

    //照片管理
    public static By uploadImgButton = By.id("uploadImg-button");//选择图片上传
    public static By uploadImg = By.id("imgUrl");//上传图片显示

    //操作
    public static By previewButton = By.xpath(".//*[text()='预览']");
    public static By saveButton = By.xpath(".//*[text()='保存草稿']");
    public static By submitButton = By.xpath(".//*[text()='提交审核']");

    //提交后对话框
    public static By submitDailog = By.id("layui-layer1");

    //操作后 确定按钮
    public static By okButton = By.xpath(".//*[@id='layui-layer1']/div[3]/a");
    public static By okDiv = By.xpath(".//*[@id='layui-layer1']/div[2]");


}
