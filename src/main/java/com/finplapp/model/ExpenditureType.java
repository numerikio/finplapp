package com.finplapp.model;

import javax.persistence.Entity;

@Entity
public class ExpenditureType extends LedgerEntryType {

    public ExpenditureType(String type) {
        super(type);
    }

    public ExpenditureType() {
    }
}
