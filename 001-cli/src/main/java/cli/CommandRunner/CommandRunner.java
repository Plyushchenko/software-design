package cli.CommandRunner;

import cli.Environment;

public interface CommandRunner {

    void run(Environment environment, String argument);

    boolean shouldExit();
}
