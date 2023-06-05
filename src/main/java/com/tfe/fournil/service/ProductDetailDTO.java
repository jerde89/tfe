package com.tfe.fournil.service;

import com.tfe.fournil.entity.Product;

/**
 * The type Product detail dto.
 */
public class ProductDetailDTO {
    private Product product;
    private int productQuantity = 0;

    private ProductVersionDTO productVersion;

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

    /**
     * Gets product quantity.
     *
     * @return the product quantity
     */
    public int getProductQuantity() {
        return productQuantity;
    }

    /**
     * Add product quantity.
     *
     * @param productQuantity the product quantity
     */
    public void addProductQuantity(int productQuantity) {
        this.productQuantity += productQuantity;
    }

    /**
     * Sets product quantity.
     *
     * @param productQuantity the product quantity
     */
    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    /**
     * Gets product version.
     *
     * @return the product version
     */
    public ProductVersionDTO getProductVersion() {
        return productVersion;
    }

    /**
     * Sets product version.
     *
     * @param productVersion the product version
     */
    public void setProductVersion(ProductVersionDTO productVersion) {
        this.productVersion = productVersion;
    }
}
