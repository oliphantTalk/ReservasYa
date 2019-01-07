package com.ttps.reservasya.models.transaction;

public enum TransactionStates {
    PENDING, STARTED, FINISHED, ROLLEDBACK, CANCELLED, APPROVED;

    TransactionStates(){}

}
