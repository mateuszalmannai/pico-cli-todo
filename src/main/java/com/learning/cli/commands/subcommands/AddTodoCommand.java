package com.learning.cli.commands.subcommands;

import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.Arrays;
import java.util.Date;
import java.util.concurrent.Callable;

@Command(
    name = "add",
    aliases = {"create", "plus"},
    version = "1.0.0",
    mixinStandardHelpOptions = true,
    requiredOptionMarker = '*',
    description = "%nThis is a subcommand to add tasks to the list",
    header = "Add Todo SubCommand",
    optionListHeading = "%nOptions are: %n",
    footerHeading = "%nCopyright",
    footer = "%nDeveloped by IsmSkism"
)
public class AddTodoCommand implements Callable<Integer> {

  @Option(
      names = {"-m", "--message"},
      required = true,
      description = "a ToDo note or message")
  String[] message;

  @Option(
      names = {"--create-date"},
      required = false,
      description = "date the todo was created"
  )
  Date dateCreated;

  @Override
  public Integer call() {
    System.out.println("[add] Add Command: ");
    if (dateCreated != null) {
      System.out.println("createdDate = " + dateCreated);
    }
    Arrays.asList(message).forEach(System.out::println);
    return 0 ;
  }
}
