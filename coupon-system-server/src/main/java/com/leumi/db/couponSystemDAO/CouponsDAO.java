package com.leumi.db.couponSystemDAO;


import com.leumi.couponSystemBackend.couponSystemEntity.Category;
import com.leumi.couponSystemBackend.couponSystemEntity.Coupon;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CouponsDAO{

    public void addCoupon(Coupon coupon) throws SQLException;
    public void updateCoupon(Coupon coupon) throws SQLException;
    public void deleteCoupon(int couponId) throws SQLException;

    public ArrayList<Coupon> getAllCoupons() throws SQLException;
    public ArrayList<Coupon> getAllCoupons(int company_id) throws SQLException;
    public ArrayList<Coupon> getAllCoupons(int value, Category category) throws SQLException;
    public ArrayList<Coupon> getAllCoupons(int value, double maxPrice) throws SQLException;
    public Coupon getOneCoupon(int couponId) throws SQLException;

    public void addCouponPurchase(int customerId, int couponId) throws SQLException;
    public void deleteCouponPurchase(int customerId, int couponId) throws SQLException;
    public void deleteCouponPurchase(String field, int value) throws SQLException;
    public ArrayList<Coupon> getCustomersCoupon(int customer_id) throws SQLException;

    ArrayList<Coupon> getCustomersCoupon(int customer_id, Category category) throws SQLException;

    ArrayList<Coupon> getCustomersCoupon(int customer_id, double max_price) throws SQLException;

    public boolean isTitleExist(String title, int company_id) throws SQLException;


}
