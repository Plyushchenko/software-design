package cli.CommandRunner;

import cli.Environment;

public class EchoCommandRunner implements CommandRunner {
    public static final String STRING_VALUE = "echo";

    @Override
    public void run(Environment environment, String arguments) {
        System.out.println("arg: " + arguments);
        environment.write(arguments);
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
