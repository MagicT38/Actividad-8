package com.example.testlistview;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNumber1;
    private EditText editTextNumber2;
    private Button btnSum;
    private ListView listViewResults;

    private ArrayList<String> resultsList;
    private ArrayAdapter<String> resultsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editTextNumber1 = findViewById(R.id.editTextNumber1);
        editTextNumber2 = findViewById(R.id.editTextNumber2);
        btnSum = findViewById(R.id.btnSum);
        listViewResults = findViewById(R.id.listViewResults);

        resultsList = new ArrayList<>();
        resultsAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, resultsList);
        listViewResults.setAdapter(resultsAdapter);

        btnSum.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sumNumbers();
            }
        });

        listViewResults.setOnItemClickListener((parent, view, position, id) -> {
            String selectedItem = resultsList.get(position);
            showToast(selectedItem);
        });
    }

    private void sumNumbers() {
        try {
            double number1 = Double.parseDouble(editTextNumber1.getText().toString());
            double number2 = Double.parseDouble(editTextNumber2.getText().toString());

            double result = number1 + number2;

            String resultText = String.format("%.2f + %.2f = %.2f", number1, number2, result);

            resultsList.add(resultText);
            resultsAdapter.notifyDataSetChanged();

        } catch (NumberFormatException e) {
            showToast("Ingresa números válidos");
        }
    }

    private void showToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
