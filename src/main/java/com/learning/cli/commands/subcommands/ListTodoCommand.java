package com.learning.cli.commands.subcommands;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.Callable;

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

  @Override
  public Integer call() {
    System.out.println("[list] Listing ToDos: ");

    return ReturnCode.SUCCESS.getCode();
  }
}
