package org.acme;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StringCalculatorTest {

    private void assertAdd(int expected, String input) {
        assertEquals(expected, StringCalculator.add(input));
    }

    @Test
    void add_emptyString_returnIntZero() {
        assertAdd(0, "");
    }

    @Test
    void add_singleNumber_returnGivenNumberAsInt() {
        assertAdd(0, "0");
        assertAdd(1, "1");
        assertAdd(2, "2");
        assertAdd(100, "100");
    }

    @Test
    void add_twoNumbers_returnSumOfNumbers() {
        assertAdd(3, "1,2");
        assertAdd(0, "0,0");
        assertAdd(25, "10,15");
    }

    @Test
    void add_threeNumbers_returnSumOfNumbers() {
        assertAdd(6, "1,2,3");
        assertAdd(0, "0,0,0");
        assertAdd(25, "10,15,0");
    }

    @Test
    void add_threeNumbersAndNewLineDelimiter_returnSumOfNumbers() {
        assertAdd(6, "1\n2\n3");
        assertAdd(0, "0\n0\n0");
        assertAdd(25, "10\n15\n0");
    }

    @Test
    void add_threeNumbersAndNewLineAndCommaDelimiters_returnSumOfNumbers() {
        assertAdd(6, "1,2\n3");
        assertAdd(25, "10,15\n0");
    }

    @Test
    void add_delimiterLine_useSpecifiedDelimiters() {
        assertAdd(3, "//;\n1;2");
        assertAdd(8, "//,.;\n1;2.4,1");
    }

    @Test
    void add_negativeNumber_throwsException() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> StringCalculator.add("-1"));
        assertEquals("negatives not allowed [-1]", exception.getMessage());
    }

    @Test
    void add_negativeNumbers_throwsExceptionAndListNegativeNumbersInErrorMessage() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> StringCalculator.add("-1,-3,5,-2"));
        assertEquals("negatives not allowed [-1 -3 -2]", exception.getMessage());
    }
}