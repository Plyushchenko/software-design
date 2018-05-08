package ru.spbau.cli.commandrunner.grep;

import com.beust.jcommander.Parameter;

import java.util.ArrayList;
import java.util.List;

public class GrepCommandRunnerConfiguration {
    @Parameter(names = {"-i"}, description = "Case ignorance flag")
    private boolean ignoreCase = false;

    @Parameter(names = {"-w"}, description = "Search words flag")
    private boolean searchWords = false;

    @Parameter(names = {"-A"}, description = "Number of lines to be printed after an occurrence")
    private int linesAfterOccurrence;

    @Parameter(description = "Pattern and file(s)")
    private List<String> arguments = new ArrayList<>();

    public boolean isIgnoreCase() {
        return ignoreCase;
    }

    public boolean isSearchWords() {
        return searchWords;
    }

    public int getLinesAfterOccurrence() {
        return linesAfterOccurrence;
    }

    public String getPattern() {
        return arguments.get(0);
    }

    public List<String> getPaths() {
        return arguments.subList(1, arguments.size());
    }

    String print() {
        StringBuilder s = new StringBuilder("GrepCommandRunnerConfiguration{" +
                "\nignoreCase=" + ignoreCase +
                "\nsearchWords=" + searchWords +
                "\nlinesAfterOccurrence=" + linesAfterOccurrence +
                "\npattern=" + arguments.get(0) +
                "\npaths=");
        for (String t: arguments.subList(1, arguments.size())) {
            s.append(t).append(";");
        }
        s.append("\n}");
        return s.toString();
    }
}
