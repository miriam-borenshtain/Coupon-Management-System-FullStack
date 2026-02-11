package com.leumi.couponSystemBackend.controllers;

import com.leumi.couponSystemBackend.authorization.SimpleTokenManager;
import com.leumi.couponSystemBackend.couponSystemEntity.Category;
import com.leumi.couponSystemBackend.couponSystemEntity.Coupon;
import com.leumi.couponSystemBackend.couponSystemEntity.Customer;
import com.leumi.couponSystemBackend.couponSystemFACADE.CustomerFacade;
import com.leumi.couponSystemBackend.couponSystemLogin.ClientType;
import com.leumi.couponSystemBackend.couponSystemLogin.LoginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/customer")
public class CustomerController extends ClientController {

    private CustomerFacade customerFacade;

    @Autowired
    SimpleTokenManager simpleTokenManager;

    public CustomerController() {
        super();
    }

    @GetMapping("/login")
    @Override
    public ResponseEntity<?> login(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {
        try {
            customerFacade = (CustomerFacade) LoginManager.getInstance().login(email, password, ClientType.Customer);
            if (customerFacade.login(email, password)) {
                String token = simpleTokenManager.getNewToken();
                return new ResponseEntity<String>(token, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>("Login error!", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/purchaseCoupon")
    ResponseEntity<?> purchaseCoupon(@RequestBody Coupon coupon, @RequestHeader("token") String token) {
        try {
            if (simpleTokenManager.isTokenExist(token)) {
                try {
                    customerFacade.purchaseCoupon(coupon);
                    return new ResponseEntity<>(HttpStatus.OK);
                } catch (Exception e) {
                    e.printStackTrace();
                    return new ResponseEntity<String>(e.getMessage(), HttpStatus.BAD_REQUEST);
                }
            }
            return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getAllCoupons")
    @ResponseBody
    ResponseEntity<?> getCustomerCoupons(@RequestHeader("token") String token) {
        try {
            if (simpleTokenManager.isTokenExist(token)) {
                try {
                    ArrayList<Coupon> coupons = customerFacade.getCustomerCoupons();
                    return new ResponseEntity<ArrayList<Coupon>>(coupons, HttpStatus.OK);
                } catch (SQLException e) {
                    e.printStackTrace();
                    return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
                }
            }
            return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getCouponsByCategory")
    @ResponseBody
    ResponseEntity<?> getCustomerCoupons(@RequestParam(name = "category") Category category, @RequestHeader("token") String token) {
        try {
            if (simpleTokenManager.isTokenExist(token)) {
                try {
                    ArrayList<Coupon> coupons = customerFacade.getCustomerCoupons(category);
                    return new ResponseEntity<ArrayList<Coupon>>(coupons, HttpStatus.OK);
                } catch (SQLException e) {
                    e.printStackTrace();
                    return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
                }
            }
            return new ResponseEntity<String>("There is no coupons with the given category", HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getCouponsByMaxPrice")
    @ResponseBody
    ResponseEntity<?> getCustomerCoupons(@RequestParam(name = "price") double maxPrice, @RequestHeader("token") String token) {
        try {
            if (simpleTokenManager.isTokenExist(token)) {
                try {
                    ArrayList<Coupon> coupons = customerFacade.getCustomerCoupons(maxPrice);
                    return new ResponseEntity<ArrayList<Coupon>>(coupons, HttpStatus.OK);
                } catch (SQLException e) {
                    e.printStackTrace();
                    return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
                }
            }
            return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getCustomerDetails")
    @ResponseBody
    ResponseEntity<?> getCustomerDetails(@RequestHeader("token") String token) {
        try {
            if (simpleTokenManager.isTokenExist(token)) {
                try {
                    Customer customer = customerFacade.getCustomerDetails();
                    return new ResponseEntity<Customer>(customer, HttpStatus.OK);
                } catch (SQLException e) {
                    e.printStackTrace();
                    return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
                }
            }
            return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/logout")
    @ResponseBody
    ResponseEntity<?> logout(@RequestHeader("token") String token) {
        try {
            if (simpleTokenManager.isTokenExist(token)) {
                simpleTokenManager.removeToken(token);
                return new ResponseEntity<>(HttpStatus.OK);
            }
            return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

}
