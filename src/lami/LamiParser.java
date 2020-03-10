package lami;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class LamiParser {

    public static Token parseLine(String line) {
        Token token = new Token();
        if (!line.contains(" ")) {
            token.command = line;
            return token;
        }
        String[] arr = line.split(Pattern.quote(" "), 2);
        String args = arr[1];
        token.argsLine = args;

        token.command = arr[0];

        char[] chars = args.toCharArray();
        StringBuilder sb = new StringBuilder();
        boolean skipMode = false;
        boolean inQuotes = false;
        for (char c : chars) {
            if (skipMode) {
                skipMode = false;
                sb.append(c);
                continue;
            }
            if (c == '\\') {
                skipMode = true;
                continue;
            }
            if (!inQuotes) {
                if (c == ',') {
                    String arg = sb.toString().trim();
                    sb = new StringBuilder();
                    token.args.add(arg);
                    continue;
                }
                if (c == '\"') {
                    inQuotes = true;
                }
            } else {
                if (c == '\"') {
                    inQuotes = false;
                }
            }
            sb.append(c);
        }

        String lastArg = sb.toString().trim();
        token.args.add(lastArg);

        return token;
    }

    public static class Token {
        public String command;
        public List<String> args = new ArrayList<>();
        public String argsLine;
    }
}
