package com.picanco.menuboard.exception;

import com.picanco.menuboard.domain.EffectivePrice;

import java.util.Set;

public class PriceInConflictException extends RuntimeException {
    public PriceInConflictException(Set<EffectivePrice> effectivePrice) {
    }
}
