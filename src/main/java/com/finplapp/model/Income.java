package com.finplapp.model;

import javax.persistence.*;

@Entity
public class Income extends Ledger {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "incomeType_id")
    private IncomeType incomeType;

    public Income() {

    }

    public IncomeType getTypeIncome() {
        return incomeType;
    }

    public void setTypeIncome(IncomeType incomeType) {
        this.incomeType = incomeType;
    }

}



