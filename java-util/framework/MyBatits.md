# MyBatits
## 框架以及原理分析
MyBatits是支持定制化SQL,存储过程以及高级映射的持久层框架，其主要完成了两件事情
* 1.封装JDBC操作。
* 2.利用反射打通Java类和SQL之间的相互转换。MyBatits的核心竞争力是让使用者在执行SQL语句时对输入输出更加方便的管理，
方便地写出SQL和获取执行SQL的结果。

## MyBatits的配置
### 打印日志信息
```
<settings>
        <setting name="cacheEnabled" value="true"/>
        <setting name="lazyLoadingEnabled" value="false"/>
        <!--<setting name="logImpl" value="STDOUT_LOGGING"/> &lt;!&ndash; 打印日志信息 &ndash;&gt;-->
</settings>
```

### 设置别名alias
```
<typeAliases>
        <typeAlias type="com.luo.dao.UserDao" alias="User"/>
</typeAliases>
```
### 设置数据库链接事物类型，用户名和密码 <environments>xxx</environments>
```
<environments default="development">
        <environment id="development">
            <transactionManager type="JDBC"/> <!--事务管理类型-->
            <dataSource type="POOLED">
                <property name="username" value="luoxn28"/>
                <property name="password" value="123456"/>
                <property name="driver" value="com.mysql.jdbc.Driver"/>
                <property name="url" value="jdbc:mysql://192.168.1.150/ssh_study"/>
            </dataSource>
        </environment>
</environments>
```
### 添加数据表对应的mapper文件<mappers>xxx</mappers>
```
<mappers>
        <mapper resource="userMapper.xml"/>
</mappers>
```
## MyBatis的框架分析
* 1.Configuration MyBatis所有的配置信息都保存在Configuration对象之中，配置文件中的大部分配置都会存储到该类中
* 2.SqlSession
* 3.Executor
* 4.StatementHandler
* 5.ParameterHandler
* 6.TypeHandler
* 7.MappedStatement
* 8.SqlSource
* 9.BoundSql

## MyBatis缓存
MyBatis提供查询缓存，用于减轻数据库压力，提高性能，MyBatis提供了一级缓存和二级缓存
### 一级缓存
> 一级缓存是SqlSession级别的缓存，每个SqlSession对象都有一个哈希表用于缓存数据，不同SqlSession之间对象不共享。
> 同一个SqlSession对象执行2遍相同的Sql查询，在第一次执行查询完毕后j将结果缓存起来，这样第二遍查询就不用向数据库查询了。
> 直接返回缓存结果即可，MyBatis默认一级缓存是开启的。
### 二级缓存
> 二级缓存是mapper级别的缓存，二级缓存是跨SqlSession的，多个SqlSession对象可以共享同一个二级缓存。
> 不同的sqlSession对象执行两次相同的SQL语句，第一次会将查询结果进行缓存，第二次直接返回二级缓存中的结果即可。
> MyBatis默认是不开启二级缓存的。
*开启二级缓存*
```
<settings>
    <setting name="cacheEnabled" value="true"/>
</settings>
```

### 缓存的缺点
当SQL语句进行更新操作(删除/添加/更新)时，会清空对应的缓存，保证缓存中存储的都是最新的数据。
MyBatis的二级缓存对细粒度的数据级别的缓存实现不友好.
> 比如如下需求：对商品信息进行缓存，由于商品信息查询访问量大，但是要求用户每次都能查询最新的商品信息，
> 此时如果使用mybatis的二级缓存就无法实现当一个商品变化时只刷新该商品的缓存信息而不刷新其它商品的信息，
> 因为mybaits的二级缓存区域以mapper为单位划分，当一个商品信息变化会将所有商品信息的缓存数据全部清空。
> 解决此类问题需要在业务层根据需求对数据有针对性缓存， 具体业务具体实现。


# 参考文章
1. https://www.cnblogs.com/luoxn28/p/6417892.html

