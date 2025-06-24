package br.com.rgsystems.dscomerce.repositories;

import br.com.rgsystems.dscomerce.entities.OrderItem;
import br.com.rgsystems.dscomerce.entities.OrderItemPK;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemPK> {
}
