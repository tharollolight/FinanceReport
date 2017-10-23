package com.example.coelab.financereport;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import static com.example.coelab.financereport.R.id.toolbar;

public class Reporting extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.activity_reporting, container, false);

        // setting Viewpager for each Tabs
        ViewPager viewPager = (ViewPager) view.findViewById(R.id.viewpager);

        //setupViewpager(viewPager);

        TabLayout tabs = (TabLayout) view.findViewById(R.id.tabs);
        tabs.addTab(tabs.newTab().setText("Detail Report"));
        tabs.addTab(tabs.newTab().setText("Income Report"));
        tabs.addTab(tabs.newTab().setText("Expense Report"));
        tabs.setupWithViewPager(viewPager);

        return view;

    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getActivity().setTitle("Finacial Report");
    }

    /* @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporting);
    } */
}
