package com.fisthu.mazebank.model;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;

public class SavingAccount extends Account {

    // withdraw limit
    private final DoubleProperty withdrawLimit;

    public SavingAccount(String owner, String accountNumber, Double balance, Double limit) {
        super(owner, accountNumber, balance);

        this.withdrawLimit = new SimpleDoubleProperty(this, "WithdrawLimit", limit);
    }

    public DoubleProperty withdrawLimitProperty() {
        return withdrawLimit;
    }
}
