package com.finplapp.model;

import javax.persistence.Entity;

@Entity
public class IncomeType extends LedgerEntryType {

    public IncomeType(String type) {
        super(type);
    }

    public IncomeType() {
    }
}
