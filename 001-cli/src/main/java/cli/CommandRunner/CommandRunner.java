package cli.CommandRunner;

import cli.Arguments;
import cli.Environment;

import java.io.InputStream;

public interface CommandRunner {

    String run(Environment environment, InputStream inputStream, Arguments arguments);

}
