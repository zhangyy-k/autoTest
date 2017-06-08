package com.glodon.autoframework.logger;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *@Author zhangyy
 *@Date 2017-4-17 15:53
 *
 */
public class LoggerControler {
    private static Logger logger = null;
    private static LoggerControler logg = null;

    public static LoggerControler getLogger(Class<?> T) {
        if (logger == null) {
//            实例化一个 Properties 类，处理log4j.Properties文件
            Properties props = new Properties();
            try {
//                获取log4j配置文件路径
                String envPaht = System.getProperty("user.dir") +
                        File.separator + "config" + File.separator + "log4j.properties";
                InputStream is = new FileInputStream(envPaht);
                props.load(is);
            } catch (IOException e) {
                e.printStackTrace();
            }
//            log4j 的PropertyConfigurator 类的configure方法输入数据流
            PropertyConfigurator.configure(props);
            logger = Logger.getLogger(T);
            logg = new LoggerControler();
        }
        return logg;
    }

    /**
     * 重写logger方法
     */
    public void info(String msg) {
        logger.info(msg);
    }

    public void infoStart(String msg) {
        logger.info("…………………………………………………"+msg+"-…………………………………………………………………………start");
    }

    public void infoEnd(String msg) {
        logger.info("…………………………………………………"+msg+"-………………………………………………………………………end");
    }

    public void debug(String msg) {
        logger.debug(msg);
    }

    public void warn(String msg) {
        logger.warn(msg);
    }

    public void error(String msg) {
        logger.error(msg);
    }
}
