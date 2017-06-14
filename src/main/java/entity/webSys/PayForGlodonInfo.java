package entity.webSys;

/**
 * 老师-给广联达汇款信息
 *@Author zhangyy
 *@Date 2017-6-13 9:32
 */
public class PayForGlodonInfo {
    //汇款人姓名
    private String payName;
    //汇款单位
    private String payCompany;
    //汇款单号
    private String payId;
    //汇款日期
    private String payTime;
    //汇款照片
    private String payImage;

    public String getPayName() {
        return payName;
    }

    public void setPayName(String payName) {
        this.payName = payName;
    }

    public String getPayCompany() {
        return payCompany;
    }

    public void setPayCompany(String payCompany) {
        this.payCompany = payCompany;
    }

    public String getPayId() {
        return payId;
    }

    public void setPayId(String payId) {
        this.payId = payId;
    }

    public String getPayTime() {
        return payTime;
    }

    public void setPayTime(String payTime) {
        this.payTime = payTime;
    }

    public String getPayImage() {
        return payImage;
    }

    public void setPayImage(String payImage) {
        this.payImage = payImage;
    }
}
