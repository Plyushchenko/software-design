package ru.spbau.cli.commandrunner.grep;

import ru.spbau.cli.Environment;
import ru.spbau.cli.commandrunner.CommandRunner;
import ru.spbau.cli.commandrunner.CommandRunnerArgumentsParserException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * grep command runner
 */
public class GrepCommandRunner implements CommandRunner {
    public static final String STRING_VALUE = "grep";
    private final GrepCommandRunnerArgumentsParser parser;

    public GrepCommandRunner() {
        parser = new GrepCommandRunnerArgumentsParser();
    }

    /**
     * Searches the pattern in the file(s)/previous command result
     * @param environment Environment state
     * @param argument Command argument
     */
    @Override
    public void run(Environment environment, String argument) {
        GrepCommandRunnerConfiguration configuration = new GrepCommandRunnerConfiguration();
        try {
            parser.parse(argument, configuration);
        } catch (CommandRunnerArgumentsParserException e) {
            environment.writeResult(e.getMessage());
            return;
        }
        String patternAsString = configuration.getPattern();
        if (configuration.isSearchWords()) {
            patternAsString = "\\b" + patternAsString + "\\b";
        }
        Pattern pattern = Pattern.compile(patternAsString, configuration.isIgnoreCase() ? Pattern.CASE_INSENSITIVE : 0);
        List<String> lines;
        List<String> paths = configuration.getPaths();
        StringBuilder result = new StringBuilder();
        if (paths == null) {
            lines = new ArrayList<>(Arrays.asList(environment.getResult().split("\n")));
            run(lines, pattern, configuration, result);
        } else {
            for (String path: paths) {
                try {
                    lines = Files.readAllLines(Paths.get(path));
                    run(lines, pattern, configuration, result);
                } catch (IOException e) {
                    environment.writeResult("IO exception at " + e.getMessage());
                    return;
                }
            }
        }

        environment.writeResult(result.toString());
    }

    private void run(List<String> lines,
                     Pattern pattern,
                     GrepCommandRunnerConfiguration configuration,
                     StringBuilder result) {
        for (int lineNumber = 0; lineNumber < lines.size(); lineNumber++) {
            String line = lines.get(lineNumber);
            Matcher matcher = pattern.matcher(line);
            if (matcher.find()) {
                if (!result.toString().isEmpty()) {
                    result.append("--\n");
                }
                result.append(line);
                result.append('\n');
                for (int i = 0; i < configuration.getLinesAfterOccurrence() && lineNumber + 1 < lines.size(); i++) {
                    lineNumber++;
                    result.append(lines.get(lineNumber));
                    result.append('\n');
                }

            }
        }
    }

    /**
     * An execution shouldn't stop
     * @return False
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}
