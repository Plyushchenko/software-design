package ru.spbau.cli.commandrunner.grep;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import ru.spbau.cli.commandrunner.CommandRunnerArgumentsParserException;
import ru.spbau.cli.utils.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import static ru.spbau.cli.utils.StringUtils.QUOTE_MARKS;
import static ru.spbau.cli.utils.StringUtils.SPACE_SYMBOLS;

/**
 * grep command runner arguments parser
 */
class GrepCommandRunnerArgumentsParser {
    /**
     * Runs JCommander parser and stores the result in the configuration
     * @param argument Raw string containing arguments
     * @param configuration Grep configuration
     */
    void parse(String argument, GrepCommandRunnerConfiguration configuration)
            throws CommandRunnerArgumentsParserException {
        List<String> arguments = StringUtils.split(argument, SPACE_SYMBOLS, QUOTE_MARKS);
        arguments = arguments.stream().map(StringUtils::trimAndEliminateQuoteMarks).collect(Collectors.toList());
        JCommander jCommander = JCommander.newBuilder().addObject(configuration).build();
        try {
            jCommander.parse(arguments.toArray(new String[0]));
        } catch (ParameterException e) {
            throw new CommandRunnerArgumentsParserException(e.getMessage());
        }
        String pattern = configuration.getPattern();
        if (pattern == null) {
            throw new CommandRunnerArgumentsParserException("No pattern was extracted");
        }
    }
}