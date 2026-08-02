package com.example.demo.Configuration;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.security.autoconfigure.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AuthorizeHttpRequestsConfigurer;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class Config {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity){

        //building by using Lamda
        httpSecurity.csrf(customize -> customize.disable())
                .authorizeHttpRequests(request -> request
                        .requestMatchers("register",  "login")
                        .permitAll()
                        .anyRequest().authenticated())
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());


        // This is buildling by using normal way
//        Customizer<CsrfConfigurer<HttpSecurity>> customizer = new Customizer<CsrfConfigurer<HttpSecurity>>() {
//            @Override
//            public void customize(CsrfConfigurer<HttpSecurity> httpSecurityCsrfConfigurer){
//                httpSecurityCsrfConfigurer.disable();
//            }
//        };
//        httpSecurity.csrf(customizer);
        return httpSecurity.build();
    }


//    this is method to hardcoded values.
//    @Bean
//    public UserDetailsService userDetailsService(){
//
//        UserDetails userDetails1 = User
//                .withDefaultPasswordEncoder()
//                .username("Rakshith")
//                .password("Java200")
//                .roles("Admin")
//                .build();
//        UserDetails userDetails2 = User
//                .withDefaultPasswordEncoder()
//                .username("Nisarga")
//                .password("Java270")
//                .roles("User")
//                .build();
//        return new InMemoryUserDetailsManager(userDetails1, userDetails2);
//    }

//  this is using a database of postgres

    @Autowired
    private UserDetailsService userDetailsService;


    @Bean
    public AuthenticationProvider authenticationProvider(){

        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(userDetailsService);

//        the below line is used as we are not using any encryption. whatever the data/password that are stored in
//        the DB will directly fetch and goes for authentication


        //this line will authorise by taking the cipher text that has stored in db
        //coverts into plain text to verify.

        //the number 12 is the number of iteration that has to go to convert
        //plain text to cipher

        // remember the 'new BCryptPasswordEncoder(12) shpuld be same in both config and service.
        provider.setPasswordEncoder(new BCryptPasswordEncoder(12));
//        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    //**********Now this lines of code is to manage and generate the JWT token easy access of data**************

    //we are creating and setUp the JWT bean to

    //we are using this for to verify during login
    //(Autowire it to RegisterService)
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

}
