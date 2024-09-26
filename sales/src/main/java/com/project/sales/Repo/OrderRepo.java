package com.project.sales.Repo;

import com.project.sales.Dto.OrderDto;
import com.project.sales.Entity.Order;
import com.project.sales.enums.OrderStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepo extends JpaRepository<Order,Long> {

    Order findByUserIdAndOrderStatus(Long userId, OrderStatus orderStatus);

    List<Order> findAllByOrderStatusIn(List<OrderStatus> orderStatusList);

    List<Order>  findByUserIdAndOrderStatusIn(Long userId, List<OrderStatus> orderStatus);





}
