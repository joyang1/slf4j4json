package cn.tommyyang.slf4j4json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.time.FastDateFormat;

import java.io.IOException;

/**
 * @author TommyYang on 2019-05-23
 */
public abstract class Logger {

    protected Gson gson = (new GsonBuilder()).disableHtmlEscaping().serializeNulls().create();
    protected FastDateFormat formatter;
    protected boolean includeLoggerName;

    public abstract JsonLogger trace();

    public abstract JsonLogger info();

    public abstract JsonLogger warn();

    public abstract JsonLogger debug();

    public abstract JsonLogger error();

    public abstract void close() throws IOException;

}
