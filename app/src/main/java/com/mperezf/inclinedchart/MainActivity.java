package com.mperezf.inclinedchart;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.FrameLayout;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FrameLayout frameLayout = findViewById(R.id.chartView);

        ChartView chartView = new ChartView(this);
        chartView.setChartData_1(new ChartView.ChartData(40, Color.RED));
        chartView.setChartData_2(new ChartView.ChartData(100, Color.BLUE));
        chartView.setDefaultSpace(ChartView.DEFAULT_SPACE);

        frameLayout.addView(chartView);
    }
}
