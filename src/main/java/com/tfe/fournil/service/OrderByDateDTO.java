package com.tfe.fournil.service;

import com.tfe.fournil.entity.OrderDetail;
import com.tfe.fournil.entity.OrderStatus;
import com.tfe.fournil.entity.ProductVersion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * The type Order by date dto.
 */
public class OrderByDateDTO {
    /**
     * The Delivery date.
     */
    LocalDate deliveryDate;
    /**
     * The Product quantity.
     */
    int productQuantity = 0;
    /**
     * The Status.
     */
    OrderStatus status;

    /**
     * The Order ids.
     */
    List<Long> orderIds = new ArrayList<>();
    /**
     * The Product detail dto map.
     */
    Map<Long, ProductDetailDTO> ProductDetailDTOMap = new HashMap<>();

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
     * @param quantity the quantity
     */
    public void addProductQuantity(int quantity) {
        productQuantity += quantity;
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
     * Gets order ids.
     *
     * @return the order ids
     */
    public List<Long> getOrderIds() {
        return orderIds;
    }

    /**
     * Add order ids.
     *
     * @param id the id
     */
    public void addOrderIds(Long id) {
        orderIds.add(id);
    }

    /**
     * Sets order ids.
     *
     * @param orderIds the order ids
     */
    public void setOrderIds(List<Long> orderIds) {
        this.orderIds = orderIds;
    }

    /**
     * Gets product detail dto map.
     *
     * @return the product detail dto map
     */
    public Map<Long, ProductDetailDTO> getProductDetailDTOMap() {
        return ProductDetailDTOMap;
    }



    /**
     * Add product detail dto.
     *
     * @param orderDetail the order detail
     */
    public void addProductDetailDTO(OrderDetail orderDetail) {
        long idProduct = orderDetail.getProductVersion().getProduct().getIdProduct();
        ProductDetailDTO productDetailDTO;
        if (ProductDetailDTOMap.containsKey(idProduct)) {
            productDetailDTO = ProductDetailDTOMap.get(idProduct);

        } else {
            productDetailDTO = new ProductDetailDTO();
            ProductVersion productVersion = orderDetail.getProductVersion();
            productDetailDTO.setProduct(productVersion.getProduct());
            ProductVersionDTO productVersionDTO = new ProductVersionDTO();
            productVersionDTO.setId(productVersion.getId());
            productVersionDTO.setPrice(productVersion.getPrice());
            productVersionDTO.setTaxRate(productVersion.getTaxRate());
            productDetailDTO.setProductVersion(productVersionDTO);
        }
        productDetailDTO.addProductQuantity(orderDetail.getQuantity());
        ProductDetailDTOMap.put(idProduct, productDetailDTO);
    }

    /**
     * Add toto 2.
     *
     * @param toto3 the toto 3
     */
    public void addToto2(ProductDetailDTO toto3) {
        ProductDetailDTOMap.put(toto3.getProduct().getIdProduct(), toto3);
    }

    /**
     * Sets product detail dto map.
     *
     * @param ProductDetailDTOMap the product detail dto map
     */
    public void setProductDetailDTOMap(Map<Long, ProductDetailDTO> ProductDetailDTOMap) {
        this.ProductDetailDTOMap = ProductDetailDTOMap;
    }
}
