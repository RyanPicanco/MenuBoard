package com.picanco.menuboard.repository.mybatis.typehandler;

import com.picanco.menuboard.domain.Description;

public class DescriptionTypeHandler extends AbstractStringWrapperTypeHandler<Description> {
    public DescriptionTypeHandler() {
        super(Description::new);
    }
}
