package com.picanco.menuboard.repository.mybatis.typehandler;

import com.picanco.menuboard.domain.AbstractWrapper;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Function;

public abstract class AbstractWrapperTypeHandler<P, T extends AbstractWrapper<P>> extends BaseTypeHandler<T> {

    private final Function<P, T> typeConstructor;

    protected abstract P getValueFromResultSet(final ResultSet rs, final String columnName) throws SQLException;
    protected abstract P getValueFromResultSet(final ResultSet rs, final int columnIndex) throws SQLException;
    protected abstract P getValueFromCallableStatement(final CallableStatement cs, final int columnIndex) throws SQLException;
    protected abstract void setParameterInPreparedStatement(final PreparedStatement ps, final int i, final T parameter) throws SQLException;


    protected AbstractWrapperTypeHandler(Function<P, T> typeConstructor) {
        this.typeConstructor = typeConstructor;
    }

    @Override
    public void setNonNullParameter(final PreparedStatement ps, final int i, final T parameter, final JdbcType jdbcType)
            throws SQLException {
        setParameterInPreparedStatement(ps, i, parameter);
    }

    @Override
    public T getNullableResult(final ResultSet rs, final String columnName) throws SQLException {
        final P value = getValueFromResultSet(rs, columnName);
        if (value == null) {
            return null;
        }
        return typeConstructor.apply(value);
    }

    @Override
    public T getNullableResult(final ResultSet rs, final int columnIndex) throws SQLException {
        final P value = getValueFromResultSet(rs, columnIndex);
        if (value == null) {
            return null;
        }
        return typeConstructor.apply(value);
    }

    @Override
    public T getNullableResult(final CallableStatement cs, final int columnIndex) throws SQLException {
        final P value = getValueFromCallableStatement(cs, columnIndex);
        if (value == null) {
            return null;
        }
        return typeConstructor.apply(getValueFromCallableStatement(cs, columnIndex));
    }
}
