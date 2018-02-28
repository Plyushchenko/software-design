package cli.CommandRunner;

import cli.Arguments;
import cli.Environment;

import java.io.InputStream;

public class ExitCommandRunner implements cli.CommandRunner.CommandRunner {
    public static final String STRING_VALUE = "exit";

    @Override
    public String run(Environment environment, InputStream inputStream, Arguments arguments) {
        return "";
    }
}