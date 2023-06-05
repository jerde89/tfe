package com.tfe.fournil.controller;

import com.tfe.fournil.entity.DeliveryMode;

import java.time.LocalDate;
import java.util.List;

/**
 * The type New order dto.
 */
public class NewOrderDTO {
    private LocalDate dateOfReceipt;


    private Float total;
    private DeliveryMode deliveryMode;
    private List<NewOrderDetailDto> orderDetails;

    /**
     * Gets date of receipt.
     *
     * @return the date of receipt
     */
    public LocalDate getDateOfReceipt() {
        return dateOfReceipt;
    }

    /**
     * Sets date of receipt.
     *
     * @param dateOfReceipt the date of receipt
     */
    public void setDateOfReceipt(LocalDate dateOfReceipt) {
        this.dateOfReceipt = dateOfReceipt;
    }

    /**
     * Gets total.
     *
     * @return the total
     */
    public Float getTotal() {
        return total;
    }

    /**
     * Sets total.
     *
     * @param total the total
     */
    public void setTotal(Float total) {
        this.total = total;
    }

    /**
     * Gets delivery mode.
     *
     * @return the delivery mode
     */
    public DeliveryMode getDeliveryMode() {
        return deliveryMode;
    }

    /**
     * Sets delivery mode.
     *
     * @param deliveryMode the delivery mode
     */
    public void setDeliveryMode(DeliveryMode deliveryMode) {
        this.deliveryMode = deliveryMode;
    }

    /**
     * Gets order details.
     *
     * @return the order details
     */
    public List<NewOrderDetailDto> getOrderDetails() {
        return orderDetails;
    }

    /**
     * Sets order details.
     *
     * @param orderDetails the order details
     */
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

/**
 * The type New order detail dto.
 */
class NewOrderDetailDto {
    private long productId;
    private Float priceUnit;
    private Integer Quantity;
    private Float total;

    /**
     * Gets product id.
     *
     * @return the product id
     */
    public long getProductId() {
        return productId;
    }

    /**
     * Sets product id.
     *
     * @param productId the product id
     */
    public void setProductId(long productId) {
        this.productId = productId;
    }

    /**
     * Gets price unit.
     *
     * @return the price unit
     */
    public Float getPriceUnit() {
        return priceUnit;
    }

    /**
     * Sets price unit.
     *
     * @param priceUnit the price unit
     */
    public void setPriceUnit(Float priceUnit) {
        this.priceUnit = priceUnit;
    }

    /**
     * Gets quantity.
     *
     * @return the quantity
     */
    public Integer getQuantity() {
        return Quantity;
    }

    /**
     * Sets quantity.
     *
     * @param quantity the quantity
     */
    public void setQuantity(Integer quantity) {
        Quantity = quantity;
    }

    /**
     * Gets total.
     *
     * @return the total
     */
    public Float getTotal() {
        return total;
    }

    /**
     * Sets total.
     *
     * @param total the total
     */
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