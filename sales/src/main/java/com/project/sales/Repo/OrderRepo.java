package com.project.sales.Repo;

import com.project.sales.Entity.Order;
import com.project.sales.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {

    Order findByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);

    List<Order> findAllByOrderStatusIn(List<OrderStatus> orderStatusList);

    List<Order>  findByUserIdAndOrderStatusIn(Long userId, List<OrderStatus> orderStatus);

    Optional<Order> findByTrackingId(UUID trackingId);

    List<Order> findByDateBetweenAndOrderStatus(Date startOfMonth, Date endOfMonth, OrderStatus status);

    Long countByOrderStatus(OrderStatus status);


}
