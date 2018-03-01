package cli.CommandRunner;

import cli.Environment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CatCommandRunner implements CommandRunner {
    public static final String STRING_VALUE = "cat";

    @Override
    public void run(Environment environment, String argument) {
        if (argument.isEmpty()) {
            environment.writeResult("");
        }
        try {
            List<String> lines = Files.readAllLines(Paths.get(argument));
            environment.writeResult("");
            StringBuilder result = new StringBuilder();
            for (String line: lines) {
                result.append(line);
                result.append('\n');
            }
            environment.writeResult(result.toString());
        } catch (IOException e) {
            environment.writeResult("IO exception at " + argument);
        }
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
