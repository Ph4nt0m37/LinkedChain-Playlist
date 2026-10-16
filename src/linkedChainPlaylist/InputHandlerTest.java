package linkedChainPlaylist;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import student.TestCase;

/**
 * Tests the InputHandler class.
 */
public class InputHandlerTest extends TestCase
{
    private Scanner originalScanner;
    private InputStream originalIn;
    private PrintStream originalOut;
    private ByteArrayOutputStream output;

    /**
     * Sets up input and output streams before each test.
     */
    public void setUp()
    {
        originalScanner = InputHandler.scanner;
        originalIn = System.in;
        originalOut = System.out;

        output = new ByteArrayOutputStream();
        System.setOut(new PrintStream(output));
    }


    /**
     * Restores the original streams after each test.
     */
    public void tearDown()
    {
        InputHandler.scanner = originalScanner;
        System.setIn(originalIn);
        System.setOut(originalOut);
    }


    /**
     * Provides simulated console input.
     *
     * @param input
     *            the input to provide
     */
    private void setInput(String input)
    {
        InputStream testInput =
            new ByteArrayInputStream(input.getBytes());
        System.setIn(testInput);
        InputHandler.scanner = new Scanner(testInput);
    }


    /**
     * Tests getting a regular string.
     */
    public void testGetUserInput()
    {
        setInput("Hello\n");

        String result = InputHandler.getUserInput("Enter text: ");

        assertEquals("Hello", result);
        assertTrue(output.toString().contains("Enter text: "));
    }


    /**
     * Tests that non-blank input is returned with surrounding spaces removed.
     */
    public void testGetNonBlankInput()
    {
        setInput("  Hello World  \n");

        String result =
            InputHandler.getNonBlankInput("Enter text: ", "Cannot be blank!");

        assertEquals("Hello World", result);
    }


    /**
     * Tests that blank input is rejected before valid input is accepted.
     */
    public void testGetNonBlankInputBlankThenValid()
    {
        setInput("\n   \n  Playlist  \n");

        String result =
            InputHandler.getNonBlankInput("Enter text: ", "Cannot be blank!");

        assertEquals("Playlist", result);

        String consoleOutput = output.toString();
        assertEquals(2, countOccurrences(consoleOutput, "Cannot be blank!"));
    }


    /**
     * Tests that a valid answer is accepted.
     */
    public void testGetUserInputValidAnswer()
    {
        setInput("Yes\n");

        String result = InputHandler.getUserInput(
            "Continue? ",
            new String[] { "Yes", "No" },
            "Invalid answer!");

        assertEquals("Yes", result);
    }


    /**
     * Tests case-insensitive matching for valid answers.
     */
    public void testGetUserInputCaseInsensitive()
    {
        setInput("yEs\n");

        String result = InputHandler.getUserInput(
            "Continue? ",
            new String[] { "Yes", "No" },
            "Invalid answer!");

        assertEquals("yEs", result);
    }


    /**
     * Tests that invalid answers are rejected before a valid answer.
     */
    public void testGetUserInputInvalidThenValid()
    {
        setInput("Maybe\nYes\n");

        String result = InputHandler.getUserInput(
            "Continue? ",
            new String[] { "Yes", "No" },
            "Invalid answer!");

        assertEquals("Yes", result);
        assertTrue(output.toString().contains("Invalid answer!"));
    }


    /**
     * Tests accepting the first integer in the allowed range.
     */
    public void testGetUserIntInputMinimum()
    {
        setInput("0\n");

        int result = InputHandler.getUserIntInput(
            "Enter number: ", 0, 10, "Invalid number!");

        assertEquals(0, result);
    }


    /**
     * Tests accepting the maximum integer in the allowed range.
     */
    public void testGetUserIntInputMaximum()
    {
        setInput("10\n");

        int result = InputHandler.getUserIntInput(
            "Enter number: ", 0, 10, "Invalid number!");

        assertEquals(10, result);
    }


    /**
     * Tests accepting an integer between the minimum and maximum.
     */
    public void testGetUserIntInputValid()
    {
        setInput("5\n");

        int result = InputHandler.getUserIntInput(
            "Enter number: ", 0, 10, "Invalid number!");

        assertEquals(5, result);
    }


    /**
     * Tests rejecting an integer below the minimum.
     */
    public void testGetUserIntInputBelowMinimum()
    {
        setInput("-1\n5\n");

        int result = InputHandler.getUserIntInput(
            "Enter number: ", 0, 10, "Invalid number!");

        assertEquals(5, result);
        assertTrue(output.toString().contains("Invalid number!"));
    }


    /**
     * Tests rejecting an integer above the maximum.
     */
    public void testGetUserIntInputAboveMaximum()
    {
        setInput("11\n5\n");

        int result = InputHandler.getUserIntInput(
            "Enter number: ", 0, 10, "Invalid number!");

        assertEquals(5, result);
        assertTrue(output.toString().contains("Invalid number!"));
    }


    /**
     * Tests rejecting non-numeric input.
     */
    public void testGetUserIntInputNonNumeric()
    {
        setInput("abc\n5\n");

        int result = InputHandler.getUserIntInput(
            "Enter number: ", 0, 10, "Invalid number!");

        assertEquals(5, result);
        assertTrue(output.toString().contains("Invalid number!"));
    }


    /**
     * Tests accepting "Yes" as true.
     */
    public void testGetUserBoolInputYes()
    {
        setInput("Yes\n");

        boolean result =
            InputHandler.getUserBoolInput("Continue? ", "Invalid answer!");

        assertTrue(result);
    }


    /**
     * Tests accepting "Y" as true.
     */
    public void testGetUserBoolInputY()
    {
        setInput("Y\n");

        boolean result =
            InputHandler.getUserBoolInput("Continue? ", "Invalid answer!");

        assertTrue(result);
    }


    /**
     * Tests accepting lowercase "y" as true.
     */
    public void testGetUserBoolInputLowercaseY()
    {
        setInput("y\n");

        boolean result =
            InputHandler.getUserBoolInput("Continue? ", "Invalid answer!");

        assertTrue(result);
    }


    /**
     * Tests accepting "No" as false.
     */
    public void testGetUserBoolInputNo()
    {
        setInput("No\n");

        boolean result =
            InputHandler.getUserBoolInput("Continue? ", "Invalid answer!");

        assertFalse(result);
    }


    /**
     * Tests accepting "N" as false.
     */
    public void testGetUserBoolInputN()
    {
        setInput("N\n");

        boolean result =
            InputHandler.getUserBoolInput("Continue? ", "Invalid answer!");

        assertFalse(result);
    }


    /**
     * Tests rejecting an invalid boolean response before accepting a valid one.
     */
    public void testGetUserBoolInputInvalidThenValid()
    {
        setInput("Maybe\nY\n");

        boolean result =
            InputHandler.getUserBoolInput("Continue? ", "Invalid answer!");

        assertTrue(result);
        assertTrue(output.toString().contains("Invalid answer!"));
    }


    /**
     * Counts occurrences of a string in another string.
     *
     * @param text
     *            the text to search
     * @param target
     *            the string to count
     * @return the number of occurrences
     */
    private int countOccurrences(String text, String target)
    {
        int count = 0;
        int index = 0;

        while ((index = text.indexOf(target, index)) != -1)
        {
            count++;
            index += target.length();
        }

        return count;
    }
}