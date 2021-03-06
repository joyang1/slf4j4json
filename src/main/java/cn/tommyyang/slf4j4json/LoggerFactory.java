package cn.tommyyang.slf4j4json;

import cn.tommyyang.slf4j4json.conf.LogConfig;
import org.apache.commons.lang3.time.FastDateFormat;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;

import java.util.Properties;

/**
 * Created by TommyYang on 2018/8/23.
 */
public class LoggerFactory {

    private static String dateFormatString = "yyyy-MM-dd HH:mm:ss";
    private static FastDateFormat formatter;
    private static boolean includeLoggerName;
    private static Producer producer;
    private static LogConfig logConfig;

    public static FileLogger getLogger(String name){
        org.slf4j.Logger slf4jLogger = org.slf4j.LoggerFactory.getLogger(name);
        return new FileLogger(slf4jLogger, formatter, includeLoggerName);
    }

    public static FileLogger getLogger(Class<?> clazz){
        org.slf4j.Logger slf4jLogger = org.slf4j.LoggerFactory.getLogger(clazz);
        return new FileLogger(slf4jLogger, formatter, includeLoggerName);
    }

    private static void setIncludeLoggerName(boolean includeLoggerName) {
        LoggerFactory.includeLoggerName = includeLoggerName;
    }

    private static KafkaLogger getKafkaLogger() {
        return new KafkaLogger(producer, logConfig, formatter, includeLoggerName);
    }

    public static Logger getLogger(){
        if (producer == null || logConfig == null){
            return getLogger("slf4j4json");
        } else {
            return getKafkaLogger();
        }
    }

    public static void openKafkaLogger(LogConfig conf){
        Properties kfkPro = new Properties();
        kfkPro.put("bootstrap.servers", conf.getKafkaCluster());
        kfkPro.put("acks", "-1");
        kfkPro.put("buffer.memory", 32 * 1024 * 1024);
        kfkPro.put("batch.size", 5 * 1024 * 1024);
        kfkPro.put("linger.ms", 10);
        kfkPro.put("compression.type", "none");
        kfkPro.put("retries", "0");
        kfkPro.put("key.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        kfkPro.put("value.serializer", "org.apache.kafka.common.serialization.StringSerializer");
        logConfig = conf;
        producer = new KafkaProducer(kfkPro);
    }

    static {
        formatter = FastDateFormat.getInstance(dateFormatString);
        includeLoggerName = false;
    }

}
