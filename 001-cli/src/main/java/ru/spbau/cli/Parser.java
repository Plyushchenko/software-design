package ru.spbau.cli;

import ru.spbau.cli.utils.Pair;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Parser interface
 */
public interface Parser {
    /**
     * Splits the string by identifiers
     * @return Parts of the string
     */
    List<String> splitByIdentifiers();

    /**
     * Splits the string by pipeline symbol
     * @return Parts of the string
     */
    List<String> splitByPipeline();

    /**
     * Finds the command name and the command argument in the string
     * @return Name and argument pair
     */
    Pair<String,String> findNameAndArgument();

    /**
     * Splits the string by assignment symbol
     * @return Parts of the string
     */
    List<String> splitByAssignment();
}
