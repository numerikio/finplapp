package com.finplapp.model;


import javax.persistence.*;

@Entity
@Table(name = "incomes")
public class Income extends Event{

   @Enumerated(EnumType.STRING)
    private IncomeType incomeType;

    public Income(){

    }

    public IncomeType getTypeIncome() {
        return incomeType;
    }

    public void setTypeIncome(IncomeType incomeType) {
        this.incomeType = incomeType;
    }

}



