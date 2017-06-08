package com.glodon.autoframework.testng;

import com.glodon.autoframework.driver.MyDriver;
import com.glodon.autoframework.testng.interfaces.WebDriverHost;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.IHookCallBack;
import org.testng.IHookable;
import org.testng.ITestResult;
import ru.yandex.qatools.allure.annotations.Attachment;

import java.io.IOException;

/**
 * 重写testng监听接口，实现失败自动截图，上传至Allure测试报告中
 *@Author zhangyy
 *@Date 2017-4-19 10:09
 */
public class AllureReporterListener extends MyDriver implements IHookable {

    @Override
    public void run(IHookCallBack callBack, ITestResult testResult) {

        callBack.runTestMethod(testResult);
        if (testResult.getThrowable() != null) {
            try {
                WebDriverHost currentClass = (WebDriverHost)testResult.getInstance();
                WebDriver driver = currentClass.getWebDriver();

                takeScreenShot(testResult.getMethod().getMethodName(),driver);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


/**
 * 使用Allure框架中@Attachment注解，实现截图附件上传
 *@Author zhangyy
 *@Date 2017-4-19 10:10
 */
    @Attachment(value = "Failure in method {0}", type = "image/png")
    private byte[] takeScreenShot(String methodName,WebDriver driver) throws IOException {
        return ((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES);
    }
}
