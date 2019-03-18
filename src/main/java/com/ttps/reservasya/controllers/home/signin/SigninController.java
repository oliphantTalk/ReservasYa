package com.ttps.reservasya.controllers.home.signin;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SigninController {

    @Autowired
    @Qualifier("redirecter")
    private SimpleUrlAuthenticationSuccessHandler simpleUrlAuthenticationSuccessHandler;


	@RequestMapping(value = "signin")
	public String signin(@RequestHeader(value = "Referer",required = false) String referer) {
        int barras = StringUtils.countMatches(referer, "/");
        if(!StringUtils.isBlank(referer) && !referer.contains("signin") && barras > 4){
	        simpleUrlAuthenticationSuccessHandler.setDefaultTargetUrl(referer);
        }
        return "signin/signin";
    }


}
