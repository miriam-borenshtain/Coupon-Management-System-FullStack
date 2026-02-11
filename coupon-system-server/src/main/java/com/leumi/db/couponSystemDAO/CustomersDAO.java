package com.leumi.db.couponSystemDAO;



import com.leumi.couponSystemBackend.couponSystemEntity.Customer;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CustomersDAO {

    public boolean isCustomerExists(String email, String password) throws SQLException;

    boolean isValueExists(String field, String value) throws SQLException;

    public void addCustomer(Customer customer) throws SQLException;
    public void updateCustomer(Customer customer) throws SQLException;
    public void deleteCustomer(int customerId) throws SQLException;
    public ArrayList<Customer> getAllCustomers() throws SQLException;
    public Customer getOneCustomer(int customerId) throws SQLException;

    Customer getOneCustomer(String email) throws SQLException;
}
