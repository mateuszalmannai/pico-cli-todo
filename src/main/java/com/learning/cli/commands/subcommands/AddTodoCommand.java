package com.learning.cli.commands.subcommands;

import com.learning.cli.service.TodoFactory;
import com.learning.cli.service.TodoService;
import com.learning.cli.service.model.Todo;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;
import java.util.concurrent.Callable;

@Command(
    name = "add",
    aliases = {"create", "plus"},
    version = "1.0.0",
    mixinStandardHelpOptions = true,
    requiredOptionMarker = '*',
    description = "%nThis is a subcommand to add tasks to the list",
    header = "%n>>>>>>>>>>>> Add Todo SubCommand",
    optionListHeading = "%nOptions are: %n",
    footerHeading = "%nCopyright",
    footer = "%nDeveloped by IsmSkism"
)
public class AddTodoCommand implements Callable<Integer> {

  private final TodoService todoService;

  @Option(
      names = {"-m", "--message"},
      required = true,
      description = "a ToDo note or message")
  String[] messages;

  @Option(
      names = {"--create-date"},
      required = false,
      description = "date the todo was created"
  )
  Date dateCreated;

  public AddTodoCommand() {
    this.todoService = TodoFactory.getTodoService();
  }

  @Override
  public Integer call() {
    if (Objects.nonNull(this.dateCreated)) {
      Arrays.asList(messages).forEach(todoMessage -> {
        Todo todo = this.todoService.createTodo(todoMessage);
        System.out.println("todo = " + todo);
      });
    } else {
      Arrays.asList(messages).forEach(todoMessage -> {
        Todo todo = this.todoService.createTodo(todoMessage, new Date());
        System.out.println("todo = " + todo);
      });
    }
    return ReturnCode.SUCCESS.getCode();
  }
}
