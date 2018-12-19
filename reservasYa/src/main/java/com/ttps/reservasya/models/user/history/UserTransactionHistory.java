package com.ttps.reservasya.models.user.history;

import com.ttps.reservasya.models.transaction.Transaction;
import com.ttps.reservasya.models.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class UserTransactionHistory implements Serializable {

    private Long id;
    private User user;
    private Long lastTransactionId;
    private List<Transaction> transactionList = new ArrayList<>();

    public UserTransactionHistory(){}
    public UserTransactionHistory(User user){
        this.user = user;
    }

    @Id
    @GeneratedValue
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @OneToOne
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getLastTransactionId() {
        if(!this.transactionList.isEmpty()){
            this.setLastTransactionId(this.transactionList.get(this.transactionList.size() - 1).getId());
            return this.lastTransactionId;
        }
        return 0l;
    }

    public void setLastTransactionId(Long lastTransactionId) {
        this.lastTransactionId = lastTransactionId;
    }

    @OneToMany
    public List<Transaction> getTransactionList() {
        return transactionList;
    }

    public void setTransactionList(List<Transaction> transactionList) {
        this.transactionList = transactionList;
    }

    @PrePersist
    public void persistLastTid(){
        if(this.transactionList != null && !this.transactionList.isEmpty()) {
            this.setLastTransactionId(this.transactionList.get(this.transactionList.size() - 1).getId());
        }
    }
}
