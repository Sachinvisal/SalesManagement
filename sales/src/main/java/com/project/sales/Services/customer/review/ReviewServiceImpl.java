package com.project.sales.Services.customer.review;


import com.project.sales.Dto.OrderedProductsResponseDto;
import com.project.sales.Dto.ProductDto;
import com.project.sales.Dto.ReviewDto;
import com.project.sales.Entity.*;
import com.project.sales.Repo.OrderRepo;
import com.project.sales.Repo.ProductRepo;
import com.project.sales.Repo.ReviewRepo;
import com.project.sales.Repo.UserRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl implements ReviewService {

    private final OrderRepo orderRepo;
    private  final ProductRepo productRepo;
    private final UserRepo userRepo;
    private final ReviewRepo reviewRepo;

    public OrderedProductsResponseDto getOrderedProductsDetailsByOrderId(Long orderId){
        Optional<Order> optionalOrder = orderRepo.findById(orderId);
        OrderedProductsResponseDto orderedProductsResponseDto = new OrderedProductsResponseDto();
        if(optionalOrder.isPresent()){
            orderedProductsResponseDto.setOrderAmount(optionalOrder.get().getAmount());
            List <ProductDto> productDtoList = new ArrayList<>();
            for(CartItems cartItems:optionalOrder.get().getCartItems()){
                ProductDto productDto = new ProductDto();

                productDto.setId(cartItems.getProduct().getId());
                productDto.setName(cartItems.getProduct().getName());
                productDto.setPrice(cartItems.getPrice());
                productDto.setQuantity(cartItems.getQuantity());
                productDto.setByteimg(cartItems.getProduct().getImg());

                productDtoList.add(productDto);
            }
            orderedProductsResponseDto.setProductDtoList(productDtoList);
        }
        return orderedProductsResponseDto;
    }

    public ReviewDto giveReview(ReviewDto reviewDto) throws IOException{
        Optional <Product> optionalProduct = productRepo.findById(reviewDto.getProductId());
        Optional<User> optionalUser = userRepo.findById(reviewDto.getUserId());

        if(optionalProduct.isPresent() && optionalUser.isPresent()){
            Review review = new Review();

            review.setRating(reviewDto.getRating());
            review.setDescription(reviewDto.getDescription());
            review.setUser(optionalUser.get());
            review.setProduct(optionalProduct.get());
            review.setImg(reviewDto.getImg().getBytes());

            return reviewRepo.save(review).getDto();
        }

        return  null;

    }
}
