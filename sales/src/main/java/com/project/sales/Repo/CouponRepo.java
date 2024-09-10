package com.project.sales.Repo;

import com.project.sales.Entity.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CouponRepo  extends JpaRepository<Coupon,Long> {
    @Query("select c from Coupon c where c.code=:code")
    Optional<Coupon> existByCode(String code);

}
