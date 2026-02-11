package com.leumi.couponSystemBackend.controllers;

import com.leumi.couponSystemBackend.authorization.SimpleTokenManager;
import com.leumi.couponSystemBackend.couponSystemEntity.Category;
import com.leumi.couponSystemBackend.couponSystemEntity.Company;
import com.leumi.couponSystemBackend.couponSystemEntity.Coupon;
import com.leumi.couponSystemBackend.couponSystemFACADE.CompanyFacade;
import com.leumi.couponSystemBackend.couponSystemLogin.ClientType;
import com.leumi.couponSystemBackend.couponSystemLogin.LoginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.ArrayList;

@RestController
@RequestMapping("api/company")
public class CompanyController extends ClientController {

    private CompanyFacade companyFacade;
    @Autowired
    SimpleTokenManager simpleTokenManager;

    public CompanyController() {
        super();
    }

    @GetMapping("/login")
    @Override
    public ResponseEntity<?> login(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password) {
        try {
            companyFacade = (CompanyFacade) LoginManager.getInstance().login(email, password, ClientType.Company);
            if (companyFacade.login(email, password)) {
                String token = simpleTokenManager.getNewToken();
                return new ResponseEntity<String>(token, HttpStatus.OK);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ResponseEntity<String>("Login error!", HttpStatus.BAD_REQUEST);
    }

    @PostMapping("/addCoupon")
    ResponseEntity<?> addCoupon(@RequestBody Coupon coupon, @RequestHeader("token") String token) {
        try {
            if (simpleTokenManager.isTokenExist(token)) {
                try {
                    System.out.println("Got coupon to add: " + coupon);
                    companyFacade.addCoupon(coupon);
                    return new ResponseEntity<>(HttpStatus.OK);
                } catch (SQLException e) {
                    e.printStackTrace();
                    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
                }
            }
            return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @PutMapping("/updateCoupon")
    ResponseEntity<?> updateCoupon(@RequestBody Coupon coupon, @RequestHeader("token") String token) {
        try {
            if (simpleTokenManager.isTokenExist(token)) {
                try {
                    System.out.println("Got coupon to update: " + coupon);
                    companyFacade.updateCoupon(coupon);
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

    @DeleteMapping("/deleteCoupon")
    ResponseEntity<?> deleteCoupon(@RequestParam(name = "id") int couponId, @RequestHeader("token") String token) {
        try {
            if (simpleTokenManager.isTokenExist(token)) {
                try {
                    System.out.println("Got couponId to delete: " + couponId);
                    companyFacade.deleteCoupon(couponId);
                    return new ResponseEntity<>(HttpStatus.OK);
                } catch (SQLException e) {
                    e.printStackTrace();
                    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
                }
            }
            return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getCoupons")
    @ResponseBody
    ResponseEntity<?> getCompanyCoupons(@RequestHeader("token") String token) {
        try {
            if (simpleTokenManager.isTokenExist(token)) {
                try {
                    System.out.println("get all the coupons of the company");
                    return new ResponseEntity<ArrayList<Coupon>>(companyFacade.getCompanyCoupons(), HttpStatus.OK);
                } catch (SQLException e) {
                    e.printStackTrace();
                    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
                }
            }
            return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getCouponsByCategory")
    @ResponseBody
    ResponseEntity<?> getCompanyCoupons(@RequestParam(name = "category") Category category, @RequestHeader("token") String token) {
        try {
            if (simpleTokenManager.isTokenExist(token)) {
                try {
                    System.out.println("get all the coupons of The company with category" + category);
                    return new ResponseEntity<ArrayList<Coupon>>(companyFacade.getCompanyCoupons(category), HttpStatus.OK);
                } catch (SQLException e) {
                    e.printStackTrace();
                    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
                }
            }
            return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getCouponsByPrice")
    @ResponseBody
    ResponseEntity<?> getCompanyCoupons(@RequestParam(name = "maxPrice") double maxPrice, @RequestHeader("token") String token) {
        try {
            if (simpleTokenManager.isTokenExist(token)) {
                try {
                    System.out.println("get all the coupons of the company that their price less than" + maxPrice);
                    return new ResponseEntity<ArrayList<Coupon>>(companyFacade.getCompanyCoupons(maxPrice), HttpStatus.OK);
                } catch (SQLException e) {
                    e.printStackTrace();
                    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
                }
            }
            return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getCoupon")
    @ResponseBody
    ResponseEntity<?> getCompanyCoupon(@RequestParam(name = "couponId") int couponId, @RequestHeader("token") String token) {
        try {
            if (simpleTokenManager.isTokenExist(token)) {
                try {
                    System.out.println("get coupon with id " + couponId + " of the company");
                    return new ResponseEntity<Coupon>(companyFacade.getCompanyCoupon(couponId), HttpStatus.OK);
                } catch (SQLException e) {
                    e.printStackTrace();
                    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
                }
            }
            return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/getCompanyDetails")
    @ResponseBody
    ResponseEntity<?> getCompanyDetails(@RequestHeader("token") String token) {
        try {
            if (simpleTokenManager.isTokenExist(token)) {
                try {
                    System.out.println("get the details of company");
                    return new ResponseEntity<Company>(companyFacade.getCompanyDetails(), HttpStatus.OK);
                } catch (SQLException e) {
                    e.printStackTrace();
                    return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
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
