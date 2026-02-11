package com.leumi.db.couponSystemDAO;


import com.leumi.db.couponSystemDB.ConnectionPool;
import com.leumi.couponSystemBackend.couponSystemEntity.Category;
import com.leumi.couponSystemBackend.couponSystemEntity.Coupon;

import java.sql.*;
import java.util.ArrayList;

/**
 * Singleton class
 */
public class CouponsDBDAO implements CouponsDAO{

    private Connection connection;
    private static CouponsDBDAO INSTANCE =null;

    private CouponsDBDAO() {
        this.connection = ConnectionPool.getInstance().getConnection();
    }

    public static CouponsDBDAO getInstance(){
        if (INSTANCE == null){
            INSTANCE = new CouponsDBDAO();
        }
        return INSTANCE;
    }

    @Override
    public void addCoupon(Coupon coupon) throws SQLException {
        String sql = "insert into coupons (company_id, category_id, title, description, start_date, end_date, amount, price, image) " +
        "values (" + coupon.getCompanyId() + "," +
                (coupon.getCategory()) + ",'"
                + coupon.getTitle() + "', '"+ coupon.getDescription()
                + "', '" + new Date(coupon.getStartDate().getTime()) + "','" +
                new Date(coupon.getEndDate().getTime())+ "'," + coupon.getAmount() + ","
                + coupon.getPrice() + ",'" + coupon.getImage() +"')";
        System.out.println(sql);
        execute(sql);
    }

    @Override
    public void updateCoupon(Coupon coupon) throws SQLException {
        String sql = "update coupons set title = '" + coupon.getTitle() +"', description ='" + coupon.getDescription() +
                "', start_date = '"+ new Date(coupon.getStartDate().getTime()) + "', end_date = '" + new Date(coupon.getEndDate().getTime()) +"', amount = '" +
                coupon.getAmount() +"', price = '" + coupon.getPrice() + "', image = '"
                + coupon.getImage() +"' where id =" + coupon.getId();
        System.out.println(sql);
        execute(sql);
    }

    @Override
    public void deleteCoupon(int couponId) throws SQLException {
        String sql = "delete from coupons where id = " + couponId ;
        System.out.println(sql);
        execute(sql);
    }

    @Override
    public ArrayList<Coupon> getAllCoupons() throws SQLException {
        String sql = "select * from coupons";
        System.out.println(sql);
        ResultSet resultSet = execute(sql);
        return returnCoupons(resultSet);
    }

    @Override
    public ArrayList<Coupon> getAllCoupons( int company_id)
            throws SQLException {
        String sql = "select * from coupons where company_id = "+company_id;
        System.out.println(sql);
        ResultSet resultSet = execute(sql);
        return returnCoupons(resultSet);
    }

    @Override
    public ArrayList<Coupon> getAllCoupons(int company_id, Category category) throws SQLException {
        String sql = "select * from coupons where company_id ="+company_id+" and category_id = " + (category.ordinal()+1);
        System.out.println(sql);
        ResultSet resultSet = execute(sql);
        return returnCoupons(resultSet);
    }

    @Override
    public ArrayList<Coupon> getAllCoupons(int company_id, double maxPrice) throws SQLException {
        String sql = "select * from coupons where company_id ="+company_id+" and price <="+maxPrice;
        System.out.println(sql);
        ResultSet resultSet = execute(sql);
        return returnCoupons(resultSet);
    }

    @Override
    public Coupon getOneCoupon(int couponId) throws SQLException {
        String sql = "select * from coupons where id =" + couponId;
        System.out.println(sql);
        ResultSet resultSet = execute(sql);
        resultSet.next();
        return new Coupon(resultSet.getInt(1),resultSet.getInt(2),
                Category.values()[resultSet.getInt(3)-1], resultSet.getString(4),
                resultSet.getString(5), resultSet.getDate(6), resultSet.getDate(7),
                resultSet.getInt(8), resultSet.getDouble(9), resultSet.getString(10));
    }

    @Override
    public void addCouponPurchase(int customerId, int couponId) throws
            SQLException {
        String sql = "insert into customers_vs_coupons (customer_id, coupon_id) " +
        "values (" +customerId + "," +couponId +")";
        System.out.println(sql);
        execute(sql);
    }

    @Override
    public void deleteCouponPurchase(int customerId, int couponId) throws SQLException {
        String sql = "delete from customers_vs_coupons where customer_id = " + customerId + "and coupon_id =" + couponId ;
        System.out.println(sql);
        execute(sql);
    }

    @Override
    public void deleteCouponPurchase(String field, int value) throws SQLException {
        String sql = "delete from customers_vs_coupons where "+field+" = " + value ;
        System.out.println(sql);
        execute(sql);
    }

    @Override
    public ArrayList<Coupon> getCustomersCoupon(int customer_id) throws SQLException {
        String sql = "select * from coupons where id in" +
                "(select coupon_id from customers_vs_coupons where customer_id ="+ customer_id + ")";
        System.out.println(sql);
        ResultSet resultSet = execute(sql);
        return returnCoupons(resultSet);
    }

    @Override
    public ArrayList<Coupon> getCustomersCoupon(int customer_id, Category category) throws SQLException {
        String sql = "select * from coupons where id in" +
                "(select coupon_id from customers_vs_coupons where customer_id ="+ customer_id + ")" +
                "and category_id ="+ category.ordinal()+1;
        System.out.println(sql);
        ResultSet resultSet = execute(sql);
        return returnCoupons(resultSet);
    }

    @Override
    public ArrayList<Coupon> getCustomersCoupon(int customer_id, double max_price) throws SQLException {
        String sql = "select * from coupons where id in" +
                "(select coupon_id from customers_vs_coupons where customer_id ="+ customer_id + ")" +
                "and price <= "+max_price;
        System.out.println(sql);
        ResultSet resultSet = execute(sql);
        return returnCoupons(resultSet);
    }

    @Override
    public boolean isTitleExist(String title, int company_id) throws SQLException {
        String sql = "select * from coupons where company_id = " + company_id + " and title = '" + title + "'";
        System.out.println(sql);
        return executeQuery(sql);
    }

    public void addCategory(Category category) throws SQLException {
        String sql = "insert into categories (name) " +
                "values ('" +category+"')";
        System.out.println(sql);
        execute(sql);
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

    /**
     * Change the results from the SQL query to a list of coupon entities.
     * @param resultSet
     * @return list of coupons
     * @throws SQLException
     */
    public ArrayList<Coupon> returnCoupons(ResultSet resultSet) throws SQLException {
        ArrayList<Coupon> coupons = new ArrayList<>();
        while (resultSet.next()) {
            coupons.add(new Coupon(resultSet.getInt(1),
                    resultSet.getInt(2), Category.values()[resultSet.getInt(3)-1],
                    resultSet.getString(4), resultSet.getString(5), resultSet.getDate(6),
                    resultSet.getDate(7), resultSet.getInt(8), resultSet.getDouble(9),
                    resultSet.getString(10)));
        }
        return coupons;
    }
}


