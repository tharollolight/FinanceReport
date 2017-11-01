package com.example.coelab.financereport;

import android.content.Context;
import android.content.res.Resources;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

import static android.R.attr.value;

public class IncomeReportFragment extends Fragment {

    private FirebaseDatabase mFirebaseDatabase;
    private DatabaseReference mIncomeReportDatabaseReference;
    private ChildEventListener mChildEventListener;

    List<FinancialReport> financialReportList;
    ReportRecyclerAdapter adapter;
    RecyclerView recyclerView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //Initialize Firebase components
        mFirebaseDatabase = FirebaseDatabase.getInstance();
        mIncomeReportDatabaseReference = mFirebaseDatabase.getReference().child("Income");
        recyclerView  = (RecyclerView) inflater.inflate(R.layout.recycler_view, container, false);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        mChildEventListener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                //FinancialReport financialReport = dataSnapshot.getValue(FinancialReport.class);
                //FinancialReport financialReport1 = new FinancialReport();
                Log.i("Ygritte",dataSnapshot.toString());

                financialReportList = new ArrayList<>();
                FinancialReport financialReport = dataSnapshot.getValue(FinancialReport.class);
                financialReportList.add(financialReport);

                /*for(DataSnapshot snapshot: dataSnapshot.getChildren()) {
                    financialReportList.add(snapshot.getValue(FinancialReport.class));
                } */

                adapter = new ReportRecyclerAdapter(recyclerView.getContext(), financialReportList);
                recyclerView.setAdapter(adapter);
               // Log.i("recycler",recyclerView.setAdapter(adapter));


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

        //recyclerView.getContext()
        mIncomeReportDatabaseReference.addChildEventListener(mChildEventListener);


        return recyclerView;
    }


}
