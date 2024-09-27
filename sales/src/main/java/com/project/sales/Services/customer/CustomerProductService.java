package com.project.sales.Services.customer;

import com.project.sales.Dto.ProductDetailsDto;
import com.project.sales.Dto.ProductDto;

import java.util.List;

public interface CustomerProductService {

    List<ProductDto> searchProductByTitle(String title);

    List<ProductDto> getAllProducts();

    ProductDetailsDto getProductDetailById(Long productId);


}
