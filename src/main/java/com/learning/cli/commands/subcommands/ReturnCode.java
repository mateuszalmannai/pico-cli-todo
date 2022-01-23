package com.learning.cli.commands.subcommands;

public enum ReturnCode {
  SUCCESS(0), FAILURE(1);

  private final int code;

  ReturnCode(int code) {
    this.code = code;
  }

  public int getCode() {
    return code;
  }
}
