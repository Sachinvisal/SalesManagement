package com.project.sales.Services.admin.adminOrder;


import com.project.sales.Dto.OrderDto;
import com.project.sales.Entity.Order;
import com.project.sales.Repo.OrderRepo;
import com.project.sales.enums.OrderStatus;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AdminOrderServiceImpl implements AdminOrderService {

    private final OrderRepo orderRepo;

    public List<OrderDto> getAllPlaceOrders(){

        List<Order> orderList = orderRepo.findAllByOrderStatusIn(List.of(OrderStatus.Placed,OrderStatus.Shipped,OrderStatus.Delivered));

        return orderList.stream().map(Order::getOrderDto).collect(Collectors.toList());

    }

    public OrderDto changeOrderStatus(Long orderId, String status){
        Optional<Order> optionalOrder = orderRepo.findById(orderId);
        if(optionalOrder.isPresent()){
            Order order = optionalOrder.get();

            if(Objects.equals(status,"Shipped")){
                order.setOrderStatus(OrderStatus.Shipped);
            }else if (Objects.equals(status,"Delivered")){
                order.setOrderStatus(OrderStatus.Delivered);
            }
            return  orderRepo.save(order).getOrderDto();
        }
        return null;
    }
}
