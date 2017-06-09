package com.glodon.autoframework.tools;

import com.glodon.autoframework.logger.LoggerControler;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 数据库操作类
 *@Author zhangyy
 *@Date 2017-6-5 8:46
 */
public class MyDataBase {
    static final LoggerControler log = LoggerControler.getLogger(MyDataBase.class);

    //声明Mysql数据库的驱动
    private static String driver= "com.mysql.jdbc.Driver";
    //声明本地数据库的Ip地址和数据库名称
    private static String url = "jdbc:mysql://172.16.230.1:3306/personcenterdata_test";
    //声明数据库的用户名
    private static String userName = "root";
    //声明数据库root用户的登录密码
    private static String password = "root";

    /**
     * 更新数据库表
     *@Author zhangyy
     *@Date 2017-6-5 15:15
     * @param sql 更新sql语句
     */
    public static int updateData(String sql){
        int results = -1;
        try {
            //设定mysql驱动
            Class.forName(driver);
            //声明数据库连接
            Connection conn = DriverManager.getConnection(url,userName,password);
            //如果数据库连接可用，打印数据库连接成功
            if(!conn.isClosed())
                log.info("数据库连接成功");
            else
                log.info("数据库连接失败");
            //创建statement对象，用于执行sql语句
            Statement statement = conn.createStatement();
            //执行更新数据库sql语句,返回影响行数
            results = statement.executeUpdate(sql);
            //关闭数据连接
            conn.close();

        }catch (ClassNotFoundException e){
            log.info("未找到Mysql驱动类");
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        //返回影响行数
        return results;
    }

    /**
     * 查询语句
     *@Author zhangyy
     *@Date 2017-6-5 15:15
     * @param sql 查询sql语句
     */
    public static List<Object[]> selectData(String sql){
        List<Object[]> records = new ArrayList<Object[]>();
        try{
            //设定mysql驱动
            Class.forName(driver);
            //声明数据库连接
            Connection conn = DriverManager.getConnection(url,userName,password);
            //如果数据库连接可用，打印数据库连接成功
            if(!conn.isClosed())
                log.info("数据库连接成功");
            else
                log.info("数据库连接失败");
            //创建statement对象，用于执行sql语句
            Statement statement = conn.createStatement();
            //声明ResultSet对象，存储sql语句返回结果集
            ResultSet rs = statement.executeQuery(sql);
            //调用 ResultSetMetaData对象的getColumnCount方法获取数据行的行数
            int cols = rs.getMetaData().getColumnCount();
            while (rs.next()){
                String[] str = new String[cols];
                for(int i =0;i < str.length;i++){
                    str[i] = rs.getString(i + 1);
                }
                records.add(str);
            }
            //关闭数据集
            rs.close();
            //关闭数据连接
            conn.close();
        }catch (ClassNotFoundException e){
            log.info("未找到Mysql驱动类");
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        return records;
    }

}