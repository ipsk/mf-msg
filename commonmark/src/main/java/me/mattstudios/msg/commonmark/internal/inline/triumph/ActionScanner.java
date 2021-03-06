package me.mattstudios.msg.commonmark.internal.inline.triumph;

import me.mattstudios.msg.commonmark.internal.inline.Scanner;
import me.mattstudios.msg.commonmark.internal.util.Parsing;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class ActionScanner {

    @NotNull
    private static final List<String> ACTIONS = Arrays.asList("COMMAND", "HOVER", "URL", "SUGGEST", "CLIPBOARD");

    public static Map<String, String> scanAction(@NotNull final Scanner scanner) {
        // Counts parenthesis
        int parens = 0;

        // Holds the actions found
        final Map<String, String> actions = new LinkedHashMap<>();
        final StringBuilder builder = new StringBuilder();

        String type = null;
        // Loops through the next characters
        while (scanner.hasNext()) {
            boolean foundEscape = false;
            final char c = scanner.peek();

            // Handles
            if (c == '\\') {
                scanner.next();
                if (Parsing.isEscapable(scanner.peek())) {
                    builder.append(scanner.peek());
                    scanner.next();
                    continue;
                }

                foundEscape = true;
            }

            if (c == ':' && type == null) {
                type = builder.toString();

                if (!ACTIONS.contains(type.toUpperCase())) {
                    return actions;
                }

                builder.setLength(0);
                scanner.next();
                continue;
            }

            if (c == '|') {
                if (type != null && builder.length() != 0) {
                    // TODO DRY
                    // Trims the first space if has it
                    actions.put(type, builder.toString().trim());
                    type = null;
                }

                builder.setLength(0);
                scanner.next();
                continue;
            }

            if (c == '(') {
                parens++;
                // Limit to 32 nested parens for pathological cases
                if (parens > 32) {
                    if (type != null && builder.length() != 0) {
                        actions.put(type, builder.toString().trim());
                    }
                    break;
                }
            }

            if (c == ')') {
                if (parens == 0) {
                    if (type != null && builder.length() != 0) {
                        actions.put(type, builder.toString().trim());
                    }
                    return actions;
                }

                parens--;

                builder.append(c);
                scanner.next();
                continue;
            }

            builder.append(c);
            if (!foundEscape) scanner.next();
        }

        return actions;
    }

}