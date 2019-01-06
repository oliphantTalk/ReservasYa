package com.ttps.reservasya.services.transaction;

import com.ttps.reservasya.models.LocalParameters;
import com.ttps.reservasya.models.transaction.PaymentData;
import com.ttps.reservasya.models.transaction.Transaction;
import com.ttps.reservasya.repository.transaction.TransactionRepository;
import com.ttps.reservasya.services.BasicCrudService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService extends BasicCrudService<Transaction, TransactionRepository> {


    public TransactionService(){};

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        super(transactionRepository);
    }

    public void start(Transaction transaction){
        transaction.start();
        //transaction.buildStateTransaction();
        transaction.setPaymentData(new PaymentData());
        createOne(transaction);
    }

    public void cancel(Transaction transaction){
        transaction.cancel();
        transaction.getPaymentData().reject();

        updateOne(transaction);
    }

    public void approve(Transaction transaction, int pointsToConvert){
        transaction.approve();
        transaction.setConvertedPoints(pointsToConvert);
        usePointsToPay(transaction, pointsToConvert);
        updateOne(transaction);
    }

    private void usePointsToPay(Transaction transaction, int pointsToConvert) {
        double cashByPoints = pointsToConvert * LocalParameters.pesosPorPunto;
        transaction.getPaymentData().setCashByPoints(String.valueOf(cashByPoints));
        transaction.getPaymentData().setCashAmount(String.valueOf(transaction.getAmount() - cashByPoints));
    }

    public void finish(Transaction transaction, PaymentData paymentData){
        transaction.finish();
        finishPaymentData(transaction, paymentData);
        updateOne(transaction);
    }

    private void finishPaymentData(Transaction transaction, PaymentData paymentData) {
        transaction.getPaymentData().approve();
        transaction.getPaymentData().setPassengerName(paymentData.getPassengerName());
        transaction.getPaymentData().setPassengerLastName(paymentData.getPassengerLastName());
        transaction.getPaymentData().setCreditCard(paymentData.getCreditCard());
        transaction.getPaymentData().setDni(paymentData.getDni());
    }

    public void rollback(Transaction transaction){
        transaction.rollBack();
        updateOne(transaction);
    }

    public void begin(Transaction transaction){
        transaction.begin();
        updateOne(transaction);
    }


}
