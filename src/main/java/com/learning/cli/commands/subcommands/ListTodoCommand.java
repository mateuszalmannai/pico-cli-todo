package com.learning.cli.commands.subcommands;

import com.learning.cli.service.TodoFactory;
import com.learning.cli.service.TodoService;
import com.learning.cli.service.model.Status;
import com.learning.cli.service.model.Todo;
import org.checkerframework.common.value.qual.EnsuresMinLenIf;
import org.jetbrains.annotations.NotNull;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

import static com.learning.cli.commands.subcommands.ReturnCode.SUCCESS;

@Command(
    name = "list",
    aliases = {"ls", "show"},
    version = "1.0.0",
    mixinStandardHelpOptions = true,
    requiredOptionMarker = '*',
    description = "%nThis is a subcommand to list all the  tasks in the list",
    header = "%n>>>>>>>>>>>> List Todos SubCommand",
    optionListHeading = "%nOptions are: %n",
    footerHeading = "%nCopyright",
    footer = "%nDeveloped by IsmSkism"
)
public class ListTodoCommand implements Callable<Integer> {

  private final TodoService todoService;

  SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd");

  @Option(
      names = {"-f", "--format"},
      description = "Formatting the Todo and the default value is ${DEFAULT-VALUE} %nAll Formats are ${COMPLETION-CANDIDATES}",
      defaultValue = "DEFAULT",
      required = false
  )
  ListFormat format;

  @Option(
      names = {"-s", "--status"},
      description = "Lists the todo by Status %nAvailable Statuses are ${COMPLETION-CANDIDATES}",
      required = false
  )
  Status status;

  @Option(
      names = {"-S", "--short", "--compact"},
      description = "Lists the todo in SHORT format",
      required = false
  )
  boolean compact;

  @Option(
      names = {"-d", "--completed", "--done"},
      description = "List completed or uncompleted todos ",
      negatable = true,
      required = false
  )
  Boolean completed = null;

  @Option(
      names = {"--id"},
      description = "Shows the todos for the given ID",
      required = false,
      split = ","
  )
  Long[] id;


  public ListTodoCommand() {
    this.todoService = TodoFactory.getTodoService();
  }

  @Override
  public Integer call() {

    if (compact) {
      format = ListFormat.SHORT;
    }


    List<Todo> todoList;



    if (Objects.isNull(completed)) {

      if (Objects.isNull(status) && Objects.isNull(id)){
        todoList = this.todoService.findAll();
      } else if(Objects.isNull(id)) {
        todoList = this.todoService.findByStatus(status);
      } else {
        todoList = this.todoService.findByIds(Arrays.asList(id));
      }

    } else {
      List<Status> statusList = new ArrayList<>();
      if (completed) {
        statusList.add(Status.COMPLETED);
      } else {
        statusList.addAll(List.of(Status.CREATED, Status.IN_PROGRESS));
      }
      todoList = this.todoService.findByStatusList(statusList);
    }

    if (Objects.nonNull(todoList) && !todoList.isEmpty()) {
      todoList.stream().forEach(todo -> printTodo(format, todo));
    } else {
      System.out.println("No todos to display.");
    }

    return SUCCESS.getCode();
  }

  private void printTodo(ListFormat format, Todo todo) {
    if (format == ListFormat.SHORT) {
      System.out.println(String.format("%4d %3s %10s %s",
          todo.getId(),
          getStatus(todo),
          this.dateFormat.format(todo.getCreatedOn()),
          todo.getMessage())
      );
    } else {
      System.out.println("ID: " + todo.getId());
      System.out.println("Message: " + todo.getMessage());
      System.out.println("Status: " + todo.getStatus());
      System.out.println("Created on: " + todo.getCreatedOn());
    }
  }

  @NotNull
  private String getStatus(Todo todo) {
    switch (todo.getStatus()) {
      case COMPLETED: return "[x]";
      case IN_PROGRESS: return "[.]";
      default: return "[ ]";
    }
  }
}
