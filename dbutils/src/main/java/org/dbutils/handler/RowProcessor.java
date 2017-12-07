package org.dbutils.handler;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

/**
 * 行处理器
 */
public class RowProcessor {

    public static <T> T toBean(ResultSet rs, Class<T> type) {
        return (T)BeanUtil.createBean(rs, type);
    }

    public static Object[] toArray(ResultSet rs) throws SQLException {
        ResultSetMetaData rsmd = rs.getMetaData();
        Object[] arr = new Object[rsmd.getColumnCount()];
        for(int i = 1;i<=rsmd.getColumnCount();i++){
            arr[i-1] = rs.getObject(rsmd.getColumnName(i));
        }
        return arr;
    }

    public static Map<String,Object> toMap(ResultSet rs) throws SQLException{
        Map<String,Object> map = new HashMap<>();
        ResultSetMetaData rsmd = rs.getMetaData();
        for(int i = 1;i<=rsmd.getColumnCount();i++){
            map.put(rsmd.getColumnName(i),rs.getObject(rsmd.getColumnName(i)));
        }
        return map;
    }

    public static <T> T toValue(ResultSet rs,int columnIndex,Class<T> type) throws SQLException {
        Object value = rs.getObject(columnIndex);
        if(value != null){
            String columnName = rs.getMetaData().getColumnLabel(columnIndex);
            value = BeanUtil.columnConvert(rs,columnName,type);
        }
        return (T)value;
    }
}
