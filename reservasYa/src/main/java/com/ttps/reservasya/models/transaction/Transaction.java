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
    @Transient
    private transient StateTransaction state;
    private String transactionState = TransactionStates.PENDING.name();
    private Double amount = 0.;
    private int convertedPoints = 0;
    private List<BusinessItem> items = new ArrayList<>();
    private LocalDateTime transactionDate;
    private PaymentData paymentData;

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


    @Transient
    public StateTransaction getState() {
        return state;
    }

    @Transient
    public void setState(StateTransaction state) {
        this.state = state;
    }

    @Transient
    public void setStateTransaction(StateTransaction state) {
        this.state = state;
    }



    public String getTransactionState() {
        return transactionState;
    }

    public void setTransactionState(String transactionState) {
        this.transactionState = transactionState;
    }

    @Column(updatable = false)
    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    @ManyToMany
    @JoinTable(name = "transaction_items", joinColumns = @JoinColumn(name = "transaction_id"), inverseJoinColumns = @JoinColumn(name = "item_id"))
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

    public int getConvertedPoints() {
        return convertedPoints;
    }

    public void setConvertedPoints(int convertedPoints) {
        this.convertedPoints = convertedPoints;
    }

    @Embedded
    public PaymentData getPaymentData() {
        return paymentData;
    }

    public void setPaymentData(PaymentData paymentData) {
        this.paymentData = paymentData;
    }

    @Transient
    public void buildStateTransaction(){
        switch (TransactionStates.valueOf(transactionState)){
            case PENDING:
                setStateTransaction(new PendingTransaction());
                break;
            case STARTED:
                setStateTransaction(new StartedTransaction());
                break;
            case APPROVED:
                setStateTransaction(new ApprovedTransaction());
                break;
            case FINISHED:
                setStateTransaction(new FinishedTransaction());
                break;
            case CANCELLED:
                setStateTransaction(new CancelledTransaction());
                break;
            case ROLLEDBACK:
                setStateTransaction(new RolledbackTransaction());
                break;
            default:
                setStateTransaction(new PendingTransaction());
                break;
        }
    }

    @Transient
    public void start(){
        state.doStart(this);
    }
    @Transient
    public void cancel(){
        state.doCancel(this);
    }
    @Transient
    public void finish(){
        state.doFinish(this);
    }
    @Transient
    public void rollBack(){
        state.doRollBack(this);}
    @Transient
    public void begin(){ state.doPending(this);}
    @Transient
    public void approve(){ state.doApprove(this);}

    @PrePersist
    public void prePersist(){
        this.getItems().forEach(i -> this.setAmount(this.getAmount() + i.getPrice()));
        this.setTransactionDate(LocalDateTime.now());
    }



}
