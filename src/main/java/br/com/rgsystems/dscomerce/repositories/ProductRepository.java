package br.com.rgsystems.dscomerce.repositories;

import br.com.rgsystems.dscomerce.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository <Product, Long> {
}
