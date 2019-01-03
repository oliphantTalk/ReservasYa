package com.ttps.reservasya.models.transaction;

public class PendingTransaction extends StateTransaction {

    public PendingTransaction(){
        this.type = TransactionStates.PENDING;
    }

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
        transaction.setTransactionState(FINISHED);
    }

    @Override
    public void doRollBack(Transaction transaction) {
        transaction.setTransactionState(ROLLEDBACK);
    }

    @Override
    public void doPending(Transaction transaction) {
        transaction.setTransactionState(PENDING);
    }

    @Override
    public void doApprove(Transaction transaction) {
        transaction.setTransactionState(APPROVED);
    }
}
