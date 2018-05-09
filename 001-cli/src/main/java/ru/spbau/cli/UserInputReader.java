package ru.spbau.cli;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static ru.spbau.cli.utils.StringUtils.QUOTE_MARKS;

/**
 * Reader wrapper
 */
class UserInputReader {
    private final BufferedReader reader;
    private boolean escaping;
    private char escaper;

    UserInputReader(InputStream inputStream) {
        InputStreamReader inputStreamReader = new InputStreamReader(inputStream);
        reader = new BufferedReader(inputStreamReader);
    }

    String read() throws IOException {
        escaping = false;
        escaper = 0;
        StringBuilder input = new StringBuilder();
        do {
            String currentLine = reader.readLine();
            input.append(currentLine);
            input.append('\n');
            recalc(currentLine);
        } while (escaping);
        return input.toString();
    }

    private void recalc(String line) {
        for (int i = 0; i < line.length(); i++) {
            char currentChar = line.charAt(i);
            if (QUOTE_MARKS.contains(currentChar) && (i == 0 || line.charAt(i - 1) != '\\')) {
                if (escaping && escaper != currentChar) {
                    continue;
                }
                escaping = !escaping;
                escaper = escaping ? currentChar : 0;
            }
        }
    }

}
