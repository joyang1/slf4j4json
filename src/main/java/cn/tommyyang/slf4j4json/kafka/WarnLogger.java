package cn.tommyyang.slf4j4json.kafka;

import cn.tommyyang.slf4j4json.AbstractJsonLogger;
import cn.tommyyang.slf4j4json.conf.LogConfig;
import cn.tommyyang.slf4j4json.conf.LogLevel;
import com.google.gson.Gson;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;

/**
 * @author TommyYang on 2019-04-30
 */
public class WarnLogger extends AbstractJsonLogger {

    private final static String LOG_LEVEL = LogLevel.WARN.toString();
    private Producer producer;
    private LogConfig conf;

    public WarnLogger(Producer producer, LogConfig conf, FastDateFormat formatter, Gson gson, boolean includeLoggerName) {
        this(conf, formatter, gson, includeLoggerName);
        this.producer = producer;
        this.conf =conf;
    }

    public WarnLogger(LogConfig logConfig, FastDateFormat formatter, Gson gson, boolean includeLoggerName) {
        super(logConfig, formatter, gson, includeLoggerName);
    }

    @Override
    public void log() {
        ProducerRecord<String, String> record = new ProducerRecord<>(this.conf.getTopic(), this.formatMessage(LOG_LEVEL));
        this.producer.send(record);
        this.clearLog();
    }
}
