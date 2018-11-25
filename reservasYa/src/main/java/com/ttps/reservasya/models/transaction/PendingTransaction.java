package com.ttps.reservasya.models.transaction;


import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue(value = "PENDING")
public class PendingTransaction extends StateTransaction implements Serializable {


    public PendingTransaction(){
        super();
        this.type = TransactionStates.PENDING;
    }

    @Override
    public void doStart(Transaction transaction) {
        transaction.setState(this);
    }

    @Override
    public void doCancel(Transaction transaction) {
        transaction.setState(new CancelledTransaction());
    }

    @Override
    public void doFinish(Transaction transaction) {
        transaction.setState(new FinishedTransaction());
    }

    @Override
    public void doPause(Transaction transaction) {
        transaction.setState(new PausedTransaction());
    }

    @Override
    public void doRollBack(Transaction transaction) {
        transaction.setState(new RollbackedTransaction());
    }
}
