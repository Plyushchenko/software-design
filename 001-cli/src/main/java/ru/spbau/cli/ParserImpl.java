package ru.spbau.cli;

import ru.spbau.cli.utils.Pair;
import ru.spbau.cli.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.Collections.emptySet;
import static ru.spbau.cli.utils.StringUtils.*;

/**
 * Parser
 */
public class ParserImpl implements Parser {
    private final String commandAsString;

    ParserImpl(String commandAsString) {
        this.commandAsString = commandAsString;
    }

    private int findSymbolPosition(Set<Character> symbols,
                                   int startPosition,
                                   Set<Character> escapers) {
        return StringUtils.findSymbolPosition(commandAsString, symbols, startPosition, escapers);
    }

    private int findSymbolPosition(char symbol,
                                   int startPosition,
                                   Set<Character> escapers) {
        Set<Character> symbols = Stream.of(symbol).collect(Collectors.toSet());
        return findSymbolPosition(symbols, startPosition, escapers);
    }

    private List<String> split(Set<Character> symbols, Set<Character> escapers) {
        return StringUtils.split(commandAsString, symbols, escapers);
    }

    private List<String> split(char symbol, Set<Character> escapers) {
        Set<Character> symbols = Stream.of(symbol).collect(Collectors.toSet());
        return split(symbols, escapers);
    }

    /**
     * Splits the string by identifiers
     * @return Parts of the string
     */
    @Override
    public List<String> splitByIdentifiers() {
        List<String> commandPartsAsString = new ArrayList<>();
        int lastPosition;
        int l = 0;
        int r;
        do {
            lastPosition = l;
            l = findSymbolPosition(DOLLAR_SIGN_SYMBOL, l, SINGLE_QUOTE_MARKS);
            r = findSymbolPosition(IDENTIFIER_STOPPERS, l + 1, emptySet());
            if (lastPosition != l) {
                commandPartsAsString.add(commandAsString.substring(lastPosition, l));
            }
            if (l >= commandAsString.length()) {
                break;
            }
            commandPartsAsString.add(commandAsString.substring(l, r));
            l = r;
        } while (l < commandAsString.length());
        return commandPartsAsString;
    }

    /**
     * Splits the string by pipeline symbol
     * @return Parts of the string
     */
    @Override
    public List<String> splitByPipeline() {
        return split(PIPELINE_SYMBOL, QUOTE_MARKS);
    }

    /**
     * Finds the command name and the command argument in the string
     * @return Name and argument pair
     */
    @Override
    public Pair<String, String> findNameAndArgument() {
        int spacePosition = findSymbolPosition(SPACE_SYMBOLS, 0, QUOTE_MARKS);
        String name = commandAsString.substring(0, spacePosition);
        String argument = StringUtils.trimAndEliminateQuoteMarks(commandAsString.substring(spacePosition));
        return new Pair<>(name, argument);
    }

    /**
     * Splits the string by assignment symbol
     * @return Parts of the string
     */
    @Override
    public List<String> splitByAssignment() {
        return split(ASSIGNMENT_SYMBOL, QUOTE_MARKS).stream()
                .map(StringUtils::trimAndEliminateQuoteMarks)
                .collect(Collectors.toList());
    }
}
