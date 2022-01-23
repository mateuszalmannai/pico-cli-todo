package com.learning.cli.service;

import com.learning.cli.service.model.Status;
import com.learning.cli.service.model.Todo;

public class DatabaseApplication {
  //TODO: replace this with unit tests
  public static void main(String[] args) {
    TodoService service = TodoFactory.getTodoService();

    boolean clearDatabase = true;
    if (clearDatabase) {
      System.err.println("Clearing Database");
      service.findAll().forEach(service::deleteTodo);
    }

    Todo helloCommand = service.createTodo("Create Hello Command");
    Todo todoCommand = service.createTodo("Create Todo Command");
    Todo addCommand = service.createTodo("Create Add Command");
    Todo listCommand = service.createTodo("Create List Command");
    Todo serviceCommand = service.createTodo("Create Service for storing data");

    System.out.println("Printing All Tasks");
    service.findAll().forEach(System.out::println);

    service.updateStatus(helloCommand.getId(), Status.COMPLETED);
    service.updateStatus(todoCommand.getId(), Status.IN_PROGRESS);
    service.updateStatus(addCommand.getId(), Status.IN_PROGRESS);
    service.updateStatus(listCommand.getId(), Status.IN_PROGRESS);
    service.updateStatus(serviceCommand.getId(), Status.COMPLETED);

    System.out.println("Printing all IN_PROGRESS todos");
    service.findByStatus(Status.IN_PROGRESS).forEach(System.out::println);

    System.out.println("\n\n-- Dummy ToDo --");
    Todo dummyTodo = service.createTodo("Dummy todo to be deleted");
    System.out.println("Dummy todo: " + dummyTodo);
    System.out.println("service.findById(dummyTodo.getId()) = " + service.findById(dummyTodo.getId()).get().getMessage());
    System.out.println("service.updateMessage(dummyTask.getId(), \"Updated Dummy Task Message\") = " + service.updateMessage(dummyTodo.getId(), "Updated Dummy Task Message").getMessage());
    System.out.println("service.updateStatus(dummyTask.getId(), Status.IN_PROGRESS) = " + service.updateStatus(dummyTodo.getId(), Status.IN_PROGRESS).getStatus());
    System.out.println("service.markCompletedById(dummyTask.getId()) = " + service.markCompleteById(dummyTodo.getId()));
    System.out.println("service.findById(dummyTask.getId()) = " + service.findById(dummyTodo.getId()).get().getStatus());
    System.out.println("service.deleteById(dummyTask.getId()) = " + service.deleteById(dummyTodo.getId()));



  }
}
