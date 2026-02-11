package com.leumi.couponSystemBackend.couponSystemFACADE;

import com.leumi.couponSystemBackend.couponSystemEntity.Company;
import com.leumi.couponSystemBackend.couponSystemEntity.Coupon;
import com.leumi.couponSystemBackend.couponSystemEntity.Customer;
import com.leumi.couponSystemBackend.couponSystemException.SystemException;

import java.util.ArrayList;

public class AdminFacade extends ClientFacade{

    public AdminFacade(){
        super();
    }

    /**
     * @param email
     * @param password
     * @return true if the admin is log in
     */
    @Override
    public boolean login(String email, String password) {
        return email.equals("admin@admin.com") && password.equals("admin");
    }

    /**
     * Add new company to the existing companies in the system.
     * @param company
     * @throws Exception
     */
    public void addCompany(Company company) throws Exception{
        if (!company.getCoupons().isEmpty()) {
            throw new SystemException("Can't insert coupons when create new company.");
        }
        if (!companiesDao.isCompanyExists(company.getEmail(), company.getPassword())){
            companiesDao.addCompany(company);
        } else {
            throw new SystemException("This company is already exists");
        }
    }

    /**
     * Update an existing company with new details, by the company's id.
     * @param company
     * @throws Exception
     */
    public void updateCompany(Company company) throws Exception{
        if (!company.getCoupons().isEmpty()) {
            throw new SystemException("Can't update the company's coupons");
        }
        Company c = companiesDao.getOneCompany(company.getId());
        if (c.getName().equals(company.getName())){
            companiesDao.updateCompany(company);
        }else{
            throw new SystemException("Can't update the company name");
        }
    }

    /**
     * Delete a company from the system by its id.
     * @param companyID
     * @throws Exception
     */
    public void deleteCompany(int companyID) throws Exception{
        for (Coupon coupon :couponsDAO.getAllCoupons(companyID)) {
            couponsDAO.deleteCoupon(coupon.getId());
        }
        companiesDao.deleteCompany(companyID);
    }

    /**
     * @return all the existing companies in the system.
     * @throws Exception
     */
    public ArrayList<Company> getAllCompanies() throws Exception{
        ArrayList<Company> companies = companiesDao.getAllCompanies();
        for (Company company: companies) {
            for (Coupon coupon : couponsDAO.getAllCoupons(company.getId())) {
                company.addCoupon(coupon);
            }
        }
        return companies;
    }

    /**
     * @param companyID
     * @return the company with the given id.
     * @throws Exception
     */
    public Company getOneCompany(int companyID) throws Exception{
        Company company = companiesDao.getOneCompany(companyID);
        for (Coupon coupon : couponsDAO.getAllCoupons(companyID)) {
            company.addCoupon(coupon);
        }
        return company;
    }

    /**
     * Add new customer to the existing customers in the system.
     * @param customer
     * @throws Exception
     */
    public void addCustomer(Customer customer) throws Exception{
        if (!customer.getCoupons().isEmpty()) {
            throw new SystemException("Can't insert coupons when create new customer.");
        }
        if(!customersDAO.isCustomerExists(customer.getEmail(), customer.getPassword())){
            customersDAO.addCustomer(customer);
        }else {
            throw new SystemException("This customer is already exists in the system");
        }
    }

    /**
     * Update an existing customer with new details, by the customer's id.
     * @param customer
     * @throws Exception
     */
    public void updateCustomer(Customer customer) throws Exception{
        customersDAO.updateCustomer(customer);
        if (!customer.getCoupons().isEmpty()) {
            throw new SystemException("Can't update the customer's coupons");
        }
    }

    /**
     * delete a customer from the system by its id.
     * @param customerID
     * @throws Exception
     */
    public void deleteCustomer(int customerID) throws Exception{
            customersDAO.deleteCustomer(customerID);
    }

    /**
     * @return all the existing customers in the system.
     * @throws Exception
     */
    public ArrayList<Customer> getAllCustomers() throws Exception{
        ArrayList<Customer> customers =customersDAO.getAllCustomers();
        for (Customer customer :customers) {
            for (Coupon c : couponsDAO.getCustomersCoupon(customer.getId())) {
                customer.addCoupons(c);
            }
        }
        return customers;
    }

    /**
     * @param customerID
     * @return the customer with the given id.
     * @throws Exception
     */
    public Customer getOneCustomer(int customerID) throws Exception{
            Customer customer = customersDAO.getOneCustomer(customerID);
            for (Coupon c : couponsDAO.getCustomersCoupon(customerID)) {
                customer.addCoupons(c);
            }
            return customer;
    }
}
