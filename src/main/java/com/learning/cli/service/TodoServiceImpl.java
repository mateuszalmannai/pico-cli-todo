package com.learning.cli.service;

import com.learning.cli.service.model.Status;
import com.learning.cli.service.model.Todo;
import org.mapdb.Atomic;
import org.mapdb.DB;
import org.mapdb.DBMaker;
import org.mapdb.Serializer;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;

public class TodoServiceImpl implements TodoService{

  public static final String TODO_MAP_DB = "todo.mapdb";
  public static final String TODO = "todo";
  DB db = null;
  ConcurrentMap<Long, Todo> map = null;
  Comparator<Todo> todoComparator = Comparator.comparing(Todo::getId);

  private void start() {
    this.db = DBMaker.fileDB(TODO_MAP_DB).make();
    this.map = db.hashMap(TODO, Serializer.LONG, Serializer.JAVA).createOrOpen();
  }

  private void shutDown() {this.db.close();}

  @Override
  public Todo createTodo(String message) {
    this.start();

    Todo todo = new Todo(message);
    todo.setId(getNewId());
    map.put(todo.getId(), todo);

    this.shutDown();

    return todo;
  }

  private Long getNewId() {
    Atomic.Long id = db.atomicLong("id").createOrOpen();
    return id.addAndGet(1);
  }

  @Override
  public Todo updateMessage(Long id, String message) {
    Optional<Todo> byId = findById(id);

    if (byId.isPresent()) {
      Todo todo = byId.get();
      todo.setMessage(message);
      this.start();
      map.put(todo.getId(), todo);
      this.shutDown();
      return todo;
    }

    return null;
  }

  @Override
  public Todo updateStatus(Long id, Status status) {
    Optional<Todo> optionalTodo = findById(id);
    if (optionalTodo.isPresent()) {
      Todo todo = optionalTodo.get();
      todo.setStatus(status);
      this.start();
      map.put(todo.getId(), todo);
      this.shutDown();
      return todo;
    }
    return null;
  }

  @Override
  public boolean markCompleteById(Long id) {
    Optional<Todo> optionalTodo = findById(id);
    if (optionalTodo.isPresent()) {
      Todo updatedTodo = updateStatus(id, Status.COMPLETED);
      return Objects.nonNull(updatedTodo);
    }
    return false;
  }

  @Override
  public boolean deleteTodo(Todo todo) {
    if (Objects.nonNull(todo.getId())) {
      this.start();
      map.remove(todo.getId());
      this.shutDown();
      return true;
    }
    return false;
  }

  @Override
  public boolean deleteById(Long id) {
    if (Objects.nonNull(id)) {
      this.start();
      map.remove(id);
      this.shutDown();
      return true;
    }
    return false;
  }

  @Override
  public List<Todo> findAll() {
    this.start();
    List<Todo> todoList = map.values()
        .stream()
        .sorted(todoComparator)
        .collect(Collectors.toList());
    this.shutDown();

    return todoList;
  }

  @Override
  public List<Todo> findByIds(List<Long> ids) {
    this.start();
    List<Todo> todoList = ids.stream()
        .map(map::get)
        .filter(Objects::nonNull)
        .collect(Collectors.toList());
    return todoList;
  }

  @Override
  public List<Todo> findByStatus(Status status) {
    List<Todo> todoList = new ArrayList<>();

    findAll().forEach(todo -> {
      if (status == todo.getStatus()) {
        todoList.add(todo);
      }});
    return todoList;
  }

  @Override
  public Optional<Todo> findById(Long id) {
    this.start();
    Todo todo = map.get(id);
    this.shutDown();
    return Optional.of(todo);
  }

  @Override
  public List<Todo> findByStatusList(List<Status> statusList) {
    List<Todo> todoList = new ArrayList<>();

    findAll().forEach(todo -> {
      if (statusList.contains(todo.getStatus())) {
        todoList.add(todo);
      }
    });
    return todoList;
  }

  @Override
  public Todo createTodo(String message, Date dateCreated) {
    this.start();

    Todo todo = new Todo(message, dateCreated);
    todo.setId(getNewId());
    map.put(todo.getId(), todo);

    this.shutDown();

    return todo;
  }
}
