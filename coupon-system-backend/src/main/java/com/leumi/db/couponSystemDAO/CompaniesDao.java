package com.leumi.db.couponSystemDAO;



import com.leumi.couponSystemBackend.couponSystemEntity.Company;

import java.sql.SQLException;
import java.util.ArrayList;

public interface CompaniesDao {

    public boolean isCompanyExists(String email, String password) throws SQLException;

    boolean isValueExists(String field, String value) throws SQLException;

    boolean isValuesExists(String name, String email) throws SQLException;

    public void addCompany(Company company) throws SQLException;
    public void updateCompany(Company company) throws SQLException;
    public void deleteCompany(int companyId) throws SQLException;
    public ArrayList<Company> getAllCompanies() throws SQLException;
    public Company getOneCompany(int companyId) throws SQLException;

    Company getOneCompany(String email) throws SQLException;
}
