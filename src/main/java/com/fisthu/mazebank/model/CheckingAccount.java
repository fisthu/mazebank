package com.fisthu.mazebank.model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class CheckingAccount extends Account {

    // the number of transactions a client is allowed to do per day
    private final IntegerProperty transactionLimit;

    public CheckingAccount(String owner, String accountNumber, Double balance, int limit) {
        super(owner, accountNumber, balance);

        this.transactionLimit = new SimpleIntegerProperty(this, "TransactionLimit", limit);
    }

    public IntegerProperty transactionLimitProperty() {
        return transactionLimit;
    }
}
