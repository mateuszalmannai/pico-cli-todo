package com.learning.cli.service;

/**
 * If we want to change the TodoService implementation, i.e. to JDBC, JPA or Hibernate
 * we can simply implement the ToDoService interface and change the implementation in
 * here in the factory and nowhere else.
 */
public class TodoFactory {
  public static TodoService getTodoService() {
    return new TodoServiceImpl();
  }
}
