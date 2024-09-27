package com.project.sales.Controller;

import com.project.sales.Dto.OrderDto;
import com.project.sales.Services.customer.cart.CartService;
import com.project.sales.Services.customer.cart.CartServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
public class TrackingController {

    private final CartService cartService;

    @GetMapping("/order/{trackingId}")
    public ResponseEntity<OrderDto> searchOrderByTrackingId(@PathVariable UUID trackingId){
        OrderDto orderDto = cartService.searchOrderByTrackingId(trackingId);
        if(orderDto == null) return ResponseEntity.notFound().build();
        return ResponseEntity.ok(orderDto);
    }
}
