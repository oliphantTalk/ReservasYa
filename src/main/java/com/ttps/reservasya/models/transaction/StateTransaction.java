package com.ttps.reservasya.models.transaction;


public abstract class StateTransaction {

    public StateTransaction(){};

    protected TransactionStates type;

    public TransactionStates getType() {
        return type;
    }

    public void setType(TransactionStates type) {
        this.type = type;
    }

    protected static final String STARTED = TransactionStates.STARTED.name();
    protected static final String PENDING = TransactionStates.PENDING.name();
    protected static final String APPROVED = TransactionStates.APPROVED.name();
    protected static final String FINISHED = TransactionStates.FINISHED.name();
    protected static final String CANCELLED = TransactionStates.CANCELLED.name();
    protected static final String ROLLEDBACK = TransactionStates.ROLLEDBACK.name();

    public abstract void doPending(Transaction transaction);
    public abstract void doStart(Transaction transaction);
    public abstract void doCancel(Transaction transaction);
    public abstract void doFinish(Transaction transaction);
    public abstract void doRollBack(Transaction transaction);
    public abstract void doApprove(Transaction transaction);
}

