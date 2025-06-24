package br.com.rgsystems.dscomerce.repositories;

import br.com.rgsystems.dscomerce.entities.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
