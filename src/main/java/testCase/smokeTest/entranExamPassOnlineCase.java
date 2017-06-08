package testCase.smokeTest;

import com.glodon.autoframework.driver.MyDriver;
import com.glodon.autoframework.logger.LoggerControler;
import com.glodon.autoframework.testng.interfaces.WebDriverHost;
import com.glodon.autoframework.tools.DateFormat;
import com.glodon.autoframework.tools.RandomNum;
import entity.webSys.AuthExam;
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
import task.webSys.authExam.superman.AuthExamLookSuperTask;
import task.webSys.authExam.superman.CertificateExamSuperTask;
import task.webSys.authExam.superman.LookAuthExamMatterSuperTask;
import task.webSys.authExam.superman.PublishExamResultSuperTask;
import task.webSys.authExam.teacher.AddAuthExamTeaTask;

import java.net.MalformedURLException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.glodon.autoframework.tools.DateFormat.DEFAULT_DATE_FORMAT;

/**
 * 冒烟测试-统招线上类考试--测试脚本(报名缴费，考试通过)
 *@Author zhangyy
 *@Date 2017-6-2 10:35
 */
public class entranExamPassOnlineCase implements WebDriverHost {
    static final LoggerControler log = LoggerControler.getLogger(entranExamPassOnlineCase.class);

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
        teaUserName = "131561996834";teaUserPwd="123456";
        //管理员账号和密码
        supernamUserName="superman";superUserPwd="123456";
        //学生账号和密码
        stuUsers = new HashMap<>();
        stuUsers.put("10000000022","123456");
        stuUsers.put("10000000023","123456");
        stuUsers.put("10000000024","123456");
        //学生新账号
        stuUsersNew = new HashMap<>();
        //学生账号及修改成绩
        stuUsersEditScore = new HashMap<>();
        stuUsersEditScore.put("10000000022",50.5);
        stuUsersEditScore.put("10000000023",80.5);
        stuUsersEditScore.put("10000000024",90.5);
    }

    @Test
    @Description("冒然测试-统招线上类考试（新建考试-审核通过-报名-缴费-参加考试-颁发证书-查看证书）")
    @Parameters({"browser"})
    public void entranExamOnlineTest(String browser) throws MalformedURLException,InterruptedException{
        log.infoStart("冒然测试-统招线上类考试（新建考试-审核通过-报名-缴费-参加考试-颁发证书-查看证书）");

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

        //遍历学生账号和密码
        if(stuUsers != null) {
            for (Map.Entry<String, String> entry : stuUsers.entrySet()) {
                //学生-登录-报名
                log.infoStart("学生:"+entry.getKey()+";报名缴费");
                driver = myDriver.openBrowser(browser);
                driver.get(testURL);
                SignUpInfo signUpInfo = signUpInfo();
                SignUpStuTask.signUp(entry.getKey(), entry.getValue(), authExamName,signUpInfo, driver);

                //更新学生缴费状态
                SignUpStuTask.updataSignUpState(authExamName, signUpInfo.getMobil());
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

        //考试开始-倒计时-处理
        sleepTime = DateFormat.timeDifference(authExamStartTime);
        if(sleepTime > 0){
            log.info("距离考试开始还有"+String.valueOf(sleepTime)+"毫秒，请等待……");
            Thread.sleep(sleepTime);
            log.info("考试开始");
        }

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

        //管理员-查看考试情况
        log.infoStart("管理员-登录-查看考试情况");
        driver = myDriver.openBrowser(browser);driver.get(testURL);
        LookAuthExamMatterSuperTask.lookExamMatter(supernamUserName,superUserPwd,authExamName,driver);
        log.infoEnd("管理员-登录-查看考试情况");

        //考试结束-倒计时-处理
        sleepTime = -1;
        sleepTime = DateFormat.timeDifference(authExamEndTime);
        if(sleepTime > 0){
            log.info("距离考试结束还有"+String.valueOf(sleepTime)+"毫秒，请等待……");
            Thread.sleep(sleepTime);
            log.info("考试结束");
        }

        //管理员-登录-发布考试结果及修改考生成绩
        log.infoStart("管理员-登录-发布考试结果及修改考生成绩");
        driver = myDriver.openBrowser(browser);driver.get(testURL);
        PublishExamResultSuperTask.publishExamResult(supernamUserName,superUserPwd,authExamName,stuUsersEditScore,driver);
        log.infoEnd("管理员-登录-发布考试结果及修改考生成绩");

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

        log.infoEnd("冒然测试-统招线上类考试（新建考试-审核通过-报名-缴费-参加考试-颁发证书-查看证书）");

    }

    //封装冒烟测试-统招线上考试数据
    private AuthExam authExamInfo(){
        AuthExam authExam = new AuthExam();
        authExam.setExamType("统招线上");
        authExam.setExamName("统上自动化1-");
        authExam.setExamSubjectZy("土建");
        authExam.setStartTime(DateFormat.formatCalendar(DEFAULT_DATE_FORMAT,3));
        authExam.setEndTime(DateFormat.formatCalendar(DEFAULT_DATE_FORMAT,6));
        authExam.setExamCapacity("60");
        authExam.setExamAddress("济南广联达济南广联达");
        authExam.setRemarkContainer("自动化测试测试");
        return authExam;
    }

    //封装学生报名信息
    private SignUpInfo signUpInfo(){
        SignUpInfo signUpInfo = new SignUpInfo();
        signUpInfo.setStuName("学生"+ RandomNum.getStringRandom(3));
        signUpInfo.setSex("男");
        signUpInfo.setMobil(String.valueOf(DateFormat.time()/100));
        signUpInfo.setIdentity("372321198811046329");
        signUpInfo.setGraduationDate("2015-07-01");
        signUpInfo.setCompany("广联达");
        return signUpInfo;
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
