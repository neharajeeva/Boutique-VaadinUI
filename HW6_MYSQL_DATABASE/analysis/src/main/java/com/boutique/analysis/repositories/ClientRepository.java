package com.boutique.analysis.repositories;

import com.boutique.analysis.entities.ClientEntity;
import org.springframework.data.repository.CrudRepository;
import java.util.Optional;

public interface ClientRepository extends CrudRepository<ClientEntity, Integer> {
    Optional<ClientEntity> findByClientNameIgnoreCase(String clientName);
}
