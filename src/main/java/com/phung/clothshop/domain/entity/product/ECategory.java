package com.phung.clothshop.domain.entity.product;

public enum ECategory {
    SHIRT,
    PANT,
    SHOE,
    JACKET;

    public String getValue() {
        return this.name();
    }
}
