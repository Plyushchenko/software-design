package cli;

import java.io.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CLI {
    private final Environment environment;
    private final UserInputReader userInputReader;
    private final UserOutputWriter userOutputWriter;

    public CLI(InputStream inputStream, OutputStream outputStream) {
        environment = new Environment();
        this.userInputReader = new UserInputReader(inputStream);
        this.userOutputWriter = new UserOutputWriter(outputStream);
    }

    public void run() throws IOException {
        boolean shouldExit = false;
        while (!shouldExit) {
            userOutputWriter.writeInvitation();
            String input = userInputReader.read();
            Command command = new Command(input);
            shouldExit = command.execute(environment);
            String result = environment.getResult();
            userOutputWriter.write(result);
        }
    }
}
