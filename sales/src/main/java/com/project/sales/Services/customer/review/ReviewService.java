package com.project.sales.Services.customer.review;

import com.project.sales.Dto.OrderedProductsResponseDto;

public interface ReviewService {

    OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(Long orderId);
}
