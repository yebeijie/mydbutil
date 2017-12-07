package org.dbutils.handler.handler;


import org.dbutils.handler.RowProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 将一条记录封装成Object[]数组
 */
public class ArrayHandler implements ResultSetHandler<Object[]>{

    @Override
    public Object[] handler(ResultSet rs) throws SQLException {
        return rs.next() ? RowProcessor.toArray(rs) : null;
    }
}
