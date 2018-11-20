package com.ttps.reservasya.models.transaction;

public class RollbackedTransaction extends StateTransaction {

    @Override
    public StateTransaction doStart(Transaction transaction) {
        return null;
    }

    @Override
    public StateTransaction doCancel(Transaction transaction) {
        return null;
    }

    @Override
    public StateTransaction doFinish(Transaction transaction) {
        return null;
    }

    @Override
    public StateTransaction doPause(Transaction transaction) {
        return null;
    }

    @Override
    public StateTransaction doRollBack(Transaction transaction) {
        return null;
    }
}
