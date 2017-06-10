package com.glodon.autoframework.element;

import com.glodon.autoframework.driver.MyDriver;
import com.glodon.autoframework.logger.LoggerControler;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

/**
 *@Author zhangyy
 *@Date 2017-4-17 16:02
 */
public class MyFind extends MyDriver {
    final static LoggerControler log = LoggerControler.getLogger(MyFind.class);

    /**
     * findElement()封装
     *@Author zhangyy
     *@Date 2017-4-17 16:02
     */
    public static WebElement findElement(final By by, WebDriver driver) throws TimeoutException {
        WebElement webElement = null;
        try {
            webElement = new WebDriverWait(driver, 10).until(new ExpectedCondition<WebElement>() {
                public WebElement apply(WebDriver driver) {
                    return driver.findElement(by);
                }
            });
        } catch (Exception e) {
            log.error("元素:" + by + "查找超时");
//            System.out.println("元素:" + by + "查找超时");
        //    e.printStackTrace();
        }
        return webElement;
    }

    /**
     * findElements()封装
     *@Author zhangyy
     *@Date 2017-4-17 16:02
     */
    public static List<WebElement> findElements(final By by, WebDriver driver) {
        List<WebElement> webElement = null;
        try {
            webElement = new WebDriverWait(driver, 10).until(new ExpectedCondition<List<WebElement>>() {
                public List<WebElement> apply(WebDriver driver) {
                    return driver.findElements(by);
                }
            });
        } catch (Exception e) {
            log.error("元素:" + by + "查找超时");
//            System.out.println("元素:" + by + "查找超时");
            e.printStackTrace();
        }
        return webElement;
    }
}
