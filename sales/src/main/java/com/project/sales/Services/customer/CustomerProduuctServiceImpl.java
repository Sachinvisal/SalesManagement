package com.project.sales.Services.customer;

import com.project.sales.Dto.ProductDto;
import com.project.sales.Entity.Product;
import com.project.sales.Repo.ProductRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomerProduuctServiceImpl implements  CustomerProductService{

    private final ProductRepo productRepo;

    public List<ProductDto> getAllProducts(){
        List<Product>products = productRepo.findAll();
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }

    public List<ProductDto> searchProductByTitle(String name){
        List<Product>products = productRepo.findAllByNameContaining(name);
        return products.stream().map(Product::getDto).collect(Collectors.toList());
    }
}
