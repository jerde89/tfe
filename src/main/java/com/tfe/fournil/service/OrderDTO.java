package com.tfe.fournil.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.tfe.fournil.entity.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Order dto.
 */
public class OrderDTO {
    private Long id;
    private LocalDate creationDate;

    private LocalDate deliveryDate;

    private OrderStatus status;

    private DeliveryMode deliveryMode;

    private Boolean paid;
    private UserDTO user;

    private List<OrderDetailDTO> OrderDetailDTOs = new ArrayList<>();

    private float total;

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
     * Gets creation date.
     *
     * @return the creation date
     */
    public LocalDate getCreationDate() {
        return creationDate;
    }

    /**
     * Sets creation date.
     *
     * @param creationDate the creation date
     */
    public void setCreationDate(LocalDate creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * Gets delivery date.
     *
     * @return the delivery date
     */
    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    /**
     * Sets delivery date.
     *
     * @param deliveryDate the delivery date
     */
    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public OrderStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(OrderStatus status) {
        this.status = status;
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

    public Boolean getPaid() {
        return paid;
    }

    public void setPaid(Boolean paid) {
        this.paid = paid;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public UserDTO getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(UserDTO user) {
        this.user = user;
    }

    /**
     * Gets order detail dt os.
     *
     * @return the order detail dt os
     */
    public List<OrderDetailDTO> getOrderDetailDTOs() {
        return OrderDetailDTOs;
    }

    /**
     * Sets order detail dt os.
     *
     * @param orderDetailDTOs the order detail dt os
     */
    public void setOrderDetailDTOs(List<OrderDetailDTO> orderDetailDTOs) {
        OrderDetailDTOs = orderDetailDTOs;
    }

    /**
     * Gets total.
     *
     * @return the total
     */
    public float getTotal() {
        return total;
    }

    /**
     * Sets total.
     *
     * @param total the total
     */
    public void setTotal(float total) {
        this.total = total;
    }
}
