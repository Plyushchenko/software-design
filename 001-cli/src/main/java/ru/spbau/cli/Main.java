package ru.spbau.cli;

import java.io.IOException;

/**
 * Пофиксить "abc' 10:21-10:31
 * Написать документацию 10:32-10:50
 * Реализовать grep 10:52-11:58
 * Написать тесты 30 мин 12:14
 * Написать, почему выбрал JCommander 30 мин
 */
public class Main {
    public static void main(String[] args) {
        try {
            new CLI(System.in, System.out).run();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

}
