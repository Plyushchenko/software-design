import java.io.InputStream;

public interface Command {
    void execute(Environment environment, InputStream inputStream, Arguments arguments);
}
