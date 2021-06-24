package com.example.todo.controller;

import com.example.todo.model.TodoResponse;
import com.example.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/")
public class TodoController {
    @Autowired
    private final TodoService todoService;

    @PostMapping("/create")
    public ResponseEntity<TodoResponse> create() {
        System.out.println("create");
        return null;
    }

    // 한개 읽어오기
    @GetMapping("/search/{id}")
    public ResponseEntity<TodoResponse> readOne() {
        System.out.println("Read One");
        return null;
    }
    
    @GetMapping("/searchAll")
    public ResponseEntity<List<TodoResponse>> readAll() {
        System.out.println("Read All");
        return null;
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteOne() {
        System.out.println("delete One");
        return null;
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<?> deleteAll() {
        System.out.println("delete All");
        return null;
    }

    @PatchMapping("/update/{id}")
    public ResponseEntity<TodoResponse> update() {
        System.out.println("update!");
        return null;
    }
}
