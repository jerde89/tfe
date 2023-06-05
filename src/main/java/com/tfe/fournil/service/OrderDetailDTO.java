package com.tfe.fournil.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tfe.fournil.entity.Order;
import com.tfe.fournil.entity.ProductVersion;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * The type Order detail dto.
 */
public class OrderDetailDTO {
    private Long idOrderDetail;

    private int quantity;
    private ProductVersionDTO productVersion;

    /**
     * Gets id order detail.
     *
     * @return the id order detail
     */
    public Long getIdOrderDetail() {
        return idOrderDetail;
    }

    /**
     * Sets id order detail.
     *
     * @param idOrderDetail the id order detail
     */
    public void setIdOrderDetail(Long idOrderDetail) {
        this.idOrderDetail = idOrderDetail;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
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
