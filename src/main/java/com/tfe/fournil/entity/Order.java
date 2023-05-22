package com.tfe.fournil.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicReference;

@Entity
@Table(name ="orders")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id_order")
    private Long idOrder;

    @Column(name = "order_date")
    private LocalDate order_date;

    @Column(name = "date_of_receipt")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private LocalDate dateOfReceipt;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false, length = 50)
    private OrderStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "delivery_mode", nullable = false, length = 50)
    private DeliveryMode  deliveryMode;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn (name = "id_shop")
    private Shop shop;

    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn (name = "id_user")
    private User user;
//
//    @OneToMany
//    @JoinColumn(name = "id_order_detail", nullable = false)
//    private List<OrderDetail> orderDetails;
//mappedBy = "post"
    //, mappedBy = "POST"
    @OneToMany(cascade = CascadeType.ALL,
            fetch = FetchType.LAZY
            )
    private Set<OrderDetail> orderDetails = new HashSet<>();

    private float total;

    public Float getTotal(){
        AtomicReference<Float> totalOrder = new AtomicReference<>((float) 0);
        this.getOrderDetails().forEach(orderDetail ->  {
            float productPrice = (orderDetail.getPrice() * orderDetail.getQuantity()) * (((float) orderDetail.getProduct().getTaxRate()/100)+1);
            totalOrder.updateAndGet(v -> v + productPrice);
        });
        return totalOrder.get();
    }
}
