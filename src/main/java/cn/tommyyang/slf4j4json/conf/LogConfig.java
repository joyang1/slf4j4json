package cn.tommyyang.slf4j4json.conf;

/**
 * @author TommyYang on 2019-04-30
 */
public class LogConfig {

    private String appName;

    private LogLevel level;

    private String kafkaCluster;

    private String topic;

    public LogConfig(String appName, LogLevel level, String kafkaCluster, String topic) {
        this.appName = appName;
        this.level = level;
        this.kafkaCluster = kafkaCluster;
        this.topic = topic;
    }

    public String getAppName() {
        return appName;
    }

    public LogLevel getLevel() {
        return level;
    }

    public String getKafkaCluster() {
        return kafkaCluster;
    }

    public String getTopic() {
        return topic;
    }
}
