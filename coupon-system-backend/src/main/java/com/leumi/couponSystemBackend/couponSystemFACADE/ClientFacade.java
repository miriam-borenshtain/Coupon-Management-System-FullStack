package com.leumi.couponSystemBackend.couponSystemFACADE;


import com.leumi.db.couponSystemDAO.*;

public abstract class ClientFacade {

    CouponsDAO couponsDAO;
    CompaniesDao companiesDao;
    CustomersDAO customersDAO;

    public ClientFacade() {
        this.couponsDAO = CouponsDBDAO.getInstance();
        this.companiesDao = CompaniesDBDAO.getInstance();
        this.customersDAO = CustomersDBDAO.getInstance();
    }

    public abstract boolean login(String email, String password);
}
