package com.finplapp.model;


import javax.persistence.*;


@Entity
@Table(name = "costs")
public class Cost {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private double amount;

    @Enumerated(EnumType.STRING)
    private CostType costType;

    private String message;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "period_id", nullable = false)
    private PeriodOfTime periodOfTime;

    public Cost() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public CostType getTypeCost() {
        return costType;
    }

    public void setTypeCost(CostType costType) {
        this.costType = costType;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public PeriodOfTime getPeriodOfTime() {
        return periodOfTime;
    }

    public void setPeriodOfTime(PeriodOfTime periodOfTime) {
        this.periodOfTime = periodOfTime;
    }
}



