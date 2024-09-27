package com.project.sales.Dto;

import com.project.sales.Entity.Product;
import com.project.sales.Entity.User;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ReviewDto {

    private Long Id;

    private Long rating;

    private String description;

    private MultipartFile img;

    private byte[ ] returnedimg;

    private Long userId;

    private Long productId;

    private String username;
}
