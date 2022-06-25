package com.jocture.factory.service;

import com.jocture.factory.client.LoginType;

public interface LoginService {

     boolean supports(LoginType loginType);
     void login();
}
