package com.picanco.menuboard.repository.mybatis.typehandler;

import com.picanco.menuboard.domain.ByteArrayProductImage;
import com.picanco.menuboard.domain.ProductImage;
import org.apache.ibatis.type.BaseTypeHandler;
import org.apache.ibatis.type.JdbcType;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductImageTypeHandler extends BaseTypeHandler<ProductImage> {
    @Override
    public void setNonNullParameter(PreparedStatement preparedStatement,
                                    int i,
                                    ProductImage productImage,
                                    JdbcType jdbcType) throws SQLException {
        preparedStatement.setBytes(i, new byte[]{});
    }

    @Override
    public ProductImage getNullableResult(ResultSet resultSet, String s) throws SQLException {
        return new ByteArrayProductImage(resultSet.getBytes(s));
    }

    @Override
    public ProductImage getNullableResult(ResultSet resultSet, int i) throws SQLException {
        return new ByteArrayProductImage(resultSet.getBytes(i));
    }

    @Override
    public ProductImage getNullableResult(CallableStatement callableStatement, int i) throws SQLException {
        return new ByteArrayProductImage(callableStatement.getBytes(i));
    }

}
