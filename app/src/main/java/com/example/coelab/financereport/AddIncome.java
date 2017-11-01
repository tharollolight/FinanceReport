package com.example.coelab.financereport;

import android.app.DatePickerDialog;
import android.app.Dialog;
import android.icu.text.DateFormat;
import android.icu.util.Calendar;
import android.icu.util.GregorianCalendar;
import android.os.Build;
import android.preference.EditTextPreference;
import android.support.annotation.NonNull;
import android.support.annotation.RequiresApi;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

//import com.google.firebase.database.DatabaseReference;
//import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Date;

public class AddIncome extends AppCompatActivity {


    private String mIncomeSource;
    private String mIncomeDate;
    private String mIncomeNotes;

    private int mIncomeAmount = 2;
    private int mIncomeBalance = 0;
    EditText amountText;
    protected static TextView dateText;
    private Button mDate_Btn;
    private Button mSaveIncome_Btn;
    EditText sourceText;
    private Button MYDATE;
    EditText notesText;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mIncomeDatabaseReference;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        setTitle("ADD Income");

        sourceText = (EditText)findViewById(R.id.income_source);
        amountText = (EditText)findViewById(R.id.income_amount);
        notesText = (EditText)findViewById(R.id.income_notes);
        dateText = (TextView) findViewById(R.id.income_date);
        mDate_Btn = (Button)findViewById(R.id.income_date_button);
        mSaveIncome_Btn = (Button)findViewById(R.id.save_income);

        mFirebaseDatabase = FirebaseDatabase.getInstance();

        mIncomeDatabaseReference = mFirebaseDatabase.getReference().child("Income");

       mDate_Btn.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               DialogFragment newFragment = new DatePickerFragment();
               newFragment.show(getSupportFragmentManager(), "Date Picker");
           }
       });

        mSaveIncome_Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //get the source
                mIncomeSource = sourceText.getText().toString();

                //get the date.
                mIncomeDate = dateText.getText().toString();

                //get the amount

                String incomeAmountString = amountText.getText().toString();
                mIncomeAmount = Integer.parseInt(incomeAmountString);

                mIncomeBalance = mIncomeBalance + mIncomeAmount;


                mIncomeNotes = notesText.getText().toString();

                String DisplayMessage = "Source: " + mIncomeSource;
                DisplayMessage += "\n" + mIncomeDate;
                DisplayMessage += "\n" + mIncomeBalance;
                DisplayMessage += "\n" + mIncomeNotes;

                TextView messageText = (TextView)findViewById(R.id.income_message);
                messageText.setText(DisplayMessage);

                FinancialReport financialReport = new FinancialReport(mIncomeSource, mIncomeDate, mIncomeAmount, mIncomeNotes);
                String id = mIncomeDatabaseReference.push().getKey();
               /* Toast.makeText(getApplicationContext(),financialReport.getSource(),Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),financialReport.getAmount() + " ",Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),financialReport.getDate(),Toast.LENGTH_SHORT).show();
                Toast.makeText(getApplicationContext(),financialReport.getNotes(),Toast.LENGTH_SHORT).show(); */
                mIncomeDatabaseReference.child(id).setValue(financialReport);
            }
        });

    }



    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        @RequiresApi(api = Build.VERSION_CODES.N)
        @NonNull
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            return new DatePickerDialog(getActivity(),this,year, month, day);
        }

        @Override
        public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
            //Editable mydate = dateText.getText();
            String StringOfDate = dayOfMonth + "/" + (month+1) + "/" + year;
            dateText.setText( StringOfDate);
        }
    }

}

