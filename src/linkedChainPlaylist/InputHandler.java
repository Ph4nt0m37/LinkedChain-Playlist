package linkedChainPlaylist;

import java.util.*;

/**
 * Handles user input for the Don't Burn Out game.
 * <p>
 * This class provides methods for retrieving strings, integers, and boolean
 * values from the user. It also provides validation methods for ensuring that
 * user input matches the expected format or allowed values.
 * </p>
 * 
 * @author Paulo Korowajczuk Nader
 */
public class InputHandler
{
    /**
     * Scanner used to read input from the user through standard input.
     */
    public static Scanner scanner = new Scanner(System.in);

    /**
     * Displays a prompt and retrieves a line of input from the user.
     *
     * @param prompt
     *            the message displayed to the user before input is read
     * @return the user's input as a String
     */
    public static String getUserInput(String prompt)
    {
        System.out.print(prompt);

        return scanner.nextLine();
    }


    /**
     * Displays a prompt and repeatedly asks the user for input until a valid
     * answer is provided.
     * <p>
     * Input is compared to the values in {@code validAnswers} without regard to
     * capitalization.
     * </p>
     *
     * @param prompt
     *            the message displayed to the user before input is read
     * @param validAnswers
     *            an array containing the acceptable answers
     * @param errorMessage
     *            the message displayed when the user enters an invalid answer
     * @return the user's valid input as a String
     */
    public static
        String
        getUserInput(String prompt, String[] validAnswers, String errorMessage)
    {
        while (true)
        {

            System.out.print(prompt);

            String resp = scanner.nextLine();

            for (String validAnswer : validAnswers)
            {

                if (validAnswer.equalsIgnoreCase(resp))
                {
                    return resp;
                }
            }

            System.out.println(errorMessage);
        }
    }


    /**
     * Displays a prompt and repeatedly asks the user for an integer until a
     * valid integer within the specified range is provided.
     * <p>
     * If the user enters a value that is not an integer or is outside the
     * specified range, the error message is displayed and the user is prompted
     * again.
     * </p>
     *
     * @param prompt
     *            the message displayed to the user before input is read
     * @param minVal
     *            the minimum acceptable integer value
     * @param maxVal
     *            the maximum acceptable integer value
     * @param errorMessage
     *            the message displayed when the user enters an invalid value
     * @return the user's valid integer input
     */
    public static int getUserIntInput(
        String prompt,
        int minVal,
        int maxVal,
        String errorMessage)
    {
        while (true)
        {

            System.out.print(prompt);

            String resp = scanner.nextLine();

            try
            {

                int respNumVal = Integer.parseInt(resp);

                if (respNumVal >= minVal && respNumVal <= maxVal)
                {
                    return respNumVal;
                }

                System.out.println(errorMessage);

            }
            catch (NumberFormatException e)
            {

                System.out.println(errorMessage);
            }
        }
    }


    /**
     * Displays a prompt and repeatedly asks the user for a yes or no response
     * until a valid response is provided.
     * <p>
     * The following inputs are accepted as {@code true}: "Yes" and "Y". The
     * following inputs are accepted as {@code false}: "No" and "N". Input is
     * compared without regard to capitalization.
     * </p>
     *
     * @param prompt
     *            the message displayed to the user before input is read
     * @param errorMessage
     *            the message displayed when the user enters an invalid response
     * @return {@code true} if the user enters "Yes" or "Y"; {@code false} if
     *             the user enters "No" or "N"
     */
    public static boolean getUserBoolInput(String prompt, String errorMessage)
    {
        while (true)
        {

            System.out.print(prompt);

            String resp = scanner.nextLine();

            for (String validYesAnswer : new String[] { "Yes", "Y" })
            {

                if (validYesAnswer.equalsIgnoreCase(resp))
                {
                    return true;
                }
            }

            for (String validNoAnswer : new String[] { "No", "N" })
            {

                if (validNoAnswer.equalsIgnoreCase(resp))
                {
                    return false;
                }
            }

            System.out.println(errorMessage);
        }
    }
}
