
# 项目介绍


### **autoCode介绍**

> - autoCode是一个代码生成工具。基于velocity模板引擎，采用SpringMVC + mybatis + FDUI + mysql
> - 此工具只负责生成代码文件,不会生成完整功能的应用程序.

-------------------
### **其特点主要有**

> - 用户登陆 - 每个用户有他自己独立的数据库连接配置和模板配置；
> - 数据源配置 - 可以配置多个数据源,多种数据库类型(目前支持Mysql,MSServer数据库,可以扩展)；
> - 模板配置 - 定义自己的模板,采用velocity模板语法.这样可以根据模板来生成不同的代码,如POJO,Dao,mybatis配置文件等；
> - 操作简单 - 生成代码只需三步:1. 选择数据源;2. 选择表;3. 选择模板。

-------------------
### **部署程序步骤:**

> 1. Maven构建eclipse工程,运行Maven命令:mvn eclipse:eclipse,完成后导入到eclipse中
> 2. 导入数据库(MYSQL),SQL文件在项目根目录下,名为autoCode.sql,运行里面的内容即可
> 3. 修改数据库连接参数,配置文件在src/main/resources/config.properties
> 4. 启动项目,运行Maven命令:mvn jetty:run
> 5. 浏览器输入http://localhost:8088/autoCode

-------------------
登录用户名密码均为 admin
端口默认用了8088,如需修改,前往pom.xml,找到maven-jetty-plugin插件的port参数.

-------------------
### **默认模板参数：**


>      ${dateTime} : 时间，用于注释
>      
>      ${pkColumn} : 主键对象,即${columns}中的一个元素
>      
>      ${context}
>           ${context.packageName} : 包名
>           ${context.javaBeanName} : Java类名
>           ${context.javaBeanNameLF} : Java类名且首字母小写
>           ${context.pkName} : 表主键名
>           ${context.javaPkName} : 表主键对应的java字段名
>           ${context.javaPkType} : 主键的java类型
>           ${context.mybatisPkType} : 主键对应的mybatis类型
>           
>      ${table}
>           ${table.tableName} : 数据库表名
>           ${table.comment} : 表注释
>           
>      #foreach($column in $columns)...#end
>           ${$column.columnName} : 表中字段名
>           ${$column.type} : 字段的数据库类型
>           ${$column.javaFieldName} : java字段名
>           ${$column.javaFieldNameUF} : java字段名首字母大写
>           ${$column.javaType} : 字段的java类型
>           ${$column.javaTypeBox} : 字段的java装箱类型,如Integer,Long
>           ${$column.isIdentity} : 是否自增,返回boolean
>           ${$column.isPk} : 是否自增主键,返回boolean
>           ${$column.isIdentityPk} : 是否自增主键,返回boolean
>           ${$column.mybatisJdbcType} : 返回mybatis定义的jdbcType
>           ${$column.comment} : 表字段注释
>           











