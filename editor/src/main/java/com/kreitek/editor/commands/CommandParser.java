package com.kreitek.editor.commands;

import com.kreitek.editor.exceptions.BadCommandException;

import java.util.regex.Pattern;

public class CommandParser {

    public String[] parse(final String commandLine) throws BadCommandException {
        if (isValidCommand(commandLine)) {
            return getArguments(commandLine);
        } else {
            throw new BadCommandException();
        }
    }

    private boolean isValidCommand(final String commandLine) {
        final var regex = "a \\\"([a-zA-ZÀ-ÿ\\u00f1\\u00d1]|[.,\\s\\/#!$%\\^&\\*;:{}=\\-_`~()”“\"…]|\\s])*\\\"|d ([0-9])*|u ([0-9])* \\\"([[a-zA-ZÀ-ÿ\\u00f1\\u00d1]|[.,\\s\\/#!$%\\^&\\*;:{}=\\-_`~()”“\"…]|\\s])*\\\"|undo|exit";
        final var pattern = Pattern.compile(regex, Pattern.MULTILINE);
        final var matcher = pattern.matcher(commandLine);

        return matcher.matches();
    }

    private String[] getArguments(String commandLine) {
        if (commandLine.startsWith("a ")) {
            final var args = commandLine.split("a \"");
            return new String[]{"a", args[1].substring(0, args[1].length() - 1)};
        }
        if (commandLine.startsWith("d ")) {
            return new String[]{"d", commandLine.split(" ")[1]};
        }
        if (commandLine.startsWith("u ")) {
            commandLine = commandLine.split("u \"")[0];
            final var index = commandLine.indexOf(" \"");
            final var arg2 = commandLine.substring(2, index);
            final var arg3 = commandLine.substring(index + 2, commandLine.length() - 1);

            return new String[]{"u", arg2, arg3};
        }
        return new String[]{commandLine};
    }
}
