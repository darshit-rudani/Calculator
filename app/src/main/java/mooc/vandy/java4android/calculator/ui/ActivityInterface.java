package mooc.vandy.java4android.calculator.ui;

/**
 * Defines the interface for user input/output.
 */
public interface ActivityInterface {
    /**
     * Get the value of the first user input operand.
     */ 
    int getValueOne();

    /**
     * Get the value of the second user input operand.
     */ 
    int getValueTwo();

    /**
     * Get the value of the user input operation.
     */
    int getOperationNumber();

    /**
     * Print the result to the user's display.
     */
    void print(String resultString);
}
