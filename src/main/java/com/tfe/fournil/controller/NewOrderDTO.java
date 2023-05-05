package com.tfe.fournil.controller;

import com.tfe.fournil.entity.DeliveryMode;

import java.time.LocalDate;
import java.util.List;

public class NewOrderDTO {
    private LocalDate dateOfReceipt;


    private Float total;
    private DeliveryMode deliveryMode;
    private List<NewOrderDetailDto> orderDetails;

    public LocalDate getDateOfReceipt() {
        return dateOfReceipt;
    }

    public void setDateOfReceipt(LocalDate dateOfReceipt) {
        this.dateOfReceipt = dateOfReceipt;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public DeliveryMode getDeliveryMode() {
        return deliveryMode;
    }

    public void setDeliveryMode(DeliveryMode deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    public List<NewOrderDetailDto> getOrderDetails() {
        return orderDetails;
    }

    public void setOrderDetails(List<NewOrderDetailDto> orderDetails) {
        this.orderDetails = orderDetails;
    }

    @Override
    public String toString() {
        return "NewOrderDTO{" +
                "dateOfReceipt=" + dateOfReceipt +
                ", total=" + total +
                ", deliveryMode=" + deliveryMode +
                ", orderDetails=" + orderDetails +
                '}';
    }
}

class NewOrderDetailDto {
    private long productId;
    private Float priceUnit;
    private Integer Quantity;
    private Float total;

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public Float getPriceUnit() {
        return priceUnit;
    }

    public void setPriceUnit(Float priceUnit) {
        this.priceUnit = priceUnit;
    }

    public Integer getQuantity() {
        return Quantity;
    }

    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    @Override
    public String toString() {
        return "NewOrderDetailDto{" +
                "productId=" + productId +
                ", priceUnit=" + priceUnit +
                ", Quantity=" + Quantity +
                ", total=" + total +
                '}';
    }
}