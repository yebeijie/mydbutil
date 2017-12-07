package org.dbutils.handler.handler;


import org.dbutils.handler.RowProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;


/**
 * 将一行记录封装成一个Bean对象
 */
public class BeanHandler<T> implements ResultSetHandler<T>{
    private Class<T> type;

    public BeanHandler(Class<T> type){
        this.type = type;
    }

    @Override
    public T handler(ResultSet rs) throws SQLException {
        return rs.next() ? RowProcessor.toBean(rs,type) : null;
    }
}
