//package com.setplex.odin.security;
//
//import com.setplex.middleware.nora.security.user.WbsApiUser;
//import java.util.Collection;
//import org.springframework.security.access.ConfigAttribute;
//import org.springframework.security.access.intercept.RunAsManagerImpl;
//import org.springframework.security.access.intercept.RunAsUserToken;
//import org.springframework.security.core.Authentication;
//import org.springframework.security.core.userdetails.UserDetails;
//
//public class MwRunAsManager extends RunAsManagerImpl {
//
//    @Override
//    public Authentication buildRunAs(Authentication authentication, Object object, Collection<ConfigAttribute> attributes) {
//        UserDetails user = WbsApiUser.getInstance();
//        return new RunAsUserToken(getKey(), user, user.getUsername(), user.getAuthorities(), authentication.getClass());
//    }
//
//    @Override
//    public boolean supports(ConfigAttribute attribute) {
//        return true;
//    }
//
//    @Override
//    public boolean supports(Class<?> clazz) {
//        return true;
//    }
//}
