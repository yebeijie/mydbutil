package org.dbutils.handler.handler;


import org.dbutils.handler.RowProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;

/**
 *
 * 将一行记录封装成Map集合
 */
public class MapHandler implements ResultSetHandler<Map<String,Object>>{
    @Override
    public Map<String, Object> handler(ResultSet rs) throws SQLException {
        return rs.next() ? RowProcessor.toMap(rs) : null;
    }
}
