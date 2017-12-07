package org.dbutils.handler;

import java.sql.*;

/**
 * 数据库连接
 */
public class DBUtils {
    static final String DRIVER="com.mysql.jdbc.Driver";
    static final String URL="jdbc:mysql://localhost:3306/mtdb?useUnicode=true&amp;character=utf-8";
    static final String UID="root";
    static final String UPWD="root";

    static{
        try {
            Class.forName(DRIVER);
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**创建数据库连接对象
     *
     * @return
     */
    public static Connection getcon() {
        Connection conn= null;
        try {
            conn = DriverManager.getConnection(URL,UID,UPWD);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return conn;
    }
    /**关闭资源
     *
     * @param rs
     * @param ps
     * @param conn
     */
    public static void colse(ResultSet rs, PreparedStatement ps, Connection conn){
        if(rs!=null){
            try {
                rs.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(ps!=null){
            try {
                ps.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void main(String[] args) {
        System.out.println(getcon());
    }
}
