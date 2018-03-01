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
        return previousResult;
    }

    public void writeResult(String s) {
        previousResult = s;
    }

    public void assignVariable(String name, String value) {
        variables.put(name, value);
    }
}
