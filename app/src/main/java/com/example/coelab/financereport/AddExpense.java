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
import android.widget.DatePicker;
import android.widget.EditText;



/**
 * Created by coeLab on 2017/10/23.
 */

public class AddExpense extends AppCompatActivity{

    protected static EditText dateText;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_expense);
        setTitle("Add Expense");

        dateText = (EditText)findViewById(R.id.expense_date);

        dateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dateText.setText("");
                DialogFragment newFragment = new AddIncome.DatePickerFragment();
                newFragment.show(getSupportFragmentManager(), "Date Picker");
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

            String StringOfDate = dayOfMonth + "/" + (month+1) + "/" + year;
            dateText.setText(dateText.getText()+ StringOfDate);
        }
    }
}
