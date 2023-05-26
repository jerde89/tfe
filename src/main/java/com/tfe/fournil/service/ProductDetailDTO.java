package com.tfe.fournil.service;

import com.tfe.fournil.entity.Product;

public class ProductDetailDTO {
    private Product product;
    private int productQuantity = 0;

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void addProductQuantity(int productQuantity) {
        this.productQuantity += productQuantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }
}
