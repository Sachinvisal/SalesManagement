package com.project.sales.Services.admin.coupon;
import com.project.sales.Entity.Coupon;
import java.util.List;

public interface AdminCouponService  {
    Coupon createCoupon(Coupon coupon);
    List<Coupon> getAllCoupons();
}
