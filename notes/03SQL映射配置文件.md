



### SQL映射配置文件

在MyBatis中，将SQL配置在独立的配置文件Mapper.xml（文件名可更改）中，简称Mapper配置文件。

在这个文件中，可以配置任何类型的SQL语句，包括select、update、delete和insert语句。

对于SQL语句执行所需要的参数，以及查询语句返回的结果集对象，都可以在Mapper.xml配置文件中配置。

输入参数：会根据配置文件中的参数配置，组装参数的Java对象或Map对象中的相关字段与Mapper.xml中的参数配置做匹配，将相关数据绑定在需要执行的SQL语句上

输出结果：根据Mapper.xml中配置的结果集信息，将数据库取出的数据字段，一一映射到相应的Java对象或Map对象中。

如下demo：

```xml
<?xml version="1.0" encoding="UTF-8"?>  
<!DOCTYPE mapper  
PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"  
"http://mybatis.org/dtd/mybatis-3-mapper.dtd">  
  
<mapper namespace="test">

    <select id="findUserById" parameterType="int" resultType="com.sisyphuswxg.mybatis.po.User">
        SELECT * FROM USER WHERE id=#{id}
    </select>
    
</mapper>
```

Mapper.xml的文件路径，一般会配置在数据源配置文件SqlMapConfig.xml中，如下：
```xml
<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration
        PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>

    ...
    ...
    
    <mappers>
        <mapper resource="sqlmap/UserMapper.xml"/>
    </mappers>

</configuration>
```

实际上，使用MyBatis框架的人员，每天面对最多的就是各种Mapper.xml配置文件。



### Mapper 

所有配置都包裹在<mapper>标签对中。

namespace属性:
对SQL进行分类化管理，实现不同业务的SQL隔离。

增删改查对应的标签对： insert、delete、update、select。

每一个SQL配置标签都有parameterType、parameterMap、resultType、resultClass、resultMap属性，
分别表示输入参数类型（一般是基本类型或包装类型）、输入参数集合（一般是Map集合）、结果类型（一般是基本类型或包装类型）、结果类（一般是Java类）、结果集合（一般是Map集合）。

id属性：
唯一标识，是映射文件的SQL被解析并转换为Statement的id。

在insert、udpate、delete、select配置中也可以配置很多属性，如：flushCache、timeout等。


### `#{}`和`${}`区别：

`#{}`
- 占位符
- sql预编译：动态解析 -> 预编译 -> 执行
- 替换的变量在DBMS中
- 变量替换后，会自动加上单引号
- 可以防止sql注入


`${}`
- 拼接符
- sql拼接：动态解析 -> 编译 -> 执行
- 替换的变量在DBMS外
- 变量替换后，不会加上单引号
- 不可以防止sql注入



