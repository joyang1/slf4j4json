package cn.tommyyang.slf4j4json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.time.FastDateFormat;

/**
 * Created by TommyYang on 2018/8/23.
 */
public class Logger {
    private org.slf4j.Logger slf4jLogger;
    private Gson gson = (new GsonBuilder()).disableHtmlEscaping().serializeNulls().create();
    private FastDateFormat formatter;
    private boolean includeLoggerName;
    private NoopLogger noopLogger = new NoopLogger();

    public Logger(org.slf4j.Logger slf4jLogger, FastDateFormat formatter, boolean includeLoggerName) {
        this.slf4jLogger = slf4jLogger;
        this.formatter = formatter;
        this.includeLoggerName = includeLoggerName;
    }

    public JsonLogger trace() {
        return this.slf4jLogger.isTraceEnabled() ? new TraceLogger(this.slf4jLogger, formatter, gson, includeLoggerName) : noopLogger;
    }

    public JsonLogger info() {
        return (this.slf4jLogger.isInfoEnabled() ? new InfoLogger(this.slf4jLogger, formatter, gson, includeLoggerName) : noopLogger);
    }

    public JsonLogger warn() {
        return this.slf4jLogger.isWarnEnabled() ? new WarnLogger(this.slf4jLogger, formatter, gson, includeLoggerName) : noopLogger;
    }

    public JsonLogger debug() {
        return this.slf4jLogger.isDebugEnabled() ? new DebugLogger(this.slf4jLogger, formatter, gson, includeLoggerName) : noopLogger;
    }

    public JsonLogger error() {
        return this.slf4jLogger.isErrorEnabled() ? new ErrorLogger(this.slf4jLogger, formatter, gson, includeLoggerName) : noopLogger;
    }


}
