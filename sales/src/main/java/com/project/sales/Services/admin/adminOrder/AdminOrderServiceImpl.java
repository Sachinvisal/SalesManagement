package com.project.sales.Services.admin.adminOrder;


import com.project.sales.Dto.OrderDto;
import com.project.sales.Entity.Order;
import com.project.sales.Repo.OrderRepo;
import com.project.sales.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminOrderServiceImpl implements AdminOrderService {

    private final OrderRepo orderRepo;

    public List<OrderDto> getAllPlaceOrders(){

        List<Order> orderList = orderRepo.findAllByOrderStatusIn(List.of(OrderStatus.Placed,OrderStatus.Shipped,OrderStatus.Delivered));

        return orderList.stream().map(Order::getOrderDto).collect(Collectors.toList());

    }
}
