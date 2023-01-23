package com.fisthu.mazebank.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

import java.time.LocalDate;

public class Client {
    private final StringProperty firstname;
    private final StringProperty lastname;

    private final StringProperty payeeAddress;

    private final ObjectProperty<Account> checkingAccount;
    private final ObjectProperty<Account> savingAccount;

    private final ObjectProperty<LocalDate> dateCreated;

    public Client(String firstname, String lastname, String payeeAddress, Account checkingAccount, Account savingAccount, LocalDate dateCreated) {
        this.firstname = new SimpleStringProperty(this, "First Name", firstname);
        this.lastname = new SimpleStringProperty(this, "Last Name", lastname);
        this.payeeAddress = new SimpleStringProperty(this, "Payee Address", payeeAddress);
        this.checkingAccount = new SimpleObjectProperty<>(this, "Checking Account", checkingAccount);
        this.savingAccount = new SimpleObjectProperty<>(this, "Saving Account", savingAccount);
        this.dateCreated = new SimpleObjectProperty<>(this, "Date Created", dateCreated);
    }

    public StringProperty firstnameProperty() {
        return firstname;
    }

    public StringProperty lastnameProperty() {
        return lastname;
    }

    public StringProperty payeeAddressProperty() {
        return payeeAddress;
    }

    public ObjectProperty<Account> checkingAccountProperty() {
        return checkingAccount;
    }

    public ObjectProperty<Account> savingAccountProperty() {
        return savingAccount;
    }

    public ObjectProperty<LocalDate> dateCreatedProperty() {
        return dateCreated;
    }
}
