package com.project.sales.Repo;

import com.project.sales.Entity.CartItems;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CartItemsRepo extends JpaRepository<CartItems,Long> {
    Optional<CartItems> findByProductIdAndOrderIdAndUserId(Long productId,Long orderId,Long userId);



}
