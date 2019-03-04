package com.finplapp.model;

import javax.persistence.*;

@MappedSuperclass
public abstract class LedgerEntryType {
    public LedgerEntryType() {
    }

    public LedgerEntryType(String type) {
        this.type = type;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(unique = true, nullable = false)
    private String type;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
