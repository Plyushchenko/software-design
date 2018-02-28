package cli.CommandRunner;

import cli.Arguments;
import cli.Environment;

import java.io.InputStream;

public interface CommandRunner {

    void run(Environment environment, String arguments);

    boolean shouldExit();
}
