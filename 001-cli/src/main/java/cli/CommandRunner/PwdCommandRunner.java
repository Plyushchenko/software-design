package cli.CommandRunner;

import cli.Arguments;
import cli.Environment;

import java.io.InputStream;

public class PwdCommandRunner implements CommandRunner {
    public static final String STRING_VALUE = "pwd";

    @Override
    public void run(Environment environment, String arguments) {
        String currentDirectory = System.getProperty("user.dir");
        environment.write(currentDirectory);
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
