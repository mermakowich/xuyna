package com.example.xuyna;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.github.mikephil.charting.animation.Easing;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText expenseInput;
    private Button addButton;
    private RecyclerView expenseList;
    private ExpenseAdapter expenseAdapter;
    private List<Expense> expenses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        expenseInput = findViewById(R.id.expense_input);
        addButton = findViewById(R.id.add_button);
        expenseList = findViewById(R.id.expense_list);

        expenseAdapter = new ExpenseAdapter(expenses);
        expenseList.setLayoutManager(new LinearLayoutManager(this));
        expenseList.setAdapter(expenseAdapter);

        PieChart mobilityPieChart = findViewById(R.id.mobility_pie_chart);
        mobilityPieChart.setDragDecelerationFrictionCoef(1f);

        ArrayList<PieEntry> mobilityEntries = new ArrayList<>();
        mobilityEntries.add(new PieEntry(75f, "Потрачено"));
        mobilityEntries.add(new PieEntry(25f, "Осталось"));

        PieDataSet mobilityDataSet = new PieDataSet(mobilityEntries, "Label");
        mobilityDataSet.setSliceSpace(5f);
        mobilityDataSet.setSelectionShift(0f);
        mobilityDataSet.setValueTextSize(12f);
        mobilityDataSet.setValueTextColor(Color.TRANSPARENT);

        int[] mobilityColor = {Color.rgb(170, 51, 78), Color.rgb(233, 157, 174)};

        mobilityDataSet.setColors(mobilityColor);

        PieData mobilityData = new PieData(mobilityDataSet);

        mobilityPieChart.setData(mobilityData);
        mobilityPieChart.setUsePercentValues(false);
        mobilityPieChart.setHoleRadius(75f);
        mobilityPieChart.setTransparentCircleRadius(100f);
        mobilityPieChart.getDescription().setEnabled(false);
        mobilityPieChart.setDrawEntryLabels(false);
        mobilityPieChart.getLegend().setEnabled(false);
        mobilityPieChart.setEntryLabelTextSize(20f);
        mobilityPieChart.setDrawCenterText(true);
//        mobilityPieChart.setCenterText("1020");
        mobilityPieChart.setCenterTextSize(50f);
        mobilityPieChart.animateY(1500, Easing.EaseInOutQuad);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText editText = findViewById(R.id.edit_text_input);
                TextView textView = findViewById(R.id.text_view_output);
                editText.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

                    }

                    @Override
                    public void afterTextChanged(Editable editable) {

                    }});
                String expenseDescription = expenseInput.getText().toString();
                if (!expenseDescription.isEmpty()) {
                    Expense expense = new Expense(expenseDescription);
                    expenseInput.setText("");
                }
            }
        });
    }

}