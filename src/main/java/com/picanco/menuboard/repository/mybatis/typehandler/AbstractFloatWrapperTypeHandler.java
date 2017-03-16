package com.picanco.menuboard.repository.mybatis.typehandler;

import com.picanco.menuboard.domain.AbstractWrapper;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

public abstract class AbstractFloatWrapperTypeHandler<T extends AbstractWrapper<Float>> extends AbstractWrapperTypeHandler<Float, T> {

    protected AbstractFloatWrapperTypeHandler(Function<Float, T> typeConstructor) {
        super(typeConstructor);
    }

    @Override
    protected Float getValueFromResultSet(ResultSet rs, String columnName) throws SQLException {
        return rs.getFloat(columnName);
    }

    @Override
    protected Float getValueFromResultSet(ResultSet rs, int columnIndex) throws SQLException {
        return rs.getFloat(columnIndex);
    }

    @Override
    protected Float getValueFromCallableStatement(CallableStatement cs, int columnIndex) throws SQLException {
        return cs.getFloat(columnIndex);
    }

    @Override
    protected void setParameterInPreparedStatement(PreparedStatement ps, int i, T parameter) throws SQLException {
        ps.setFloat(i, parameter.getValue());
    }


}
