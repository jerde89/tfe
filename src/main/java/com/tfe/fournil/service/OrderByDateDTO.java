package com.tfe.fournil.service;

import com.tfe.fournil.entity.OrderDetail;
import com.tfe.fournil.entity.OrderStatus;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class OrderByDateDTO {
    LocalDate deliveryDate;
    int productQuantity = 0;
    OrderStatus Status;
    List<Long> orderIds = new ArrayList<>();
    Map<Long, ProductDetailDTO> ProductDetailDTOMap = new HashMap<>();

    public LocalDate getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDate deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public int getProductQuantity() {
        return productQuantity;
    }

    public void addProductQuantity(int quantity) {
        productQuantity += quantity;
    }

    public void setProductQuantity(int productQuantity) {
        this.productQuantity = productQuantity;
    }

    public OrderStatus getStatus() {
        return Status;
    }

    public void setStatus(OrderStatus status) {
        Status = status;
    }

    public List<Long> getOrderIds() {
        return orderIds;
    }

    public void addOrderIds(Long id) {
        orderIds.add(id);
    }

    public void setOrderIds(List<Long> orderIds) {
        this.orderIds = orderIds;
    }

    public Map<Long, ProductDetailDTO> getProductDetailDTOMap() {
        return ProductDetailDTOMap;
    }

    public void addProductDetailDTO(OrderDetail orderDetail) {
        long idProduct = orderDetail.getProductVersion().getProduct().getIdProduct();
        ProductDetailDTO ProductDetailDTO;
        if (ProductDetailDTOMap.containsKey(idProduct)) {
            ProductDetailDTO = ProductDetailDTOMap.get(idProduct);

        } else {
            ProductDetailDTO = new ProductDetailDTO();
            ProductDetailDTO.setProduct(orderDetail.getProductVersion().getProduct());
        }
        ProductDetailDTO.addProductQuantity(orderDetail.getQuantity());
        ProductDetailDTOMap.put(idProduct, ProductDetailDTO);
    }

    public void addToto2(ProductDetailDTO toto3) {
        ProductDetailDTOMap.put(toto3.getProduct().getIdProduct(), toto3);
    }

    public void setProductDetailDTOMap(Map<Long, ProductDetailDTO> ProductDetailDTOMap) {
        this.ProductDetailDTOMap = ProductDetailDTOMap;
    }
}
