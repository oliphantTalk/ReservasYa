package com.ttps.reservasya.models.transaction;

import com.ttps.reservasya.error.exceptions.ForbiddenTransactionException;

import java.io.Serializable;

/*Entity
@DiscriminatorValue(value = "FINISHED")*/
public class FinishedTransaction extends StateTransaction implements Serializable {


    public FinishedTransaction(){
        super();
        this.type = TransactionStates.FINISHED;
    }

    @Override
    public void doStart(Transaction transaction) {
        throw new ForbiddenTransactionException();
    }

    @Override
    public void doCancel(Transaction transaction) {
        throw new ForbiddenTransactionException();
    }

    @Override
    public void doFinish(Transaction transaction) {
        transaction.setTransactionState(FINISHED);
    }

    @Override
    public void doRollBack(Transaction transaction) {
        throw new ForbiddenTransactionException();
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
