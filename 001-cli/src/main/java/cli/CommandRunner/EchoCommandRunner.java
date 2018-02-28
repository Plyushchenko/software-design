package cli.CommandRunner;

import cli.Arguments;
import cli.Environment;

import java.io.InputStream;

public class EchoCommandRunner implements cli.CommandRunner.CommandRunner {
    public static final String STRING_VALUE = "echo";

    @Override
    public String run(Environment environment, InputStream inputStream, Arguments arguments) {
        System.out.println(arguments);
        return arguments.toString();
    }
}
