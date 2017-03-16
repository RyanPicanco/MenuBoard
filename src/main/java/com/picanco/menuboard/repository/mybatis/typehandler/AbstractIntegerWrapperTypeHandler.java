package com.picanco.menuboard.repository.mybatis.typehandler;

import com.picanco.menuboard.domain.IntegerWrapper;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

public abstract class AbstractIntegerWrapperTypeHandler<T extends IntegerWrapper> extends AbstractWrapperTypeHandler<Integer, T> {

    protected AbstractIntegerWrapperTypeHandler(Function<Integer, T> typeConstructor) {
        super(typeConstructor);
    }

    @Override
    protected Integer getValueFromResultSet(ResultSet rs, String columnName) throws SQLException {
        return rs.getInt(columnName);
    }

    @Override
    protected Integer getValueFromResultSet(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getInt(columnIndex);
    }

    @Override
    protected Integer getValueFromCallableStatement(CallableStatement cs, int columnIndex) throws SQLException {
        return cs.getInt(columnIndex);
    }

    @Override
    protected void setParameterInPreparedStatement(PreparedStatement ps, int i, T parameter) throws SQLException {
        ps.setInt(i, parameter.getValue());
    }


}
