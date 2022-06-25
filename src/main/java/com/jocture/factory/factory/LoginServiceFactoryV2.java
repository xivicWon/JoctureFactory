package com.jocture.factory.factory;

import com.jocture.factory.client.LoginType;
import com.jocture.factory.service.GoogleLoginService;
import com.jocture.factory.service.LoginService;
import com.jocture.factory.service.MobileLoginService;
import com.jocture.factory.service.WebLoginService;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.NoSuchElementException;

//@Component
public class LoginServiceFactoryV2 implements LoginServiceFactory{
    private final List<LoginService> loginServices;

    public LoginServiceFactoryV2(List<LoginService> loginServices) {
        this.loginServices = loginServices;
    }

    public LoginService find(LoginType loginType){
        return loginServices.stream()
                .filter(service-> service.supports(loginType))
                .findFirst()
                .orElseThrow(()->new NoSuchElementException("Cannot found LoginService of " + loginType));

    }

}
