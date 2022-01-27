package com.learning.cli.commands;

import com.learning.cli.commands.subcommands.*;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(
    name = "todo",
    version = "1.0.0",
    mixinStandardHelpOptions = true,
    requiredOptionMarker = '*',
    description = "%nThis is a todo Tool which will help us to manage todo activities",
    header = "%n>>>>>>>>>>>> Todo CLI",
    footerHeading = "%nCopyright",
    footer = "%nDeveloped by IsmSkism",
    optionListHeading = "%nOptions are: %n",
    subcommandsRepeatable = true,
    commandListHeading = "%nSubcommands are: %n",
    subcommands = {
        AddTodoCommand.class,
        ListTodoCommand.class,
        ModifyTodoCommand.class,
        DeleteTodoCommand.class,
        CompleteTodoCommand.class,
        StartTodoCommand.class
    }
)
public class TodoCommand implements Callable<Integer> {

  @Override
  public Integer call() {
    System.out.println("[todo] Welcome to Todo");
    return ReturnCode.SUCCESS.getCode();
  }
}
