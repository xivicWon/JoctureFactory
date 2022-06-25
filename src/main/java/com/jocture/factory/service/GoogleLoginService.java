package com.jocture.factory.service;

import com.jocture.factory.client.LoginType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class GoogleLoginService implements LoginService{

    private final Logger log = LoggerFactory.getLogger(GoogleLoginService.class);
    @Override
    public void login(){
        log.info(">>>>>>>>>>>> login");
    }
    @Override
    public boolean supports(LoginType loginType) {
        return loginType == LoginType.GOOGLE;
    }

}
