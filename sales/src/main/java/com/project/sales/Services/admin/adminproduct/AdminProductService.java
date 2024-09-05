package com.project.sales.Services.admin.adminproduct;

import com.project.sales.Dto.ProductDto;

import java.io.IOException;
import java.util.List;

public interface AdminProductService {

    ProductDto addProduct(ProductDto productDto) throws IOException ;
    List<ProductDto> getAllProdct();

}
