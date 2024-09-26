package com.project.sales.Services.customer.cart;

import com.project.sales.Dto.AddProductInCartDto;
import com.project.sales.Dto.CartItemsDto;
import com.project.sales.Dto.OrderDto;
import com.project.sales.Dto.PlaceOrderDto;
import com.project.sales.Entity.*;
import com.project.sales.Repo.*;
import com.project.sales.enums.OrderStatus;
import com.project.sales.exception.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private OrderRepo orderRepo;

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private CartItemsRepo cartItemsRepo;

    @Autowired
    private ProductRepo productRepo;

    @Autowired
    private CouponRepo couponRepo;

    public ResponseEntity<?> addProductToCart(AddProductInCartDto addProductInCartDto){
        Order activeOrder = orderRepo.findByUserIdAndOrderStatus(addProductInCartDto.getProductId(), OrderStatus.Pending);
        Optional<CartItems> optionalCartItems=cartItemsRepo.findByProductIdAndOrderIdAndUserId
                (addProductInCartDto.getProductId(),activeOrder.getId(),addProductInCartDto.getUserId());

        if (optionalCartItems.isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(null);
        }else {
            Optional<Product>optionalProduct=productRepo.findById(addProductInCartDto.getProductId());
            Optional<User>optionalUser= userRepo.findById(addProductInCartDto.getUserId());

            if(optionalProduct.isPresent()&& optionalUser.isPresent()){
                CartItems cart = new CartItems();
                cart.setProduct(optionalProduct.get());
                cart.setPrice(optionalProduct.get().getPrice());
                cart.setQuantity(1L);
                cart.setUser(optionalUser.get());
                cart.setOrder(activeOrder);

                CartItems updatedCart = cartItemsRepo.save(cart);
                activeOrder.setTotalAmount(activeOrder.getAmount()+cart.getPrice());
                activeOrder.getCartItems().add(cart);

                orderRepo.save(activeOrder);

                return ResponseEntity.status(HttpStatus.CREATED).body(cart);

            }else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User or product not found");
            }
        }


    }

    public OrderDto getCartByUserId(Long userId){
        Order activeOrder  = orderRepo.findByUserIdAndOrderStatus(userId,OrderStatus.Pending);
        List<CartItemsDto> cartItemsList = activeOrder.getCartItems().stream().map(CartItems::geCartItemsDto).collect(Collectors.toList());
        OrderDto orderDto = new OrderDto();
        orderDto.setAmount(activeOrder.getAmount());
        orderDto.setId(activeOrder.getId());
        orderDto.setOrderStatus(activeOrder.getOrderStatus());
        orderDto.setDiscount(activeOrder.getDiscount());
        orderDto.setTotalAmount(activeOrder.getTotalAmount());
        if(activeOrder.getCoupon()!= null){
            orderDto.setCouponName(activeOrder.getCoupon().getName());
        }

        return orderDto;
    }

    public OrderDto applyCoupon(Long userId,String code){
    Order activeOrder  = orderRepo.findByUserIdAndOrderStatus(userId,OrderStatus.Pending);
    Coupon coupon = couponRepo.findByCode(code).orElseThrow(()-> new ValidationException("Coupon not found"));

    if (couponIsExpired(coupon)){
        throw new ValidationException("Coupon has expired");
    }

    double discountAmount= ((coupon.getDiscount()/100.0) * activeOrder.getTotalAmount());
    double netAmount = activeOrder.getAmount()- discountAmount;

    activeOrder.setAmount((long)netAmount);
    activeOrder.setDiscount((long)discountAmount);
    activeOrder.setCoupon(coupon);

    orderRepo.save(activeOrder);
    return activeOrder.getOrderDto();
}

    private boolean couponIsExpired(Coupon coupon){
        Date currentdate = new Date();
        Date expirationDate = coupon.getExpirationDate();


        return expirationDate != null && currentdate.after(expirationDate);
}

    public  OrderDto increaseProductQuantity(AddProductInCartDto addProductInCartDto){
       Order activeOrder = orderRepo.findByUserIdAndOrderStatus(addProductInCartDto.getUserId(), OrderStatus.Pending);
       Optional<Product> optionalProduct = productRepo.findById(addProductInCartDto.getProductId());

       Optional<CartItems> optionalCartItems = cartItemsRepo.findByProductIdAndOrderIdAndUserId(addProductInCartDto.getProductId(), activeOrder.getId(),addProductInCartDto.getUserId());

       if(optionalProduct.isPresent() && optionalCartItems.isPresent()){
           CartItems  cartItems = optionalCartItems.get();
           Product product = optionalProduct.get();

           activeOrder.setAmount(activeOrder.getAmount() + product.getPrice());
           activeOrder.setTotalAmount(activeOrder.getTotalAmount() + product.getPrice());

           cartItems.setQuantity(cartItems.getQuantity() + 1);

           if(activeOrder.getCoupon() != null){

               double discountAmount = ((activeOrder.getCoupon().getDiscount()/100.00)* activeOrder.getTotalAmount());
               double netAmount =activeOrder.getTotalAmount()-discountAmount;

               activeOrder.setAmount((long)netAmount);
               activeOrder.setDiscount((long)discountAmount);

           }
           cartItemsRepo.save(cartItems);
           orderRepo.save(activeOrder);
           return activeOrder.getOrderDto();
       }
       return null;
}


    public  OrderDto decreaseProductQuantity(AddProductInCartDto addProductInCartDto){
        Order activeOrder = orderRepo.findByUserIdAndOrderStatus(addProductInCartDto.getUserId(), OrderStatus.Pending);
        Optional<Product> optionalProduct = productRepo.findById(addProductInCartDto.getProductId());

        Optional<CartItems> optionalCartItems = cartItemsRepo.findByProductIdAndOrderIdAndUserId(addProductInCartDto.getProductId(), activeOrder.getId(),addProductInCartDto.getUserId());

        if(optionalProduct.isPresent() && optionalCartItems.isPresent()){
            CartItems  cartItems = optionalCartItems.get();
            Product product = optionalProduct.get();

            activeOrder.setAmount(activeOrder.getAmount() - product.getPrice());
            activeOrder.setTotalAmount(activeOrder.getTotalAmount() - product.getPrice());

            cartItems.setQuantity(cartItems.getQuantity() - 1);

            if(activeOrder.getCoupon() != null){

                double discountAmount = ((activeOrder.getCoupon().getDiscount()/100.00)* activeOrder.getTotalAmount());
                double netAmount =activeOrder.getTotalAmount()-discountAmount;

                activeOrder.setAmount((long)netAmount);
                activeOrder.setDiscount((long)discountAmount);

            }
            cartItemsRepo.save(cartItems);
            orderRepo.save(activeOrder);
            return activeOrder.getOrderDto();
        }
        return null;
    }

    public OrderDto placeOrder(PlaceOrderDto placeOrderDto){
        Order activeOrder = orderRepo.findByUserIdAndOrderStatus(placeOrderDto.getUserId(), OrderStatus.Pending);
        Optional<User> optionalUser = userRepo.findById(placeOrderDto.getUserId());

        if(optionalUser.isPresent()){
            activeOrder.setOrderDescription(placeOrderDto.getOrderDescription());
            activeOrder.setAddress(placeOrderDto.getAddress());
            activeOrder.setDate(new Date());

            activeOrder.setOrderStatus(OrderStatus.Placed);
            activeOrder.setTrackingId(UUID.randomUUID());

            orderRepo.save(activeOrder);

            Order order = new Order();
            order.setAmount(0L);
            order.setTotalAmount(0L);
            order.setDiscount(0L);
            order.setUser(optionalUser.get());
            order.setOrderStatus(OrderStatus.Pending);
            orderRepo.save(order);

            return activeOrder.getOrderDto();


        }
        return  null;
    }


 }
