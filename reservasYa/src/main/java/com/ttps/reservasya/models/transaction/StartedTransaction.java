package com.ttps.reservasya.models.transaction;

import com.ttps.reservasya.error.exceptions.ForbiddenTransactionException;

import java.io.Serializable;

/*@Entity
@DiscriminatorValue(value = "STARTED")*/
public class StartedTransaction extends StateTransaction implements Serializable {


    public StartedTransaction(){
        super();
        this.type = TransactionStates.STARTED;
    }

    @Override
    public void doPending(Transaction transaction) { throw new ForbiddenTransactionException();}

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
        throw new ForbiddenTransactionException();
    }

    @Override
    public void doRollBack(Transaction transaction) {
        transaction.setTransactionState(ROLLEDBACK);
    }

    @Override
    public void doApprove(Transaction transaction) {
        transaction.setTransactionState(APPROVED);
    }
}
