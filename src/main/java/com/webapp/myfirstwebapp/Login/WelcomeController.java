package com.webapp.myfirstwebapp.Login;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

@Controller
@SessionAttributes("name")
// session attributes helps to store information about a user's session.
// eg name, shopping cart items, info
// need to add this annotation in every controller/class that you want to
// use it
public class WelcomeController {

    // is an annotation used to automatically wire or inject dependencies
    // into a Spring-managed bean or component.
    // the Spring framework will automatically attempt to find and inject
    // an instance of the required dependency, based on its type
    // thus dont need a constructor

    // not required as it is in spring
    // security...................................................
    // @Autowired
    // private AuthenticationService authenticationService;

    // for logging the input
    private Logger logger = LoggerFactory.getLogger(getClass());

    // when user enters the URL, GET method is invoke which is used to obtain
    // webpage
    // from the server
    // after pressing submit it goes to Request Method: POST

    // this is handled by spring security, not
    // requred...............................................
    // @RequestMapping( value = "/login", method = RequestMethod.GET)
    // //public String goToLoginPage(@RequestParam(required = false) String name,
    // ModelMap model ){
    // //model.addAttribute("name", name);
    // // //only shows the logging at debug level
    // // logger.debug("Request param is {}", name);
    // // //only shows the logging at info level
    // // logger.info("Request param is {}", name);

    // public String goToLoginPage(){
    // return "login";
    // }

    // this is handled by spring security, not
    // requred...............................................
    // // when Request Method:POST it brings user to the welcome page
    // @RequestMapping(value = "/login", method = RequestMethod.POST)
    // // form data form the html can be captured using @RequestParam
    // // to pass it to the welcome page use ModelMap
    // public String goToWelcomePage(@RequestParam String name, @RequestParam String
    // password, ModelMap model) {
    // // Authentication logic
    // if (authenticationService.authenticate(name, password)) {
    // // the added attribute is only avaialbe for the scope of this request, by
    // // default cannot use in other pages. eg only can us ein login page
    // // cannt use in todo page.
    // model.addAttribute("name", name);
    // return "welcome";
    // }
    // // this is to input the error msg into the model when wrong credentials are
    // // entered
    // model.addAttribute("errorMessage", "Invalid Credentials! Please try again.");
    // return "login";
    // }

    // redirect to welcome page after log in
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String goToWelcomePage(ModelMap model) {
        model.addAttribute("name", getLoginUsername());
        return "welcome";
    }

    // // This method returns the login username of the currently authenticated user

    private String getLoginUsername() {
        // Get the authentication object for the current SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Return the name of the currently authenticated user
        return authentication.getName();
    }

}
