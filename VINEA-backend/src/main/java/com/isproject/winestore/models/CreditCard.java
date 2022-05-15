package com.isproject.winestore.models;

import javax.persistence.*;

@Entity
@Table(name = "credit_cards")
public class CreditCard {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "credit_cards_id")
    @SequenceGenerator(name = "credit_cards_id", sequenceName = "credit_cards_id_seq",
            initialValue = 5, allocationSize = 1)
    private long id;
    private String name;
    private String surname;
    private String iban;
    private String controlNumber;
    private String worthDate;

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    private User user;

    public CreditCard(String name, String surname, String iban, String controlNumber,
                         String worthDate, User user) {
        this.name = name;
        this.surname = surname;
        this.iban = iban;
        this.controlNumber = controlNumber;
        this.worthDate = worthDate;
        this.user = user;
    }

    public CreditCard() {

    }

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "credit_cards_id")
    @SequenceGenerator(name = "credit_cards_id", sequenceName = "credit_cards_id_seq",
            initialValue = 5, allocationSize = 1)
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

    @ManyToOne
    @JoinColumn(referencedColumnName = "id")
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
