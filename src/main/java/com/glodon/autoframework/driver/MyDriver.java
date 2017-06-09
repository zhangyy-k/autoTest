package com.glodon.autoframework.driver;

import com.glodon.autoframework.logger.LoggerControler;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.FileInputStream;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

/**
 *@Author zhangyy
 *@Date 2017-4-17 16:14
 */
public class MyDriver {
    private final static LoggerControler log = LoggerControler.getLogger(MyDriver.class);
    public static String testURL = "http://172.16.230.107:7080";
//    public static String testURL = "http://testper-autotest.glodonedu.com";

    private String hubURL = "http://172.16.231.38:4446/wd/hub";
    private String nodeFireFoxURL = "http://172.16.231.38:8888/wd/hub";
    private String nodeChromeURL = "http://172.16.231.38:8888/wd/hub";
    private String nodeIEURL = "http://172.16.231.38:8888/wd/hub";
    private String authBrowserWin7 = "http://172.16.231.38:8888/wd/hub";
    private String authBrowserWin8 = "http://172.16.231.38:8888/wd/hub";
    private String authBrowserXP = "http://172.16.231.38:8888/wd/hub";

    //根据浏览器类型返回 Webdriver对象
    public WebDriver openBrowser(String browser) throws MalformedURLException {
        WebDriver driver = null;

        if (browser.equals("chrome")) {
            DesiredCapabilities dc = DesiredCapabilities.chrome();
            driver = setWebBrowser(nodeChromeURL,dc);
        } else if (browser.equals("ie")) {
            DesiredCapabilities dc =DesiredCapabilities.internetExplorer();
            driver = setWebBrowser(nodeIEURL,dc);
        } else if (browser.equals("firefox")) {
            DesiredCapabilities dc =DesiredCapabilities.firefox();
            driver = setWebBrowser(nodeFireFoxURL,dc);
        } else if(browser.equals("authBrowserWin7")){
            driver = setAuthBrowser(authBrowserWin7);
        }else if(browser.equals("authBrowserWin8")){
            driver = setAuthBrowser(authBrowserWin8);
        }  else if(browser.equals("authBrowserXP")){
            driver = setAuthBrowser(authBrowserXP);
        } else {
            log.error("你传入的浏览器名有误为：" + browser);
        }
        return driver;
    }


    //设置web端浏览器 兼容性
    private WebDriver setWebBrowser(String browserNode,DesiredCapabilities dc) throws MalformedURLException{
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL(browserNode), dc);
            driver.manage().window().maximize();
        }catch (Exception e){
            log.error("设置web端浏览器兼容性错误");
        }
        return driver;
    }

    //设置专用浏览器 兼容性
    private WebDriver setAuthBrowser(String authBroserNode) throws MalformedURLException{
        WebDriver driver = null;
        try {
            DesiredCapabilities dc = DesiredCapabilities.chrome();
            String browser_url = "C:\\Program Files (x86)\\GrandSoft\\GCS_RZ\\3.1\\NWjs\\ksxt.exe";
            ChromeOptions options = new ChromeOptions();
            options.setBinary(browser_url);
            dc.setCapability(ChromeOptions.CAPABILITY, options);
            driver = new RemoteWebDriver(new URL(authBroserNode), dc);
        }catch (Exception e){
            log.error("设置专用浏览器兼容性错误");
        }
        return driver;
    }
}
