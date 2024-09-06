package com.project.sales.Services.customer.cart;

import com.project.sales.Dto.AddProductInCartDto;
import com.project.sales.Entity.CartItems;
import com.project.sales.Entity.Order;
import com.project.sales.Entity.Product;
import com.project.sales.Entity.User;
import com.project.sales.Repo.CartItemsRepo;
import com.project.sales.Repo.OrderRepo;
import com.project.sales.Repo.ProductRepo;
import com.project.sales.Repo.UserRepo;
import com.project.sales.enums.OrderStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

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

}
