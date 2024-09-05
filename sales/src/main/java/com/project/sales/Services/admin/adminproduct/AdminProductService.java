package com.project.sales.Services.admin.adminproduct;

import com.project.sales.Dto.ProductDto;
import com.project.sales.Entity.Product;

import java.io.IOException;
import java.util.List;

public interface AdminProductService {

    ProductDto addProduct(ProductDto productDto) throws IOException ;

    List<ProductDto> getAllProduct();

    List<ProductDto> getAllProductByName(String name);

    boolean deleteProduct(Long id);

}
