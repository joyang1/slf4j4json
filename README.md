# slf4j4json
为slf4j提供自定义json格式的log输出

## maven dependency
1. 添加repository
```
<repositories>
    <repository>
        <id>maven-repo-master</id>
        <url>https://raw.github.com/joyang1/slf4j4json/master/</url>
        <snapshots>
            <enabled>true</enabled>
            <updatePolicy>always</updatePolicy>
        </snapshots>
    </repository>
</repositories>
```
2. 添加dependency
```
<dependency>
    <groupId>cn.tommyyang</groupId>
    <artifactId>slf4j4json</artifactId>
    <version>1.0</version>
</dependency>
```

### 使用
1. 需要配置log4j.properties
因为该jar包同样是依赖于slf4j,所以按log4j配置log4j.properties即可正常使用
```
log4j.rootLogger=DEBUG,console

#输出日志到控制台
log4j.appender.console=org.apache.log4j.ConsoleAppender
log4j.appender.console.Threshold=all
log4j.appender.console.layout=org.apache.log4j.PatternLayout
log4j.appender.console.layout.ConversionPattern=%-d{yyyy-MM-dd HH\:mm\:ss} [%c\:%L]-[%p] %m%n
```

2. slf4j4json使用
直接打印
```
logger.info().field("key1", "value1").intField("key2", 1).log();
logger.warn().field("key1", "value1").intField("key2", 1).log();
logger.debug().strField("key1", "value1").longField("key2", 1L).log();
logger.trace().strField("key1", "value1").intField("key2", 1).log();
logger.error().strField("key1", "value1").intField("key2", 1).log();
```

比如有一些键值对要重复赋值,使用getLogger方法，实例如下
```
JsonLogger jsonLogger = logger.info().strField("appname", "app").getLogger();
jsonLogger.strField("msg","infotest1").intField("port", 2).log();
jsonLogger.field("msg","infotest2").longField("long", 23L).log();
```