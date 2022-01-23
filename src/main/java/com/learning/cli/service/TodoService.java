package com.learning.cli.service;

import com.learning.cli.service.model.Status;
import com.learning.cli.service.model.Todo;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface TodoService {

  Todo createTodo(String message);

  Todo updateMessage(Long id, String message);

  Todo updateStatus(Long id, Status status);

  boolean markCompleteById(Long id);

  boolean deleteTodo(Todo todo);

  boolean deleteById(Long id);

  List<Todo> findAll();

  List<Todo> findByIds(List<Long> ids);

  List<Todo> findByStatus(Status status);

  Optional<Todo> findById(Long id);

  List<Todo> findByStatusList(List<Status> statusList);

  Todo createTodo(String message, Date dateCreated);
}
