package com.glodon.autoframework.actions;

import com.glodon.autoframework.driver.MyDriver;
import com.glodon.autoframework.logger.LoggerControler;
import com.glodon.autoframework.tools.DateFormat;
import com.glodon.autoframework.tools.MyFile;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.Augmenter;

import java.io.File;
import java.io.IOException;

/**
 * Created by Administrator on 2017-4-17.
 */
public class MyScreenShot{
    protected static final LoggerControler log = LoggerControler.getLogger(MyScreenShot.class);

    static String path = System.getProperties().getProperty("user.dir") + "/error/";


    /**
     * 错误截图，文件名随机命名
     **/
    public static void screenShots(WebDriver driver) {
        WebDriver augmentedDriver = new Augmenter().augment(driver);
        File file = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
        try {
            MyFile myFile = new MyFile();
//            根据日期创建文件夹
            myFile.creatFile(path + DateFormat.format(DateFormat.CHECK_LOG_FORMAT));
            FileUtils.copyFile(file, new File(path + DateFormat.format(DateFormat.CHECK_LOG_FORMAT) + "/" +
                    DateFormat.format(DateFormat.REPORT_CSV_FORMAT) + ".jpg"));
        } catch (IOException e) {
            log.error("截图失败！！");
            e.printStackTrace();
        }
    }

    /**
     * 错误截图，自定义文件名
     **/
    public static void screenShots(String name,WebDriver driver) {
        WebDriver augmentedDriver = new Augmenter().augment(driver);
        File file = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
        try {
//            ScreenShot.fileExists();
            MyFile myFile = new MyFile();
            myFile.creatFile(path + DateFormat.format(DateFormat.CHECK_LOG_FORMAT));
            FileUtils.copyFile(file, new File(path + DateFormat.format(DateFormat.CHECK_LOG_FORMAT) + "/" + name + ".jpg"));
        } catch (IOException e) {
            log.error("截图失败！！");
            e.printStackTrace();
        }
    }
}
