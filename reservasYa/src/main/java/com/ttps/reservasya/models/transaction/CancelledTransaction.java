package com.ttps.reservasya.models.transaction;

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

    }

    @Override
    public void doCancel(Transaction transaction) {

    }

    @Override
    public void doFinish(Transaction transaction) {

    }

    @Override
    public void doPause(Transaction transaction) {

    }

    @Override
    public void doRollBack(Transaction transaction) {

    }
}
