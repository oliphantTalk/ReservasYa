package com.ttps.reservasya.models.transaction;


import com.ttps.reservasya.error.exceptions.ForbiddenTransactionException;

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
        transaction.setState(new StartedTransaction());
    }

    @Override
    public void doCancel(Transaction transaction) {
        transaction.setState(new CancelledTransaction());
    }

    @Override
    public void doFinish(Transaction transaction) {
        throw new ForbiddenTransactionException();
    }

    @Override
    public void doRollBack(Transaction transaction) {
        transaction.setState(new RolledbackTransaction());
    }

    @Override
    public void doPending(Transaction transaction) {
        transaction.setState(this);
    }

    @Override
    public void doApprove(Transaction transaction) {
        throw new ForbiddenTransactionException();
    }
}
