// package com.example.toppou.controller;
package dgi.dic2.a4l0u_c0d3.toppou20.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

// import com.example.toppou.dao.TodoRepository;
// import com.example.toppou.model.Todo;

import dgi.dic2.a4l0u_c0d3.toppou20.model.Todo;
import dgi.dic2.a4l0u_c0d3.toppou20.repository.TodoRepository;

// @CrossOrigin(origins = "http://localhost:8081")
@PreAuthorize("hasAuthority('CHERCHEUR')")
@RestController
@RequestMapping("/api")
public class TodoController {

    @Autowired
    TodoRepository todoRepository;

    @GetMapping("hello")
    public String hello() {
        return "Hello World";
    }


    @GetMapping("/todos")
    public ResponseEntity<List<Todo>> getAllTodos(@RequestParam(required = false) String title) {

        try {
            List<Todo> todos = new ArrayList<Todo>();

            if (title == null)
                todoRepository.findAll().forEach(todos::add);
            else
                todoRepository.findByTitle(title).forEach(todos::add);

            if (todos.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(todos, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/todos/{id}")
    public ResponseEntity<Todo> getTodoById(@PathVariable("id") long id) {
        Optional<Todo> todoData = todoRepository.findById(id);

        if (todoData.isPresent()) {
            return new ResponseEntity<>(todoData.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/todos")
    public ResponseEntity<Todo> createTodo(@RequestBody Todo todo) {
        try {
            Todo _todo = todoRepository.save(
                new Todo(
                todo.getTitle(),
                todo.getDescription(),
                todo.getCreationDate(),
                todo.getUpdateDate(),
                todo.getDueDate(),
                todo.getStatus(),
                todo.getPriority(),
                todo.getCategory()
                // todo

                )
            );
            // pourquoi new Todo tu pouvais directement passé le todo en question ?

            return new ResponseEntity<>(_todo, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/todos/{id}")
    public ResponseEntity<Todo> updateTodo(@PathVariable("id") long id, @RequestBody Todo todo) {
        Optional<Todo> todoData = todoRepository.findById(id);

        if (todoData.isPresent()) {
            Todo _todo = todoData.get();
            _todo.setTitle(todo.getTitle());
            _todo.setDescription(todo.getDescription());
            // _todo.setDone(todo.isDone());
            return new ResponseEntity<>(todoRepository.save(_todo), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/todos/{id}")
    public ResponseEntity<HttpStatus> deleteTodo(@PathVariable("id") long id) {
        try {
            todoRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/todos")
    public ResponseEntity<HttpStatus> deleteAllTodos() {
        try {
            todoRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    
}
