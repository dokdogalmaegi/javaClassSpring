package com.example.todo.service;

import com.example.todo.model.TodoEntity;
import com.example.todo.model.TodoRequest;
import com.example.todo.todoRepository.TodoRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@AllArgsConstructor
public class TodoService {
    @Autowired
    private final TodoRepository todoRepository;
    // 아이템 추가
    public TodoEntity add(TodoRequest request) {
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setTitle(request.getTitle());
        todoEntity.setOrder(request.getOrder());
        todoEntity.setComplete(request.getCompleted());

        return todoEntity;
    }

    // todo 아이템 한개 조회
    public TodoEntity searchById(long id) {
        return this.todoRepository.findById(id).orElseThrow(
                () -> new ResponseStatusException(HttpStatus.NOT_FOUND)
        );
    }

    // todo 아이템 여러개 조회
    public List<TodoEntity> searchAll() {
        return this.todoRepository.findAll();
    }

    // todo 아이템 한개 삭제
    public void deleteById(long id) {
        this.todoRepository.deleteById(id);
    }

    // todo 아이템 여러개 삭제
    public void deleteAll() {
        this.todoRepository.deleteAll();
    }

    // todo 아이템 변경하기
    public TodoEntity updateById(long id, TodoRequest request) {
        TodoEntity todoEntity = this.searchById(id);
        if(request.getTitle() != null) {
            todoEntity.setTitle(request.getTitle());
        }
        if(request.getOrder() != null) {
            todoEntity.setOrder(request.getOrder());
        }
        if(request.getCompleted() != null) {
            todoEntity.setComplete(request.getCompleted());
        }

        return this.todoRepository.save(todoEntity);
    }
}
