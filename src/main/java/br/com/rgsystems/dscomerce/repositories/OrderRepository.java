package br.com.rgsystems.dscomerce.repositories;

import br.com.rgsystems.dscomerce.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
}
