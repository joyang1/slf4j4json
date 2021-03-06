package cn.tommyyang.slf4j4json;

import cn.tommyyang.slf4j4json.logger.*;
import org.apache.commons.lang3.time.FastDateFormat;

import java.io.IOException;

/**
 * Created by TommyYang on 2018/8/23.
 */
public class FileLogger extends Logger {
    private org.slf4j.Logger slf4jLogger;
    private NoopLogger noopLogger = new NoopLogger();

    public FileLogger(org.slf4j.Logger slf4jLogger, FastDateFormat formatter, boolean includeLoggerName) {
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

    @Override
    public void close() throws IOException {

    }


}
