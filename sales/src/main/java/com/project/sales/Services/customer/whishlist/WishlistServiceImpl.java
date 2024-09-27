package com.project.sales.Services.customer.whishlist;

import com.project.sales.Dto.WishlistDto;
import com.project.sales.Entity.Product;
import com.project.sales.Entity.User;
import com.project.sales.Entity.Wishlist;
import com.project.sales.Repo.ProductRepo;
import com.project.sales.Repo.UserRepo;
import com.project.sales.Repo.WishlistRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WishlistServiceImpl implements WishlistService {

    private  final UserRepo userRepo;

    private final ProductRepo productRepo;

    private  final WishlistRepo wishlistRepo;

    public WishlistDto addProductWishlistList(WishlistDto wishlistDto){
        Optional<Product> optionalProduct = productRepo.findById(wishlistDto.getProductId());
        Optional<User> optionalUser = userRepo.findById(wishlistDto.getUserId());

        if(optionalProduct.isPresent() && optionalUser.isPresent()){
            Wishlist wishlist = new Wishlist();

            wishlist.setProduct(optionalProduct.get());
            wishlist.setUser(optionalUser.get());


            return  wishlistRepo.save(wishlist).getWishlistDto();
        }
        return null;
    }
}
