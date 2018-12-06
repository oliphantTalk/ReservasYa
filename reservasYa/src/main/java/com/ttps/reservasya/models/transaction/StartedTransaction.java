package com.ttps.reservasya.models.transaction;

import com.ttps.reservasya.error.exceptions.ForbiddenTransactionException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue(value = "STARTED")
public class StartedTransaction extends StateTransaction implements Serializable {


    public StartedTransaction(){
        super();
        this.type = TransactionStates.STARTED;
    }

    @Override
    public void doPending(Transaction transaction) { throw new ForbiddenTransactionException();}

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
        throw new ForbiddenTransactionException();
    }

    @Override
    public void doRollBack(Transaction transaction) {
        transaction.setState(new RolledbackTransaction());
    }

    @Override
    public void doApprove(Transaction transaction) {
        transaction.setState(new ApprovedTransaction());
    }
}
