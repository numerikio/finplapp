package com.finplapp.model;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Entity
@Table(name = "periods_of_time")
public class PeriodOfTime implements Comparable<PeriodOfTime> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private LocalDate localDate;

    private Double balance = 0.0;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @OneToMany(mappedBy = "periodOfTime", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Cost> costList = new ArrayList<>();

    @OneToMany(mappedBy = "periodOfTime", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Income> incomeList = new ArrayList<>();

    public PeriodOfTime() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDate getLocalDate() {
        return localDate;
    }

    public void setLocalDate(LocalDate localDate) {
        this.localDate = localDate;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String toString() {
        return "PeriodOfTime " + "[id = " + id + ", data = " + localDate.toString() + "]";
    }

    public List<Cost> getCostList() {
        return costList;
    }

    public void setCostList(List<Cost> costList) {
        this.costList = costList;
    }

    public List<Income> getIncomeList() {
        return incomeList;
    }

    public void setIncomeList(List<Income> incomeList) {
        this.incomeList = incomeList;
    }

    public Double getBalance() {
        return balance;
    }

    public void setBalance(Double balance) {
        this.balance = balance;
    }

    @Override
    public int compareTo(PeriodOfTime o) {
        return Comparator.comparing(PeriodOfTime::getLocalDate).compare(this, o);

    }
}
