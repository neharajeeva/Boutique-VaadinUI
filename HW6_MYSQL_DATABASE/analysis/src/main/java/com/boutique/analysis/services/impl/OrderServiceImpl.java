package com.boutique.analysis.services.impl;

import com.boutique.analysis.entities.ClientEntity;
import com.boutique.analysis.entities.OrderEntity;
import com.boutique.analysis.models.Client;
import com.boutique.analysis.models.Order;
import com.boutique.analysis.repositories.ClientRepository;
import com.boutique.analysis.repositories.OrderRepository;
import com.boutique.analysis.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;
    private final ClientRepository clientRepository;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, ClientRepository clientRepository) {
        this.orderRepository = orderRepository;
        this.clientRepository = clientRepository;
    }

    @Override
    public List<Order> getAllOrders() {
        return orderRepository.findAll()
                .stream()
                .map(this::convertToOrderModel)
                .collect(Collectors.toList());
    }
    
    @Override
    public Optional<Order> getOrderById(int orderId) {
        return orderRepository.findById(orderId)
                .map(this::convertToOrderModel);
    }

    @Override
    public List<Order> getOrdersByStatus(String status) {
        return orderRepository.findByStatusIgnoreCase(status)
                .stream()
                .map(this::convertToOrderModel)
                .collect(Collectors.toList());
    }

    @Override
    public Order getMostExpensiveOrder() {
        return getAllOrders().stream()
                .max((o1, o2) -> Double.compare(o1.getTotalPrice(), o2.getTotalPrice()))
                .orElse(null);
    }

    @Override
    public List<Order> getOrdersByDateRange(String startDate, String endDate) {
        LocalDate startLocalDate = LocalDate.parse(startDate);
        LocalDate endLocalDate = LocalDate.parse(endDate);
        return orderRepository.findByOrderDateBetween(startLocalDate, endLocalDate)
                .stream()
                .map(this::convertToOrderModel)
                .collect(Collectors.toList());
    }

    @Override
    public double calculateTotalRevenue() {
        return getAllOrders().stream()
                .mapToDouble(Order::getTotalPrice)
                .sum();
    }

    @Override
    public Order addOrder(Order newOrder, int clientId) {
        Optional<ClientEntity> clientEntityOpt = clientRepository.findById(clientId);
        if (!clientEntityOpt.isPresent()) {
            throw new RuntimeException("Client not found for ID: " + clientId);
        }

        ClientEntity clientEntity = clientEntityOpt.get();
        OrderEntity orderEntity = convertToEntity(newOrder);
        orderEntity.setClient(clientEntity); // Associate the order with the client

        OrderEntity savedEntity = orderRepository.save(orderEntity);
        return convertToOrderModel(savedEntity);
    }

    @Override
    public Order addOrderWithNewClient(Order newOrder, String clientName, String clientEmail, String clientPhone) {
        ClientEntity clientEntity = new ClientEntity(0, clientName, clientEmail, clientPhone);
        clientRepository.save(clientEntity); // Save the new client in the database

        OrderEntity orderEntity = convertToEntity(newOrder);
        orderEntity.setClient(clientEntity); // Associate the order with the new client

        OrderEntity savedEntity = orderRepository.save(orderEntity);
        return convertToOrderModel(savedEntity);
    }

    @Override
    public List<Order> getOrdersByClientId(int clientId) {
        List<OrderEntity> orders = orderRepository.findByClient_ClientId(clientId); // Fetch orders by client ID
        return orders.stream()
                .map(this::convertToOrderModel)
                .collect(Collectors.toList());
    }

    @Override
    public Order updateOrder(Order updatedOrder) {
    	if(updatedOrder.getClient() == null) {
    		Order ord = getOrderById(updatedOrder.getOrderID()).get();
    		updatedOrder.setClient(ord.getClient());
    	}
        OrderEntity savedEntity = orderRepository.save(convertToEntity(updatedOrder));
        return convertToOrderModel(savedEntity);
    }

    @Override
    public void deleteOrder(int orderId) {
        orderRepository.deleteById(orderId);
    }

//    private Order convertToModel(OrderEntity entity) {
//        if (entity == null) return null;
//        return new Order(
//            entity.getOrderID(),
//            entity.getClient().getClientName(), 
//            entity.getOrderDate(),
//            entity.getOutfitType(),
//            entity.getFabricType(),
//            entity.getSize(),
//            entity.getColor(),
//            entity.getAccessories(),
//            entity.getStatus(),
//            entity.getEstimatedDeliveryDate(),
//            entity.getTotalPrice()
//        );
//    }
    
    private Order convertToOrderModel(OrderEntity entity) {
        if (entity == null) return null;
        return new Order(
        	entity.getOrderID(),
            convertToClientModel(entity.getClient()),
            entity.getOrderDate(),
            entity.getOutfitType(),
            entity.getFabricType(),
            entity.getSize(),
            entity.getColor(),
            entity.getAccessories(),
            entity.getStatus(),
            entity.getEstimatedDeliveryDate(),
            entity.getTotalPrice()
        );
    }

    private OrderEntity convertToEntity(Order model) {
        if (model == null) return null;
        return new OrderEntity(
            convertToClientEntity(model.getClient()), 
            model.getOrderDate(),
            model.getOutfitType(),
            model.getFabricType(),
            model.getSize(),
            model.getColor(),
            model.getAccessories(),
            model.getStatus(),
            model.getEstimatedDeliveryDate(),
            model.getTotalPrice()
        );
    }

    @SuppressWarnings("unused")
	private ClientEntity findClientByName(String clientName) {
        return clientRepository.findByClientNameIgnoreCase(clientName)
                .orElseThrow(() -> new RuntimeException("Client not found: " + clientName));
    }
    
    private Client convertToClientModel(ClientEntity entity) {
        if (entity == null) return null; // Null check
        return new Client(entity.getClientId(), entity.getClientName(), entity.getEmail(), entity.getPhoneNumber());
    }
    
    private ClientEntity convertToClientEntity(Client model) {
        if (model == null) return null; // Null check
        return new ClientEntity(model.getClientId(), model.getClientName(), model.getEmail(), model.getPhoneNumber());
    }
}
