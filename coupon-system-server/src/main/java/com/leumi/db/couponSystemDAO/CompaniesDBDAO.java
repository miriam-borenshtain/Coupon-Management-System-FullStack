package com.leumi.db.couponSystemDAO;


import com.leumi.db.couponSystemDB.ConnectionPool;
import com.leumi.couponSystemBackend.couponSystemEntity.Company;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * Singleton class
 */
public class CompaniesDBDAO implements CompaniesDao{
    private Connection connection;
    private static CompaniesDBDAO INSTANCE =null;

    private CompaniesDBDAO() {
        this.connection = ConnectionPool.getInstance().getConnection();
    }

    public static CompaniesDBDAO getInstance(){
        if (INSTANCE == null){
            INSTANCE = new CompaniesDBDAO();
        }
        return INSTANCE;
    }

    @Override
    public boolean isCompanyExists(String email, String password) throws SQLException {
        String sql = "select * from companies where email = '" + email + "' and password = '" + password + "'";
        System.out.println(sql);
        return executeQuery(sql);
    }

    @Override
    public boolean isValueExists(String field, String value) throws SQLException {
        String sql = "select * from companies where " + field + " = '" + value + "'";
        System.out.println(sql);
        return executeQuery(sql);
    }

    @Override
    public boolean isValuesExists(String name, String email) throws SQLException {
        String sql = "select * from companies where name = '" + name + "' or email = '" + email + "'";
        System.out.println(sql);
        return executeQuery(sql);
    }

    @Override
    public void addCompany(Company company) throws SQLException {
        String sql = "insert into companies (id, name, email, password) " +
                "values (" + company.getId() + ",'" + company.getName() + "', '"+
                company.getEmail() + "', '" + company.getPassword() +"')";
        System.out.println(sql);
        execute(sql);
    }

    @Override
    public void updateCompany(Company company) throws SQLException {
        String sql = "update companies set name = '" + company.getName() +
                "', email ='"+ company.getEmail() + "', password = '" + company.getPassword() +"' where id ="+ company.getId();
        System.out.println(sql);
        execute(sql);
    }

    @Override
    public void deleteCompany(int companyId) throws SQLException {
        String sql = "delete from companies where id = " + companyId ;
        System.out.println(sql);
        execute(sql);
    }

    @Override
    public ArrayList<Company> getAllCompanies() throws SQLException {
        String sql = "select * from companies";
        System.out.println(sql);
        ResultSet resultSet = execute(sql);
        ArrayList<Company> companies = new ArrayList<>();
        while (resultSet.next()) {
            companies.add(new Company(resultSet.getInt(1), resultSet.getNString(2), resultSet.getNString(3), resultSet.getNString(4)));
        }
        return companies;
    }

    @Override
    public Company getOneCompany(int companyId) throws SQLException {
        String sql = "select * from companies where id = " +companyId;
        System.out.println(sql);
        ResultSet resultSet = execute(sql);
        resultSet.next();
        return new Company(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
    }

    @Override
    public Company getOneCompany(String email) throws SQLException {
        String sql = "select * from companies where email = '" + email+"'";
        System.out.println(sql);
        ResultSet resultSet = execute(sql);
        resultSet.next();
        return new Company(resultSet.getInt(1), resultSet.getString(2), resultSet.getString(3), resultSet.getString(4));
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
