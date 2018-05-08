package ru.spbau.cli.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StringUtils {
    public static final Set<Character> QUOTE_MARKS = Stream.of('\'', '"').collect(Collectors.toSet());
    public static final Set<Character> SINGLE_QUOTE_MARKS = Stream.of('\'').collect(Collectors.toSet());
    public static final char PIPELINE_SYMBOL = '|';
    public static final char DOLLAR_SIGN_SYMBOL = '$';
    public static final Set<Character> IDENTIFIER_STOPPERS = Stream.of('$', '"', '\'', ' ', '\n', '\r', '\t')
            .collect(Collectors.toSet());
    public static final char ASSIGNMENT_SYMBOL = '=';
    public static final Set<Character> SPACE_SYMBOLS = Stream.of(' ', '\n', '\r', '\t').collect(Collectors.toSet());

    public static int findSymbolPosition(String s, Set<Character> symbols, int startPosition, Set<Character> escapers) {
        boolean escaping = false;
        for (int i = startPosition; i < s.length(); i++) {
            char currentChar = s.charAt(i);
            if (escapers.contains(currentChar) && (i == 0 || s.charAt(i - 1) != '\'')) {
                escaping = !escaping;
            }
            if (!escaping && symbols.contains(currentChar)) {
                return i;
            }
        }
        return s.length();
    }

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

    //TODO "abc'
    public static String eliminateQuoteMarks(String s) {
        s = s.trim();
        if (s.isEmpty()) {
            return s;
        }
        if (QUOTE_MARKS.contains(s.charAt(0)) && QUOTE_MARKS.contains(s.charAt(s.length() - 1))) {
            return s.substring(1, s.length() - 1);
        }
        return s;
    }
}
