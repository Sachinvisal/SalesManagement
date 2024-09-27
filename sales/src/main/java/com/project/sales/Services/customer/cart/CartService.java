package com.project.sales.Services.customer.cart;

import com.project.sales.Dto.AddProductInCartDto;
import com.project.sales.Dto.OrderDto;
import com.project.sales.Dto.PlaceOrderDto;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.UUID;

public interface CartService {
    ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);

    OrderDto getCartByUserId(Long userId);

    OrderDto applyCoupon(Long userId,String code);

    OrderDto increaseProductQuantity(AddProductInCartDto addProductInCartDto);

    OrderDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto);

    OrderDto placeOrder(PlaceOrderDto placeOrderDto);

    public List<OrderDto> getMyPlaceOrder(Long userId);

    OrderDto searchOrderByTrackingId(UUID trackingId);
}
