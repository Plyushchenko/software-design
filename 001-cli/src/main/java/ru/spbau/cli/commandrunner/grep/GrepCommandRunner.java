package ru.spbau.cli.commandrunner.grep;

import ru.spbau.cli.Environment;
import ru.spbau.cli.commandrunner.CommandRunner;

import java.util.List;

public class GrepCommandRunner implements CommandRunner {
    public static final String STRING_VALUE = "grep";

    /**
     * Runs the command modifying the environment
     * @param environment Environment state
     * @param argument Command argument
     */
    @Override
    public void run(Environment environment, String argument) {
        System.out.println("res = " + environment.getResult());
        System.out.println("arg = " + argument);
        GrepCommandRunnerConfiguration configuration = new GrepCommandRunnerConfiguration();
        GrepCommandRunnerArgumentsParser parser = new GrepCommandRunnerArgumentsParser();
        parser.parse(argument, configuration);
        System.out.println(configuration.print());
        //String s = env.getRes() или извлечённый из arg файл (приоритет у второго)
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
