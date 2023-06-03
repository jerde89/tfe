package com.tfe.fournil.service;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tfe.fournil.entity.Order;
import com.tfe.fournil.entity.ProductVersion;

import javax.persistence.CascadeType;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

public class OrderDetailDTO {
    private Long idOrderDetail;

    private int quantity;
    private ProductVersionDTO productVersion;

    public Long getIdOrderDetail() {
        return idOrderDetail;
    }

    public void setIdOrderDetail(Long idOrderDetail) {
        this.idOrderDetail = idOrderDetail;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public ProductVersionDTO getProductVersion() {
        return productVersion;
    }

    public void setProductVersion(ProductVersionDTO productVersion) {
        this.productVersion = productVersion;
    }
}
