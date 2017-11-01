package com.example.coelab.financereport;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class ExpenseReportfragment extends Fragment {

    private FinancialReport financialReport;

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mExpenseReportDatabaseReference;
    private DatabaseReference myDatabaseReference;
    FirebaseRecyclerAdapter<FinancialReport, ViewHolder> firebaseRecyclerAdapter;
    private RecyclerView recyclerView;

    private String FINANCIAL_CHILD = "Expense";

    private ChildEventListener mChildEventListener;

    List<FinancialReport> financialReportList;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        recyclerView = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        //Initialize Firebase components
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mExpenseReportDatabaseReference = mFirebaseDatabase.getReference().child("Expense");

        myDatabaseReference = FirebaseDatabase.getInstance().getReference();
        myDatabaseReference.keepSynced(true);


         /*firebaseRecyclerAdapter = new FirebaseRecyclerAdapter<FinancialReport, ViewHolder>(
                FinancialReport.class,
                R.layout.activity_expense_reportfragment,
                ViewHolder.class,
                myDatabaseReference.child(FD)) {
            @Override
            protected void onBindViewHolder(ViewHolder holder, int position, FinancialReport model) {

                holder.vSource.setText(model.getSource());
            } */

            /*
            @Override
            public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
                return null;
            }/*
        };


        /*
       for ( int i = 0; i < 20; i++){
            financialReport = new FinancialReport();
        }*/

        /*
        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                FinancialReport financialReport = dataSnapshot.getValue(FinancialReport.class);

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        };
        mExpenseReportDatabaseReference.addChildEventListener(mChildEventListener);
        */

        return recyclerView;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        public TextView vSource;
        public TextView vDate;
        public TextView vAmount;
        public TextView vNotes;

        View view;
        public ViewHolder(View itemView) {
            super(itemView);

            vSource = (TextView)itemView.findViewById(R.id.inc_report_source);
            vDate = (TextView)itemView.findViewById(R.id.inc_report_date);
            vAmount = (TextView)itemView.findViewById(R.id.inc_report_amount);
            vNotes = (TextView)itemView.findViewById(R.id.inc_report_notes);

            view = itemView;
        }
    }
}
