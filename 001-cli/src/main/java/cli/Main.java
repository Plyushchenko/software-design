package cli;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        new CLI(System.in, System.out).run();
    }

}
