package com.ttps.reservasya.models.transaction;

import com.ttps.reservasya.models.users.User;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class Transaction {

    private Long id;
    private User user;
    private StateTransaction state = StateTransaction.start();
    private Double amount;
    private LocalDateTime transactionDate;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne(targetEntity = User.class)
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Embedded
    public StateTransaction getState() {
        return state;
    }

    public void setState(StateTransaction state) {
        this.state = state;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }



}
