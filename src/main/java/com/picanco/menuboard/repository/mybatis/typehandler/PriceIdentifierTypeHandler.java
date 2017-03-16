package com.picanco.menuboard.repository.mybatis.typehandler;

import com.picanco.menuboard.domain.PriceIdentifier;

public class PriceIdentifierTypeHandler extends AbstractIntegerWrapperTypeHandler<PriceIdentifier> {
    public PriceIdentifierTypeHandler() {
        super(PriceIdentifier::new);
    }
}
