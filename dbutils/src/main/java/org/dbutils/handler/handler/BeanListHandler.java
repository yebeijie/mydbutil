package org.dbutils.handler.handler;


import org.dbutils.handler.RowProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 多行记录封装成List<>集合
 */
public class BeanListHandler<T> extends AbstractListHandler<T>{
    private Class<T> type;

    public BeanListHandler(Class<T> type){
        this.type = type;
    }

    @Override
    protected T getRow(ResultSet rs) throws SQLException {
        return RowProcessor.toBean(rs,type);
    }
}
