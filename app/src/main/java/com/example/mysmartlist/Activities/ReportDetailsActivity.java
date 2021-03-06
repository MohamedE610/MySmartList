package com.example.mysmartlist.Activities;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.example.mysmartlist.Models.Reports.ReportData;
import com.example.mysmartlist.R;
import com.example.mysmartlist.Utils.MySharedPreferences;

public class ReportDetailsActivity extends AppCompatActivity {


    TextView msgTextView;
    TextView expensesTextView;
    TextView budgetTextView;
    private ReportData reportData;
    private long averageBudget;
    private double expenses;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        setTitle("التقارير");
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });


        ActionBar actionBar = getSupportActionBar();
        if(actionBar!=null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        msgTextView = (TextView) findViewById(R.id.text_msg);
        expensesTextView = (TextView) findViewById(R.id.text_expenses);
        budgetTextView = (TextView) findViewById(R.id.text_budget);

        Bundle bundle = getIntent().getExtras();
        reportData = new ReportData();

        if (bundle != null)
            reportData = (ReportData) bundle.getSerializable("report");

        MySharedPreferences.setUpMySharedPreferences(this);

        int clientSalary = Integer.valueOf(MySharedPreferences.getUserSetting("clientSalary"));
        String clientBudget = MySharedPreferences.getUserSetting("clientBudget");

        try {
            expenses = Double.valueOf(MySharedPreferences.getUserSetting("last_expenses"));
        } catch (Exception e) {
            expenses = 0;
        }

        averageBudget = 0;

        if (clientBudget.equals("weekly")) {
            averageBudget += clientSalary / 4;
            averageBudget += averageBudget / 4;
        } else if (clientBudget.equals("monthly")) {
            averageBudget = clientSalary / 4;
        }

        expenses += reportData.totalExpenses;
        if (reportData != null && averageBudget == expenses) {
            goodClientReport();
        } else if (reportData != null && averageBudget < expenses) {
            badClientReport();
        } else if (reportData != null && averageBudget > expenses) {
            perfectClientReport();
        }

    }


    private void perfectClientReport() {
        String msgStr = "ممتاز" + "\n" + "لقد قمت بتوفير جزء من الميزانيه قدره " + (averageBudget - expenses) + "";
        String expensesStr = "مصاريفك : " + reportData.totalExpenses + "";
        String budgetStr = "ميزانيتك : " + averageBudget + "";

        MySharedPreferences.setUserSetting("last_expenses", (averageBudget - expenses) + "");

        msgTextView.setText(msgStr);
        expensesTextView.setText(expensesStr);
        budgetTextView.setText(budgetStr);

    }

    private void badClientReport() {
        String msgStr = "احذر " + "\n" + "لقد قمت بتخطى ميزانيتك بمبلغ قدره " + (expenses - averageBudget) + "";
        String expensesStr = "مصاريفك : " + reportData.totalExpenses + "";
        String budgetStr = "ميزانيتك : " + averageBudget + "";
        MySharedPreferences.setUserSetting("last_expenses", (expenses + averageBudget) + "");
        msgTextView.setText(msgStr);
        expensesTextView.setText(expensesStr);
        budgetTextView.setText(budgetStr);
    }

    private void goodClientReport() {
        String msgStr = "جيد" + "\n" + "لقد قمت بصرف الميزانيه الخاصه بك بطريقه جيده " + (averageBudget - expenses) + "";
        String expensesStr = "مصاريفك : " + reportData.totalExpenses + "";
        String budgetStr = "ميزانيتك : " + averageBudget + "";

        MySharedPreferences.setUserSetting("last_expenses", (averageBudget - expenses) + "");
        msgTextView.setText(msgStr);
        expensesTextView.setText(expensesStr);
        budgetTextView.setText(budgetStr);
    }

}
