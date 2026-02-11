package com.leumi.couponSystemBackend.couponSystemLogin;


import com.leumi.couponSystemBackend.couponSystemFACADE.AdminFacade;
import com.leumi.couponSystemBackend.couponSystemFACADE.ClientFacade;
import com.leumi.couponSystemBackend.couponSystemFACADE.CompanyFacade;
import com.leumi.couponSystemBackend.couponSystemFACADE.CustomerFacade;

/**
 * Singleton class
 */
public class LoginManager {

    private static LoginManager instance = null;
    private ClientFacade clientFacade;
    private LoginManager(){
    }

    public static LoginManager getInstance() {

        if (instance == null){
            instance = new LoginManager();
        }
        return instance;
    }

    /**
     * @param email
     * @param password
     * @param type
     * @return facade of the user's type (admin, customer or company) if the login was succeeded.
     */
    public ClientFacade login(String email, String password, ClientType type){
        switch (type){
            case Company:
                clientFacade = new CompanyFacade();
                break;
            case Customer:
                clientFacade = new CustomerFacade();
                break;
            case Administrator:
                clientFacade = new AdminFacade();
                break;
        }
        if (clientFacade.login(email, password)){
            return clientFacade;
        } else {
            return null;
        }
    }
}
