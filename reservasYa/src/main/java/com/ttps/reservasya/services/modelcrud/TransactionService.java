package com.ttps.reservasya.services.modelcrud;

import com.ttps.reservasya.models.transaction.StateTransaction;
import com.ttps.reservasya.models.transaction.Transaction;
import com.ttps.reservasya.repository.TransactionRepository;
import com.ttps.reservasya.repository.TransactionStateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionService extends BasicCrudService<Transaction, TransactionRepository> {

    private TransactionStateRepository transactionStateRepository;

    public TransactionService(){};

    @Autowired
    public TransactionService(TransactionRepository transactionRepository, TransactionStateRepository transactionStateRepository) {
        super(transactionRepository);
        this.transactionStateRepository = transactionStateRepository;
    }

    public void start(Transaction transaction){
        transaction.start();
        updateOne(transaction);
    }

    public void cancel(Transaction transaction){
        transaction.cancel();
        updateOne(transaction);
    }

    public void approve(Transaction transaction){
        transaction.approve();
        updateOne(transaction);
    }

    public void finish(Transaction transaction){
        transaction.finish();
        updateOne(transaction);
    }

    public void rollback(Transaction transaction){
        transaction.rollBack();
        updateOne(transaction);
    }

    public void begin(Transaction transaction){
        transaction.begin();
        updateOne(transaction);
    }

    public List<StateTransaction> createStates(List<StateTransaction> transactionStates){
        return this.transactionStateRepository.saveAll(transactionStates);
    }

    @Override
    public Transaction createOne(Transaction transaction){
        if (transaction.getState().getId() == null){
            transaction.getState().setId(this.transactionStateRepository.findByType(transaction.getState().getType()).getId());
        }
        return this.repository.save(transaction);
    }

}
