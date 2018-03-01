package cli;

import javafx.util.Pair;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public interface Parser {

    Set<Character> QUOTE_MARKS = Stream.of('\'', '"').collect(Collectors.toSet());
    Set<Character> SINGLE_QUOTE_MARKS = Stream.of('\'').collect(Collectors.toSet());
    char PIPELINE_SYMBOL = '|';
    char DOLLAR_SIGN_SYMBOL = '$';
    Set<Character> IDENTIFIER_STOPPERS = Stream.of('$', '"', '\'', ' ', '\n', '\r', '\t')
            .collect(Collectors.toSet());
    char ASSIGNMENT_SYMBOL = '=';
    Set<Character> SPACE_SYMBOLS = Stream.of(' ', '\n', '\r', '\t').collect(Collectors.toSet());


    List<String> splitByIdentifiers();

    List<String> splitByPipeline();

    Pair<String,String> findNameAndArgument();

    List<String> splitByAssignment();
}
