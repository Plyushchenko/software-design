package cli.CommandRunner;

import cli.Environment;

public class PwdCommandRunner implements CommandRunner {
    public static final String STRING_VALUE = "pwd";

    @Override
    public void run(Environment environment, String arguments) {
        String currentDirectory = System.getProperty("user.dir");
        environment.writeResult(currentDirectory);
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
