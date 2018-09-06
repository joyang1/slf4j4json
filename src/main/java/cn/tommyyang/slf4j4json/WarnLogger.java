package cn.tommyyang.slf4j4json;

import com.google.gson.Gson;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;

/**
 * Created by TommyYang on 2018/8/23.
 */
public class WarnLogger extends AbstractJsonLogger {
    public final static String LOG_LEVEL = "warn";

    public WarnLogger(Logger slf4jLogger, FastDateFormat formatter, Gson gson, boolean includeLoggerName) {
        super(slf4jLogger, formatter, gson, includeLoggerName);
    }

    @Override
    public void log() {
        this.slf4jLogger.warn(this.formatMessage(LOG_LEVEL));
        this.clearLog();

    }
}
