package com.example.coelab.financereport;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by coeLab on 2017/10/26.
 */

public class ReportRecyclerAdapter extends RecyclerView.Adapter<ReportRecyclerAdapter.MyHolder>{

    List<FinancialReport> financialReportList;
    Context context;

    public ReportRecyclerAdapter(Context context, List<FinancialReport> financialReportList) {
        this.context = context;
        this.financialReportList = financialReportList;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_income_report_fragment, parent, false);
        MyHolder myHolder = new MyHolder(view);
        return myHolder;
    }

    @Override
    public void onBindViewHolder(MyHolder holder, int position) {
        //int myAmount;
        FinancialReport financialReport = financialReportList.get(position);
       // myAmount = Integer.parseInt(financialReport.getAmount());
        holder.vSource.setText(financialReport.getSource());
        holder.vDate.setText(financialReport.getDate());
       // holder.vAmount.setText(financialReport.getAmount());
        holder.vNotes.setText(financialReport.getNotes());

    }

    @Override
    public int getItemCount() {
        return financialReportList == null ? 0 : financialReportList.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        public TextView vSource;
        public TextView vDate;
        public TextView vAmount;
        public TextView vNotes;
        View view;

        public MyHolder(View itemView) {
            super(itemView);

            vSource = (TextView)itemView.findViewById(R.id.inc_report_source);
            vDate = (TextView)itemView.findViewById(R.id.inc_report_date);
            vAmount = (TextView)itemView.findViewById(R.id.inc_report_amount);
            vNotes = (TextView)itemView.findViewById(R.id.inc_report_notes);

            //
            view = itemView;
        }
    }
}
