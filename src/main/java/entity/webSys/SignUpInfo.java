package entity.webSys;

/**
 * 学生报名信息
 *@Author zhangyy
 *@Date 2017-6-7 16:38
 */
public class SignUpInfo {
    private String stuName;//姓名
    private String sex;//性别
    private String mobil;//联系电话
    private String identity;//身份证号
    private String graduationDate;//毕业时间
    private String company;//单位

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public String getMobil() {
        return mobil;
    }

    public void setMobil(String mobil) {
        this.mobil = mobil;
    }

    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity;
    }

    public String getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(String graduationDate) {
        this.graduationDate = graduationDate;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }
}
