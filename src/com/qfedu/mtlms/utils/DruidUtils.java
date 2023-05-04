package com.qfedu.mtlms.utils;

import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * @Description 数据库连接池的工具类
 * @Author 千锋涛哥
 * 公众号： Java架构栈
 */
public class DruidUtils {

    //定义Druid数据库连接池数据源
    private static DruidDataSource druidDataSource;

    /**
     * 初始化数据库连接池
     */
    static{
        try {
            InputStream is = DruidUtils.class.getResourceAsStream("druid.properties");
            Properties properties = new Properties();
            properties.load(is);
            druidDataSource = (DruidDataSource) DruidDataSourceFactory.createDataSource(properties);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 返回DataSource对象
     * @return
     */
    public static DataSource getDataSource(){
        return druidDataSource;
    }

    /**
     * 从连接池中获取数据库连接
     * @return
     */
    public static Connection getConnection(){
        Connection connection = null;
        try {
            connection = druidDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return connection;
    }

}
