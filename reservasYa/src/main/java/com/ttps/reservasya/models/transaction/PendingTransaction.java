package com.ttps.reservasya.models.transaction;


import com.ttps.reservasya.error.exceptions.ForbiddenTransactionException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

/*@Entity
@DiscriminatorValue(value = "PENDING")*/
public class PendingTransaction extends StateTransaction implements Serializable {


    public PendingTransaction(){
        this.type = TransactionStates.PENDING;
    }

    @Override
    public void doStart(Transaction transaction) {
        transaction.setTransactionState(STARTED);
    }

    @Override
    public void doCancel(Transaction transaction) {
        transaction.setTransactionState(CANCELLED);
    }

    @Override
    public void doFinish(Transaction transaction) {
        transaction.setTransactionState(FINISHED);
    }

    @Override
    public void doRollBack(Transaction transaction) {
        transaction.setTransactionState(ROLLEDBACK);
    }

    @Override
    public void doPending(Transaction transaction) {
        transaction.setTransactionState(PENDING);
    }

    @Override
    public void doApprove(Transaction transaction) {
        transaction.setTransactionState(APPROVED);
    }
}
