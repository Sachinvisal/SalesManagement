package com.project.sales.Controller.customer;

import com.project.sales.Dto.WishlistDto;
import com.project.sales.Services.customer.whishlist.WishlistService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/customer")
public class WishlistController {

    private final WishlistService wishlistService;

    @PostMapping("/wishlist")
    public ResponseEntity<?> addProductToWishlist(@RequestBody WishlistDto wishlistDto)
    {
        WishlistDto postedWishlistDto = wishlistService.addProductWishlistList(wishlistDto);
        if (postedWishlistDto == null)
              return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Something went wrong");
            return ResponseEntity.status(HttpStatus.CREATED).body(postedWishlistDto);

    }
}
