package com.learning.cli.commands;

import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

@Command(
        name = "hello",
        version = "1.0.0",
        mixinStandardHelpOptions = true,
        requiredOptionMarker = '*',
        description = "%nThis is a simple HelloWorld Command",
        header = {
                "@|green       _ _      _             |@",
                "@|green  __ _(_) |_ __| |_ __ _ _ _  |@",
                "@|green / _` | |  _(_-<  _/ _` | '_| |@",
                "@|green \\__, |_|\\__/__/\\__\\__,_|_|   |@",
                "@|green |___/                        |@%n"},
        optionListHeading = "%nOptions are: %n"
)
public class HelloCommand implements Runnable{

//  @Option(names = {"-h", "--help"}, usageHelp = true)
//  boolean help;

  @Option(names = {"-u", "--user"}, required = false, description = "Provide User Name", paramLabel = "<user name>")
  String user;

  @Override
  public void run() {
    if (user == null || user.length() == 0) {
      System.out.println("Hello CLI ");
    } else {
      System.out.println("Hello " + user);
    }
  }
}