package com.boutique.analysis.repositories;

import com.boutique.analysis.entities.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OrderRepository extends JpaRepository<OrderEntity, Integer> {
	List<OrderEntity> findAll();

    List<OrderEntity> findByStatusIgnoreCase(String status);

    List<OrderEntity> findByOrderDateBetween(LocalDate startDate, LocalDate endDate);

    List<OrderEntity> findByClient_ClientId(int clientId);
}
