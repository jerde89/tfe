package com.tfe.fournil.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@Entity
@Table(name = "orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id_order")
    private Long idOrder;

    @Column(name = "creation_date")
    private LocalDate creationDate;

    @Column(name = "delivery_Date")
    @JsonFormat(pattern = "dd/MM/yyyy")
    @NotNull(message = "Date de livraison ne peut etre vide")
    private LocalDate deliveryDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_mode", nullable = false, length = 50)
    private DeliveryMode deliveryMode;

    @JsonIgnore
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_shop")
    private Shop shop;


    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "id_user")
    private User user;


    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "order_id_order")
    private List<OrderDetail> orderDetails = new ArrayList<>();

    public Float getTotal(){
        AtomicReference<Float> totalOrder = new AtomicReference<>((float) 0);
        this.getOrderDetails().forEach(orderDetail ->  {
            float productPrice = (orderDetail.getProductVersion().getPrice() * orderDetail.getQuantity()) * (((float) orderDetail.getProductVersion().getTaxRate()/100)+1);
            totalOrder.updateAndGet(v -> v + productPrice);
        });
        return totalOrder.get();
    }

    public int getTotalProduct(){
        return this.getOrderDetails().stream().mapToInt(OrderDetail::getQuantity).sum();
    }
}
