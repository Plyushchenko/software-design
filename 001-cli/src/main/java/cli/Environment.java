package cli;

import cli.CommandRunner.*;

import java.lang.reflect.InvocationTargetException;
import java.util.HashMap;
import java.util.Map;

public class Environment {
    public final Map<String, String> variables;
    private final Map<String, CommandRunner> commandRunners;

    public Environment() {
        variables = new HashMap<>();
        commandRunners = new HashMap<>();
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

    public String getVariableValue(String name) {
        return variables.get(name);
    }

    public void assignVariable(String name, String value) {
        variables.put(name, value);
    }

    public CommandRunner getOrCreateCommandRunner(String name) {
        if (commandRunners.containsKey(name)) {
            return commandRunners.get(name);
        }
        CustomCommandRunner customCommandRunner = new CustomCommandRunner(name);
        commandRunners.put(name, customCommandRunner);
        return customCommandRunner;
    }

}
