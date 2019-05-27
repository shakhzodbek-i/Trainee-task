package com.somecompany.traineetask;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import java.util.ArrayList;


public class ListActivity extends AppCompatActivity {

    private final static int FIBONACCI_REQUEST = 0;
    private final static int SQUARE_OF_ODD_REQUEST = 1;
    private final static int PALINDROME_REQUEST = 2;
    public final static String RANGE_EXTRA = "com.list_size";
    public final static String OPTION_CODE_EXTRA = "com.request_code";

    private RecyclerView mRecyclerView;
    private TextView mTitleText;
    private CustomListAdapter mAdapter;
    private ArrayList<Integer> mListOfNumbers = new ArrayList<>();
    private int mRange;
    private int mOptionCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);

        mRange = getIntent().getIntExtra(RANGE_EXTRA, 10);

        mOptionCode = getIntent().getIntExtra(OPTION_CODE_EXTRA, 0);

        mRecyclerView = findViewById(R.id.rw);

        mTitleText = findViewById(R.id.title);

        loadList();
    }

    /**
     * Load the list of elements according option code.
     */
    private void loadList(){

        switch (mOptionCode) {
            case FIBONACCI_REQUEST:
                mTitleText.setText("Fibonacci Sequence");
                fibonacciSeqGenerator();
                break;
            case SQUARE_OF_ODD_REQUEST:
                mTitleText.setText("Square of Odd Numbers");
                squareOfOddGenerator();
                break;
            case PALINDROME_REQUEST:
                mTitleText.setText("Palindromes");
                palindromeGenerator();
                break;
        }

        mAdapter = new CustomListAdapter(mListOfNumbers);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));

        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * Generate the list of Fibonacci sequence from 0 to N (where N is a range).
     */
    private void fibonacciSeqGenerator(){
        mListOfNumbers.clear();
        ArrayList<Integer> tempList = new ArrayList<>();
        int temp;
        int i = 1;

        if (mRange == 0) {
            mListOfNumbers.add(0);
            return;
        }

        tempList.add(0);
        tempList.add(1);

        while(true) {
            i++;
            temp = tempList.get(i - 2) + tempList.get(i - 1);
            if (temp > mRange)
                break;

            tempList.add(temp);
        }

        for (Integer num :
                tempList) {
            mListOfNumbers.add(num * num);
        }
    }

    /**
     * Generate list square root of odd numbers from 0 to N (where N is a range).
     */
    private void squareOfOddGenerator(){
        mListOfNumbers.clear();

        for (int i = 1; i < mRange; i++) {
            if(i % 2 == 1)
                mListOfNumbers.add(i * i);
        }
    }

    /**
     * Generate the list of palindromes from 0 to N (where N is a range)
     */
    private void palindromeGenerator(){
        mListOfNumbers.clear();

        for (int i = 0; i < mRange; i++) {
            if(isPalindrome(i))
                mListOfNumbers.add(i);
        }
    }

    /**
     * Check if number is palindrome or not.
     * @param number number for checking
     * @return true if number is palindrome, false otherwise
     */
    private boolean isPalindrome(int number) {
        int revers = 0;

        for (int i = number; i > 0; i /= 10) {
            revers = revers * 10 + i % 10;
        }

        return (number == revers);
    }

}
