package com.learning.cli.commands.subcommands;

import com.learning.cli.service.TodoFactory;
import com.learning.cli.service.TodoService;
import com.learning.cli.service.model.Status;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

import static com.learning.cli.commands.subcommands.ReturnCode.SUCCESS;

@Command(
    name = "start",
    aliases = {"begin"},
    version = "1.0.0",
    mixinStandardHelpOptions = true,
    requiredOptionMarker = '*',
    description = "%nThis is a subcommand to mark todos as IN_PROGRESS",
    header = "%n>>>>>>>>>>>> Start Todo SubCommand",
    optionListHeading = "%nOptions are: %n",
    footerHeading = "%nCopyright",
    footer = "%nDeveloped by IsmSkism"
)
public class StartTodoCommand implements Callable<Integer> {

  @Option(
      names = {"--id"},
      description = "Provide the ids of the todos to be started",
      required = true
  )
  List<Long> idList;


  private final TodoService todoService;


  public StartTodoCommand() {
    this.todoService = TodoFactory.getTodoService();
  }

  @Override
  public Integer call() {

    if (Objects.nonNull(idList) && !idList.isEmpty()) {
      idList.stream().forEach(id -> todoService.updateStatus(id, Status.IN_PROGRESS));
    }

    System.out.println("Start request submitted.");

    return SUCCESS.getCode();
  }
}
