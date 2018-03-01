package cli;

import java.io.OutputStream;
import java.io.PrintWriter;

public class UserOutputWriter {
    private static final char INVITATION_SYMBOL = '>';
    private final PrintWriter writer;

    public UserOutputWriter(OutputStream outputStream) {
        writer = new PrintWriter(outputStream);
    }

    public void writeInvitation() {
        writer.print(INVITATION_SYMBOL);
        writer.flush();
    }

    public void write(String s) {
        writer.println(s);
        writer.flush();
    }
}
