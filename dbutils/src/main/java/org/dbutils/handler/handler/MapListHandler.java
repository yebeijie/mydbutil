package org.dbutils.handler.handler;


import org.dbutils.handler.RowProcessor;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Map;


/**
 * 将多行记录封装成List<Map<String,Object>集合
 */
public class MapListHandler extends AbstractListHandler<Map<String,Object>>{
    @Override
    protected Map<String, Object> getRow(ResultSet rs) throws SQLException {
        return RowProcessor.toMap(rs);
    }
}
