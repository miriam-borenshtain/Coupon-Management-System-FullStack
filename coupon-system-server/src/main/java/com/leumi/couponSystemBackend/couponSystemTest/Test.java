package com.leumi.couponSystemBackend.couponSystemTest;

import com.leumi.couponSystemBackend.couponSystemEntity.Category;
import com.leumi.couponSystemBackend.couponSystemEntity.Company;
import com.leumi.couponSystemBackend.couponSystemEntity.Coupon;
import com.leumi.couponSystemBackend.couponSystemEntity.Customer;
import com.leumi.couponSystemBackend.couponSystemFACADE.AdminFacade;
import com.leumi.couponSystemBackend.couponSystemFACADE.CompanyFacade;
import com.leumi.couponSystemBackend.couponSystemFACADE.CustomerFacade;
import com.leumi.couponSystemBackend.couponSystemJob.CouponExpirationDailyJob;
import com.leumi.couponSystemBackend.couponSystemLogin.ClientType;
import com.leumi.couponSystemBackend.couponSystemLogin.LoginManager;

import java.sql.Date;
import java.util.ArrayList;

public class Test {

    public static void testAll() {
        Thread job= new Thread(new CouponExpirationDailyJob());
        job.start();

        AdminFacade adminFacade = (AdminFacade) LoginManager.getInstance().login("admin@admin.com","admin", ClientType.Administrator);
        try {
            adminFacade.addCompany(new Company(1, "AAA", "AAA@gmail.com", "123"));
            adminFacade.addCompany(new Company(2, "BBB", "BBB@gmail.com", "234"));
            adminFacade.addCompany(new Company(3, "CCC", "CCC@gmail.com", "345"));
            adminFacade.addCustomer(new Customer(1, "aaa", "bbb", "aaa_bbb@gmail.com", "111"));
            adminFacade.addCustomer(new Customer(2, "bbb", "ccc", "bbb_ccc@gmail.com", "222"));

            ArrayList<Company> allCompanies = adminFacade.getAllCompanies();
            System.out.println("allCompanies " + allCompanies);

            ArrayList<Customer> allCustomers = adminFacade.getAllCustomers();
            System.out.println("allCustomers " + allCustomers);

            adminFacade.updateCompany(new Company(1, "AAA", "AAA1@gmail.com", "1234"));
            System.out.println("After update: " + adminFacade.getOneCompany(1));
            adminFacade.deleteCompany(2);
            adminFacade.updateCustomer(new Customer(1, "aaa", "bbb", "aaa_bbb_c@gmail.com", "333"));
            System.out.println("After update: " + adminFacade.getOneCustomer(1));
            adminFacade.deleteCustomer(2);

            CompanyFacade companyFacade = (CompanyFacade) LoginManager.getInstance().login("AAA1@gmail.com", "1234", ClientType.Company);
            companyFacade.addCoupon(new Coupon(1, 1, Category.Food, "50%", "Only above 100$", Date.valueOf("2022-01-01"), Date.valueOf("2022-03-01"), 5, 20.0, ""));
            companyFacade.addCoupon(new Coupon(2, 1, Category.Electricity, "30%", "Only above 100$", Date.valueOf("2022-01-01"), Date.valueOf("2022-02-01"), 5, 10.0, ""));
            companyFacade.addCoupon(new Coupon(3, 1, Category.Food, "1+1", "Coffee", Date.valueOf("2022-01-01"), Date.valueOf("2022-01-26"), 5, 25.0, ""));
            companyFacade.deleteCoupon(1);

            Coupon c = new Coupon(4, 3, Category.Food, "Free", "Only above 50$", Date.valueOf("2022-01-01"), Date.valueOf("2022-03-01"), 5, 20.0, "");
            companyFacade.addCoupon(c);
            c.setAmount(100);
            companyFacade.updateCoupon(c);

            System.out.println("-----By max price:\n " + companyFacade.getCompanyCoupons(19));
            System.out.println("-----By category:\n" + companyFacade.getCompanyCoupons(Category.Food));
            System.out.println("-----All:\n" + companyFacade.getCompanyCoupons());
            System.out.println(companyFacade.getCompanyCoupon(2));
            System.out.println(companyFacade.getCompanyDetails());

            CustomerFacade customerFacade = (CustomerFacade) LoginManager.getInstance().login("aaa_bbb_c@gmail.com", "333", ClientType.Customer);
            customerFacade.purchaseCoupon(companyFacade.getCompanyCoupons().get(0));
            customerFacade.purchaseCoupon(companyFacade.getCompanyCoupons().get(1));

            System.out.println("___Coupons___");
            System.out.println(customerFacade.getCustomerCoupons());
            System.out.println(customerFacade.getCustomerCoupons(19));
            System.out.println(customerFacade.getCustomerCoupons(Category.Food));
            System.out.println(customerFacade.getCustomerDetails());

        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
