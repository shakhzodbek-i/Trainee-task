package com.somecompany.traineetask;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button mFibonacciButton;
    private Button mSquareOddButton;
    private Button mPalindromeButton;
    private int mRange;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mFibonacciButton = findViewById(R.id.fibonacci);
        mSquareOddButton = findViewById(R.id.odd);
        mPalindromeButton = findViewById(R.id.palindrome);

        mFibonacciButton.setOnClickListener(view -> inputDialog("Enter the range ( from 0 to N ): ", 0));

        mSquareOddButton.setOnClickListener(view -> inputDialog("Enter the range ( from 0 to N ):", 1));

        mPalindromeButton.setOnClickListener(view -> inputDialog("Enter the range ( from 0 to N ):", 2));
    }

    /**
     * Displays the dialog window for entering range ( from 0 to N). After taking proper input, function sends it and option code to the ListActivity.
     *
     * @param dialogText title of dialog window
     * @param optCode code of selected option
     *
     * @see ListActivity
     */
    private void inputDialog(String dialogText, int optCode){
        Intent intent = new Intent(this, ListActivity.class);
        final EditText inputText = new EditText(this);
        inputText.setInputType(InputType.TYPE_CLASS_NUMBER | InputType.TYPE_NUMBER_FLAG_DECIMAL);
        inputText.setHint("Enter value for N");

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(dialogText)
                .setView(inputText)
                .setPositiveButton("OK", (dialogInterface, i) -> {
                    String text = inputText.getText().toString();
                    if (text.length() != 0 && Integer.valueOf(text) <= 40000) {
                        mRange = Integer.valueOf(text);
                        intent.putExtra(ListActivity.RANGE_EXTRA, mRange);
                        intent.putExtra(ListActivity.OPTION_CODE_EXTRA, optCode);
                        startActivity(intent);
                    } else {
                        Toast.makeText(this,"INPUT ERROR!!!\nYou can enter any integer between 0 and 40000.", Toast.LENGTH_SHORT).show();
                    }
                })
                .setNegativeButton("Cancel", ((dialogInterface, i) -> {
                    dialogInterface.cancel();
                }))
                .show();
    }

}
