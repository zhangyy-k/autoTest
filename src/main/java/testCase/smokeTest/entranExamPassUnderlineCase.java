package testCase.smokeTest;

import com.glodon.autoframework.driver.MyDriver;
import com.glodon.autoframework.logger.LoggerControler;
import com.glodon.autoframework.testng.interfaces.WebDriverHost;
import com.glodon.autoframework.tools.DateFormat;
import com.glodon.autoframework.tools.RandomNum;
import entity.webSys.AuthExam;
import entity.webSys.PayForGlodonInfo;
import entity.webSys.SignUpInfo;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import ru.yandex.qatools.allure.annotations.Description;
import task.authExamSys.authExam.student.AuthExamStuTask;
import task.webSys.authExam.student.MyCertificateStuTask;
import task.webSys.authExam.student.SignUpStuTask;
import task.webSys.authExam.superman.*;
import task.webSys.authExam.teacher.AddAuthExamTeaTask;
import task.webSys.authExam.teacher.SignUpPayTeaTask;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.glodon.autoframework.tools.DateFormat.DEFAULT_DATE_FORMAT;

/**
 * 冒烟测试-统招线下类考试--测试脚本(报名缴费，考试通过)
 *@Author zhangyy
 *@Date 2017-6-13 10:11
 */
public class entranExamPassUnderlineCase implements WebDriverHost {
    static final LoggerControler log = LoggerControler.getLogger(entranExamPassUnderlineCase.class);

    private MyDriver myDriver = new MyDriver();
    //web端测试地址
    private String testURL = myDriver.testURL;
    //当前浏览器对象
    private WebDriver driver = null;
    //认证考试测试数据实体
    private AuthExam authExam = null;
    //认证考试考试名称
    private String authExamName = null;
    //认证考试开始时间和结束时间
    Date authExamStartTime = null , authExamEndTime = null;
    //倒计时等待时间（毫秒）
    long sleepTime = -1;
    //老师账号
    String teaUserName = null,teaUserPwd=null;
    //管理员账号
    String supernamUserName=null,superUserPwd=null;
    //学生账号及密码
    Map<String,String> stuUsers = null;
    Map<String,String> stuUsersNew = null;
    //学生账号及修改成绩
    Map<String,Double > stuUsersEditScore = null;
    //报名付款信息判断
    Map<String,String> payInfo = null;

    @Override
    public WebDriver getWebDriver() {
        return driver;
    }

    @BeforeMethod
    public void initTestData(){
        //认证考试测试数据
        authExam = authExamInfo();
        //重置 考试名称
        authExamName = authExam.getExamName() + DateFormat.time();
        authExam.setExamName(authExamName);
        //老师账号和密码
        teaUserName = "15662421884";teaUserPwd="123456";
        //管理员账号和密码
        supernamUserName="superman";superUserPwd="123456";
        //学生账号和密码
        stuUsers = new HashMap<>();
        stuUsers.put("10000000051","123456");
        stuUsers.put("10000000052","123456");
        stuUsers.put("10000000053","123456");
        //学生新账号
        stuUsersNew = new HashMap<>();
        //学生账号及修改成绩
        stuUsersEditScore = new HashMap<>();
        stuUsersEditScore.put("10000000051",70.0);
        stuUsersEditScore.put("10000000052",80.5);
        stuUsersEditScore.put("10000000053",90.5);
    }

    @Test
    @Description("冒烟测试-统招线下（新建考试-审核通过-报名-确认缴费-参加考试-发布成绩-给广联达汇款-颁发证书-查看证书）")
    @Parameters({"browser"})
    public void entranExamPassOnlineTest(String browser) throws MalformedURLException,InterruptedException{
        log.infoStart("冒烟测试-统招线下（新建考试-审核通过-报名-确认缴费-参加考试-发布成绩-给广联达汇款-颁发证书-查看证书）");
        log.info("当前浏览器为："+browser);

        //老师-登录-发布认证考试
        log.infoStart("老师-登录-发布认证考试");
        driver = myDriver.openBrowser(browser);driver.get(testURL);
        AddAuthExamTeaTask.publishAuthExam(teaUserName,teaUserPwd,authExam,driver);
        log.infoEnd("老师-登录-发布认证考试");

        //管理员-登录-审核认证考试-通过
        log.infoStart("管理员-登录-审核认证考试-通过");
        driver = myDriver.openBrowser(browser);driver.get(testURL);
        AuthExamLookSuperTask.passAuthExam(supernamUserName,superUserPwd,authExamName,driver);
        log.infoEnd("管理员-登录-审核认证考试-通过");

        //获取考试开始时间和结束时间
        List<Object[]> list = AuthExamStuTask.getAuthExamTime(authExamName);
        if(list.size() > 0){
            //考试开始时间
            authExamStartTime = DateFormat.format(DEFAULT_DATE_FORMAT, list.get(0)[0].toString());
            //考试结束时间
            authExamEndTime = DateFormat.format(DEFAULT_DATE_FORMAT, list.get(0)[1].toString());
            log.info("考试："+authExamName+"；开始时间："+String.valueOf(authExamStartTime)+";结束时间："+String.valueOf(authExamEndTime));
        }else{
            log.info("没有查询到认证考试的开始时间和结束时间");
        }

        //遍历学生账号和密码  登录-报名
        if(stuUsers != null) {
            for (Map.Entry<String, String> entry : stuUsers.entrySet()) {
                //学生-登录-报名
                log.infoStart("学生:"+entry.getKey()+";报名缴费");
                driver = myDriver.openBrowser(browser);
                driver.get(testURL);
                SignUpInfo signUpInfo = signUpInfo();
                SignUpStuTask.signUpUnderlinePay(entry.getKey(), entry.getValue(), authExamName,signUpInfo,payInfo,driver);

                //更新 学生账号及修改成绩Map集合中 学生账号
                if(stuUsersEditScore.containsKey(entry.getKey()))
                    stuUsersEditScore.put(signUpInfo.getMobil(),stuUsersEditScore.remove(entry.getKey()));
                //初始化 学生新账号Map集合中 学生账号
                stuUsersNew.put(signUpInfo.getMobil(),"123456");
                log.info("学生："+entry.getKey()+"报名手机为："+signUpInfo.getMobil());
                log.infoEnd("学生:"+entry.getKey()+";报名缴费");
            }
        }else{
            log.info("学生账号集合为空");
        }

        //老师-登录-确认缴费
        log.infoStart("老师-登录-确认缴费");
        driver = myDriver.openBrowser(browser);driver.get(testURL);
        SignUpPayTeaTask.sureStuPay(teaUserName,teaUserPwd,authExamName,driver);
        log.infoEnd("老师-登录-确认缴费");

        //学生-登录考试系统-考试
        if(stuUsersNew != null){
            for(Map.Entry<String,String> entry : stuUsersNew.entrySet()){
                log.infoStart("学生:"+entry.getKey()+";登录考试系统开始考试");
                driver = myDriver.openBrowser("authBrowserWin7");
                AuthExamStuTask.enterAuthExam(entry.getKey(), entry.getValue(),authExamName,driver);
                log.infoEnd("学生:"+entry.getKey()+";登录考试系统开始考试");
            }
        }else{
            log.info("学生账号集合为空");
        }

        //管理员-查看考试情况-正在进行的考试
        log.infoStart("管理员-登录-查看考试情况-正在进行的考试");
        driver = myDriver.openBrowser(browser);driver.get(testURL);
        LookAuthExamMatterSuperTask.lookExamingMatter(supernamUserName,superUserPwd,authExamName,driver);
        log.infoEnd("管理员-登录-查看考试情况-正在进行的考试");

        //考试结束-倒计时-处理
        sleepTime = -1;
        sleepTime = DateFormat.timeDifference(authExamEndTime);
        if(sleepTime > 0){
            log.info("距离考试结束还有"+String.valueOf(sleepTime)+"毫秒，请等待……");
            Thread.sleep(sleepTime);
            log.info("考试结束");
        }

        //管理员-查看考试情况-已结束的考试
        log.infoStart("管理员-登录-查看考试情况-已结束的考试");
        driver = myDriver.openBrowser(browser);driver.get(testURL);
        LookAuthExamMatterSuperTask.lookExamEndMatter(supernamUserName,superUserPwd,authExamName,driver);
        log.infoEnd("管理员-登录-查看考试情况-已结束的考试");

        //管理员-登录-发布考试结果及修改考生成绩
        log.infoStart("管理员-登录-发布考试结果及修改考生成绩");
        driver = myDriver.openBrowser(browser);driver.get(testURL);
        PublishExamResultSuperTask.publishExamResult(supernamUserName,superUserPwd,authExamName,stuUsersEditScore,driver);
        log.infoEnd("管理员-登录-发布考试结果及修改考生成绩");

        //老师-登录-给广联达汇款
        log.infoStart("老师-登录-给广联达汇款");
        driver = myDriver.openBrowser(browser);driver.get(testURL);
        PayForGlodonInfo payForGlodonInfo = payForGlodonInfo();
        SignUpPayTeaTask.payForGlodon(teaUserName,teaUserPwd,authExamName,payForGlodonInfo,driver);
        log.infoEnd("老师-登录-给广联达汇款");

        //管理员-登录-确认收款
        log.infoStart("管理员-登录-确认收款");
        driver = myDriver.openBrowser(browser);driver.get(testURL);
        PayLookSuperTask.sureGetPay(supernamUserName,superUserPwd,authExamName,driver);
        log.infoEnd("管理员-登录-确认收款");


        //管理员-登录-证书颁发
        log.infoStart("管理员-登录-证书颁发");
        driver = myDriver.openBrowser(browser);driver.get(testURL);
        Map<String,String> passStu = CertificateExamSuperTask.certificateExam(supernamUserName,superUserPwd,authExamName,driver);
        log.infoEnd("管理员-登录-证书颁发");

        //学生-登录-我的证书
        String certificateCode = null;
        if(passStu != null){
            for(Map.Entry<String,String> entry : passStu.entrySet()){
                log.infoStart("学生:"+entry.getKey()+";查看我的证书");
                driver = myDriver.openBrowser(browser);driver.get(testURL);
                MyCertificateStuTask.lookMyCertificate(entry.getKey(),"123456",entry.getValue(),driver);
                log.infoEnd("学生:"+entry.getKey()+";查看我的证书");
            }
        }else{
            log.info("没有考试合格的考生");
        }

        log.infoEnd("冒烟测试-统招线下（新建考试-审核通过-报名-确认缴费-参加考试-发布成绩-给广联达汇款-颁发证书-查看证书）");

    }

    //封装冒烟测试-统招线上考试数据
    private AuthExam authExamInfo(){
        AuthExam authExam = new AuthExam();
        authExam.setExamType("统招线下");
        authExam.setExamName("统下自动化3-");
        authExam.setExamSubjectZy("土建");
        authExam.setStartTime(DateFormat.formatCalendar(DEFAULT_DATE_FORMAT,4));
        authExam.setEndTime(DateFormat.formatCalendar(DEFAULT_DATE_FORMAT,7));
        authExam.setExamCapacity("60");
        authExam.setExamAddress("济南广联达济南广联达");
        authExam.setRemarkContainer("自动化测试测试");

        String pay_contacts = "小布丁";
        String pay_mobile = String.valueOf(DateFormat.time()/100);
        String pay_endTime = DateFormat.formatCalendar(DEFAULT_DATE_FORMAT,60);
        pay_endTime = pay_endTime.replace(pay_endTime.substring(17,19),"00");
        String pay_address = "广联达教育培训";
        authExam.setPay_contacts(pay_contacts);
        authExam.setPay_mobile(pay_mobile);
        authExam.setPay_endTime(pay_endTime);
        authExam.setPay_address(pay_address);
        //报名付款信息
        payInfo = new HashMap<>();
        payInfo.put("联系人：","联系人： "+pay_contacts);
        payInfo.put("联系电话：","联系电话："+pay_mobile);
        payInfo.put("缴费地址：","缴费地址："+pay_address);
        payInfo.put("缴费截止日期：","缴费截止日期："+pay_endTime);

        log.info("联系人： "+pay_contacts);
        log.info("联系电话："+pay_mobile);
        log.info("缴费地址："+pay_address);
        log.info("缴费截止日期："+pay_endTime);
        return authExam;
    }

    //封装学生报名信息
    private SignUpInfo signUpInfo(){
        SignUpInfo signUpInfo = new SignUpInfo();
        signUpInfo.setStuName("学生"+ RandomNum.getStringRandom(3));
        signUpInfo.setSex("女");
        signUpInfo.setMobil(String.valueOf(DateFormat.time()/100));
        signUpInfo.setIdentity("372321198811046329");
        signUpInfo.setGraduationDate("2015-07-01");
        signUpInfo.setCompany("广联达");
        return signUpInfo;
    }

    //封装给广联达汇款信息
    private PayForGlodonInfo payForGlodonInfo(){
        PayForGlodonInfo payForGlodonInfo = new PayForGlodonInfo();
        payForGlodonInfo.setPayName("李老师");
        payForGlodonInfo.setPayCompany("山东大学");
        payForGlodonInfo.setPayId("1002354");
        payForGlodonInfo.setPayTime("2017-06-13 14:20:00");
        payForGlodonInfo.setPayImage("E:\\psb.jpg");
        return payForGlodonInfo;
    }

    @AfterMethod
    public void clearTestData(){
        authExam = null;authExamName = null;
        teaUserName = null;teaUserPwd=null;
        supernamUserName = null ;superUserPwd = null;
        stuUsers.clear();
        stuUsersEditScore.clear();
        stuUsersNew.clear();
    }
}
