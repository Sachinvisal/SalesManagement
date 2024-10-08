package com.project.sales.Controller.customer;

import com.project.sales.Dto.ProductDetailsDto;
import com.project.sales.Dto.ProductDto;
import com.project.sales.Services.customer.CustomerProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/customer")
@RequiredArgsConstructor
public class CustomerProductController {

    private final CustomerProductService customerProductService;

    @GetMapping("/products")
    public ResponseEntity<List<ProductDto>> getAllProducts(){
        List<ProductDto> productDtos = customerProductService.getAllProducts();
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/search/{name}")
    public ResponseEntity<List<ProductDto>>getAllProductByName(@PathVariable String name){
        List<ProductDto> productDtos = customerProductService.searchProductByTitle(name);
        return ResponseEntity.ok(productDtos);
    }

    @GetMapping("/product/{productId}")
    public ResponseEntity<ProductDetailsDto> getProductDetailById(@PathVariable Long productId){
        ProductDetailsDto productDetailsDto = customerProductService.getProductDetailById(productId);
        if(productDetailsDto == null) return  ResponseEntity.notFound().build();
        return ResponseEntity.ok(productDetailsDto);
    }
}
