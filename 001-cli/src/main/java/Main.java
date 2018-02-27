import javafx.util.Pair;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toList;

public class Main {
    private static final char PIPELINE_SYMBOL = '|';
    private static final List<String> BUILTIN_COMMANDS = Stream.of(
            "cat",
            "echo",
            "exit",
            "pwd",
            "wc"
    ).collect(toList());

    public static void main(String[] args) {
        //execute:
        //1. split by "|", then iterate over these blocks (2, 3)
        //2. evaluate every $x by executing x recursively
        //3. execute command and modify environment

        Environment environment = new Environment();
        String input = "";
        List<String> splitInput = Splitter.split(input, PIPELINE_SYMBOL);
        for (String currentCommandAsString: splitInput) {
            Pair<String, String> extractedCommand = extractCommand(currentCommandAsString);
        }
    }

    private static Pair<String,String> extractCommand(String currentCommandAsString) {
        return null;
    }
}
