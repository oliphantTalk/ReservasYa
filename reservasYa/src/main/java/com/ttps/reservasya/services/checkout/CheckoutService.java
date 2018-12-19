package com.ttps.reservasya.services.checkout;

import com.ttps.reservasya.controllers.checkout.CheckoutForm;
import com.ttps.reservasya.models.businessitem.BusinessItem;
import com.ttps.reservasya.models.transaction.PaymentData;
import com.ttps.reservasya.models.transaction.Transaction;
import com.ttps.reservasya.models.user.User;
import com.ttps.reservasya.models.user.history.UserTransactionHistory;
import com.ttps.reservasya.models.user.settings.UserSettings;
import com.ttps.reservasya.repository.transaction.UserTransactionHistoryRepository;
import com.ttps.reservasya.repository.user.UserSettingsRepository;
import com.ttps.reservasya.services.transaction.TransactionService;
import com.ttps.reservasya.services.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class CheckoutService {


    @Autowired
    private UserService userService;

    @Autowired
    private TransactionService transactionService;

    @Autowired
    private UserTransactionHistoryRepository historyRepository;

    @Autowired
    private UserSettingsRepository userSettingsRepository;

    public void startTransaction(String loggedUserName, List<BusinessItem> items) {
        User loggedUser = userService.findByUserName(loggedUserName);
        Transaction transaction = createTransaction(loggedUser, items);
        createTransactionHistory(loggedUser, transaction);
    }


    public void approveTransaction(String loggedUserName, int pointsToConvert, CheckoutForm form){
        User loggedUser = userService.findByUserName(loggedUserName);
        UserTransactionHistory history = this.getUserTransactionHistory(loggedUser);
        Optional<Transaction> lastTransaction = transactionService.findById(history.getLastTransactionId());
        PaymentData paymentData = new PaymentData();
        try {
            transactionService.approve(lastTransaction.get(), pointsToConvert);
            paymentData = createPaymentDataFromCheckout(form);
        }
        catch (Exception e){
            //Cancelar
        }
        finishSuccesfulTransaction(loggedUser, lastTransaction.get(), paymentData);
    }

    private PaymentData createPaymentDataFromCheckout(CheckoutForm form) {
        PaymentData paymentData = new PaymentData();
        paymentData.setPassengerName(form.getPassengerName());
        paymentData.setPassengerLastName(form.getPassengerLastName());
        paymentData.setDni(String.valueOf(form.getDni()));
        paymentData.setCreditCard(form.getCreditCard());
        return paymentData;
    }


    private void finishSuccesfulTransaction(User loggedUser, Transaction lastTransaction, PaymentData paymentData){
        try {
            transactionService.finish(lastTransaction, paymentData);
            UserSettings userSettings = userSettingsRepository.findByUser(loggedUser).get();
            int previousPoints = userSettings.getPointsToUse();
            userSettings.setPointsToUse(lastTransaction.getConvertedPoints() - previousPoints);
        }
        catch (Exception e){
            //CANCELAR
        }
    }

    private void createTransactionHistory(User loggedUser, Transaction transaction) {
        UserTransactionHistory transactionHistory = getUserTransactionHistory(loggedUser);
        transactionHistory.getTransactionList().add(transaction);
        transactionHistory.getLastTransactionId();
        historyRepository.save(transactionHistory);
    }

    private UserTransactionHistory getUserTransactionHistory(User loggedUser) {
        return historyRepository.findByUser(loggedUser).orElseGet(() -> new UserTransactionHistory(loggedUser));
    }

    private Transaction createTransaction(User loggedUser, List<BusinessItem> items) {
        Transaction transaction = new Transaction();
        transaction.setUser(loggedUser);
        transaction.getItems().addAll(items);
        transactionService.createOne(transaction);
        try {
            transactionService.start(transaction);
        }
        catch (Exception e){
            //Cancelar
        }
        return transaction;
    }

    private User getLoggedUser(String username) {
        return userService.findByUserName(username);
    }

}
