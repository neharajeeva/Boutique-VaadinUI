package com.boutique.analysis.services;

import com.boutique.analysis.models.Order;
import java.util.List;
import java.util.Optional;

public interface OrderService {
	
    List<Order> getAllOrders();

    Optional<Order> getOrderById(int orderId);

    List<Order> getOrdersByStatus(String status);

    Order getMostExpensiveOrder();

    List<Order> getOrdersByDateRange(String startDate, String endDate);

    double calculateTotalRevenue();

    Order addOrder(Order newOrder, int clientId);

    Order addOrderWithNewClient(Order newOrder, String clientName, String email, String phoneNumber); 

    Order updateOrder(Order updatedOrder);

    void deleteOrder(int orderId);

    List<Order> getOrdersByClientId(int clientId); 
}
