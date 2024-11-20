package com.boutique.analysis.controllers;

import com.boutique.analysis.models.Order;
import com.boutique.analysis.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @Autowired
    private OrderService orderService;
    
    @GetMapping
    public List<Order> getAllOrders() {
        return orderService.getAllOrders();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getOrderById(@PathVariable int id) {
        Optional<Order> order = orderService.getOrderById(id);
        return order.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    @GetMapping("/status/{status}")
    public List<Order> getOrdersByStatus(@PathVariable String status) {
        return orderService.getOrdersByStatus(status);
    }

    @GetMapping("/most-expensive")
    public ResponseEntity<Order> getMostExpensiveOrder() {
        Order order = orderService.getMostExpensiveOrder();
        return (order != null) ? ResponseEntity.ok(order) : ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @GetMapping("/total-revenue")
    public double calculateTotalRevenue() {
        return orderService.calculateTotalRevenue();
    }

    @PostMapping("/add/existing-client/{clientId}")
    public ResponseEntity<Order> addOrderForExistingClient(@RequestBody Order newOrder, @PathVariable int clientId) {
        Order addedOrder = orderService.addOrder(newOrder, clientId);
        return addedOrder != null ? ResponseEntity.status(HttpStatus.CREATED).body(addedOrder)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @GetMapping("/client/{clientId}")
    public List<Order> getOrdersByClientId(@PathVariable int clientId) {
        return orderService.getOrdersByClientId(clientId);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable int id, @RequestBody Order updatedOrder) {
        updatedOrder.setOrderID(id); // Ensure the ID in the URL matches the order object
        Order updated = orderService.updateOrder(updatedOrder);
        return updated != null ? ResponseEntity.ok(updated)
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteOrder(@PathVariable int id) {
        orderService.deleteOrder(id);
        return ResponseEntity.ok("Order deleted successfully.");
    }
}
