package com.project.sales.Services.customer.cart;

import com.project.sales.Dto.AddProductInCartDto;
import com.project.sales.Dto.OrderDto;
import org.springframework.http.ResponseEntity;

public interface CartService {
    ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto);

    OrderDto getCartByUserId(Long userId);

    OrderDto applyCoupon(Long userId,String code);
}
