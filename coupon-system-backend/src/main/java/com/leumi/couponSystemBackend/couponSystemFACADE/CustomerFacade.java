package com.leumi.couponSystemBackend.couponSystemFACADE;

import com.leumi.couponSystemBackend.couponSystemEntity.Category;
import com.leumi.couponSystemBackend.couponSystemEntity.Coupon;
import com.leumi.couponSystemBackend.couponSystemEntity.Customer;
import com.leumi.couponSystemBackend.couponSystemException.SystemException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

public class CustomerFacade extends ClientFacade{

    private int customerID;

    public CustomerFacade() {
        super();
    }

    /**
     * @param email
     * @param password
     * @return true if the customer is log in
     */
    @Override
    public boolean login(String email, String password) {
        try {
            if( customersDAO.isCustomerExists(email,password)){
                Customer customer = customersDAO.getOneCustomer(email);
                this.customerID = customer.getId();
                return true;
            } else return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    /**
     * Add a specific coupon to the customer's owned coupons.
     * @param coupon
     * @throws Exception
     */
    public void purchaseCoupon(Coupon coupon) throws Exception {

        if ( getCustomerCoupons().contains(coupon)) {
            throw new SystemException("This coupon has bought before");
        }if (coupon.getAmount() == 0){
            throw new SystemException("This coupon finished");
        }if (coupon.getEndDate().before(new Date())){
            throw new SystemException("The coupon is not valid");
        }
        couponsDAO.addCouponPurchase(customerID, coupon.getId());
        coupon.setAmount(coupon.getAmount()-1);
        couponsDAO.updateCoupon(coupon);

    }

    /**
     * @return all the coupons of the customer.
     * @throws SQLException
     */
    public ArrayList<Coupon> getCustomerCoupons() throws SQLException {
        return couponsDAO.getCustomersCoupon(customerID);
    }

    /**
     * @param category
     * @return all the coupons of the company whose category is as the given n category.
     * @throws SQLException
     */
    public ArrayList<Coupon> getCustomerCoupons(Category category) throws SQLException {
            return couponsDAO.getCustomersCoupon(customerID, category);
    }

    /**
     * @param maxPrice
     * @return all the coupons of the customer whose price is less than the given maxPrice.
     * @throws SQLException
     */
    public ArrayList<Coupon> getCustomerCoupons(double maxPrice) throws SQLException {
            return couponsDAO.getCustomersCoupon(customerID, maxPrice);
    }

    /**
     * @return the customer's details.
     * @throws SQLException
     */
    public Customer getCustomerDetails() throws SQLException {
            Customer customer = customersDAO.getOneCustomer(customerID);
            for (Coupon c : couponsDAO.getCustomersCoupon(customerID)) {
                customer.addCoupons(c);
            }
            return customer;
    }
}
