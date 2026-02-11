package com.leumi.couponSystemBackend.couponSystemJob;

import com.leumi.db.couponSystemDAO.CouponsDAO;
import com.leumi.db.couponSystemDAO.CouponsDBDAO;
import com.leumi.couponSystemBackend.couponSystemEntity.Coupon;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class CouponExpirationDailyJob implements Runnable{

    CouponsDAO couponsDAO = CouponsDBDAO.getInstance();
    private final int SLEEP_DURATION = 1000 * 60 * 60 * 24; //one day
    private boolean exit =false;

    /**
     * run a job that once a day delete the coupons whose date was over.
     */
    @Override
    public void run() {
        while (!exit) {
            try {
                ArrayList<Coupon> coupons = couponsDAO.getAllCoupons();
                Date currentDate = new Date();
                for (Coupon coupon : coupons) {
                    System.out.println(currentDate);
                    System.out.println(coupon.getEndDate());
                    if (!coupon.getEndDate().after(currentDate)) {
                        couponsDAO.deleteCoupon(coupon.getId());
                        System.out.println("coupon "+coupon.getId() +"was deleted");
                    }
                }
                Thread.sleep(SLEEP_DURATION);
            } catch (InterruptedException | SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void stop(){
        exit = true;
    }
}
