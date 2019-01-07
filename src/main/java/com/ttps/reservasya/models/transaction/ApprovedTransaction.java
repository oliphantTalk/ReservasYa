package com.ttps.reservasya.models.transaction;

import com.ttps.reservasya.error.exceptions.ForbiddenTransactionException;

import java.io.Serializable;

/*@Entity
@DiscriminatorValue(value = "APPROVED")*/
public class ApprovedTransaction extends StateTransaction implements Serializable {

    public ApprovedTransaction(){
        super();
        this.type = TransactionStates.APPROVED;
    }

    @Override
    public void doPending(Transaction transaction) {
        throw new ForbiddenTransactionException();
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
        transaction.setTransactionState(FINISHED);
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
