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

import static com.learning.cli.commands.subcommands.ReturnCode.FAILURE;
import static com.learning.cli.commands.subcommands.ReturnCode.SUCCESS;

@Command(
    name = "modify",
    aliases = {"change", "alter"},
    version = "1.0.0",
    mixinStandardHelpOptions = true,
    requiredOptionMarker = '*',
    description = "%nThis is a subcommand to modify a tasks message",
    header = "%n>>>>>>>>>>>> Modify Todo SubCommand",
    optionListHeading = "%nOptions are: %n",
    footerHeading = "%nCopyright",
    footer = "%nDeveloped by IsmSkism"
)
public class ModifyTodoCommand implements Callable<Integer> {

  @Option(
      names = {"--id"},
      description = "Provide the id of the todo that needs to be updated",
      required = true
  )
  Long id;

  @Option(
      names = {"--message"},
      description = "Provide the new message to update the todo with",
      required = true
  )
  String message;

  private final TodoService todoService;


  public ModifyTodoCommand() {
    this.todoService = TodoFactory.getTodoService();
  }

  @Override
  public Integer call() {

    Todo todo = todoService.updateMessage(id, message);
    if (Objects.nonNull(todo)) {
      System.out.printf("Todo with id %d updated successfully\n", id);
    } else {
      System.err.printf("Todo with id %d not found\n", id);
    }

    return SUCCESS.getCode();
  }
}
