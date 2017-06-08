package com.glodon.autoframework.tools;

import com.glodon.autoframework.logger.LoggerControler;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *@Author zhangyy
 *@Date 2017-4-17 16:14
 */
public class DateFormat {
    final static LoggerControler log = LoggerControler.getLogger(DateFormat.class);

    public static final String COMMON_DATE_FORMAT = "yyyy-MM-dd";

    public static final String SHORT_DATE_FORMAT = "yy-MM-dd HH:mm";

    public static final String SMALL_DATE_FORMAT = "MM-dd";

    public static final String ABC_ORDER_DATE_FORMAT = "yyyy/MM/dd";

    public static final String ABC_ORDER_TIME_FORMAT = "HH:mm:ss";

    public static final String LOTTERY_PRINTING_TIME_FORMAT = "HH_mm_ss";

    public static final String CHECK_LOG_FORMAT = "yyyyMMdd";

    public static final String TEN_PAY_DATE_STRING_FORMAT = "yyMMdd";

    public static final String REPORT_CSV_FORMAT = "yyyyMMdd_HHmmss";

    public static final String ZH_DATE_FORMAT = "yyyy年MM月dd日 HH:mm:ss";

    public static final String DEFAULT_DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_FORMAT = "yyyy-MM-dd HH-mm-ss";

    //	引入SimpleDateFormat类处理时间
    private static SimpleDateFormat simpleDateFormat;

    /**
     * 当前时间根据格式返回字符串
     *@Author zhangyy
     *@Date 2017-6-6 8:36
     * @param type 时间格式
     */
    public static String format(String type) {
        simpleDateFormat = new SimpleDateFormat(type);
        String s = simpleDateFormat.format(new Date());
        log.info(s);
        return s;
    }

    /**
     * 字符串时间根据格式转换为时间类型
     *@Author zhangyy
     *@Date 2017-6-6 8:36
     * @param type 时间格式
     * @param time 要转换的时间
     */
    public static Date format(String type,String time) {
        Date formatTime = null;
        try {
            simpleDateFormat = new SimpleDateFormat(type);
            formatTime = simpleDateFormat.parse(time);
        }catch (ParseException e){
            log.info("字符串转换时间错误");
        }
        return formatTime;
    }

    /**
     * 当前时间根据格式返回时间类型
     *@Author zhangyy
     *@Date 2017-6-6 8:40
     * @param type 时间格式
     */
    public static Date formatDate(String type){
        Date formatTime = null;
        try{
            simpleDateFormat = new SimpleDateFormat(type);
            String s = simpleDateFormat.format(new Date());
            formatTime = simpleDateFormat.parse(s);
        }catch (ParseException e){
            log.info("字符串转换时间错误");
        }
        return  formatTime;
    }

    /**
     * 返回比当前时间大于多长分钟的时间字符串
     *@Author zhangyy
     *@Date 2017-6-6 15:54
     * @param type 时间格式
     * @param minute 大于或小于时间差
     */
    public static String formatCalendar(String type,int minute) {
        simpleDateFormat = new SimpleDateFormat(type);
        long timeLose=time()+minute*60*1000;
        String loseDate=simpleDateFormat.format(new Date(timeLose));
        log.info(loseDate);
        return loseDate;
    }

    /**
     * 返回当前时间的时间戳
     *@Author zhangyy
     *@Date 2017-6-6 15:56
     */
    public static long time() {
        long t = System.currentTimeMillis();
        log.info(String.valueOf(t));
        return t;
    }

    /**
     * 返回同当前时间对比的时间差的毫秒数
     *@Author zhangyy
     *@Date 2017-6-6 15:56
     */
    public static long timeDifference(Date time){
        //需等待毫秒数
        long sleepTime = -1;
        //当前时间
        Date nowTime = DateFormat.formatDate(DEFAULT_DATE_FORMAT);
        //对比时间-当前时间的时间差
        sleepTime = time.getTime() - nowTime.getTime();
        return sleepTime;
    }
}
