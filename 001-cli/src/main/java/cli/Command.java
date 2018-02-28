package cli;

import cli.CommandRunner.CommandRunner;

import java.util.Collections;

import static cli.Splitter.QUOTE_MARKS;

class Command {
    private String command;

    Command(String command) {
        this.command = command;
    }

    boolean evaluate(Environment environment) {
        command = command.trim();
        eliminateDollarSigns(environment);
        if (findAssignment(environment)) {
            return false;
        }
        int spacePosition = Splitter.findSymbolPosition(command, Splitter.SPACE_SYMBOLS, 0, QUOTE_MARKS);
        String name = command.substring(0, spacePosition);
        if (findVariable(name, environment)) {
            return false;
        }
        String arguments = "";
        if (spacePosition < command.length()) {
            arguments = eliminateQuoteMarks(command.substring(spacePosition + 1));
        }
        CommandRunner commandRunner = environment.getOrCreateCommandRunner(name);
        commandRunner.run(environment, arguments);
        return commandRunner.shouldExit();
    }

    private boolean findVariable(String name, Environment environment) {
        if (environment.hasVariable(name)) {
            String value = environment.getVariableValue(name);
            environment.write(value);
            return true;
        }
        return false;
    }

    private boolean findAssignment(Environment environment) {
        int assignmentPosition = Splitter.findSymbolPosition(command, Splitter.ASSIGNMENT_SYMBOL, 0, QUOTE_MARKS);
        if (assignmentPosition < command.length()) {
            environment.assignVariable(command, assignmentPosition);
            return true;
        }
        return false;
    }

    private String eliminateQuoteMarks(String s) {
        if (s.isEmpty()) {
            return s;
        }
        if (QUOTE_MARKS.contains(s.charAt(0)) && QUOTE_MARKS.contains(s.charAt(s.length() - 1))) {
            return s.substring(1, s.length() - 1);
        }
        return s;
    }

    private void eliminateDollarSigns(Environment environment) {
        int l = 0;
        int r;
        do {
            l = Splitter.findSymbolPosition(command, Splitter.DOLLAR_SIGN_SYMBOL, l, Splitter.SINGLE_QUOTE_MARKS);
            r = Splitter.findSymbolPosition(command, Splitter.IDENTIFIER_STOPPERS, l + 1, Collections.emptySet());
            if (l >= command.length()) {
                break;
            }
            command = evaluateIdentifier(l, r, environment);
            r++;
            l = r;
        } while (l < command.length());
    }

    private String evaluateIdentifier(int l, int r, Environment environment) {
        String commandTail = "";
        if (r < command.length()) {
            commandTail = command.substring(r);
        }
        StringBuilder modifiedCommandAsString = new StringBuilder(command.substring(0, l));
        Command Identifier = new Command(command.substring(l + 1, r));
        Identifier.evaluate(environment);
        String evaluatedIdentifier = environment.getData();
        modifiedCommandAsString.append(evaluatedIdentifier);
        modifiedCommandAsString.append(commandTail);
        return modifiedCommandAsString.toString();
    }
}
