package com.setplex.odin.configuration;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.annotation.Order;
import org.springframework.security.access.intercept.RunAsImplAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.method.configuration.GlobalMethodSecurityConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;
import org.springframework.security.web.firewall.DefaultHttpFirewall;
import org.springframework.security.web.firewall.HttpFirewall;

@Configuration
@EnableWebSecurity
@EnableAspectJAutoProxy
@ComponentScan("com.setplex.odin")
public class MwSecurityConfiguration {

    // TODO should be removed when there will be no "//" in requests
    @Bean
    public HttpFirewall httpFirewall() {
        return new DefaultHttpFirewall();
    }

    /**
     * RunAsManager configuration to execute API calls annotated with @PreAuthorize as user with pre-populated SecurityContext
     */
    @Configuration
    @EnableGlobalMethodSecurity(prePostEnabled = true)
    public static class MwGlobalMethodSecurityConfiguration extends GlobalMethodSecurityConfiguration {

        private static final String KEY = "7AcwpyML5WKTyG3c";

        @Bean
        public AuthenticationProvider runAsAuthenticationProvider() {
            RunAsImplAuthenticationProvider provider = new RunAsImplAuthenticationProvider();
            provider.setKey(KEY);
            return provider;
        }
    }

    /**
     * Security for Portal application. In-memory BASIC auth.
     */
    @Configuration
    @Order(2)
    public static class ApiProviderSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        //private final HttpFirewall httpFirewall;
//        private final String token;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.antMatcher("/api/provider/**")
                    .authorizeRequests()
                    .anyRequest().authenticated()
                    .and()
                    .exceptionHandling()
                    .and()
                    .httpBasic()
                    .and()
                    .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .csrf().disable();
        }

//        @Override
//        public void configure(AuthenticationManagerBuilder auth) throws Exception {
//            auth.userDetailsService(new PortalUserDetailsService(token));
//        }

//        // TODO should be removed when there will be no "//" in requests
//        @Override
//        public void configure(WebSecurity web) throws Exception {
//            super.configure(web);
//            web.httpFirewall(httpFirewall);
//        }

//        public ApiPortalSecurityConfigurationAdapter(@NonNull ApplicationSettings settings,
//                                                     @NonNull HttpFirewall httpFirewall) {
//            this.httpFirewall = httpFirewall;
//            token = settings.getNoraPortalToken();
//        }
    }

    /**
     * Main configuration chain. Uses DB as source of authentication data.
     * Should be last Configurer adapter in chain with lowest precedence.
     */
    @Configuration
    @Order
    @RequiredArgsConstructor
    public static class ApiGlobalSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        private final AuthenticationEntryPoint authEntryPoint;
        private final AuthenticationFailureHandler authFailureHandler;
        private final AuthenticationSuccessHandler authSuccessHandler;
        private final HttpFirewall httpFirewall;
        private final LogoutSuccessHandler logoutSuccessHandler;
        private final PasswordEncoder passwordEncoder;
        private final UserDetailsService userDetailsService;

        @Override
        protected void configure(HttpSecurity http) throws Exception {
            http.authorizeRequests()
                    .antMatchers("/api/user/**").permitAll()
                    .antMatchers("/api/**").authenticated()
                    .and()
                    .exceptionHandling()
                    .authenticationEntryPoint(authEntryPoint)
                    .and()
                    .logout().logoutUrl("/logout").logoutSuccessHandler(logoutSuccessHandler)
                    .and()
                    .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse())
                    .and()
                    .addFilterAt(usernamePasswordAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        }

        @Override
        protected void configure(AuthenticationManagerBuilder auth) throws Exception {
            auth.userDetailsService(userDetailsService)
                    .passwordEncoder(passwordEncoder);
        }

        @Bean
        @Override
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

        // TODO should be removed when there will be no "//" in requests
        @Override
        public void configure(WebSecurity web) throws Exception {
            super.configure(web);
            web.httpFirewall(httpFirewall);
        }

        @Bean
        public UsernamePasswordAuthenticationFilter usernamePasswordAuthenticationFilter() throws Exception {
            UsernamePasswordAuthenticationFilter filter = new UsernamePasswordAuthenticationFilter();
            filter.setFilterProcessesUrl("/auth");
            filter.setAuthenticationManager(authenticationManagerBean());
            filter.setAuthenticationSuccessHandler(authSuccessHandler);
            filter.setAuthenticationFailureHandler(authFailureHandler);
            return filter;
        }
    }
}
