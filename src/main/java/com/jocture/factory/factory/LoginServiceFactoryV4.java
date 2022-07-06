package com.jocture.factory.factory;

import com.jocture.factory.client.LoginType;
import com.jocture.factory.service.LoginService;
import com.jocture.factory.service.MobileLoginService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.kafka.KafkaProperties;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.function.Consumer;

@Component
public class LoginServiceFactoryV4 implements LoginServiceFactory{
    private final List<LoginService> loginServices;
    private final Logger log = LoggerFactory.getLogger(LoginServiceFactoryV4.class);
    public LoginServiceFactoryV4(List<LoginService> loginServices) {
        this.loginServices = loginServices;
    }

    @PostConstruct
    void postConstruct(){
        log.info(">>> postConstruct()");
        Set<LoginType> loginTypes = EnumSet.allOf(LoginType.class);
        loginTypes.forEach(loginType-> {
            try {
                LoginServiceCache.put(loginType, getLoginService(loginType));
            } catch (NoSuchElementException e) {
                log.warn(getNotFoundString(loginType));
            }
        });
    }

    private String getNotFoundString ( LoginType loginType){
        return "Cannot found LoginService of " + loginType;
    }

    public LoginService find(LoginType loginType){
        return LoginServiceCache.get(loginType)
                .orElseGet(()->{
                    log.info(">>>> No Cache {} ", loginType);
                    LoginService loginService = getLoginService(loginType);
                    LoginServiceCache.put(loginType, loginService );
                    return loginService;
                });
    }

    private LoginService getLoginService(LoginType loginType) {
        return loginServices.stream()
                .filter(service -> service.supports(loginType))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException(getNotFoundString(loginType)));
    }


    private static class LoginServiceCache{
        private static final Map<LoginType, LoginService> cachedLoginServiceMap = new EnumMap<>(LoginType.class);
        public static Optional<LoginService> get(LoginType loginType){
            return Optional.ofNullable(cachedLoginServiceMap.get(loginType));
        }
        public static void put(LoginType loginType , LoginService loginService){
            cachedLoginServiceMap.put(loginType , loginService);
        }
    }
}
