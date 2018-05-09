package ru.spbau.cli.commandrunner;

public class CommandRunnerArgumentsParserException extends Exception {
    public CommandRunnerArgumentsParserException(Exception e) {
        super(e);
    }

    public CommandRunnerArgumentsParserException(String message) {
        super(message);
    }
}
