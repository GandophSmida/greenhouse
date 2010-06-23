package com.springsource.greenhouse.networks;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.oauth.consumer.token.OAuthConsumerToken;
import org.springframework.security.oauth.consumer.token.OAuthConsumerTokenServicesFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/networks")
public class SocialNetworksController {
  @RequestMapping("/twitter")
  public void authorizeTwitter(HttpServletRequest request, Authentication authentication) {
      String oauthToken = request.getParameter("oauth_token");      
      if(oauthToken != null && !oauthToken.isEmpty()) {
          String oauthVerifier = request.getParameter("oauth_verifier");
          OAuthConsumerToken token = new OAuthConsumerToken();
          token.setAccessToken(true);
          token.setResourceId("twitter");
          token.setValue(oauthToken);
          token.setSecret(oauthVerifier);          
          tokenServicesFactory.getTokenServices(authentication, request).storeToken("twitter", token);
      }
  }
  
  @Autowired
  OAuthConsumerTokenServicesFactory tokenServicesFactory;
}