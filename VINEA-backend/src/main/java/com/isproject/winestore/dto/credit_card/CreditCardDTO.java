package com.isproject.winestore.dto.credit_card;

public class CreditCardDTO {

    private long id;
    private String name;
    private String surname;
    private String iban;
    private String controlNumber;
    private String worthDate;
    private long userId;

    public CreditCardDTO(long id, String name, String surname, String iban, String controlNumber,
                         String worthDate, long userId) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.iban = iban;
        this.controlNumber = controlNumber;
        this.worthDate = worthDate;
        this.userId = userId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
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
