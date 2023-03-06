package com.webapp.myfirstwebapp.Hello;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class SayHelloController {
    

    @RequestMapping("/say-hello")
    //to indicate the specific url
    @ResponseBody
    //it will return the string instead of a view 
    public String sayHello(){
        return "Hello! What are you learning today?";
    }

    @RequestMapping("say-hello-manual")
    @ResponseBody
    public String sayHelloLong(){
        StringBuffer sb =new StringBuffer();
        sb.append("<html>");
        sb.append("<head>");
        sb.append("<title> My First HTML Page </title>");
        sb.append("</head>");
        sb.append("<body>");
        sb.append("My first html page with body");
        sb.append("</body>");
        sb.append("</html>");
        return sb.toString();
    }

    @RequestMapping( value = "/my-html", method = RequestMethod.GET)
    public String showMyPage(){
        return "my-page";
    }
    
}
