package ru.spbau.cli.commandrunner.grep;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.ParameterException;
import ru.spbau.cli.utils.StringUtils;

import java.util.List;
import java.util.stream.Collectors;

import static ru.spbau.cli.utils.StringUtils.QUOTE_MARKS;
import static ru.spbau.cli.utils.StringUtils.SPACE_SYMBOLS;

class GrepCommandRunnerArgumentsParser {
    void parse(String argument, GrepCommandRunnerConfiguration configuration) {
        List<String> arguments = StringUtils.split(argument, SPACE_SYMBOLS, QUOTE_MARKS);
        arguments = arguments.stream().map(StringUtils::eliminateQuoteMarks).collect(Collectors.toList());
        for (String s: arguments) {
            System.out.println(s);
        }
        JCommander jCommander = JCommander.newBuilder().addObject(configuration).build();
        try {
            jCommander.parse(arguments.toArray(new String[0]));
        } catch (ParameterException e) {
            //TODO
        }
    }
}