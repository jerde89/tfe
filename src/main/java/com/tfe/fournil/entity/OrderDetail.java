package com.tfe.fournil.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "order_details")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetail {
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Id
    @Column(name = "id_order_detail")
    private Long idOrderDetail;

    private int quantity;

    @JsonIgnore
//    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @ManyToOne()
    private Order order;

    @ManyToOne
    @JoinColumn(name = "product_version_id")
    private ProductVersion productVersion;

    public Float getTotal(){
        return (getProductVersion().getPrice() * getQuantity()) * (((float) getProductVersion().getTaxRate()/100)+1);
    }

    public float getPriceWithTVA(){
        return (getProductVersion().getPrice() ) * (((float) getProductVersion().getTaxRate()/100)+1);
    }
    @Override
    public String toString() {
        return "OrderDetail{" +
                "idOrderDetail=" + idOrderDetail +
                ", quantity=" + quantity +
                ", productVersion=" + productVersion +
                '}';
    }
}
