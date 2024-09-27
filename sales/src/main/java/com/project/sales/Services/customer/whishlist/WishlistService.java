package com.project.sales.Services.customer.whishlist;

import com.project.sales.Dto.WishlistDto;

import java.util.List;

public interface WishlistService {
    public WishlistDto addProductWishlistList(WishlistDto wishlistDto);

    List<WishlistDto> getWishlistByUserId(Long userId);
}
