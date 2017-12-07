package org.dbutils.handler;

import java.lang.reflect.Field;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;

public class BeanUtil {
/*
数据库数据与类匹对
 */
    public static Object createBean(ResultSet rs,Class<?> type){
        Object obj = null;
        try {
            obj = type.newInstance();
            ResultSetMetaData rsmd = rs.getMetaData();
            //if(rs.next()){
                Field[] fields = type.getDeclaredFields();
                for(Field f : fields){
                    f.setAccessible(true);
                    //获取注解值
                    Column column = f.getAnnotation(Column.class);
                    String columnName = column.value();
                    //获取类型
                    Class<?> pType = f.getType();
                    for(int i = 1;i<=rsmd.getColumnCount();i++){
                        String cName = rsmd.getColumnName(i);
                        if(cName.equals(columnName)){
                            Object value = columnConvert(rs,columnName,pType);
                            f.set(obj,value);
                        }
                    }
                //}
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return obj;
    }
    /*
    获取类型
     */
    public static Object columnConvert(ResultSet rs,String columnName,Class<?> type) throws SQLException {
        Object value = null;
        if(type.equals(String.class)){
            value = rs.getString(columnName);
        }else if(type.equals(Integer.TYPE)||type.equals(Integer.class)){
            value = Integer.valueOf(rs.getInt(columnName));
        }else if(type.equals(Double.TYPE)||type.equals(Double.class)){
            value = Double.valueOf(rs.getDouble(columnName));
        }else if(type.equals(Short.TYPE)||type.equals(Short.class)){
            value = Short.valueOf(rs.getShort(columnName));
        }else if(type.equals(Long.TYPE)||type.equals(Long.class)){
            value = Long.valueOf(rs.getLong(columnName));
        }else if(type.equals(Boolean.TRUE)||type.equals(Boolean.class)){
            value = Boolean.valueOf(rs.getBoolean(columnName));
        }else if (type.equals(Float.TYPE)||type.equals(Float.class)){
            value = Float.valueOf(rs.getFloat(columnName));
        }else if(type.equals(Byte.TYPE)||type.equals(Byte.class)){
            value = Byte.valueOf(rs.getByte(columnName));
        }
        return value;
    }
}
