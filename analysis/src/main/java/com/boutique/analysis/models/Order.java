package com.boutique.analysis.models;

import java.time.LocalDate;

public class Order {

    private int orderID;
//    private String clientName;
    private Client client;
    private LocalDate orderDate;
    private String outfitType;
    private String fabricType;
    private String size;
    private String color;
    private String accessories;
    private String status;
    private LocalDate estimatedDeliveryDate;
    private double totalPrice;

    public Order() {
    }

    public Order(int orderId, LocalDate localDate, String outfitType, String fabricType, String size,
                 String color, String accessories, String status, LocalDate localDate2, double totalPrice) {
    	this.orderID = orderId;
//        this.clientName = clientName;
        this.orderDate = localDate;
        this.outfitType = outfitType;
        this.fabricType = fabricType;
        this.size = size;
        this.color = color;
        this.accessories = accessories;
        this.status = status;
        this.estimatedDeliveryDate = localDate2;
        this.totalPrice = totalPrice;
    }
    
    
    public Order(int id, Client client, LocalDate localDate, String outfitType, String fabricType, String size,
            String color, String accessories, String status, LocalDate localDate2, double totalPrice) {
    	this.orderID = id;
	   this.client = client;
	   this.orderDate = localDate;
	   this.outfitType = outfitType;
	   this.fabricType = fabricType;
	   this.size = size;
	   this.color = color;
	   this.accessories = accessories;
	   this.status = status;
	   this.estimatedDeliveryDate = localDate2;
	   this.totalPrice = totalPrice;
}

    // Getters and Setters
    public int getOrderID() { return orderID; }
    public void setOrderID(int orderID) { this.orderID = orderID; }

//    public String getClientName() { return clientName; }
//    public void setClientName(String clientName) { this.clientName = clientName; }

    public LocalDate getOrderDate() { return orderDate; }
    public void setOrderDate(LocalDate orderDate) { this.orderDate = orderDate; }

    public String getOutfitType() { return outfitType; }
    public void setOutfitType(String outfitType) { this.outfitType = outfitType; }

    public String getFabricType() { return fabricType; }
    public void setFabricType(String fabricType) { this.fabricType = fabricType; }

    public String getSize() { return size; }
    public void setSize(String size) { this.size = size; }

    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }

    public String getAccessories() { return accessories; }
    public void setAccessories(String accessories) { this.accessories = accessories; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public LocalDate getEstimatedDeliveryDate() { return estimatedDeliveryDate; }
    public void setEstimatedDeliveryDate(LocalDate estimatedDeliveryDate) { this.estimatedDeliveryDate = estimatedDeliveryDate; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

	public Client getClient() {return client;}
	public void setClient(Client client) { this.client = client;}
}
