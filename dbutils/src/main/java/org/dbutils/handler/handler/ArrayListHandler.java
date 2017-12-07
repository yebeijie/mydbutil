package org.dbutils.handler.handler;


import org.dbutils.handler.RowProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 将多条记录封装成List<Object[]>集合
 */
public class ArrayListHandler extends AbstractListHandler<Object[]>{
    @Override
    protected Object[] getRow(ResultSet rs) throws SQLException {
        return RowProcessor.toArray(rs);
    }
}
