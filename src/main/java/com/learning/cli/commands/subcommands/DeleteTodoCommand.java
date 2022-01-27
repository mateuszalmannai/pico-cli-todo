package com.learning.cli.commands.subcommands;

import com.learning.cli.service.TodoFactory;
import com.learning.cli.service.TodoService;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.List;
import java.util.Objects;
import java.util.concurrent.Callable;

import static com.learning.cli.commands.subcommands.ReturnCode.SUCCESS;

@Command(
    name = "delete",
    aliases = {"rm", "nuke"},
    version = "1.0.0",
    mixinStandardHelpOptions = true,
    requiredOptionMarker = '*',
    description = "%nThis is a subcommand to delete todos",
    header = "%n>>>>>>>>>>>> Delete Todo SubCommand",
    optionListHeading = "%nOptions are: %n",
    footerHeading = "%nCopyright",
    footer = "%nDeveloped by IsmSkism"
)
public class DeleteTodoCommand implements Callable<Integer> {

  @Option(
      names = {"--id"},
      description = "Provide the ids of the todos to be deleted",
      required = true
  )
  List<Long> idList;


  private final TodoService todoService;


  public DeleteTodoCommand() {
    this.todoService = TodoFactory.getTodoService();
  }

  @Override
  public Integer call() {

    if (Objects.nonNull(idList) && !idList.isEmpty()) {
      idList.stream().forEach(id -> todoService.deleteById(id));
    }

    System.out.println("Delete request submitted.");

    return SUCCESS.getCode();
  }
}
