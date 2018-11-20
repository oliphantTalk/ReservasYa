package com.ttps.reservasya.services.modelcrud;

import com.ttps.reservasya.models.transaction.Transaction;
import com.ttps.reservasya.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService extends BasicCrudService<Transaction, TransactionRepository> {

    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }

    public void start(Transaction transaction){
        transaction.getState().doStart(transaction);
    }

    public void cancel(Transaction transaction){
        transaction.getState().doCancel(transaction);
    }

    public void pause(Transaction transaction){
        transaction.getState().doPause(transaction);
    }

    public void finish(Transaction transaction){
        transaction.getState().doFinish(transaction);
    }


}
