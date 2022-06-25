package com.jocture.factory.client;

import com.jocture.factory.factory.LoginServiceFactory;
import com.jocture.factory.service.LoginService;
import org.springframework.stereotype.Component;


@Component
public class LoginClient {
    private final LoginServiceFactory loginServiceFactory;

    public LoginClient(LoginServiceFactory loginServiceFactory) {
        this.loginServiceFactory = loginServiceFactory;
    }

    public void login(LoginType loginType){
        LoginService loginService = loginServiceFactory.find(loginType);
        loginService.login();
    }
}
