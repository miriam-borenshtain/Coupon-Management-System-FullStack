package com.leumi.db.couponSystemDAO;



import com.leumi.db.couponSystemDB.ConnectionPool;
import com.leumi.couponSystemBackend.couponSystemEntity.Customer;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Singleton class
 */
public class CustomersDBDAO implements CustomersDAO{
    private Connection connection;
    private static CustomersDBDAO INSTANCE = null;

    private CustomersDBDAO() {
        this.connection = ConnectionPool.getInstance().getConnection();
    }

    public static CustomersDBDAO getInstance(){
        if(INSTANCE == null){
            INSTANCE = new CustomersDBDAO();
        }
        return INSTANCE;
    }

    @Override
    public boolean isCustomerExists(String email, String password) throws SQLException {
        String sql = "select * from customers where email = '" + email + "' and password = '" + password + "'";
        System.out.println(sql);
        return executeQuery(sql);
    }

    @Override
    public boolean isValueExists(String field, String value) throws SQLException {
        String sql = "select * from customers where " + field + " = '" + value+"'";
        System.out.println(sql);
        return executeQuery(sql);
    }

    @Override
    public void addCustomer(Customer customer) throws SQLException {
        String sql = "insert into customers (id, first_name, last_name, email, password) " +
                "values (" + customer.getId() + ",'" + customer.getFirstName() + "', '"+ customer.getLastName() + "', '"
                + customer.getEmail() + "', '" + customer.getPassword() +"')";
        System.out.println(sql);
        execute(sql);
    }

    @Override
    public void updateCustomer(Customer customer) throws SQLException {
        String sql = "update customers set first_name = '" + customer.getFirstName() + "', last_name = '" + customer.getLastName()
                + "', email = '" + customer.getEmail() + "', password = '" +customer.getPassword() +"' where id =" + customer.getId();
        System.out.println(sql);
        execute(sql);
    }

    @Override
    public void deleteCustomer(int customerId) throws SQLException {
        String sql = "delete from customers where id = " + customerId ;
        System.out.println(sql);
        execute(sql);
    }

    @Override
    public ArrayList<Customer> getAllCustomers() throws SQLException {
        String sql = "select * from customers";
        System.out.println(sql);
        ResultSet resultSet = execute(sql);
        ArrayList<Customer> customers = new ArrayList<>();
        while (resultSet.next()) {
            customers.add(new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5)));
        }
        return customers;
    }

    @Override
    public Customer getOneCustomer(int customerId) throws SQLException {
        String sql = "select * from customers where id = " + customerId;
        System.out.println(sql);
        ResultSet resultSet = execute(sql);
        resultSet.next();
        return new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
    }

    @Override
    public Customer getOneCustomer(String email) throws SQLException {
        String sql = "select * from customers where email = '" + email + "'";
        System.out.println(sql);
        ResultSet resultSet = execute(sql);
        resultSet.next();
        return new Customer(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4), resultSet.getString(5));
    }

    /**
     * Runs the SQL query with 'executeQuery'
     * @param sqlQuery
     * @return a boolean result that return when running the SQL query.
     * @throws SQLException
     */
    public Boolean executeQuery(String sqlQuery) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        ResultSet resultSet = preparedStatement.executeQuery();
        return resultSet.next();
    }

    /**
     * Runs the SQL query with 'execute'
     * @param sqlQuery
     * @return the results that return when running the SQL query.
     * @throws SQLException
     */
    public ResultSet execute(String sqlQuery) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sqlQuery);
        preparedStatement.execute();
        return preparedStatement.getResultSet();
    }
}
