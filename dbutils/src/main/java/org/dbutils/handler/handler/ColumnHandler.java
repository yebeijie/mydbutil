package org.dbutils.handler.handler;

import org.dbutils.handler.RowProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;



/**
 * 查询一行指定列数的值，统计
 */
public class ColumnHandler<T> implements ResultSetHandler<T>{
    private int columnIndex;
    private Class<T> type;

    public ColumnHandler(int columnIndex,Class<T> type){
        this.columnIndex = columnIndex;
        this.type = type;
    }

    @Override
    public T handler(ResultSet rs) throws SQLException {
        return rs.next() ? RowProcessor.toValue(rs,columnIndex,type) : null;
    }
}
