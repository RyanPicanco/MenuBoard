package com.picanco.menuboard.repository.mybatis.typehandler;

import com.picanco.menuboard.domain.Cost;

public class CostTypeHandler extends AbstractFloatWrapperTypeHandler<Cost> {
    public CostTypeHandler() {
        super(Cost::new);
    }
}
