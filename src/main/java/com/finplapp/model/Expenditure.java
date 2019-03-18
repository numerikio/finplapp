package com.finplapp.model;

import javax.persistence.*;

@Entity
public class Expenditure extends Ledger {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "costType_id")
    private ExpenditureType expenditureType;

    public Expenditure() {

    }

    public ExpenditureType getExpenditureType() {
        return expenditureType;
    }

    public void setExpenditureType(ExpenditureType expenditureType) {
        this.expenditureType = expenditureType;
    }
}



