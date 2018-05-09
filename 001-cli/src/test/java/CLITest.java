import ru.spbau.cli.CLI;
import org.junit.Test;

import java.io.*;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class CLITest {
    private final static String NEWLINE = System.lineSeparator();
    private final static char INVITATION = '>';
    private final static String INVITATION_NEWLINE = INVITATION + NEWLINE;

    private String execute(String input) throws IOException {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        new CLI(inputStream, outputStream).run();
        return new String(outputStream.toByteArray());
    }

    @Test
    public void wcCommandTest() throws IOException {
        //Windows and Unix newliners
        String expected1 = INVITATION + "172 709 5299" + NEWLINE + INVITATION_NEWLINE;
        String expected2 = INVITATION + "172 709 5471" + NEWLINE + INVITATION_NEWLINE;
        String pipedActual = execute("cat gradlew | wc\nexit");
        assertTrue(expected1.equals(pipedActual) || expected2.equals(pipedActual));

        String noPipeActual = execute("wc gradlew\nexit");
        assertTrue(expected1.equals(noPipeActual) || expected2.equals(noPipeActual));

        String expected = INVITATION + "1 1 3" + NEWLINE + INVITATION_NEWLINE;
        String actual = execute("echo 123 | wc\nexit");
        assertEquals(expected, actual);
    }

    @Test
    public void identifierTest() throws IOException {

        String expected = INVITATION_NEWLINE + INVITATION + "5" + NEWLINE + INVITATION_NEWLINE;
        String actual = execute("x=5\necho $x\nexit");
        assertEquals(expected, actual);

        expected = INVITATION_NEWLINE + INVITATION_NEWLINE;
        actual = execute("x=exit\n$x");
        assertEquals(expected, actual);
    }

    @Test
    public void weakQuotingTest() throws IOException {
        String expected = INVITATION_NEWLINE + INVITATION + "$x" + NEWLINE + INVITATION_NEWLINE;
        String actual = execute("x=5\necho '$x'\nexit");
        assertEquals(expected, actual);
    }

    @Test
    public void multilineCommandTest() throws IOException {
        String expected = INVITATION + "ab\ncd" + NEWLINE + INVITATION_NEWLINE;
        String actual = execute("echo \"ab\ncd\"\nexit");
        assertEquals(expected, actual);
    }

    @Test
    public void echoPipeCatTest() throws IOException {
        String expected = INVITATION + "123" + NEWLINE + INVITATION_NEWLINE;
        String actual = execute("echo 123 | cat\nexit");
        assertEquals(expected, actual);
    }

    @Test
    public void GrepTest() throws IOException {
        String expected = INVITATION + "group 'swd'\n" +
                "version '1.0-SNAPSHOT'\n" +
                "\n" +
                "apply plugin: 'java'\n" +
                "--\n" +
                "sourceCompatibility = 1.8\n" +
                "\n" +
                "repositories {\n" +
                "    mavenCentral()\n" +
                "--\n" +
                "    testCompile group: 'junit', name: 'junit', version: '4.12'\n" +
                "    compile group: 'com.beust', name: 'jcommander', version: '1.72'\n" +
                "}\n" + NEWLINE + INVITATION_NEWLINE;
        String actualPiped = execute("cat build.gradle | grep -A 3 o\nexit");
        String actualNotPiped = execute("grep -A 3 o build.gradle\nexit");
        assertEquals(expected, actualPiped);
        assertEquals(expected, actualNotPiped);
        assertEquals(actualPiped, actualNotPiped);
    }

    @Test
    public void grepNoSuchFile() throws IOException {
        String actual = execute("grep -i x build.gralde\nexit");
        String expected = ">IO exception at build.gralde\n" + INVITATION_NEWLINE;
        assertEquals(expected, actual);
    }

    @Test
    public void grepIgnoreCaseTest() throws IOException {
        String expected = INVITATION +
                "ABC\n" +
                "--\n" +
                "abc\n" +
                "--\n" +
                "Abc\n\n" + INVITATION_NEWLINE;
        String actual = execute("echo \"ABC\nabc\nAbc\ncbA\" | grep -i abc\nexit");
        assertEquals(expected, actual);
    }

    @Test
    public void grepTestWords() throws IOException {
        String expected = ">Abc\n\n" + INVITATION_NEWLINE;
        String actual = execute("echo \"ABCd\nabcde\nAbc\ncbA\" | grep -i -w abc\nexit");
        assertEquals(expected, actual);
    }

    @Test
    public void grepMultipleFiles() throws IOException {
        String expected = ">group 'swd'\n" +
                "--\n" +
                "version '1.0-SNAPSHOT'\n" +
                "--\n" +
                "apply plugin: 'java'\n" +
                "--\n" +
                "    testCompile group: 'junit', name: 'junit', version: '4.12'\n" +
                "--\n" +
                "    compile group: 'com.beust', name: 'jcommander', version: '1.72'\n" +
                "--\n" +
                "rootProject.name = 'swd'\n\n" + INVITATION_NEWLINE;
        String actual = execute("grep \"'\" build.gradle settings.gradle\nexit");
        assertEquals(expected, actual);
    }

}
