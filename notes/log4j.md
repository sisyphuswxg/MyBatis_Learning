

## log4j

1. pom.xml添加依赖：
```xml
    <!-- log4j -->
    <dependency>
        <groupId>log4j</groupId>
        <artifactId>log4j</artifactId>
        <version>1.2.17</version>
    </dependency>
```

2. resource/目录下添加log4j.properties的日志配置文件

```properties
# Global logging configuration
log4j.rootLogger=DEBUG, stdout
# Console output...
log4j.appender.stdout=org.apache.log4j.ConsoleAppender
log4j.appender.stdout.layout=org.apache.log4j.PatternLayout
log4j.appender.stdout.layout.ConversionPattern=%5p [%t] - %m%n
```

运行一个Test用例，此时已经多了日志打印记录：
```
DEBUG [main] - Logging initialized using 'class org.apache.ibatis.logging.log4j.Log4jImpl' adapter.
DEBUG [main] - PooledDataSource forcefully closed/removed all connections.
DEBUG [main] - PooledDataSource forcefully closed/removed all connections.
DEBUG [main] - PooledDataSource forcefully closed/removed all connections.
DEBUG [main] - PooledDataSource forcefully closed/removed all connections.
DEBUG [main] - Opening JDBC Connection
DEBUG [main] - Created connection 1311146128.
DEBUG [main] - Setting autocommit to false on JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@4e268090]
DEBUG [main] - ==>  Preparing: SELECT * FROM USER WHERE id=?
DEBUG [main] - ==> Parameters: 1(Integer)
DEBUG [main] - <==      Total: 1
姓名:张三
性别:男
生日:1991-04-23
所在地:河南省郑州市
DEBUG [main] - Resetting autocommit to true on JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@4e268090]
DEBUG [main] - Closing JDBC Connection [com.mysql.cj.jdbc.ConnectionImpl@4e268090]
DEBUG [main] - Returned connection 1311146128 to pool.
```



### 配置介绍


(1) log4j.rootLogger=DEBUG, stdout

日志输出级别。一共有4个级别：OFF/FATAL/ERROR/WARN/INFO/DEBUG/ALL。

一般常用的日志输出级别分为：DEBUG/INFO/ERROR/WARN，分别表示调试级别、标准信息级别、错误级别、异常级别。

- 如果需要查看程序运行的详细步骤信息，一般选择DEBUG级别，因为该级别在程序运行期间，会在控制台打印出底层运行信息，以及在程序中使用Log对象打印出调试信息。
- 如果是日常运行，可以选择INFO级别，会在控制台打印出程序运行的主要步骤信息。
- ERROR级别和WARN级别分别代表不影响程序运行的错误事件和潜在的错误情形。

最后的stdout是：将等级为DEBUG的日志信息输出到stdout参数所指定的输出载体中。


(2) log4j.appender.stdout=org.apache.log4j.ConsoleAppender

设置名为stdout输出端载体是那种类型。

目前输出载体有：
- ConsoleAppender  控制台
- FileAppender  文件
- DailuRollingFileAppender  每天产生一个日志文件
- RollingFileAppender  文件大小达到指定大小时产生一个新的文件
- WriterAppender  将日志信息以流格式发送到任意指定的地方

这里是将日志打印到控制台 ConsoleAppender。


（3）log4j.appender.stdout.layout=org.apache.log4j.PatternLayout

stdout的输出载体的layout即页面布局是那种类型。

目前输出端的界面类型分为：
- HTMLLayout  以HTML表格形式布局
- PatternLayout  可以灵活地指定布局模式
- SimpleLayout  包含日志信息的级别和信息字符串
- TTCCLayout  包含日志产生的时间、线程、类别等信息。

这里是设置为PatternLayout，即自己去布置布局。


（4）log4j.appender.stdout.layout.ConversionPattern=%5p [%t] - %m%n


- %m：输出代码中指定的消息
- %p：输出优先级，即DEBUG、INFO等
- %r：输出自应用启动到输出该log信息耗费的毫秒数
- %c：输出所属的类目，通常是所在类的类名
- %t：输出产生该日志时间的线程名
- %n：输出一个回车换行符
- %d：输入日志时的日期或时间，默认格式是ISO8601
- %l：输出日志时间的发生位置，包括类目名、发生的线程、以及在代码中的行数
- [QC]：是log信息的开头，可以为任意字符，一般为项目简称

