package com.webapp.myfirstwebapp.Todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import org.springframework.stereotype.Service;
import jakarta.validation.Valid;

//this class is to handle all the logic for the toDoController
@Service
public class TodoService {
    private static List<Todo> todos = new ArrayList<>();

    private static int toDosCount = 0;
    static {
        // ++toDosCount = add +1 before assigning it
        // toDosCount++ = add +1 after assigning current value
        todos.add(new Todo(++toDosCount, "Benz", "Hello", LocalDate.now().plusDays(2), true));
        todos.add(new Todo(++toDosCount, "Benz", "Eating", LocalDate.now().plusDays(1), true));
        todos.add(new Todo(++toDosCount, "Benz", "Sleeping", LocalDate.now().plusDays(3), true));
    }

    public List<Todo> findByUsername(String username) {
        Predicate<? super Todo> predicate = todo -> todo.getUsername().equalsIgnoreCase(username);
        return todos.stream().filter(predicate).toList();
    }

    public void addTodo(String username, String description, LocalDate targetDate, boolean done) {
        Todo todo = new Todo(++toDosCount, username, description, targetDate, done);
        todos.add(todo);
    }

    public void deleteTodo(int id) {
        // checking if this todo has a matching id and remove if it does
        // The Predicate is a functional interface that takes an object
        // of a certain type and returns a boolean value based on some condition.
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        todos.removeIf(predicate);
    }

    // stream the todo list, filter by the predicate(id) finding the first and
    // getting the value
    public Todo findById(int id) {
        Predicate<? super Todo> predicate = todo -> todo.getId() == id;
        Todo todo = todos.stream().filter(predicate).findFirst().get();
        return todo;
    }

    public void updateTodo(@Valid Todo todo) {
        // delete the old todo
        deleteTodo(todo.getId());
        // adding the new todo
        todos.add(todo);
    }

}
