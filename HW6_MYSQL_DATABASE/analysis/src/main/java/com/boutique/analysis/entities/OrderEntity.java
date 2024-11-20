	package com.boutique.analysis.entities;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int order_id;

    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false) 
    private ClientEntity client;

    @Column(name = "order_date")
    private LocalDate orderDate;

    @Column(name = "outfit_type")
    private String outfitType;

    @Column(name = "fabric_type")
    private String fabricType;

    @Column(name = "size")
    private String size;

    @Column(name = "color")
    private String color;

    @Column(name = "accessories")
    private String accessories;

    @Column(name = "status")
    private String status;

    @Column(name = "estimated_delivery_date")
    private LocalDate estimatedDeliveryDate;

    @Column(name = "total_price", nullable = false)
    private double totalPrice;

    public OrderEntity() {
    }

    public OrderEntity(ClientEntity client, LocalDate orderDate, String outfitType, String fabricType, String size,
                       String color, String accessories, String status, LocalDate estimatedDeliveryDate, double totalPrice) {
        this.client = client;
        this.orderDate = orderDate;
        this.outfitType = outfitType;
        this.fabricType = fabricType;
        this.size = size;
        this.color = color;
        this.accessories = accessories;
        this.status = status;
        this.estimatedDeliveryDate = estimatedDeliveryDate;
        this.totalPrice = totalPrice;
    }

    // Getters and Setters
    public int getOrderID() {
        return order_id;
    }

    public void setOrderID(int orderID) {
        this.order_id = orderID;
    }

    public ClientEntity getClient() {
        return client;
    }

    public void setClient(ClientEntity client) {
        this.client = client;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getOutfitType() {
        return outfitType;
    }

    public void setOutfitType(String outfitType) {
        this.outfitType = outfitType;
    }

    public String getFabricType() {
        return fabricType;
    }

    public void setFabricType(String fabricType) {
        this.fabricType = fabricType;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getAccessories() {
        return accessories;
    }

    public void setAccessories(String accessories) {
        this.accessories = accessories;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getEstimatedDeliveryDate() {
        return estimatedDeliveryDate;
    }

    public void setEstimatedDeliveryDate(LocalDate estimatedDeliveryDate) {
        this.estimatedDeliveryDate = estimatedDeliveryDate;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }
}
