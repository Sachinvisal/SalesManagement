package com.project.sales.Services.customer;

import com.project.sales.Dto.ProductDetailsDto;
import com.project.sales.Dto.ProductDto;
import com.project.sales.Entity.FAQ;
import com.project.sales.Entity.Product;
import com.project.sales.Entity.Review;
import com.project.sales.Repo.FAQRepo;
import com.project.sales.Repo.ProductRepo;
import com.project.sales.Repo.ReviewRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerProductServiceImpl implements  CustomerProductService{

    private final ProductRepo productRepo;

    private final FAQRepo faqRepo;

    private final ReviewRepo reviewRepo;

    public List<ProductDto> getAllProducts(){
        List<Product>products = productRepo.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    public List<ProductDto> searchProductByTitle(String name){
        List<Product>products = productRepo.findAllByNameContaining(name);
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    public ProductDetailsDto getProductDetailById(Long productId){
        Optional<Product> optionalProduct = productRepo.findById(productId);
        if(optionalProduct.isPresent()){
            List<FAQ> faqList =faqRepo.findAllByProductId(productId);
            List<Review> reviewList = reviewRepo.findAllByProductId(productId);

            ProductDetailsDto productDetailsDto = new ProductDetailsDto();

            productDetailsDto.setProductDto(optionalProduct.get().getDto());
            productDetailsDto.setFaqDtoList(faqList.stream().map(FAQ::getFAQDto).collect(Collectors.toList()));
            productDetailsDto.setReviewDtoList(reviewList.stream().map(Review::getDto).collect(Collectors.toList()));

            return  productDetailsDto;

        }
        return null;
    }


}
