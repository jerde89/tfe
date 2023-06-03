package com.tfe.fournil.service;

import com.tfe.fournil.entity.Product;

import javax.persistence.Column;

public class ProductVersionDTO {
    private Long id;

    private float price;

    private int taxRate;

    private Product product;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    @Override
    public String toString() {
        return "ProductVersionDTO{" +
                "id=" + id +
                ", price=" + price +
                ", taxRate=" + taxRate +
                '}';
    }
}
