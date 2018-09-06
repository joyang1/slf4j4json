package cn.tommyyang.slf4j4json.logger;

import cn.tommyyang.slf4j4json.logger.JsonLogger;
import com.google.gson.JsonElement;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

/**
 * Created by TommyYang on 2018/8/23.
 */
public class NoopLogger implements JsonLogger {

    public NoopLogger() {
    }

    @Override
    public JsonLogger message(String message) {
        return null;
    }

    @Override
    public JsonLogger message(Supplier<String> message) {
        return null;
    }

    @Override
    public JsonLogger map(String key, Map map) {
        return null;
    }

    @Override
    public JsonLogger map(String key, Supplier<Map> map) {
        return null;
    }

    @Override
    public JsonLogger list(String key, List list) {
        return null;
    }

    @Override
    public JsonLogger list(String key, Supplier<List> list) {
        return null;
    }

    @Override
    public JsonLogger field(String key, Object value) {
        return null;
    }

    @Override
    public JsonLogger field(String key, Supplier value) {
        return null;
    }

    @Override
    public JsonLogger strField(String key, String value) {
        return null;
    }

    @Override
    public JsonLogger intField(String key, int value) {
        return null;
    }

    @Override
    public JsonLogger longField(String key, long value) {
        return null;
    }

    @Override
    public JsonLogger json(String key, JsonElement jsonElement) {
        return null;
    }

    @Override
    public JsonLogger json(String key, Supplier<JsonElement> jsonElement) {
        return null;
    }

    @Override
    public JsonLogger exception(String key, Exception exception) {
        return null;
    }

    @Override
    public JsonLogger getLogger() {
        return null;
    }

    @Override
    public void log() {

    }
}
