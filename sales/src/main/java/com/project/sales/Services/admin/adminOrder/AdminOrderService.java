package com.project.sales.Services.admin.adminOrder;

import com.project.sales.Dto.AnalyticsResponseDto;
import com.project.sales.Dto.OrderDto;

import java.util.List;

public interface AdminOrderService {

    List<OrderDto> getAllPlaceOrders();

    OrderDto changeOrderStatus(Long orderId, String status);

    AnalyticsResponseDto calculateAnalytics();
}
