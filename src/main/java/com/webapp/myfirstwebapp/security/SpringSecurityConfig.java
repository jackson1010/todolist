package com.webapp.myfirstwebapp.security;

import java.util.function.Function;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

// an annotation in the Spring Framework that is used to indicate 
//that a class contains Spring configuration information. 
//This annotation is used to declare a class as a configuration class
// and to indicate that the class should be processed by the Spring 
//container during application startup.
@Configuration
public class SpringSecurityConfig {
    // usually password and username is store in LDAP or Database

    // now it will be store in memory
    // InMemoryUserDetailsManager
    // InMemoryUserDetailsManager(UsersDetails...users)

    // @Bean methods, which are used to declare Spring beans.
    // These methods return an object that is registered with
    // the Spring container as a bean, and can be used by other beans
    // in the application.
    @Bean
    public InMemoryUserDetailsManager createUserDetailsManager() {

        // lambda function
        // The Function implementation takes the input String,
        // passes it to a passwordEncoder method, which returns a
        // PasswordEncoder object, and then invokes the encode method
        // of that PasswordEncoder object, passing the input String as
        // an argument. The encode method returns the encoded version of
        // the input String, which is then returned by the Function as its
        // output.

        UserDetails userDetails1 = createNewUser("Benz", "dummy");
        UserDetails userDetails2 = createNewUser("Zin", "dummy");

        // can passs in many userDeatils as this method accepts variable arguments
        // return new InMemoryUserDetailsManager(userDetails1);
        return new InMemoryUserDetailsManager(userDetails1);
    }

    private UserDetails createNewUser(String username, String password) {

        Function<String, String> passwordEncoder = input -> passwordEncoder().encode(input);
        UserDetails userDetails = User.builder()
                .passwordEncoder(passwordEncoder)
                .username(username)
                .password(password)
                .roles("USER", "ADMIN")
                .build();

    return userDetails;
    }

    @Bean
    // password encoder
    // uses bcypt strong hashing function
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
