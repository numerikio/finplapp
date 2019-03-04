package com.finplapp.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Cost extends Ledger {

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "costType_id")
    private CostType costType;

    public Cost() {

    }

    public CostType getCostType() {
        return costType;
    }

    public void setCostType(CostType costType) {
        this.costType = costType;
    }
}



