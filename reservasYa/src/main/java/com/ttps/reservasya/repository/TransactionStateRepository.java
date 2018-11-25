package com.ttps.reservasya.repository;

import com.ttps.reservasya.models.transaction.StateTransaction;
import com.ttps.reservasya.models.transaction.TransactionStates;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionStateRepository extends JpaRepository<StateTransaction, Long> {

    public StateTransaction findByType(TransactionStates type);

}
