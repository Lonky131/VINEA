package com.isproject.winestore.dto.credit_card;

public class AddCreditCardDTO {

    private String name;
    private String surname;
    private String iban;
    private String controlNumber;
    private String worthDate;
    private long userId;

    public AddCreditCardDTO(String name, String surname, String iban, String controlNumber,
                         String worthDate, long userId) {
        this.name = name;
        this.surname = surname;
        this.iban = iban;
        this.controlNumber = controlNumber;
        this.worthDate = worthDate;
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getIban() {
        return iban;
    }

    public void setIban(String iban) {
        this.iban = iban;
    }

    public String getControlNumber() {
        return controlNumber;
    }

    public void setControlNumber(String controlNumber) {
        this.controlNumber = controlNumber;
    }

    public String getWorthDate() {
        return worthDate;
    }

    public void setWorthDate(String worthDate) {
        this.worthDate = worthDate;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
