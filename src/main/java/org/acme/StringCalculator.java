package org.acme;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class StringCalculator {
    private static final String DEFAULT_DELIMITERS = ",\n";

    public static int add(final String inputString) {
        List<String> numbers = tokenize(inputString);
        validateNegativesNotAllowed(numbers);
        return numbers.stream()
                .mapToInt(Integer::parseInt)
                .sum();
    }

    private static List<String> tokenize(String inputString) {
        if (inputString.isEmpty()) {
            return List.of("0");
        }
        String delimiters = obtainDelimiters(inputString);
        String numbersLine = obtainNumbers(inputString);
        return split(delimiters, numbersLine);
    }

    private static String obtainDelimiters(String input) {
        if(containsDelimiters(input)) {
            return input.substring(2, input.indexOf("\n"));
        } else {
            return DEFAULT_DELIMITERS;
        }
    }

    private static boolean containsDelimiters(String input) {
        return input.startsWith("//");
    }

    private static String obtainNumbers(String input) {
        if(containsDelimiters(input)) {
            int newLineIndex = input.indexOf("\n");
            return input.substring(newLineIndex + 1);
        } else {
            return input;
        }
    }

    private static List<String> split(String delimiters, String numbersLine) {
        List<String> tokens = new ArrayList<>();
        StringTokenizer tokenizer = new StringTokenizer(numbersLine, delimiters);
        while (tokenizer.hasMoreTokens()) {
            tokens.add(tokenizer.nextToken());
        }
        return tokens;
    }

    private static void validateNegativesNotAllowed(List<String> numbers) {
        StringBuilder negativeNumbers = new StringBuilder();
        for(String number : numbers) {
            if(Integer.parseInt(number) < 0) {
                negativeNumbers.append(number).append(" ");
            }
        }
        if(negativeNumbers.length() > 0) {
            throw new IllegalArgumentException(String.format("negatives not allowed [%s]",
                    negativeNumbers.toString().trim()));
        }
    }
}
