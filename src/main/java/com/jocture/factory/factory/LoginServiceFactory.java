package com.jocture.factory.factory;

import com.jocture.factory.client.LoginType;
import com.jocture.factory.service.LoginService;
import org.springframework.stereotype.Component;

@Component
public interface LoginServiceFactory {


    LoginService find (LoginType loginType);
}
