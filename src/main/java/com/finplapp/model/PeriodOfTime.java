package com.finplapp.model;

import javax.persistence.*;

@Entity
@Table(name = "periods_of_time")
public class PeriodOfTime {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String data;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public PeriodOfTime() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
