package com.tfe.fournil.service;

import com.tfe.fournil.entity.Product;

import javax.persistence.Column;

/**
 * The type Product version dto.
 */
public class ProductVersionDTO {
    private Long id;

    private float price;

    private int taxRate;

    private Product product;

    /**
     * Gets id.
     *
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Gets price.
     *
     * @return the price
     */
    public float getPrice() {
        return price;
    }

    /**
     * Sets price.
     *
     * @param price the price
     */
    public void setPrice(float price) {
        this.price = price;
    }

    /**
     * Gets tax rate.
     *
     * @return the tax rate
     */
    public int getTaxRate() {
        return taxRate;
    }

    /**
     * Sets tax rate.
     *
     * @param taxRate the tax rate
     */
    public void setTaxRate(int taxRate) {
        this.taxRate = taxRate;
    }

    /**
     * Gets product.
     *
     * @return the product
     */
    public Product getProduct() {
        return product;
    }

    /**
     * Sets product.
     *
     * @param product the product
     */
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
