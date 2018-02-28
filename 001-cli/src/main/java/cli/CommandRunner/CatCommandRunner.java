package cli.CommandRunner;


import cli.Arguments;
import cli.Environment;

import java.io.InputStream;

public class CatCommandRunner implements CommandRunner {
    public static final String STRING_VALUE = "cat";
    @Override
    public String run(Environment environment, InputStream inputStream, Arguments arguments) {
        return "";
    }
}
