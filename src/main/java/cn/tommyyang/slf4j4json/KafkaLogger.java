package cn.tommyyang.slf4j4json;

import cn.tommyyang.slf4j4json.conf.LogConfig;
import cn.tommyyang.slf4j4json.kafka.DebugLogger;
import cn.tommyyang.slf4j4json.kafka.ErrorLogger;
import cn.tommyyang.slf4j4json.kafka.InfoLogger;
import cn.tommyyang.slf4j4json.kafka.WarnLogger;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.kafka.clients.producer.Producer;

import java.io.Closeable;
import java.io.IOException;

/**
 * @author TommyYang on 2019-04-30
 */
public class KafkaLogger implements Closeable {

    private Producer producer;
    private Gson gson = (new GsonBuilder()).disableHtmlEscaping().serializeNulls().create();
    private FastDateFormat formatter;
    private boolean includeLoggerName;
    private LogConfig logConfig;

    public KafkaLogger(Producer producer, LogConfig conf, FastDateFormat formatter, boolean includeLoggerName) {
        this.producer = producer;
        this.formatter = formatter;
        this.includeLoggerName = includeLoggerName;
        this.logConfig = conf;
    }


    public JsonLogger info(){
        return new InfoLogger(this.producer, this.logConfig, this.formatter, this.gson, this.includeLoggerName);
    }

    public JsonLogger debug(){
        return new DebugLogger(this.producer, this.logConfig, this.formatter, this.gson, this.includeLoggerName);
    }

    public JsonLogger warn(){
        return new WarnLogger(this.producer, this.logConfig, this.formatter, this.gson, this.includeLoggerName);
    }

    public JsonLogger error(){
        return new ErrorLogger(this.producer, this.logConfig, this.formatter, this.gson, this.includeLoggerName);

    }
    @Override
    public void close() throws IOException {
        if (this.producer != null) {
            this.producer.flush();
            this.producer.close();
        }
    }
}
