package com.leumi.couponSystemBackend.couponSystemEntity;

import java.util.ArrayList;

public class Company {
    private int id;
    private String name;
    private String email;
    private String password;
    private ArrayList<Coupon> coupons;

    public Company(){
        super();
    }

    public Company(int id, String name, String email, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.coupons = new ArrayList<>();
    }

    public Company( String name, String email, String password) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.coupons = new ArrayList<>();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ArrayList<Coupon> getCoupons() {
        return coupons;
    }

    public void addCoupon(Coupon coupon) {
        this.coupons.add(coupon);
    }

    @Override
    public String toString() {
        return "Company{" +
                "\nid=" + id +
                "\nname='" + name + '\'' +
                "\nemail='" + email + '\'' +
                "\npassword='" + password + '\'' +
                "\ncoupons=" + coupons +
                '}';
    }
}