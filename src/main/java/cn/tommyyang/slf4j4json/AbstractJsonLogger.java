package cn.tommyyang.slf4j4json;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.apache.commons.lang3.time.FastDateFormat;
import org.slf4j.Logger;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.text.Format;
import java.time.Instant;
import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Created by TommyYang on 2018/8/23.
 */
public abstract class AbstractJsonLogger implements JsonLogger {

    protected Logger slf4jLogger;
    private FastDateFormat formatter;
    private Gson gson;
    private JsonObject jsonObject;
    private JsonObject jsonObjectMore;
    private boolean includeLoggerName;

    public AbstractJsonLogger(Logger slf4jLogger, FastDateFormat formatter, Gson gson, boolean includeLoggerName) {
        this.slf4jLogger = slf4jLogger;
        this.formatter = formatter;
        this.gson = gson;
        this.includeLoggerName = includeLoggerName;
        this.jsonObject = new JsonObject();
        this.jsonObjectMore = new JsonObject();
    }

    @Override
    public JsonLogger message(String message) {
        try {
            this.jsonObject.add("message", this.gson.toJsonTree(message));
        } catch (Exception var3) {
            this.jsonObject.add("message", this.gson.toJsonTree(this.formatException(var3)));
        }
        return this;
    }

    @Override
    public JsonLogger message(Supplier<String> message) {
        try {
            this.jsonObject.add("message", this.gson.toJsonTree(message.get()));
        } catch (Exception var3) {
            this.jsonObject.add("message", this.gson.toJsonTree(this.formatException(var3)));
        }

        return this;
    }

    @Override
    public JsonLogger map(String key, Map map) {
        try {
            this.jsonObject.add(key, this.gson.toJsonTree(map));
        } catch (Exception var3) {
            this.jsonObject.add(key, this.gson.toJsonTree(this.formatException(var3)));
        }

        return this;
    }

    @Override
    public JsonLogger map(String key, Supplier<Map> map) {
        try {
            this.jsonObject.add(key, this.gson.toJsonTree(map.get()));
        } catch (Exception var3) {
            this.jsonObject.add(key, this.gson.toJsonTree(this.formatException(var3)));
        }

        return this;
    }

    @Override
    public JsonLogger list(String key, List list) {
        try {
            this.jsonObject.add(key, this.gson.toJsonTree(list));
        } catch (Exception var3) {
            this.jsonObject.add(key, this.gson.toJsonTree(this.formatException(var3)));
        }

        return this;
    }

    @Override
    public JsonLogger list(String key, Supplier<List> list) {
        try {
            this.jsonObject.add(key, this.gson.toJsonTree(list.get()));
        } catch (Exception var3) {
            this.jsonObject.add(key, this.gson.toJsonTree(this.formatException(var3)));
        }

        return this;
    }

    @Override
    public JsonLogger field(String key, Object value) {
        try {
            this.jsonObject.add(key, this.gson.toJsonTree(value));
        } catch (Exception var3) {
            this.jsonObject.add(key, this.gson.toJsonTree(this.formatException(var3)));
        }

        return this;
    }

    @Override
    public JsonLogger field(String key, Supplier value) {
        try {
            this.jsonObject.add(key, this.gson.toJsonTree(value.get()));
        } catch (Exception var3) {
            this.jsonObject.add(key, this.gson.toJsonTree(this.formatException(var3)));
        }

        return this;
    }

    @Override
    public JsonLogger strField(String key, String value) {
        try {
            this.jsonObject.add(key, this.gson.toJsonTree(value));
        } catch (Exception var3) {
            this.jsonObject.add(key, this.gson.toJsonTree(this.formatException(var3)));
        }

        return this;
    }

    @Override
    public JsonLogger intField(String key, int value) {
        try {
            this.jsonObject.add(key, this.gson.toJsonTree(value));
        } catch (Exception var3) {
            this.jsonObject.add(key, this.gson.toJsonTree(this.formatException(var3)));
        }

        return this;
    }

    @Override
    public JsonLogger longField(String key, long value) {
        try {
            this.jsonObject.add(key, this.gson.toJsonTree(value));
        } catch (Exception var3) {
            this.jsonObject.add(key, this.gson.toJsonTree(this.formatException(var3)));
        }

        return this;
    }

    @Override
    public JsonLogger json(String key, JsonElement jsonElement) {
        try {
            this.jsonObject.add(key, jsonElement);
        } catch (Exception var3) {
            this.jsonObject.add(key, this.gson.toJsonTree(this.formatException(var3)));
        }

        return this;
    }

    @Override
    public JsonLogger json(String key, Supplier<JsonElement> jsonElement) {
        try {
            this.jsonObject.add(key, jsonElement.get());
        } catch (Exception var3) {
            this.jsonObject.add(key, this.gson.toJsonTree(this.formatException(var3)));
        }

        return this;
    }

    @Override
    public JsonLogger exception(String key, Exception exception) {
        try {
            this.jsonObject.add(key, this.gson.toJsonTree(this.formatException(exception)));
        } catch (Exception var3) {
            this.jsonObject.add(key, this.gson.toJsonTree(this.formatException(var3)));
        }

        return this;
    }


    @Override
    public JsonLogger getLogger() {
        if(this.jsonObject != null && this.jsonObject.size() > 0){
            for(Map.Entry<String, JsonElement> item : this.jsonObject.entrySet()){
                this.jsonObjectMore.add(item.getKey(), item.getValue());
            }
        }
        return this;
    }

    private String formatException(Exception e) {
        return ExceptionUtils.getStackTrace(e);
    }

    @Override
    public abstract void log();

    protected final void clearLog(){
        this.jsonObject = new JsonObject();
    }

    protected String formatMessage(String level) {
        if(this.jsonObjectMore != null && this.jsonObjectMore.size() > 0){
            for(Map.Entry<String, JsonElement> item : this.jsonObjectMore.entrySet()){
                this.jsonObject.add(item.getKey(), item.getValue());
            }
        }

        //this.jsonObject.add("index", this.gson.toJsonTree("server-end-log"));
        this.jsonObject.add("hostname", this.gson.toJsonTree(this.getHostName()));
        this.jsonObject.add("level", this.gson.toJsonTree(level));
//        this.jsonObject.add("thread_name", this.gson.toJsonTree(Thread.currentThread().getName()));

//        try {
//            this.jsonObject.add("class", this.gson.toJsonTree(this.getCallingClass()));
//        } catch (Exception var6) {
//            this.jsonObject.add("class", this.gson.toJsonTree(this.formatException(var6)));
//        }

        if (this.includeLoggerName) {
            this.jsonObject.add("logger_name", this.gson.toJsonTree(this.slf4jLogger.getName()));
        }

        try {
            this.jsonObject.add("time", this.gson.toJsonTree(this.getTime()));
        } catch (Exception var5) {
            this.jsonObject.add("time", this.gson.toJsonTree(this.formatException(var5)));
        }

//        Map mdc = MDC.getCopyOfContextMap();
//        if (mdc != null && !mdc.isEmpty()) {
//            try {
//                this.jsonObject.add("mdc", this.gson.toJsonTree(mdc));
//            } catch (Exception var4) {
//                this.jsonObject.add("mdc", this.gson.toJsonTree(this.formatException(var4)));
//            }
//        }

        return this.gson.toJson(this.jsonObject);
    }

    private String getCallingClass() {
        StackTraceElement[] stackTraceElements = (new Exception()).getStackTrace();
        return stackTraceElements[3].getClassName();
    }

    private String getHostName(){
        String hostName;
        try {
            hostName = (InetAddress.getLocalHost()).getHostName();
        } catch (UnknownHostException e) {
            hostName = "null";
        }
        return hostName;
    }


    private long getTime(){
        return Instant.now().getEpochSecond();
    }

    private String getCurrentTimestamp(Format formatter) {
        return formatter.format(System.currentTimeMillis());
    }

    private String formatStack() {
        StringBuilder output = new StringBuilder();
        StackTraceElement[] stackTraceElements = (new Exception()).getStackTrace();
        output.append(stackTraceElements[2]);

        for(int index = 3; index < stackTraceElements.length; ++index) {
            output.append("\n\tat ").append(stackTraceElements[index]);
        }

        return output.toString();
    }

}
