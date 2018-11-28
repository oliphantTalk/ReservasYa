package com.ttps.reservasya.models.businessitem;

import com.ttps.reservasya.models.transaction.Transaction;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@DiscriminatorColumn
public abstract class BusinessItem implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    protected Long id;
    protected Transaction transaction;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Transaction getTransaction() {
        return transaction;
    }
    @ManyToOne
    @JoinTable(name = "transaction_items", joinColumns = @JoinColumn(name="item_id"), inverseJoinColumns = @JoinColumn(name = "transaction_id"))
    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }
}
