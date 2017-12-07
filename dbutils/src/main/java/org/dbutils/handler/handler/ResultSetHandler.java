package org.dbutils.handler.handler;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * 将查询到的结果集转换成不同的对象
 * @param <T>
 */
public interface ResultSetHandler<T> {

    T handler(ResultSet rs) throws SQLException;
}
