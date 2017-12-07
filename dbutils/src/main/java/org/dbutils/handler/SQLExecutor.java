package org.dbutils.handler;


import org.dbutils.handler.handler.ResultSetHandler;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/*
处理事务
 */
public class SQLExecutor {

    private Connection conn;
    //是否自动关闭连接，默认true为自动关闭
    private Boolean autoClose = true;

    public SQLExecutor(Connection conn){
        this.conn = conn;
    }


    public <T> T executeQuery(String sql, ResultSetHandler handler, Object...params) throws SQLException {
        if(conn == null){
            throw new SQLException("conn is null");
        }
        if(sql == null){
            throw new SQLException("sql is null");
        }
        if(handler == null){
            throw new SQLException("handler is null");
        }
        PreparedStatement ps = conn.prepareStatement(sql);
        //替换参数
        replace(ps,params);
        ResultSet rs = ps.executeQuery();
        Object obj = handler.handler(rs);
        return (T)obj;
    }


    /**
     * 执行DML操作
     * @param sql
     * @param params
     * @return
     * @throws SQLException
     */
    public int executeUpdate(String sql,Object...params) throws SQLException {
        if(conn == null){
            throw new SQLException("Connection is null");
        }
        if(sql == null){
            conn.close();
            throw new SQLException("sql is null");
        }

        PreparedStatement ps = conn.prepareStatement(sql);
        replace(ps,params);
        int row = ps.executeUpdate();
        //close(ps,conn);
        close(ps);
        //关闭连接
        if(autoClose){
            close();
        }
        return row;
    }

    /**
     * 批量添加
     * @param sql
     * @param objs
     * @return
     */
    public int[] executeBatch(String sql,Object[][] objs) throws SQLException {
        int [] ints = null;
        PreparedStatement ps = conn.prepareStatement(sql);
        for(int i = 0;i<objs.length;i++){
            replaces(ps,objs[i]);
            ps.addBatch();
        }
        ints = ps.executeBatch();
        return ints;
    }

    /**
     * 批量添加，替换参数
     * @param ps
     * @param objs
     */
    public void replaces(PreparedStatement ps,Object[] objs) throws SQLException {
        for(int i = 0;i<objs.length;i++){
            ps.setObject(i+1,objs[i]);
        }
    }

    /**
     * 替换参数
     * @param ps
     * @param params
     * @throws SQLException
     */
    private void replace(PreparedStatement ps,Object...params) throws SQLException {
        for(int i = 0;i<params.length;i++){
            ps.setObject(i+1,params[i]);
        }
    }

    /**
     * 开启事务
     */
    public void beginTranstaction() throws SQLException {
        this.conn.setAutoCommit(false);
        autoClose = false;
    }

    /**
     * 提交事务
     */
    public void commit() throws SQLException {
        conn.commit();
        conn.close();
    }

    /**
     * 事务回滚
     */
    public void rollback() throws SQLException {
        conn.rollback();
        conn.close();
    }

    private void close(PreparedStatement ps) throws SQLException {
        ps.close();
    }

    /**
     * 关闭连接
     */
    public void close(){
        try {
            if(!this.conn.isClosed()||this.conn != null){
                this.conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
