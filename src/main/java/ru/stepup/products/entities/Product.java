package ru.stepup.products.entities;

import ru.stepup.products.enums.ProductType;

import java.util.Optional;

public class Product {
    private Long id;

    private Long accountNumber;

    private int balance;

    private ProductType type;

    public Product() {
        super();
    }

    public Product(Long accountNumber, int balance, ProductType type) {
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.type = type;
    }

    public Product(Long id, Long accountNumber, int balance, ProductType type) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.type = type;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(Long accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }

    public ProductType getType() {
        return type;
    }

    public void setType(String type) {
        this.type = ProductType.valueOf(type.toUpperCase());
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", accountNumber=" + accountNumber +
                ", balance=" + balance +
                ", type=" + type +
                '}';
    }
}
