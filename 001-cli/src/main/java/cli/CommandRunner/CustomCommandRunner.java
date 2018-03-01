package cli.CommandRunner;

import cli.Environment;

import java.io.IOException;

public class CustomCommandRunner implements CommandRunner {
    private final String stringValue;

    public CustomCommandRunner(String stringValue) {
        this.stringValue = stringValue;
    }

    @Override
    public void run(Environment environment, String arguments) {
        Runtime r = Runtime.getRuntime();
        try {
            if (arguments.isEmpty()) {
                r.exec(stringValue + " " + environment.getResult());
            } else {
                r.exec(stringValue + " " + arguments);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public boolean shouldExit() {
        return false;
    }

    public String getStringValue() {
        return stringValue;
    }
}
