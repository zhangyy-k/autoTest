package entity.webSys;

/**
 *@Author zhangyy
 *@Date 2017-6-2 8:49
 * 考试信息实体类
 */
public class AuthExam {
    private String examType; //考试类型  “统招线上”表示统招线上；“统招线下”表示统招线下；“定向线上”表示定向线上
    private String examName;//考试名称
    private String examLevel;//考试级别
    private String examSubjectZy;//考试专业
    private String examSubjectId;//考试科目
    private String startTime;//开始时间
    private String endTime;//结束时间
    private String examCapacity;//考场容量
    private String examAddress;//考试地点
    private String pay_contacts;//缴费联系人
    private String pay_mobile;//联系人电话
    private String pay_address;//考生缴费地址
    private String pay_endTime;//缴费截止时间
    private String remarkContainer;//考试说明


    public String getPay_contacts() {
        return pay_contacts;
    }

    public void setPay_contacts(String pay_contacts) {
        this.pay_contacts = pay_contacts;
    }

    public String getPay_mobile() {
        return pay_mobile;
    }

    public void setPay_mobile(String pay_mobile) {
        this.pay_mobile = pay_mobile;
    }

    public String getPay_address() {
        return pay_address;
    }

    public void setPay_address(String pay_address) {
        this.pay_address = pay_address;
    }

    public String getPay_endTime() {
        return pay_endTime;
    }

    public void setPay_endTime(String pay_endTime) {
        this.pay_endTime = pay_endTime;
    }


    public String getExamType() {
        return examType;
    }

    public void setExamType(String examType) {
        this.examType = examType;
    }

    public String getExamName() {
        return examName;
    }

    public void setExamName(String examName) {
        this.examName = examName;
    }

    public String getExamLevel() {
        return examLevel;
    }

    public void setExamLevel(String examLevel) {
        this.examLevel = examLevel;
    }

    public String getExamSubjectZy() {
        return examSubjectZy;
    }

    public void setExamSubjectZy(String examSubjectZy) {
        this.examSubjectZy = examSubjectZy;
    }

    public String getExamSubjectId() {
        return examSubjectId;
    }

    public void setExamSubjectId(String examSubjectId) {
        this.examSubjectId = examSubjectId;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getExamCapacity() {
        return examCapacity;
    }

    public void setExamCapacity(String examCapacity) {
        this.examCapacity = examCapacity;
    }

    public String getExamAddress() {
        return examAddress;
    }

    public void setExamAddress(String examAddress) {
        this.examAddress = examAddress;
    }

    public String getRemarkContainer() {
        return remarkContainer;
    }

    public void setRemarkContainer(String remarkContainer) {
        this.remarkContainer = remarkContainer;
    }

    private String browser;//测试浏览器
    private String assertValue;//结果校验
    private String testCaseID;//测试用例ID

    public String getTestCaseID() {
        return testCaseID;
    }

    public void setTestCaseID(String testCaseID) {
        this.testCaseID = testCaseID;
    }

    public String getBrowser() {
        return browser;
    }

    public void setBrowser(String browser) {
        this.browser = browser;
    }

    public String getAssertValue() {
        return assertValue;
    }

    public void setAssertValue(String assertValue) {
        this.assertValue = assertValue;
    }
}
