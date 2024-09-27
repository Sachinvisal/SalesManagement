package com.project.sales.Services.customer.review;

import com.project.sales.Dto.OrderedProductsResponseDto;
import com.project.sales.Dto.ReviewDto;

import java.io.IOException;

public interface ReviewService {

    OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(Long orderId);

    ReviewDto giveReview(ReviewDto reviewDto) throws IOException;
}
