package com.ttps.reservasya.models.transaction;

import com.ttps.reservasya.exceptions.ForbiddenTransactionException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue(value = "APPROVED")
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
        transaction.setState(new CancelledTransaction());
    }

    @Override
    public void doFinish(Transaction transaction) {
        transaction.setState(new FinishedTransaction());
    }

    @Override
    public void doRollBack(Transaction transaction) {
        transaction.setState(new RolledbackTransaction());
    }

    @Override
    public void doApprove(Transaction transaction) {
        transaction.setState(this);
    }
}
