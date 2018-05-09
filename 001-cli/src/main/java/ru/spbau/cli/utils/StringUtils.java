package ru.spbau.cli.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Utility class with some string functions
 */
public class StringUtils {
    public static final Set<Character> QUOTE_MARKS = Stream.of('\'', '"').collect(Collectors.toSet());
    public static final Set<Character> SINGLE_QUOTE_MARKS = Stream.of('\'').collect(Collectors.toSet());
    public static final char PIPELINE_SYMBOL = '|';
    public static final char DOLLAR_SIGN_SYMBOL = '$';
    public static final Set<Character> IDENTIFIER_STOPPERS = Stream.of('$', '"', '\'', ' ', '\n', '\r', '\t')
            .collect(Collectors.toSet());
    public static final char ASSIGNMENT_SYMBOL = '=';
    public static final Set<Character> SPACE_SYMBOLS = Stream.of(' ', '\n', '\r', '\t').collect(Collectors.toSet());

    /**
     * Finds the position of any unescaped symbol presented in 'symbols' in s.substring(startPosition)
     * @param s Argument
     * @param symbols Symbols to find
     * @param startPosition Position to begin search from
     * @param escapers Escapers
     * @return Position (s.length() if not found)
     */
    public static int findSymbolPosition(String s, Set<Character> symbols, int startPosition, Set<Character> escapers) {
        boolean escaping = false;
        char escaper = 0;
        for (int i = startPosition; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (escapers.contains(currentChar) && (i == 0 || s.charAt(i - 1) != '\\')) {
                if (escaping && escaper != currentChar) {
                    continue;
                }
                escaping = !escaping;
                escaper = escaping ? currentChar : 0;
            }
            if (!escaping && symbols.contains(currentChar)) {
                return i;
            }
        }
        return s.length();
    }

    /**
     * Splits the string by any unescaped symbol presented in 'symbols' in s.substring(startPosition)
     * @param s Argument
     * @param symbols Symbols to split by
     * @param escapers Escapers
     * @return List of split parts
     */
    public static List<String> split(String s, Set<Character> symbols, Set<Character> escapers) {
        List<String> result = new ArrayList<>();
        int l;
        int r = 0;
        do {
            l = r;
            r = findSymbolPosition(s, symbols, l, escapers);
            result.add(s.substring(l, r));
            r++;
        } while (r < s.length());
        return result;
    }

    /**
     * Trims the string and removes quote mark symbols at the edges if they are presented and the same
     * @param s Argument
     * @return Trimmed string without quote mark symbols at the edges
     */
    public static String trimAndEliminateQuoteMarks(String s) {
        s = s.trim();
        if (s.isEmpty()) {
            return s;
        }
        for (char quoteMark: QUOTE_MARKS) {
            if (s.charAt(0) == quoteMark && s.charAt(s.length() - 1) == quoteMark) {
                return s.substring(1, s.length() - 1);
            }
        }
        return s;
    }
}