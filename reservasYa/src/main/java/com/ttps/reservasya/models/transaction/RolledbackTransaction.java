package com.ttps.reservasya.models.transaction;

import com.ttps.reservasya.error.exceptions.ForbiddenTransactionException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

/*@Entity
@DiscriminatorValue(value = "ROLLEDBACK")*/
public class RolledbackTransaction extends StateTransaction implements Serializable {

    public RolledbackTransaction(){
        super();
        this.type = TransactionStates.ROLLEDBACK;
    }

    @Override
    public void doStart(Transaction transaction) {
        throw new ForbiddenTransactionException();
    }

    @Override
    public void doCancel(Transaction transaction) {
        transaction.setTransactionState(CANCELLED);
    }

    @Override
    public void doFinish(Transaction transaction) {
        throw new ForbiddenTransactionException();
    }

    @Override
    public void doRollBack(Transaction transaction) {
        transaction.setTransactionState(ROLLEDBACK);
    }

    @Override
    public void doPending(Transaction transaction) {
        throw new ForbiddenTransactionException();
    }

    @Override
    public void doApprove(Transaction transaction) {
        throw new ForbiddenTransactionException();
    }
}
