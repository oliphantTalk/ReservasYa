package com.ttps.reservasya.models.transaction;

import com.ttps.reservasya.error.exceptions.ForbiddenTransactionException;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import java.io.Serializable;

@Entity
@DiscriminatorValue(value = "CANCELLED")
public class CancelledTransaction extends StateTransaction implements Serializable {

   public CancelledTransaction(){
       super();
       this.type = TransactionStates.CANCELLED;
   }

    @Override
    public void doStart(Transaction transaction) {
        throw new ForbiddenTransactionException();
    }

    @Override
    public void doCancel(Transaction transaction) {
        transaction.setState(this);
    }

    @Override
    public void doFinish(Transaction transaction) {
        throw new ForbiddenTransactionException();
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
