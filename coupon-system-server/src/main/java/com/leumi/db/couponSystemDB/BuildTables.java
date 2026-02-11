package com.leumi.db.couponSystemDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class BuildTables {

    private static Connection connection;


    public BuildTables(){
        try{
        createAllTables();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void createAllTables() throws SQLException {
        createCompaniesTable();
        System.out.println("createCompaniesTable");
        createCustomersTable();
        System.out.println("createCustomersTable");
        createCategoriesTable();
        System.out.println("createCategoriesTable");
        createCouponsTable();
        System.out.println("createCouponsTable");
        createCustomersVsCouponsTable();
    }


    private void createCompaniesTable() throws SQLException {
        String sql = "CREATE TABLE `coupon_system`.`companies` (`id` INT NOT NULL AUTO_INCREMENT," +
                "`name` VARCHAR(45) NULL UNIQUE,\n" +
                "  `email` VARCHAR(45) NULL UNIQUE,\n" +
                "  `password` VARCHAR(45) NULL,\n" +
                "  PRIMARY KEY (`id`));";
        executeTableCreation(sql);
    }

    private void createCustomersTable() throws SQLException {
        String sql = "CREATE TABLE `coupon_system`.`customers` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `first_name` VARCHAR(45) NULL,\n" +
                "  `last_name` VARCHAR(45) NULL,\n" +
                "  `email` VARCHAR(45) NULL UNIQUE,\n" +
                "  `password` VARCHAR(45) NULL,\n" +
                "   PRIMARY KEY (`id`));";
        executeTableCreation(sql);
    }

    private void createCategoriesTable() throws SQLException {
        String sql = "CREATE TABLE `coupon_system`.`categories` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `name` VARCHAR(45) NULL,\n" +
                "  PRIMARY KEY (`id`));";
        executeTableCreation(sql);
    }


    private void createCouponsTable() throws SQLException {
        String sql ="CREATE TABLE `coupon_system`.`coupons` (\n" +
                "  `id` INT NOT NULL AUTO_INCREMENT,\n" +
                "  `company_id` INT NOT NULL,\n" +
                "  `category_id` INT NOT NULL,\n" +
                "  `title` VARCHAR(45) NULL,\n" +
                "  `description` VARCHAR(150) NULL,\n" +
                "  `start_date` DATE NULL,\n" +
                "  `end_date` DATE NULL,\n" +
                "  `amount` INT NULL,\n" +
                "  `price` DOUBLE NULL,\n" +
                "  `image` VARCHAR(150) NULL,\n" +
                "  PRIMARY KEY (`id`),\n" +
                "  INDEX `company_id_idx` (`company_id` ASC) VISIBLE,\n" +
                "  INDEX `category_id_idx` (`category_id` ASC) VISIBLE,\n" +
                "  CONSTRAINT `company_id`\n" +
                "    FOREIGN KEY (`company_id`)\n" +
                "    REFERENCES `coupon_system`.`companies` (`id`)\n" +
                "    ON DELETE CASCADE\n" +
                "    ON UPDATE NO ACTION,\n" +
                "  CONSTRAINT `category_id`\n" +
                "    FOREIGN KEY (`category_id`)\n" +
                "    REFERENCES `coupon_system`.`categories` (`id`)\n" +
                "    ON DELETE CASCADE\n" +
                "    ON UPDATE NO ACTION," +
                "    CONSTRAINT UC_Coupon UNIQUE (company_id,title));";

        executeTableCreation(sql);
    }

    private void createCustomersVsCouponsTable() throws SQLException {
        String sql = "CREATE TABLE `coupon_system`.`customers_vs_coupons` (\n" +
                "  `customer_id` INT NOT NULL,\n" +
                "  `coupon_id` INT NOT NULL,\n" +
                "  PRIMARY KEY (`customer_id`, `coupon_id`),\n" +
                "  INDEX `coupon_id_idx` (`coupon_id` ASC) VISIBLE,\n" +
                "  CONSTRAINT `customer_id`\n" +
                "    FOREIGN KEY (`customer_id`)\n" +
                "    REFERENCES `coupon_system`.`customers` (`id`)\n" +
                "    ON DELETE CASCADE\n" +
                "    ON UPDATE NO ACTION,\n" +
                "  CONSTRAINT `coupon_id`\n" +
                "    FOREIGN KEY (`coupon_id`)\n" +
                "    REFERENCES `coupon_system`.`coupons` (`id`)\n" +
                "    ON DELETE CASCADE\n" +
                "    ON UPDATE NO ACTION);";
        executeTableCreation(sql);
    }

    private void executeTableCreation(String sql) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(sql);
        preparedStatement.execute();
    }
}
