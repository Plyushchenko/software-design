package cli.CommandRunner;

import cli.Arguments;
import cli.Environment;

import java.io.InputStream;

public class CustomCommandRunner implements cli.CommandRunner.CommandRunner {
    private final String stringValue;

    public CustomCommandRunner(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public String run(Environment environment, InputStream inputStream, Arguments arguments) {
        return "";
    }

    public String getStringValue() {
        return stringValue;
    }
}
