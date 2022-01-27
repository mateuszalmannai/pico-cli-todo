package com.learning.cli.commands;

import com.learning.cli.commands.subcommands.AddTodoCommand;
import com.learning.cli.commands.subcommands.ListTodoCommand;
import com.learning.cli.commands.subcommands.ModifyTodoCommand;
import com.learning.cli.commands.subcommands.ReturnCode;
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
        ModifyTodoCommand.class
    }
)
public class TodoCommand implements Callable<Integer> {

  @Override
  public Integer call() {
    System.out.println("[todo] Welcome to Todo");
    return ReturnCode.SUCCESS.getCode();
  }
}
