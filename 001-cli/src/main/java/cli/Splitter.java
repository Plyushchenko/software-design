package cli;

import com.sun.org.apache.xpath.internal.operations.Bool;
import javafx.util.Pair;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Splitter {

    public static final Set<Character> QUOTE_MARKS = Stream.of('\'', '"')
            .collect(Collectors.toSet());
    public static final Set<Character> SINGLE_QUOTE_MARKS = Stream.of('\'')
            .collect(Collectors.toSet());
    public static final char PIPELINE_SYMBOL = '|';
    public static final char DOLLAR_SIGN_SYMBOL = '$';
    public static final Set<Character> IDENTIFIER_STOPPERS = Stream.of(
            '$',
            '"',
            '\'',
            ' ',
            '\n',
            '\r',
            '\t'
    ).collect(Collectors.toSet());
    public static final char ASSIGNMENT_SYMBOL = '=';
    public static final Set<Character> SPACE_SYMBOLS = Stream.of(
            ' ',
            '\n',
            '\r',
            '\t'
    ).collect(Collectors.toSet());

    static int findSymbolPosition(String input,
                                  Set<Character> symbols,
                                  int startPosition,
                                  Set<Character> escapers) {
        boolean escaping = false;
        for (int i = startPosition; i < input.length(); i++) {
            char currentChar = input.charAt(i);
            if (escapers.contains(currentChar) && (i == 0 || input.charAt(i - 1) != '\'')) {
                escaping = !escaping;
            }
            if (!escaping && symbols.contains(currentChar)) {
                return i;
            }
        }
        return input.length();
    }

    static int findSymbolPosition(String input,
                                  char symbol,
                                  int startPosition,
                                  Set<Character> escapers) {
        Set<Character> symbols = Stream.of(symbol).collect(Collectors.toSet());
        return findSymbolPosition(input, symbols, startPosition, escapers);
    }

    static List<String> split(String input, char symbol) {
        List<String> result = new ArrayList<>();
        int l;
        int r = 0;
        do {
            l = r;
            r = findSymbolPosition(
                    input,
                    symbol,
                    l,
                    QUOTE_MARKS
            );
            result.add(input.substring(l, r));
            r++;
        } while (r < input.length());
        return result;
    }
}
