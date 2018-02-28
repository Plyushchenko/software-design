package cli.CommandRunner;

import cli.Environment;
import cli.Splitter;

public class WcCommandRunner implements CommandRunner {
    public static final String STRING_VALUE = "wc";

    @Override
    public void run(Environment environment, String arguments) {
        String data = environment.getData();
        int bytes = data.getBytes().length;
        int lines = 0;
        int words = 0;
        char prevChar = (char)0;
        for (char c: data.toCharArray()) {
            if (c == '\n') {
                lines++;
            }
            if (Splitter.SPACE_SYMBOLS.contains(c) && !Splitter.SPACE_SYMBOLS.contains(prevChar)) {
                words++;
            }
            prevChar = c;
        }
        environment.write(lines + " " + words + " " + bytes);
    }

    @Override
    public boolean shouldExit() {
        return false;
    }
}
