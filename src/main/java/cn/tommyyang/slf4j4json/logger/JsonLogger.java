package cn.tommyyang.slf4j4json.logger;

import com.google.gson.JsonElement;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Created by TommyYang on 2018/8/23.
 * 定义JsonLogger接口
 */
public interface JsonLogger {

    JsonLogger message(String message);

    JsonLogger message(Supplier<String> message);

    JsonLogger map(String key, Map map);

    JsonLogger map(String key, Supplier<Map> map);

    JsonLogger list(String key, List list);

    JsonLogger list(String key, Supplier<List> list);

    JsonLogger field(String key, Object value);

    JsonLogger field(String key, Supplier value);

    JsonLogger strField(String key, String value);

    JsonLogger intField(String key, int value);

    JsonLogger longField(String key, long value);

    JsonLogger json(String key, JsonElement jsonElement);

    JsonLogger json(String key, Supplier<JsonElement> jsonElement);

    JsonLogger exception(String key, Exception exception);

    //JsonLogger stack();

    JsonLogger getLogger();

    void log();

}
