package cli;

import cli.CommandRunner.*;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

public class Environment {
    private final Map<String, String> variables;
    private final Map<String, CommandRunner> commandRunners;
    private BufferedReader inputBufferedReader;


    public Environment() {
        variables = new HashMap<>();
        commandRunners = new HashMap<>();
        write("");
        initializeCommandMapWithBuiltinCommands();
    }

    public Environment(Map<String, String> variables, Map<String, CommandRunner> commandRunners) {
        this.variables = variables;
        this.commandRunners = commandRunners;
    }

    private void initializeCommandMapWithBuiltinCommands() {
        commandRunners.put(CatCommandRunner.STRING_VALUE, new CatCommandRunner());
        commandRunners.put(EchoCommandRunner.STRING_VALUE, new EchoCommandRunner());
        commandRunners.put(ExitCommandRunner.STRING_VALUE, new ExitCommandRunner());
        commandRunners.put(PwdCommandRunner.STRING_VALUE, new PwdCommandRunner());
        commandRunners.put(WcCommandRunner.STRING_VALUE, new WcCommandRunner());
    }

    String getVariableValue(String name) {
        return variables.get(name);
    }

    private void assignVariable(String name, String value) {
        variables.put(name, value);
    }

    void assignVariable(String command, int assignmentPosition) {
        String name = command.substring(0, assignmentPosition);
        String value = command.substring(assignmentPosition + 1);
        assignVariable(name, value);
    }

    CommandRunner getOrCreateCommandRunner(String name) {
        if (commandRunners.containsKey(name)) {
            return commandRunners.get(name);
        }
        CustomCommandRunner customCommandRunner = new CustomCommandRunner(name);
        commandRunners.put(name, customCommandRunner);
        return customCommandRunner;
    }

    public String getData() {
        StringBuilder result = new StringBuilder();
        for (String s: inputBufferedReader.lines().collect(Collectors.toList())) {
            result.append(s);
            result.append("\n");
        }
        return result.toString();
    }

    boolean hasVariable(String name) {
        return variables.containsKey(name);
    }

    public void write(String s) {
        InputStream inputStream = new ByteArrayInputStream(s.getBytes());
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        inputBufferedReader = new BufferedReader(inputStreamReader);
    }
}
