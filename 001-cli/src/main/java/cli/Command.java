package cli;

import cli.CommandRunner.CommandRunner;
import javafx.util.Pair;

import java.util.List;
import java.util.Optional;

import static cli.ParserImpl.DOLLAR_SIGN_SYMBOL;

public class Command {
    private final Parser parser;

    public Command(String commandAsString) {
        parser = new ParserImpl(commandAsString.trim());
    }

    public boolean execute(Environment environment) {
        Optional<Boolean> shouldExit = parser.splitByPipeline().stream()
                .map(Command::new)
                .map(command -> command.eliminateDollarSigns(environment))
                .map(command -> command.executeWithoutPipelines(environment))
                .filter(x -> x)
                .findFirst();
        return shouldExit.isPresent();
    }

    private Command eliminateDollarSigns(Environment environment) {
        List<String> commandPartsAsString = parser.splitByIdentifiers();
        StringBuilder commandWithoutDollarSignsAsStringBuilder = new StringBuilder();
        for (String commandPartAsString: commandPartsAsString) {
            if (commandPartAsString.charAt(0) == DOLLAR_SIGN_SYMBOL) {
                String name = commandPartAsString.substring(1);
                String value = environment.getVariableValue(name);
                commandWithoutDollarSignsAsStringBuilder.append(value);
            } else {
                commandWithoutDollarSignsAsStringBuilder.append(commandPartAsString);
            }
        }
        return new Command(commandWithoutDollarSignsAsStringBuilder.toString());
    }

    private boolean executeWithoutPipelines(Environment environment) {
        List<String> splitByAssignment = parser.splitByAssignment();
        if (splitByAssignment.size() >= 2) {
            String name = splitByAssignment.get(0);
            String value = splitByAssignment.get(1);
            environment.assignVariable(name, value);
            environment.writeResult("");
            return false;
        }
        Pair<String, String> nameAndArguments = parser.findNameAndArgument();
        String name = nameAndArguments.getKey();
        String argument = nameAndArguments.getValue();
        CommandRunner commandRunner = environment.getOrCreateCommandRunner(name);
        commandRunner.run(environment, argument);
        return commandRunner.shouldExit();
    }
}
