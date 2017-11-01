package com.example.coelab.financereport;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.util.Calendar;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * Created by coeLab on 2017/10/23.
 */

public class AddExpense extends AppCompatActivity{

    String mExpenseSource;
    String mExpenseDate;
    String mExpenseNotes;
    int mExpenseAmount = 0;
    int mExpenseBalance = 0;

    protected static TextView dateText;
    private Button mDate_Btn;
    private Button mSaveExpense_Btn;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mExpenseDatabaseReference;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        setTitle("Add Expense");

        dateText = (TextView) findViewById(R.id.expense_date);
        mDate_Btn = (Button)findViewById(R.id.expense_date_btn);
        mSaveExpense_Btn = (Button)findViewById(R.id.save_expense);

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mExpenseDatabaseReference = mFirebaseDatabase.getReference().child("Expense");

        mDate_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //dateText.setText("");
                DialogFragment newFragment = new AddExpense.DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "Date Picker");
            }
        });

        mSaveExpense_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText exp_sourceText = (EditText)findViewById(R.id.expense_source);
                mExpenseSource = exp_sourceText.getText().toString();

                mExpenseDate = dateText.getText().toString();

                EditText exp_amountText = (EditText)findViewById(R.id.expense_amount);
                String expenseAmountString = exp_amountText.getText().toString();
                mExpenseAmount = Integer.parseInt(expenseAmountString);

                EditText exp_notesText = (EditText)findViewById(R.id.expense_notes);
                mExpenseNotes = exp_notesText.getText().toString();

                TextView messageText = (TextView)findViewById(R.id.expense_message);
                String DisplayMsg ="my expense";
                DisplayMsg += "\n" + mExpenseSource;
                DisplayMsg += "\n" + mExpenseDate;
                DisplayMsg += "\n" + expenseAmountString;
                DisplayMsg += "\n" + mExpenseNotes;


                messageText.setText(DisplayMsg);

                FinancialReport financialReport = new FinancialReport(mExpenseSource, mExpenseDate, mExpenseAmount, mExpenseNotes);
                String id = mExpenseDatabaseReference.push().getKey();
                mExpenseDatabaseReference.child(id).setValue(financialReport);
            }
        });

    }



    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{


        @RequiresApi(api = Build.VERSION_CODES.N)
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(),this,year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

            String StringOfDate = dayOfMonth + "/" + (month+1) + "/" + year;
            dateText.setText( StringOfDate);
        }
    }
}
