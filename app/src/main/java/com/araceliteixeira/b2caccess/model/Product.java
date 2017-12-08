package com.araceliteixeira.b2caccess.model;

/**
 * Created by araceliteixeira on 07/12/17.
 */

public class Product {
    private long id;
    private String description;
    private double price;
    private double discount;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    @Override
    public String toString() {
        return description;
    }
}
