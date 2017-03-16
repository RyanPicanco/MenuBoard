package com.picanco.menuboard.repository.mybatis.typehandler;

import com.picanco.menuboard.domain.Size;

public class SizeTypeHandler extends AbstractStringWrapperTypeHandler<Size> {
    public SizeTypeHandler() {
        super(Size::new);
    }
}
