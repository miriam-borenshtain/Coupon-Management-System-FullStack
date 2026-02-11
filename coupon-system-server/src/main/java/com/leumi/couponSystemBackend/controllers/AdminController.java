package com.leumi.couponSystemBackend.controllers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.leumi.couponSystemBackend.authorization.SimpleTokenManager;
import com.leumi.couponSystemBackend.couponSystemEntity.Company;
import com.leumi.couponSystemBackend.couponSystemEntity.Customer;
import com.leumi.couponSystemBackend.couponSystemFACADE.AdminFacade;
import com.leumi.couponSystemBackend.couponSystemLogin.ClientType;
import com.leumi.couponSystemBackend.couponSystemLogin.LoginManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/admin")
public class AdminController extends ClientController{


	@Autowired
	SimpleTokenManager simpleTokenManager;

	private AdminFacade adminFacade;

	public AdminController() {
		super();
	}

	@GetMapping("/login")
	@Override
	public ResponseEntity<?> login(@RequestParam(name = "email") String email, @RequestParam(name = "password") String password)
	{

			System.out.println("Got request: /api/login " + email + " and " + password);
			adminFacade = (AdminFacade) LoginManager.getInstance().login(email, password, ClientType.Administrator);
			if (adminFacade.login(email, password)) {
				String token = simpleTokenManager.getNewToken();
				return new ResponseEntity<String>("true", HttpStatus.OK);
			}
			return new ResponseEntity<String>("Login error!", HttpStatus.BAD_REQUEST);
	}
	@PostMapping("/addCompany")
	@ResponseBody
	ResponseEntity<?> addCompany(@RequestBody Company company, @RequestHeader("token") String token) {
		System.out.println("/addCompany");
		try {
			if (simpleTokenManager.isTokenExist(token)) {
				try {
					System.out.println("Got company to add: " + company);
					adminFacade.addCompany(company);
					return new ResponseEntity<>("company add successful", HttpStatus.OK);
				} catch (Exception e) {
					e.printStackTrace();
					return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
				}
			}
			return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/updateCompany")
	@ResponseBody
	ResponseEntity<?> updateCompany(@RequestBody Company company, @RequestHeader("token") String token) {
		try {
			if (simpleTokenManager.isTokenExist(token)) {
				try {
					System.out.println("Got company to update:" + company);
					adminFacade.updateCompany(company);
					return new ResponseEntity<>(HttpStatus.OK);
				} catch (Exception e) {
					e.printStackTrace();
					return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
				}
			}
			return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/deleteCompany")
	@ResponseBody
	ResponseEntity<?> deleteCompany(@RequestParam(name = "id") int companyId, @RequestHeader("token") String token) {
		try {
			if (simpleTokenManager.isTokenExist(token)) {
				try {
					System.out.println("Got company id to delete: " + companyId);
					adminFacade.deleteCompany(companyId);
					return new ResponseEntity<>(HttpStatus.OK);
				} catch (Exception e) {
					e.printStackTrace();
					return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
				}
			}
			return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getAllCompanies")
	public ResponseEntity<?> getAllCompanies(@RequestHeader("token") String token){
	    System.out.println("Got request: /api/admin/getAllCompanies");
        try {
		if (simpleTokenManager.isTokenExist(token)) {
			try {
				System.out.println("Return all the companies");
				ArrayList<Company> companies = adminFacade.getAllCompanies();
				return new ResponseEntity<>(companies, HttpStatus.OK);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
	} catch (Exception e) {
		return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
	}
	}
	@GetMapping("/getOneCompany")
	@ResponseBody
	ResponseEntity<?> getOneCompany(@RequestParam(name = "id") int companyId, @RequestHeader("token") String token) {
		try {
			if (simpleTokenManager.isTokenExist(token)) {
				try {
					System.out.println("Return the company by Id: " + companyId);
					Company company = adminFacade.getOneCompany(companyId);
					return new ResponseEntity<>(company, HttpStatus.OK);
				} catch (Exception e) {
					e.printStackTrace();
					return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
				}
			}

			return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PostMapping("/addCustomer")
	@ResponseBody
	ResponseEntity<?> addCustomer(@RequestBody Customer customer, @RequestHeader("token") String token) {
		try {
			if (simpleTokenManager.isTokenExist(token)) {
				try {
					System.out.println("Got company to add: " + customer);
					adminFacade.addCustomer(customer);
					return new ResponseEntity<>(HttpStatus.OK);
				} catch (Exception e) {
					e.printStackTrace();
					return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
				}
			}
			return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@PutMapping("/updateCustomer")
	@ResponseBody
	ResponseEntity<?> updateCustomer(@RequestBody Customer customer, @RequestHeader("token") String token) {
		try {
			if (simpleTokenManager.isTokenExist(token)) {
				try {
					System.out.println("Got customer to update:" + customer);
					adminFacade.updateCustomer(customer);
					return new ResponseEntity<>(HttpStatus.OK);
				} catch (Exception e) {
					e.printStackTrace();
					return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
				}
			}
			return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@DeleteMapping("/deleteCustomer")
	@ResponseBody
	ResponseEntity<?> deleteCustomer(@RequestParam(name = "id") int customerId, @RequestHeader("token") String token) {
		try {
			if (simpleTokenManager.isTokenExist(token)) {
				try {
					System.out.println("Got customer id to delete: " + customerId);
					adminFacade.deleteCustomer(customerId);
					return new ResponseEntity<>(HttpStatus.OK);
				} catch (Exception e) {
					e.printStackTrace();
					return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
				}
			}
			return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getAllCustomers")
	@ResponseBody
	ResponseEntity<?> getAllCustomers(@RequestHeader("token") String token) {
		try {
			if (simpleTokenManager.isTokenExist(token)) {
				try {
					System.out.println("Return all the customers");
					ArrayList<Customer> customers = adminFacade.getAllCustomers();
					return new ResponseEntity<>(customers, HttpStatus.OK);
				} catch (Exception e) {
					e.printStackTrace();
					return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
				}
			}
			return new ResponseEntity<String>("No Session!", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("/getOneCustomer")
	@ResponseBody
	ResponseEntity<?> getOneCustomer(@RequestParam(name = "id") int customerId, @RequestHeader("token") String token) {
		try {
			if (simpleTokenManager.isTokenExist(token)) {
				try {
					System.out.println("Return the customer by Id: " + customerId);
					Customer customer = adminFacade.getOneCustomer(customerId);
					return new ResponseEntity<>(customer, HttpStatus.OK);
				} catch (Exception e) {
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




