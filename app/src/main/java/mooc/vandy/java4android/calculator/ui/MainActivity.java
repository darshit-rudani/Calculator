package mooc.vandy.java4android.calculator.ui;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

import mooc.vandy.java4android.calculator.R;
import mooc.vandy.java4android.calculator.logic.Logic;
import mooc.vandy.java4android.calculator.logic.LogicInterface;

/**
 * This Activity prompts the user for two integer values and
 * and operation to perform on these values.
 */
public class MainActivity extends Activity implements ActivityInterface {
    /**
     * The Spinner (drop down selector) that you choose which
     * operation to use from.
     */
    private Spinner mMathSpinner;

    /**
     * EditText that holds the first value entered by the user.
     */
    private EditText mValueOne;

    /**
     * EditText that holds the second value entered by the user.
     */
    private EditText mValueTwo;

    /**
     * EditText that stores the results of the computation.
     */
    private TextView mResult;

    /**
     * Reference to the Logic object.
     */
    private LogicInterface mLogic;

    /**
     * Hook method called back by the Activity Manager Service when
     * the Activity is created.
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Call up to the super class.
        super.onCreate(savedInstanceState);

        // Initialize the UI.
        initializeUI();

        // Initialize the Logic instance.
        mLogic = new Logic(this);
    }

    /**
     * Initialize the UI Views.
     */
    private void initializeUI() {
        // Set the layout
        setContentView(R.layout.activity_main);

        // Store references to the two values entered by the user.
        mValueOne = findViewById(R.id.valueOneEditText);
        mValueTwo = findViewById(R.id.valueTwoEditText);

        // Store a reference to the MathSpinner.
        mMathSpinner = findViewById(R.id.mathSpinner);

        // Store a reference to the EditText containing the result.
        mResult = findViewById(R.id.results);

        // Initialize the adapter that binds the Array of CharSequence data to mMathSpinner widget.
        ArrayAdapter<CharSequence> mAdapter = ArrayAdapter.createFromResource(this,
                R.array.math_options,
                R.layout.spinner_item);
        mAdapter.setDropDownViewResource(R.layout.spinner_item);

        // Associate the ArrayAdapter with the Spinner.
        mMathSpinner.setAdapter(mAdapter);

        // Set the default selection of the mMathSpinner to the first entry.
        mMathSpinner.setSelection(0);
    }

    /**
     * Called back by the Android UI framework when the user presses
     * the "Calculate" button.
     */
    public void buttonPressed(View view) {
        // Operation selected by the user.
        final int operation = getOperationNumber();

        int argOne;
        int argTwo;

        // First argument specified by the user.
        try {
            argOne = getValueOne();
        } catch (Exception e) {
            Toast.makeText(this, "First value cannot be blank", Toast.LENGTH_SHORT).show();
            return;
        }

        // Second argument specified by the user.
        try {
            argTwo = getValueTwo();
        } catch (Exception e) {
            Toast.makeText(this, "Second value cannot be blank", Toast.LENGTH_SHORT).show();
            return;
        }

        // Perform the operation on the two arguments.
        mLogic.process(argOne, argTwo, operation);
    }

    /**
     * Get the value of the first user input operand.
     */
    @Override
    public int getValueOne() {
        return Integer.parseInt(mValueOne.getText().toString());
    }

    /**
     * Get the value of the second user input operand.
     */
    @Override
    public int getValueTwo() {
        return Integer.parseInt(mValueTwo.getText().toString());
    }

    /**
     * Get the value of the user input operation.
     */
    @Override
    public int getOperationNumber() {
        return Arrays.asList(getResources()
                .getStringArray(R.array.math_options))
                // Add 1 to start the selected operation from 1 rather than 0.
                .indexOf(mMathSpinner.getSelectedItem().toString()) + 1;
    }

    /**
     * Print the result to the user's display.
     */
    @Override
    public void print(String resultString) {
        mResult.setText(resultString);
    }
}
