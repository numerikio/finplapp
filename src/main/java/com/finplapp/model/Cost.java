package com.finplapp.model;


import javax.persistence.*;


@Entity
@Table(name = "costs")
public class Cost extends Event{

    @Enumerated(EnumType.STRING)
    private CostType costType;

    public Cost() {

    }

    public CostType getTypeCost() {
        return costType;
    }

    public void setTypeCost(CostType costType) {
        this.costType = costType;
    }

}



