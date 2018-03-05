package cli;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        try {
            new CLI(System.in, System.out).run();
        } catch(Exception e) {
            System.out.println(e.getMessage());
        }
    }

}
