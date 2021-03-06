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
    private List<Expenditure> expenditureList = new ArrayList<>();

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

    public List<Expenditure> getExpenditureList() {
        return expenditureList;
    }

    public void setExpenditureList(List<Expenditure> expenditureList) {
        this.expenditureList = expenditureList;
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

    public Double getBalanceEndOfPeriod() {
        return getSum(getIncomeList()) - getSum(getExpenditureList());
    }

    private Double getSum(List<? extends Ledger> ledgers) {
        Double result = 0.0;
        for (Ledger ledger : ledgers
        ) {
            result += ledger.getAmount();
        }
        return result;
    }

    @Override
    public int compareTo(PeriodOfTime o) {
        return Comparator.comparing(PeriodOfTime::getLocalDate).compare(this, o);
    }

    @Override
    public String toString() {
        return "PeriodOfTime{" +
                "id=" + id +
                ", localDate=" + localDate +
                ", user=" + user +
                '}';
    }
}
