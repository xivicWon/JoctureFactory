package com.jocture.factory.factory;

import com.jocture.factory.client.LoginType;
import com.jocture.factory.service.GoogleLoginService;
import com.jocture.factory.service.LoginService;
import com.jocture.factory.service.MobileLoginService;
import com.jocture.factory.service.WebLoginService;
import org.springframework.stereotype.Component;

import java.util.NoSuchElementException;

//@Component
public class LoginServiceFactoryV1 implements LoginServiceFactory {
    private final WebLoginService webLoginService;
    private final MobileLoginService mobileLoginService;
    private final GoogleLoginService googleLoginService;

    public LoginServiceFactoryV1(
            WebLoginService webLoginService,
            MobileLoginService mobileLoginService,
            GoogleLoginService googleLoginService) {
        this.webLoginService = webLoginService;
        this.mobileLoginService = mobileLoginService;
        this.googleLoginService = googleLoginService;
    }

    public LoginService find(LoginType loginType){
        switch ( loginType ) {
            case WEB:
                return webLoginService;
            case MOBILE:
                return mobileLoginService;
            case GOOGLE:
                return googleLoginService;

            default:
                throw new NoSuchElementException("Cannot found LoginService of " + loginType);
        }
    }

}
