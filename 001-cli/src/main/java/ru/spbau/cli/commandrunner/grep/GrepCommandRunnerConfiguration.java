package ru.spbau.cli.commandrunner.grep;

import com.beust.jcommander.IParameterValidator;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;

import java.util.ArrayList;
import java.util.List;

/**
 * Data class to store grep command runner configuration
 */
class GrepCommandRunnerConfiguration {
    @Parameter(names = {"-i"}, description = "Case ignorance flag")
    private boolean ignoreCase = false;

    @Parameter(names = {"-w"}, description = "Search words flag")
    private boolean searchWords = false;

    @Parameter(
            names = {"-A"},
            description = "Number of lines to be printed after an occurrence",
            validateWith = PositiveInteger.class
    )
    private int linesAfterOccurrence;

    @Parameter(description = "Pattern and file(s)")
    private List<String> arguments = new ArrayList<>();

    boolean isIgnoreCase() {
        return ignoreCase;
    }

    boolean isSearchWords() {
        return searchWords;
    }

    int getLinesAfterOccurrence() {
        return linesAfterOccurrence;
    }

    String getPattern() {
        if (arguments == null || arguments.isEmpty()) {
            return null;
        }
        return arguments.get(0);
    }

    List<String> getPaths() {
        if (arguments != null && arguments.size() >= 2) {
            return arguments.subList(1, arguments.size());
        }
        return null;
    }

    public static class PositiveInteger implements IParameterValidator {
        @Override
        public void validate(String name, String value) throws ParameterException {
            try {
                int n = Integer.valueOf(value);
                if (n < 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                throw new ParameterException("Parameter " + name + " should be positive integer (found " + value +")");
            }
        }
    }
}
