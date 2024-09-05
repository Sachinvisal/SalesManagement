package com.project.sales.Repo;

import com.project.sales.Entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Long > {

    List<Product> findAllByNameContaining(String title);

}
