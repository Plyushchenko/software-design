package ru.spbau.cli.commandrunner;

import ru.spbau.cli.Environment;
import ru.spbau.cli.Parser;
import ru.spbau.cli.utils.StringUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import static ru.spbau.cli.utils.StringUtils.SPACE_SYMBOLS;

public class WcCommandRunner implements CommandRunner {
    public static final String STRING_VALUE = "wc";

    /**
     * Counts the number of lines, words and bytes in the string/file/result of the previous command
     * @param environment Environment state
     * @param argument Command argument
     */
    @Override
    public void run(Environment environment, String argument) {
        byte[] data;
        if (argument.isEmpty()) {
            data = environment.getResult().getBytes();
        } else {
            try {
                data = Files.readAllBytes(Paths.get(argument));
            } catch (IOException e) {
                environment.writeResult("0 0 0");
                return;
            }
        }
        int bytes = data.length;
        int lines = 0;
        int words = 0;
        char prevChar = (char)0;
        for (byte b: data) {
            char c = (char)b;
            if (c == '\n') {
                lines++;
            }
            if (SPACE_SYMBOLS.contains(c) && !SPACE_SYMBOLS.contains(prevChar)) {
                words++;
            }
            prevChar = c;
        }
        if (prevChar != '\n') {
            lines++;
        }
        if (!SPACE_SYMBOLS.contains(prevChar)) {
            words++;
        }
        environment.writeResult(lines + " " + words + " " + bytes);
    }

    /**
     * An execution shouldn't stop
     * @return False
     */
    @Override
    public boolean shouldExit() {
        return false;
    }
}
