import cn.tommyyang.slf4j4json.KafkaLogger;
import cn.tommyyang.slf4j4json.LoggerFactory;
import cn.tommyyang.slf4j4json.conf.LogConfig;
import cn.tommyyang.slf4j4json.conf.LogLevel;
import org.junit.Test;

/**
 * @author TommyYang on 2019-04-30
 */
public class KafkaLoggerTest {

    @Test
    public void test() throws Exception {
        LogConfig conf = new LogConfig("Vinci", LogLevel.INFO, "localhost:9092", "kfk-log");
        LoggerFactory.openKafkaLogger(conf);

        KafkaLogger logger = LoggerFactory.getKafkaLogger();
        new Thread(new Runnable() {
            @Override
            public void run() {
                logger.info().strField("test", "test-info").log();
                logger.error().strField("test", "test-error").log();
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                logger.warn().strField("test", "test-warn").log();
                logger.debug().strField("test", "test-debug").log();
            }
        }).start();

        Thread.sleep(10000);
       //logger.close();

    }

}
