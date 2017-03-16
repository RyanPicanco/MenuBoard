package com.picanco.menuboard.repository.mybatis.typehandler;

import com.picanco.menuboard.domain.StringWrapper;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

public abstract class AbstractStringWrapperTypeHandler<T extends StringWrapper> extends AbstractWrapperTypeHandler<String, T> {

    protected AbstractStringWrapperTypeHandler(Function<String, T> typeConstructor) {
        super(typeConstructor);
    }

    @Override
    protected String getValueFromResultSet(ResultSet rs, String columnName) throws SQLException {
        return rs.getString(columnName);
    }

    @Override
    protected String getValueFromResultSet(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getString(columnIndex);
    }

    @Override
    protected String getValueFromCallableStatement(CallableStatement cs, int columnIndex) throws SQLException {
        return cs.getString(columnIndex);
    }

    @Override
    protected void setParameterInPreparedStatement(PreparedStatement ps, int i, T parameter) throws SQLException {
        ps.setString(i, parameter.getValue());
    }


}
