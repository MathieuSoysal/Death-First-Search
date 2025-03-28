package io.github.mathieusoysal;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.jupiter.api.Test;

class PlayerTest {

    @Test
    void testBasicFunctionality() {
        String input = "5 5 2\n" +
                "0 1\n" +
                "1 2\n" +
                "2 3\n" +
                "3 4\n" +
                "4 0\n" +
                "2\n" +
                "4\n" +
                "1\n";
        String expectedOutput = "1 2\n";

        assertPlayerOutput(input, expectedOutput);
    }

    @Test
    void testNoLinks() {
        String input = "5 0 0\n" +
                "1\n";
        String expectedOutput = "";

        assertPlayerOutput(input, expectedOutput);
    }

    @Test
    void testNoGateways() {
        String input = "5 5 0\n" +
                "0 1\n" +
                "1 2\n" +
                "2 3\n" +
                "3 4\n" +
                "4 0\n" +
                "1\n";
        String expectedOutput = "";

        assertPlayerOutput(input, expectedOutput);
    }

    private void assertPlayerOutput(String input, String expectedOutput) {
        ByteArrayInputStream in = new ByteArrayInputStream(input.getBytes());
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PrintStream originalOut = System.out;
        java.io.InputStream originalIn = System.in;

        try {
            System.setIn(in);
            System.setOut(new PrintStream(out));

            Player.main(new String[0]);

            String actualOutput = out.toString().trim();
            assertEquals(expectedOutput.trim(), actualOutput);
        } finally {
            System.setIn(originalIn);
            System.setOut(originalOut);
        }
    }
}