package com.learning.cli;

import com.learning.cli.commands.HelloCommand;
import com.learning.cli.commands.TodoCommand;
import picocli.CommandLine;

public class App {
  public static void main(String[] args) {
//    new CommandLine(new HelloCommand()).execute(args);
    int exitStatus = new CommandLine(new TodoCommand()).execute(args);
    System.exit(exitStatus);
  }
}

