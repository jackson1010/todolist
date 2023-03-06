// package com.webapp.myfirstwebapp.Login;

// import org.springframework.stereotype.Service;


// //not required as spring security include this authentication !............................................................


// //s an annotation used to identify a class as a service component in a Spring-based application
// //the Spring framework can automatically detect and manage the service component, making it available
// // for injection into other components that require its functionality
// // The service component might perform some business logic, such as querying a database, and return the results
// //to the controller, which in turn might generate an appropriate response for the client.
// @Service
// public class AuthenticationService {
//     public boolean authenticate (String username, String password){
//         boolean isValidUserName= username.equalsIgnoreCase("benz");
//         boolean isValidPassword= password.equalsIgnoreCase("");

//         //need both to be true to return ture, if 1 is false, it will return false
//         return isValidUserName && isValidPassword;
//     }
// }
