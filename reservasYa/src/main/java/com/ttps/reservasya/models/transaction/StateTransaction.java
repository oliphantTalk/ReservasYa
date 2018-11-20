package com.ttps.reservasya.models.transaction;

import javax.persistence.Embeddable;

@Embeddable
public abstract class StateTransaction {

    public static StateTransaction start(){
        return new PendingTransaction();
    }

    public abstract StateTransaction doStart(Transaction transaction);
    public abstract StateTransaction doCancel(Transaction transaction);
    public abstract StateTransaction doFinish(Transaction transaction);
    public abstract StateTransaction doPause(Transaction transaction);
    public abstract StateTransaction doRollBack(Transaction transaction);
}
