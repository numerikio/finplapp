package com.finplapp.model;

import javax.persistence.Entity;

@Entity
public class CostType extends LedgerEntryType {

    public CostType(String type) {
        super(type);
    }

    public CostType() {
    }
}
