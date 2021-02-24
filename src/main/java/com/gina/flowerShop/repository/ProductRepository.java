package com.gina.flowerShop.repository;

import com.gina.flowerShop.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    @Query("SELECT DISTINCT p.origin FROM Product p")
    List<String> findDistinctOrigin();
}
