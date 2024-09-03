package com.project.sales.Services.admin.adminproduct;

import com.project.sales.Dto.ProductDto;
import com.project.sales.Entity.Category;
import com.project.sales.Entity.Product;
import com.project.sales.Repo.CategoryRepo;
import com.project.sales.Repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AdminProductServiceImpl implements AdminProductService {
    private final ProductRepo productRepo;

    private final CategoryRepo categoryRepo;

    public ProductDto addProduct(ProductDto productDto) throws IOException {
        Product product = new Product();
        product.setName(productDto.getName());
        product.setDescription(productDto.getDescription());
        product.setPrice(productDto.getPrice());
        product.setImg(productDto.getImg().getBytes());

        Category category =categoryRepo.findById(productDto.getCategoryId()).orElseThrow();
        product.setCategory(category);
        return productRepo.save(product).getDto();


    }
}
