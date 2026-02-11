package com.leumi.couponSystemBackend.couponSystemFACADE;


import com.leumi.couponSystemBackend.couponSystemEntity.Category;
import com.leumi.couponSystemBackend.couponSystemEntity.Company;
import com.leumi.couponSystemBackend.couponSystemEntity.Coupon;
import com.leumi.couponSystemBackend.couponSystemException.SystemException;

import java.sql.SQLException;
import java.util.ArrayList;

public class CompanyFacade extends ClientFacade{
    private int companyID;

    public CompanyFacade() {
        super();
    }

    /**
     * @param email
     * @param password
     * @return true if the company is log in
     */
    @Override
    public boolean login(String email, String password) {
        try {
            if( companiesDao.isCompanyExists(email,password)){
                Company company = companiesDao.getOneCompany(email);
                this.companyID = company.getId();
                return true;
            }else return false;
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return false;
    }

    /**
     * Add new coupon to the existing coupons in the system.
     * @param coupon
     * @throws Exception
     */
    public void  addCoupon(Coupon coupon) throws SQLException {
        couponsDAO.addCoupon(coupon);
    }

    /**
     * Update an existing coupon of the company with new details, by the coupon's id.
     * @param coupon
     * @throws Exception
     */
    public void updateCoupon(Coupon coupon) throws Exception{
            Coupon c = couponsDAO.getOneCoupon(coupon.getId());
            if (c.getCompanyId() == companyID ){
                couponsDAO.updateCoupon(coupon);
            }else{
                throw new SystemException("Can't update the company id");
            }
    }

    /**
     * Delete a coupon from the company's coupon by its id.
     * @param couponID
     * @throws SQLException
     */
    public void  deleteCoupon(int couponID) throws SQLException {
        couponsDAO.deleteCoupon(couponID);
    }

    /**
     * @return all the coupons of the company.
     * @throws SQLException
     */
    public ArrayList<Coupon> getCompanyCoupons() throws SQLException {
            return couponsDAO.getAllCoupons(companyID);
    }

    /**
     * @param category
     * @return all the coupons of the company whose category is as the given n category.
     * @throws SQLException
     */
    public ArrayList<Coupon> getCompanyCoupons(Category category) throws SQLException {
            return couponsDAO.getAllCoupons(companyID, category);
    }

    /**
     * @param maxPrice
     * @return all the coupons of the company whose price is less than the given maxPrice.
     * @throws SQLException
     */
    public ArrayList<Coupon> getCompanyCoupons(double maxPrice) throws SQLException {
        return couponsDAO.getAllCoupons(companyID, maxPrice);

    }

    /**
     * @param couponId
     * @return the company's coupon with the given id.
     * @throws SQLException
     */
    public Coupon getCompanyCoupon(int couponId) throws SQLException {
        return couponsDAO.getOneCoupon(couponId);
    }

    /**
     * @return the company's details.
     * @throws SQLException
     */
    public Company getCompanyDetails() throws SQLException {
        Company company = companiesDao.getOneCompany(companyID);
        for (Coupon coupon : couponsDAO.getAllCoupons(companyID)) {
            company.addCoupon(coupon);
        }
        return company;
    }
}
