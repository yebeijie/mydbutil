package org.dbutils.handler.handler;

import org.dbutils.handler.RowProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;

;

/**
 * 查询指定列的值,统计
 */
public class ColumnListHandler<T> extends AbstractListHandler<T>{
    private int columnIndex;
    private Class<T> type;

    public ColumnListHandler(int columnIndex,Class<T> type){
        this.columnIndex = columnIndex;
        this.type = type;
    }

    @Override
    protected T getRow(ResultSet rs) throws SQLException {
        return RowProcessor.toValue(rs,columnIndex,type);
    }
}
