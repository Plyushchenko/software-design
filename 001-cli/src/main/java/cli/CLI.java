package cli;

import java.io.*;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class CLI {
    private final Environment environment;
    private final BufferedReader inputBufferedReader;
    private final PrintWriter printWriter;

    CLI(InputStream inputStream, OutputStream outputStream) {
        environment = new Environment();
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        inputBufferedReader = new BufferedReader(inputStreamReader);
        printWriter = new PrintWriter(outputStream, true);
    }

    public void run() throws IOException {
        StringBuilder input = new StringBuilder();
        boolean escaping = false;
        while (true) {
            if (!escaping) {
                printWriter.print('>');
            }
            String currentLine = inputBufferedReader.readLine();
            input.append(currentLine);
            if (isMultiline(currentLine, escaping)) {
                escaping = true;
                input.append('\n');
                continue;
            } else {
                escaping = false;
            }
            List<Command> splitInput = Splitter
                    .split(input.toString(), Splitter.PIPELINE_SYMBOL)
                    .stream()
                    .map(Command::new)
                    .collect(Collectors.toList());
            for (Command currentCommand: splitInput) {
                boolean shouldExit = currentCommand.evaluate(environment);
                if (shouldExit) {
                    return;
                }
            }
            String data = environment.getData();
            if (!data.isEmpty()) {
                printWriter.println(data);
            }
            input = new StringBuilder();
        }
    }

    private boolean isMultiline(String input, boolean escaping) {
        Set<Character> escapers = Splitter.QUOTE_MARKS;
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (escapers.contains(currentChar) && (i == 0 || input.charAt(i - 1) != '\'')) {
                escaping = !escaping;
            }
        }
        return escaping;
    }
}
