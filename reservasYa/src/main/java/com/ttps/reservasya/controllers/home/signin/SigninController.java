package com.ttps.reservasya.controllers.home.signin;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class SigninController {

    @Autowired
    private SimpleUrlAuthenticationSuccessHandler simpleUrlAuthenticationSuccessHandler;


	@RequestMapping(value = "signin")
	public String signin(@RequestHeader(value = "Referer",required = false) String referer) {
	    if(!StringUtils.isBlank(referer) && !referer.contains("signin")){
	        simpleUrlAuthenticationSuccessHandler.setDefaultTargetUrl(referer);
        }
        return "signin/signin";
    }


}
