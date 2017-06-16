package com.glodon.autoframework.actions;

import com.glodon.autoframework.driver.MyDriver;
import com.glodon.autoframework.element.MyFind;
import com.glodon.autoframework.logger.LoggerControler;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *@Author zhangyy
 *@Date 2017-4-17 16:00
 */
public class MyActions extends MyDriver {
    final static LoggerControler log = LoggerControler.getLogger(MyActions.class);

    /**
     * 刷新页面
     *@Author zhangyy
     *@Date 2017-6-13 14:45
     */
    public static void refresh(WebDriver driver){
        driver.navigate().refresh();
    }

    /**
     * 获取当前url
     *@Author zhangyy
     *@Date 2017-6-13 20:53
     */
    public static String getCurrentUrl(WebDriver driver){return driver.getCurrentUrl();}

    /**
     * clcik() 封装
     *@Author zhangyy
     *@Date 2017-4-17 16:03
     */
    public static void click(By by, WebDriver driver) {
        MyFind.findElement(by, driver).click();
    }

    public static void click(WebElement element) {
        element.click();
    }

    /**
     * sendText() 封装
     *@Author zhangyy
     *@Date 2017-4-17 16:04
     */
    public static void sendText(By by, String text, WebDriver driver) {
        WebElement element = MyFind.findElement(by, driver);
        element.clear();
        element.sendKeys(text);
        log.info("在" + by + "输入" + text);
    }

    /**
     * getText() 封装
     *@Author zhangyy
     *@Date 2017-4-17 16:04
     */
    public static String getText(By by, WebDriver driver) {
        String text = MyFind.findElement(by, driver).getText();
        log.info("获取了" + by + "文本为" + text);
        return text;
    }

    public static String getText(WebElement element) {
        String text = element.getText();
        log.info("获取了" + element + "文本为" + text);
        return text;
    }

    /**
     * 获取多个getText()的封装
     *@Author zhangyy
     *@Date 2017-4-17 16:04
     */
    public static ArrayList getTexts(By by, WebDriver driver) {
        ArrayList arrayList = new ArrayList();
        List<WebElement> list = MyFind.findElements(by, driver);
        for (int j = 0; j < list.size(); j++) {
            String text = list.get(j).getText();
            arrayList.add(text);
        }
        return arrayList;
    }

    /**
     * 元素是否显示
     *@Author zhangyy
     *@Date 2017-4-17 22:50
     */
    public static boolean isDisplayed(By by, WebDriver driver){
        boolean b = false;
        try{
            b = MyFind.findElement(by, driver).isDisplayed();
        }catch (TimeoutException e){
            log.info("元素:" + by + "超时");
            b = false;
        }
        catch (Exception  e){
            log.info("元素:" + by + "不存在");
            b = false;
        }
        return b;
    }

    public static boolean isDisplayed(WebElement element){
        try{
            return element.isDisplayed();
        }catch (NoSuchElementException  e){
            log.info("元素:" + element + "不存在");
            return false;
        }
    }

    /**
     * 元素是否可用
     *@Author zhangyy
     *@Date 2017-4-17 22:50
     */
    public static boolean isEnabled(By by, WebDriver driver){
        try{
            return MyFind.findElement(by, driver).isEnabled();
        }catch (Exception e){
            return false;
        }
    }

    public static boolean isEnabled(WebElement element){
        try{
            return element.isEnabled();
        }catch (Exception e){
            return false;
        }
    }

    /**
     * 执行js语句操作
     *@Author zhangyy
     *@Date 2017-6-2 9:14
     */
    public static Object executeJS(String str,WebDriver driver){
        Object object = null;
        try {
            JavascriptExecutor js = (JavascriptExecutor) driver;
            object = js.executeScript(str);
        }catch (Exception e){
            log.info("执行js代码过程错误");
        }
        return object;
    }

    /**
     * windows页面切换
     *@Author zhangyy
     *@Date 2017-6-2 9:17
     */
    public static void toWindow(WebDriver driver){
        try{
            String handlOne = driver.getWindowHandle();
            Set<String> handles = driver.getWindowHandles();
            for(String handle : handles){
                if(handle.equals(handlOne))
                    continue;
                driver.switchTo().window(handle);
            }
        }catch (Exception e){
                e.printStackTrace();
                log.info("toWindow错误");
            }
    }

    /**
     * 拖动滚动条到底部
     *@Author zhangyy
     *@Date 2017-6-2 9:30
     */
    public static void pageBottom(WebDriver driver){
        try{
            Actions actions = new Actions(driver);
            actions.sendKeys(Keys.PAGE_DOWN).perform();
        }catch (Exception e){
            log.info("滚动条拖到底部错误");
        }
    }

    /**
     * 鼠标移动
     *@Author zhangyy
     *@Date 2017-6-13 18:57
     */
    public static void mouseRemove(WebElement element,WebDriver driver){
        Actions actions = new Actions(driver);
        actions.moveToElement(element).perform();
    }

}
