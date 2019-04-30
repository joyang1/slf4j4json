import cn.tommyyang.slf4j4json.KafkaLogger;
import cn.tommyyang.slf4j4json.LoggerFactory;
import cn.tommyyang.slf4j4json.conf.LogConfig;
import cn.tommyyang.slf4j4json.conf.LogLevel;
import org.junit.*;

/**
 * @author TommyYang on 2019-04-30
 */
public class KafkaLoggerTest {

    static {
        LogConfig conf = new LogConfig("Vinci", LogLevel.INFO, "kfk04.jianshu.int:9092,kfk05.jianshu.int:9092", "admin-app-log");
        LoggerFactory.openKafkaLogger(conf);
    }

    @Before
    public void init(){

    }

    @Test
    public void test() throws Exception {

        new Thread(new MyRunnable()).start();

        new Thread(new MyRunnable()).start();

        Thread.sleep(10000); //producer 每10s flush 一次
    }

    @After
    public void close(){

    }

}

class MyRunnable implements Runnable{
    private final static KafkaLogger logger  = LoggerFactory.getKafkaLogger();
    @Override
    public void run() {
        logger.info().strField("test", "test-info").log();
        logger.error().strField("test", "test-error").log();
        logger.warn().strField("test", "test-warn").log();
        logger.debug().strField("test", "test-debug").log();
    }
}

