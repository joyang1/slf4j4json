# `slf4j4json`

[![Build Status](https://travis-ci.org/joyang1/slf4j4json.svg?branch=master)](https://travis-ci.org/joyang1/slf4j4json.svg)

## Description
为slf4j提供自定义json格式的log输出

## maven dependency
1. 添加repository
``` java
<repositories>
    <repositories>
         <repository>
             <id>release</id>
             <url>https://raw.github.com/joyang1/slf4j4json/mvn-repo/</url>
             <snapshots>
                 <enabled>true</enabled>
                 <updatePolicy>always</updatePolicy>
             </snapshots>
         </repository>
     </repositories>
</repositories>
```
2. 添加dependency
``` java
<dependency>
    <groupId>cn.tommyyang</groupId>
    <artifactId>slf4j4json</artifactId>
    <version>1.4.0</version>
</dependency>
```

### 使用
#### 需要配置log4j.properties
因为该jar包同样是依赖于slf4j,所以按log4j配置log4j.properties即可正常使用
``` properties
log4j.rootLogger=DEBUG,console

#输出日志到控制台
log4j.appender.console=org.apache.log4j.ConsoleAppender
log4j.appender.console.Threshold=all
log4j.appender.console.layout=org.apache.log4j.PatternLayout
log4j.appender.console.layout.ConversionPattern=%-d{yyyy-MM-dd HH\:mm\:ss} [%c\:%L]-[%p] %m%n
```

#### slf4j4json使用
    
1. 直接打印
``` java
//通过LoggerFactory获取一个logger
final static Logger logger = LoggerFactory.getLogger(Test.class);

//打印必须调用log()方法
logger.info().field("key1", "value1").intField("key2", 1).log();
logger.warn().field("key1", "value1").intField("key2", 1).log();
logger.debug().strField("key1", "value1").longField("key2", 1L).log();
logger.trace().strField("key1", "value1").intField("key2", 1).log();
logger.error().strField("key1", "value1").intField("key2", 1).log();
```

2. 比如有一些键值对要重复赋值,使用getLogger方法，实例如下
``` java
JsonLogger jsonLogger = logger.info().strField("appname", "app").getLogger();
jsonLogger.strField("msg","infotest1").intField("port", 2).log();
jsonLogger.field("msg","infotest2").longField("long", 23L).log();
```

3. 1.4.0更新,只提供getLogger方法
``` java
LogConfig conf = new LogConfig("Vinci", LogLevel.INFO, "localhost:9092", "admin-app-log");
LoggerFactory.openKafkaLogger(conf);

Logger logger  = LoggerFactory.getLogger();

logger.info().strField("test", "test-info").log();
logger.error().strField("test", "test-error").log();
logger.warn().strField("test", "test-warn").log();
logger.debug().strField("test", "test-debug").log();

```

## 输出
``` json
{"key1":"value1","key2":1,"index":"server-end-log","host":"DESKTOP-2B1VG6J","level":"info","time":1535021174}
```


## version
- `1.0版本`
    - 实现JsonLog For Java的基本功能
- `1.1版本`
    - 修复JsonLogger jsonLogger = logger.info().strField("appname", "app").getLogger(); 出现重复内容的bug

- `1.2版本`
   - 去掉log中自带的index键值对
   - repository修改
     ``` java
     <repositories>
         <repository>
             <id>release</id>
             <url>https://raw.github.com/joyang1/slf4j4json/mvn-repo/</url>
             <snapshots>
                 <enabled>true</enabled>
                 <updatePolicy>always</updatePolicy>
             </snapshots>
         </repository>
     </repositories>
     ```
- `1.3.0版本`
  - 添加kafka logger的实现，批量打印log到kafka
  - 10s或者5m flush一次
  
- `1.4.0版本`
  - 优化logger实现
     
     
## License
Slf4j4json is available under the MIT License
