package com.ott.onetwotrip;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final EditText editText = findViewById(R.id.input);
        final EditText textToFind = findViewById(R.id.textToFind);
        final Button calculate = findViewById(R.id.calculate);
        final TextView result = findViewById(R.id.result);
        final EditText inputMatrix = findViewById(R.id.inputMatrix);
        final Button calculateMatrix = findViewById(R.id.calculateMatrix);
        final TextView resultMatrix = findViewById(R.id.resultMatrix);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                result.setText(calculate(textToFind.getText().toString(), editText.getText().toString()));
            }
        });
        calculateMatrix.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resultMatrix.setText(calculateMatrix(inputMatrix.getText().toString()));
            }
        });
    }

    @NonNull
    public String calculate(String text, String input) {
        final String[] rows = input.split("\\n");
        final String[] massiveSize = rows[0].split("\\s");
        int m = Integer.parseInt(massiveSize[0]);
        int n = Integer.parseInt(massiveSize[1]);

        return calculateResultString(text, Arrays.copyOfRange(rows, 1, rows.length), m, n);
    }

    @NonNull
    private String calculateResultString(String text, String[] rows, int m, int n) {
        String[] resultLettersWithIndexes = new String[text.length()];
        int findedLettersCount = 0;
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                char letter = rows[i].charAt(j);
                for (int k = 0; k < text.length(); k++) {
                    if (text.charAt(k) == letter && resultLettersWithIndexes[k] == null) {
                        resultLettersWithIndexes[k] = letter + " - (" + i + ", " + j + ")\n";
                        if (++findedLettersCount == text.length())
                            return foldArrayToString(resultLettersWithIndexes);
                        break;
                    }
                }
            }
        }
        return "Impossible";
    }

    @NonNull
    private String calculateMatrix(String input) {
        final String[] rows = input.split("\\n");
        int n = rows[0].length();
        String[] resultArray = new String[n * n];

        int x = (n - 1) / 2;
        int y = (n - 1) / 2;
        int index = 0;

        //первый элемент
        resultArray[index++] = String.valueOf(rows[y].charAt(x));

        //идем влево - вверх, вправо - вниз
        int addVal = 1;
        for (int i = 1; i < n; i++) {
            addVal *= -1;
            for (int j = 0; j < i; j++) {
                resultArray[index++] = String.valueOf(rows[y].charAt(x += addVal));
            }
            for (int j = 0; j < i; j++) {
                resultArray[index++] = String.valueOf(rows[y += addVal].charAt(x));
            }
        }

        //оставшаяся нижняя часть
        for (int j = 0; j < n - 1; j++) {
            resultArray[index++] = String.valueOf(rows[y].charAt(--x));
        }

        return foldArrayToString(resultArray);
    }

    @NonNull
    private String foldArrayToString(String[] resultLettersWithIndexes) {
        StringBuilder sb = new StringBuilder();
        for (String s : resultLettersWithIndexes) {
            sb.append(s);
        }
        return sb.toString();
    }
}
