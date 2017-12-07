package org.dbutils.handler.handler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public abstract class AbstractListHandler<T> implements ResultSetHandler<List<T>>{


    protected abstract T getRow(ResultSet rs) throws SQLException;

    @Override
    public List<T> handler(ResultSet rs) throws SQLException {
        List<T> list = new ArrayList<>();
        while (rs.next()){
            list.add(getRow(rs));
        }
        return list;
    }
}
