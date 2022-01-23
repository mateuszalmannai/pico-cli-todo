package com.learning.cli.commands;

import com.learning.cli.commands.subcommands.AddTodoCommand;
import picocli.CommandLine;

import java.util.concurrent.Callable;

@CommandLine.Command(
    name = "todo",
    version = "1.0.0",
    mixinStandardHelpOptions = true,
    requiredOptionMarker = '*',
    description = "%nThis is a todo Tool which will help us to manage todo activities",
    header = "Todo CLI",
    footerHeading = "%nCopyright",
    footer = "%nDeveloped by IsmSkism",
    optionListHeading = "%nOptions are: %n",
    subcommandsRepeatable = true,
    commandListHeading = "%nSubcommands are: %n",
    subcommands = {
        AddTodoCommand.class
    }
)
public class TodoCommand implements Callable<Integer> {

  final Integer SUCCESS = 0;
  final Integer FAILURE = 1;

  @Override
  public Integer call() throws Exception {
    System.out.println("[todo] Welcome to Todo");
    return SUCCESS;
  }
}
