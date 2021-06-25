package com.example.todo.controller;

import com.example.todo.model.TodoEntity;
import com.example.todo.model.TodoRequest;
import com.example.todo.model.TodoResponse;
import com.example.todo.service.TodoService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@AllArgsConstructor
@RestController
@RequestMapping("/")
public class TodoController {
    @Autowired
    private final TodoService todoService;

    @PostMapping
    public ResponseEntity<TodoResponse> create(@RequestBody TodoRequest request) {
        System.out.println("create");
        if(ObjectUtils.isEmpty(request.getTitle()))
            return ResponseEntity.badRequest().build();
        if(ObjectUtils.isEmpty(request.getOrder()))
            request.setOrder(0L);
        if(ObjectUtils.isEmpty(request.getCompleted()))
            request.setCompleted(false);

        TodoEntity result = this.todoService.add(request);


        return ResponseEntity.ok(new TodoResponse(result));
    }

    // 한개 읽어오기
    @GetMapping("{id}")
    public ResponseEntity<TodoResponse> readOne(@PathVariable Long id) {
        System.out.println("Read One");
        TodoEntity result = this.todoService.searchById(id);

        return ResponseEntity.ok(new TodoResponse(result));
    }
    
    @GetMapping
    public ResponseEntity<List<TodoResponse>> readAll() {
        System.out.println("Read All");

        List<TodoEntity> list = this.todoService.searchAll();
        List<TodoResponse> responses = list.stream().map(TodoResponse::new).collect(Collectors.toList());

        return ResponseEntity.ok(responses);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> deleteOne(@PathVariable Long id) {
        System.out.println("delete One");

        this.todoService.deleteById(id);

        return ResponseEntity.ok().build();
    }

    @DeleteMapping
    public ResponseEntity<?> deleteAll() {
        System.out.println("delete All");

        this.todoService.deleteAll();

        return ResponseEntity.ok().build();
    }

    @PatchMapping("{id}")
    public ResponseEntity<TodoResponse> update(@PathVariable Long id, @RequestBody TodoRequest request){
        System.out.println("Update");
        TodoEntity result = this.todoService.updateById(id, request);

        return ResponseEntity.ok(new TodoResponse(result));
    }
}
