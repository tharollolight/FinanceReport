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
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Date;

public class AddIncome extends AppCompatActivity {

    int incomeAmount = 0;
    int incomeBalance = 0;

    protected static EditText dateText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_income);
        setTitle("ADD Income");

        dateText = (EditText)findViewById(R.id.income_date);

        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateText.setText("");
                DialogFragment newFragment = new DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "Date Picker");
            }
        });

    }

    public void SaveIncome(View view){

        //get the source
        EditText sourceText = (EditText)findViewById(R.id.income_source);
        String incomeSource = sourceText.getText().toString();

        //get the date.
        String incomeDate = dateText.getText().toString();

        //get the amount
        EditText amountText = (EditText)findViewById(R.id.income_amount);
        String incomeAmountString = amountText.getText().toString();
        incomeAmount = Integer.parseInt(incomeAmountString);

        incomeBalance = incomeBalance + incomeAmount;

    }


    @RequiresApi(api = Build.VERSION_CODES.N)
    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

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

            String StringOfDate = dayOfMonth + "/" + (month+1) + "/" + year;
            dateText.setText(dateText.getText()+ StringOfDate);
        }
    }

}

