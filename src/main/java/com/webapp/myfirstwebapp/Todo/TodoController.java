package com.webapp.myfirstwebapp.Todo;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import jakarta.validation.Valid;

@Controller
@SessionAttributes("name")
public class TodoController {

    @Autowired
    private TodoService todoService;

    @RequestMapping("list-todos")
    public String listAllTodos(ModelMap model) {
        String username = getLoginUsername(model);
        List<Todo> todos = todoService.findByUsername(username);
        model.addAttribute("todos", todos);
        return "listTodos";
    }

    @RequestMapping(value = "add-todo", method = RequestMethod.GET)
    public String showNewTodoPage(ModelMap model) {

        // add a default object to tell spring what to bind to in the post method.
        // these default values are overwriten
        // the todo is create as the todo html expect a todo object with values;
      
        String username = getLoginUsername(model);
        Todo todo = new Todo(0, username, "", LocalDate.now(), false);
        model.addAttribute("todo", todo);
        return "todo";

        // When you are getting user input from a website, you typically use a form to
        // submit the input.
        // The form fields correspond to the properties of the object that you want to
        // bind the user input to.
        // In your Spring MVC controller method, you use the @ModelAttribute annotation
        // to bind the user input to the object.
        // In order for this to work, Spring needs to know which object to bind the user
        // input to. This is where adding the object to the model comes in.
        // By adding the object to the model, you are telling Spring to use that object
        // as the target for binding the user input.
        // Without adding the object to the model, Spring won't know which object to use
        // for binding the user input, and you will get an error like the one you
        // encountered.
    }

    // @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    // // can directly bind the user input to the Todo class, which is called command
    // // bean.
    // public String addNewTodo(ModelMap model, @ModelAttribute("todo") @Valid Todo todo, BindingResult result) {
    //     // the @Valid Todo todo helps to validify the user input based on what is set in
    //     // the Todo.java class
    //     // the bindingResult (need @Valid), Spring will automatically perform data
    //     // binding and validation,
    //     // and will store the results of the validation in a BindingResult result.
    //     System.out.println("11111");
    //     // checking if result has Errors
    //     if (result.hasErrors()) {
    //         return "todo";
    //     }
    //     // one method is to retrive the todos and add them to model and return
    //     // List<Todo> todos= todoService.findByUsername("Benz");
    //     // model.addAttribute("todos", todos);
    //     // however this is code repetiton thus can always redirect to the
    //     // URL with this function
    //     System.out.println("1");
    //     String username = (String) mode.getUsername();(model);
    //     System.out.println("2");
    //     todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(), false);

    //     return "list-todos";
    // }

    @RequestMapping(value = "add-todo", method = RequestMethod.POST)
    // can directly bind the user input to the Todo class, which is called command bean.
    public String addNewTodo2(ModelMap model, @Valid Todo todo, BindingResult result)   {
             
        if (result.hasErrors()) {
            return "todo";
        }

        String username = getLoginUsername(model);
        System.out.println(username);
        todo.setUsername(username);
        todoService.addTodo(username, todo.getDescription(), todo.getTargetDate(),false);
        return "redirect:list-todos";
       
    }

    // to delete a toDo
    @RequestMapping("delete-todo")
    public String deleteTodos(@RequestParam int id) {
        todoService.deleteTodo(id);
        return "redirect:list-todos";
    }

    // update todo
    // invoke the todoSevice to find the specific todo by the id
    @RequestMapping(value = "update-todo", method = RequestMethod.GET)
    public String ShowUpdateTodos(@RequestParam int id, ModelMap model) {
        Todo todo = todoService.findById(id);
        model.addAttribute("todo", todo);
        return "todo";
    }

    @RequestMapping(value = "update-todo", method = RequestMethod.POST)
    public String updateTodo(ModelMap model, @ModelAttribute("todo") @Valid Todo todo, BindingResult result) {

        if (result.hasErrors()) {
            return "todo";
        }

        String username = getLoginUsername(model);
        todo.setUsername(username);
        todoService.updateTodo(todo);
        return "redirect:list-todos";
    }

    private String getLoginUsername(ModelMap model){
        // Get the authentication object for the current SecurityContext
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // Return the name of the currently authenticated user
        return authentication.getName();
    }
}
