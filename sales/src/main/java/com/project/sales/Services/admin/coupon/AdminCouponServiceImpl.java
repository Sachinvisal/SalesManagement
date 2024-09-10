package com.project.sales.Services.admin.coupon;

import com.project.sales.Entity.Coupon;
import com.project.sales.Repo.CouponRepo;
import com.project.sales.exception.ValidationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
public class AdminCouponServiceImpl implements AdminCouponService {
    private final CouponRepo couponRepo;

    public Coupon createCoupon(Coupon coupon){
        if(couponRepo.existByCode((coupon.getCode())).isPresent()){
            throw new ValidationException("Coupon code all ready exist");
        }
        return  couponRepo.save(coupon);
    }

    public List<Coupon> getAllCoupons(){
        return couponRepo.findAll();
    }
}
