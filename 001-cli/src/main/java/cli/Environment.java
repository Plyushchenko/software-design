package cli;

import cli.CommandRunner.*;

import java.util.HashMap;
import java.util.Map;

/**
 * Environment class that stores variables, command runners and the previous command result
 */
public class Environment {
    private final Map<String, String> variables;
    private final Map<String, CommandRunner> commandRunners;
    private String previousResult;


    public Environment() {
        variables = new HashMap<>();
        commandRunners = new HashMap<>();
        writeResult("");
        initializeCommandMapWithBuiltinCommands();
    }

    private void initializeCommandMapWithBuiltinCommands() {
        commandRunners.put(CatCommandRunner.STRING_VALUE, new CatCommandRunner());
        commandRunners.put(EchoCommandRunner.STRING_VALUE, new EchoCommandRunner());
        commandRunners.put(ExitCommandRunner.STRING_VALUE, new ExitCommandRunner());
        commandRunners.put(PwdCommandRunner.STRING_VALUE, new PwdCommandRunner());
        commandRunners.put(WcCommandRunner.STRING_VALUE, new WcCommandRunner());
    }

    String getVariableValue(String name) {
        return variables.getOrDefault(name, "");
    }

    CommandRunner getOrCreateCommandRunner(String name) {
        if (commandRunners.containsKey(name)) {
            return commandRunners.get(name);
        }
        CustomCommandRunner customCommandRunner = new CustomCommandRunner(name);
        commandRunners.put(name, customCommandRunner);
        return customCommandRunner;
    }

    public String getResult() {
        String result = previousResult;
        previousResult = "";
        return result;
    }

    public void writeResult(String s) {
        previousResult = s;
    }

    void assignVariable(String name, String value) {
        variables.put(name, value);
    }
}
