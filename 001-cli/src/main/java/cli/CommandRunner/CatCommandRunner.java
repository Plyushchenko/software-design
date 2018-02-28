package cli.CommandRunner;

import cli.Environment;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class CatCommandRunner implements CommandRunner {
    public static final String STRING_VALUE = "cat";
    @Override
    public void run(Environment environment, String arguments) {
        if (arguments.isEmpty()) {
            environment.write("");
        }
        try {
            List<String> lines = Files.readAllLines(Paths.get(arguments));
            StringBuilder result = new StringBuilder();
            lines.forEach(x -> { result.append(x); result.append('\n'); });
            environment.write(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
