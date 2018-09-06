package cn.tommyyang.slf4j4json;

import cn.tommyyang.slf4j4json.logger.Logger;
import org.apache.commons.lang3.time.FastDateFormat;

/**
 * Created by TommyYang on 2018/8/23.
 */
public class LoggerFactory {

    private static String dateFormatString = "yyyy-MM-dd HH:mm:ss.SSSZ";
    private static FastDateFormat formatter;
    private static boolean includeLoggerName;

    public static Logger getLogger(String name){
        org.slf4j.Logger slf4jLogger = org.slf4j.LoggerFactory.getLogger(name);
        return new Logger(slf4jLogger, formatter, includeLoggerName);
    }

    public static Logger getLogger(Class<?> clazz){
        org.slf4j.Logger slf4jLogger = org.slf4j.LoggerFactory.getLogger(clazz);
        return new Logger(slf4jLogger, formatter, includeLoggerName);
    }

    public static void setIncludeLoggerName(boolean includeLoggerName) {
        LoggerFactory.includeLoggerName = includeLoggerName;
    }

    static {
        formatter = FastDateFormat.getInstance(dateFormatString);
        includeLoggerName = false;
    }

}
