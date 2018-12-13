package com.ttps.reservasya.models.transaction;

import com.ttps.reservasya.models.businessitem.BusinessItem;
import com.ttps.reservasya.models.user.User;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "TRANSACTION")
public class Transaction implements Serializable {

    private Long id;
    private User user;
    private StateTransaction state = new PendingTransaction();
    private Double amount = 0.;


    private List<BusinessItem> items = new ArrayList<>();
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

    @ManyToOne
    public StateTransaction getState() {
        return state;
    }


    public void setState(StateTransaction state) {
        this.state = state;
    }

    @Column(updatable = false)
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @Column(updatable = false)
    @ElementCollection(targetClass = BusinessItem.class, fetch = FetchType.EAGER)
    public List<BusinessItem> getItems() {
        return items;
    }

    public void setItems(List<BusinessItem> items) {
        this.items = items;
    }

    public LocalDateTime getTransactionDate() {
        return transactionDate;
    }

    public void setTransactionDate(LocalDateTime transactionDate) {
        this.transactionDate = transactionDate;
    }

    public void start(){
        state.doStart(this);
    }

    public void cancel(){
        state.doCancel(this);
    }

    public void finish(){
        state.doFinish(this);
    }

    public void rollBack(){ state.doRollBack(this);}

    public void begin(){ state.doPending(this);}

    public void approve(){ state.doApprove(this);}

    @PrePersist
    public void prePersist(){
        this.getItems().forEach(i -> this.setAmount(this.getAmount() + i.getPrice()));
        this.setTransactionDate(LocalDateTime.now());
    }



}
