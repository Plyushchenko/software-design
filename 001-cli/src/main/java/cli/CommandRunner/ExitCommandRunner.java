package cli.CommandRunner;

import cli.Environment;

public class ExitCommandRunner implements CommandRunner {
    public static final String STRING_VALUE = "exit";

    @Override
    public void run(Environment environment, String arguments) {}

    @Override
    public boolean shouldExit() {
        return true;
    }
}