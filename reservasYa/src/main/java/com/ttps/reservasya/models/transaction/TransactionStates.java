package com.ttps.reservasya.models.transaction;

public enum TransactionStates {
    PENDING, STARTED, FINISHED, ROLLBACKED, PAUSED, CANCELLED;

    TransactionStates(){}

}
