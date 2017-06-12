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
    //测试地址
    public static String testURL = null;
    //hub和node地址
    private String hubURL = null;
    private String nodeFireFoxURL = null;
    private String nodeChromeURL = null;
    private String nodeIEURL = null;
    private String authBrowserWin7 = null;
    private String authBrowserWin8 = null;
    private String authBrowserXP = null;

    public MyDriver(){
        getURL();
    }

    //根据浏览器类型返回 Webdriver对象
    public WebDriver openBrowser(String browser) throws MalformedURLException {
        WebDriver driver = null;

        if (browser.equals("chrome")) {
            log.info("nodeChromeURL:"+nodeChromeURL);
            DesiredCapabilities dc = DesiredCapabilities.chrome();
            driver = setWebBrowser(nodeChromeURL,dc);
        } else if (browser.equals("ie")) {
            log.info("nodeIEURL:"+nodeIEURL);
            DesiredCapabilities dc =DesiredCapabilities.internetExplorer();
            driver = setWebBrowser(nodeIEURL,dc);
        } else if (browser.equals("firefox")) {
            log.info("nodeFireFoxURL:"+nodeFireFoxURL);
            DesiredCapabilities dc =DesiredCapabilities.firefox();
            driver = setWebBrowser(nodeFireFoxURL,dc);
        } else if(browser.equals("authBrowserWin7")){
            log.info("authBrowserWin7:"+authBrowserWin7);
            driver = setAuthBrowser(authBrowserWin7);
        }else if(browser.equals("authBrowserWin8")){
            log.info("authBrowserWin8:"+authBrowserWin8);
            driver = setAuthBrowser(authBrowserWin8);
        }  else if(browser.equals("authBrowserXP")){
            log.info("authBrowserXP:"+authBrowserXP);
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

    //读取hub和node
    public void getURL(){
        //本地hub和node
        String configFile = System.getProperty("user.dir") +"/config/localSeleniumGrid.properties";
        //阿里hub和node
//        String configFile = System.getProperty("user.dir") +"/config/ailSeleniumGrid.properties";
        Properties properties = new Properties();
        try{
            properties.load(new FileInputStream(configFile));
            hubURL = properties.getProperty("hubURL");
            nodeChromeURL = properties.getProperty("nodeChromeURL");
            nodeFireFoxURL = properties.getProperty("nodeFireFoxURL");
            nodeIEURL = properties.getProperty("nodeIEURL");
            authBrowserWin7 = properties.getProperty("authBrowserWin7");
            authBrowserWin8 = properties.getProperty("authBrowserWin8");
            authBrowserXP = properties.getProperty("authBrowserXP");
        }catch (Exception e){
            log.error("错误的seleniumGrid配置文件路径");
        }
    }

    //读取测试地址
    public void getTestURL(){
        //本地测试地址
        String configFile = System.getProperty("user.dir") +"/config/localTestURL.properties";
        //阿里自动化测试地址
//        String configFile = System.getProperty("user.dir") +"/config/ailTestMutoURL.properties";
        //阿里功能测试地址
//        String configFile = System.getProperty("user.dir") +"/config/ailTestURL.properties";
        Properties properties = new Properties();
        try{
            properties.load(new FileInputStream(configFile));
            testURL = properties.getProperty("testURL");
        }catch (Exception e){
            log.error("错误的测试地址");
        }
    }


}
