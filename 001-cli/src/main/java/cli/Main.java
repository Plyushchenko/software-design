package cli;

import cli.CommandRunner.CommandRunner;
import javafx.util.Pair;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;  

public class Main {
    public static void main(String[] args) throws IOException {
        Environment environment = new Environment();
        InputStreamReader inputStreamReader = new InputStreamReader(System.in);
        BufferedReader inputBufferedReader = new BufferedReader(inputStreamReader);
        while (true) {
            String input = inputBufferedReader.readLine();
            if (input.equals("exit")) {
                break;
            }
            //String input = "cat $abc $cc | wc \"$e23\" | cat '$b52' \"|\" wc";
            System.out.println(input);
            List<String> splitInput = Splitter.split(input, Splitter.PIPELINE_SYMBOL);
            for (String s : splitInput) {
                System.out.println(">" + s + "<");
            }
            System.out.println("?");
            for (int i = 0; i < splitInput.size(); i++) {
                evaluate(splitInput.get(i), environment);
            }
/*            for (String x : environment.variables.keySet()) {
                System.out.println(x + " " + environment.variables.get(x));
            }
*/        }
    }

    private static String evaluate(String currentCommandAsString, Environment environment) {
        System.out.println("CURRENT " + currentCommandAsString);
        int l = 0;
        int r;
        do {
            l = Splitter.findSymbolPosition(
                    currentCommandAsString,
                    Splitter.DOLLAR_SIGN_SYMBOL,
                    l,
                    Splitter.SINGLE_QUOTE_MARKS
            );
            r = Splitter.findSymbolPosition(
                    currentCommandAsString,
                    Splitter.IDENTIFIER_STOPPERS,
                    l + 1,
                    Collections.emptySet()
            );
            System.out.println(currentCommandAsString.substring(l, r));
            if (l >= currentCommandAsString.length()) {
                break;
            }
            String tmp = "";
            if (r < currentCommandAsString.length()) {
                tmp = currentCommandAsString.substring(r);
            }
            currentCommandAsString =
                    currentCommandAsString.substring(0, l) +
                    evaluate(currentCommandAsString.substring(l + 1, r), environment) + tmp;
            r++;
            l = r;
        } while (l < currentCommandAsString.length());
        int assignmentPosition = Splitter.findSymbolPosition(currentCommandAsString,
                Splitter.ASSIGNMENT_SYMBOL,
                0,
                Splitter.QUOTE_MARKS
        );
        if (assignmentPosition < currentCommandAsString.length()) {
            String name = currentCommandAsString.substring(0, assignmentPosition);
            String value = currentCommandAsString.substring(assignmentPosition + 1);
            environment.assignVariable(name, value);
            return "";
        } else {
            int spacePosition = Splitter.findSymbolPosition(
                    currentCommandAsString,
                    Splitter.SPACE_SYMBOLS,
                    0,
                    Splitter.QUOTE_MARKS
            );

            String name = currentCommandAsString.substring(0, spacePosition);
            if (environment.variables.containsKey(name)) {
                String value = environment.getVariableValue(name);
                if (spacePosition < currentCommandAsString.length()) {
                    return value + currentCommandAsString.substring(spacePosition);
                } else {
                    return value;
                }

            } else {
                String arguments = "";
                if (spacePosition < currentCommandAsString.length()) {
                    arguments = currentCommandAsString.substring(spacePosition + 1);
                }
                CommandRunner commandRunner = environment.getOrCreateCommandRunner(name);
                return commandRunner.run(environment, null, new Arguments(arguments));
            }
        }
    }


}
