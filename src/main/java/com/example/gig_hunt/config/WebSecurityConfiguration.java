package com.example.gig_hunt.config;

import com.example.gig_hunt.service.impl.OrderDetailsServiceImpl;
import com.example.gig_hunt.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfiguration {

    private UserServiceImpl userService;

    private OrderDetailsServiceImpl ods;

    @Autowired
    public WebSecurityConfiguration(UserServiceImpl userService, OrderDetailsServiceImpl ods) {
        this.userService = userService;

        this.ods = ods;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        var authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userService);
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }

    @Bean
    public SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()

                .requestMatchers(HttpMethod.GET, "/towns**", "/towns/{townId}", "/regions**", "/regions/{regionId}",
                        "/categories**", "/categories/{categoryId}", "/goods**", "/goods/{goodsId}")
                .permitAll()

                .requestMatchers(HttpMethod.GET, "/roles", "/roles/{roleId}",
                        "/users", "/users/{userId}")
                .hasAuthority("ROLE_ADMIN")

                .requestMatchers(HttpMethod.GET, "/companies", "/companies/number={registrationNumber}",
                        "/companies/by_name", "/companies/by_user")
                .hasAnyRole("ADMIN", "CUSTOMER")

                .requestMatchers(HttpMethod.GET, "/cards", "/orders")
                .denyAll()

                .requestMatchers("/cards/{cardId}", "/cards/{cardId}/")
                .access("@cardAuthorization.check(authentication, #cardId)")

                .requestMatchers("/companies/{companyId}")
                .access ("@companyAuthorization.check(authentication, #companyId)")

                .requestMatchers("/users/{userId}/orders")
                .access("@userAuthorization.check(authentication, #userId)")

                .requestMatchers("/users/masters/amount/{userId}", "/users/masters/amount_for_month/{userId}")
                .access("@userAuthorization.checkMaster(authentication, #userId)")

                .requestMatchers("/orders/{orderId}")
                .access("@orderDetailsAuthorization.check(authentication, #orderId)")

                .requestMatchers("/orders/my_orders/{userId}/")
                .access("@orderDetailsAuthorization.checkMaster(authentication, #userId)")

                .requestMatchers(HttpMethod.POST,"/categories/", "/towns/", "/regions/")
                .hasAuthority("ROLE_ADMIN")

                .anyRequest().permitAll()

                .and()
                .exceptionHandling().accessDeniedPage("/access_denied")

                .and()
                .formLogin()
                //.loginPage("/login") // Custom login page URL
                //NEW
                .defaultSuccessUrl("/")
                .permitAll() // Allow access to login page

                .and()
                .logout()
                .permitAll();

        return http.build();
    }

    @Bean
    public CardAuthorization cardAuthorization() {
        return new CardAuthorization(userService);
    }

    @Bean
    public CompanyAuthorization companyAuthorization() {
        return new CompanyAuthorization(userService);
    }

    @Bean
    public UserAuthorization userAuthorization() {
        return new UserAuthorization(userService);
    }

    @Bean
    public OrderDetailsAuthorization orderDetailsAuthorization() {
        return new OrderDetailsAuthorization(userService, ods);
    }

}
