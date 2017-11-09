package com.ott.onetwotrip;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    public static final String RESULT_IMPOSSIBLE = "Impossible";

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
        calculate.setOnClickListener(v -> result.setText(findLettersInMatrixString(textToFind.getText().toString(), editText.getText().toString())));
        calculateMatrix.setOnClickListener(v -> resultMatrix.setText(spiralStringFromMatrix(inputMatrix.getText().toString())));
    }

    @NonNull
    public String findLettersInMatrixString(String text, String input) {
        final String[] rows = input.split("\\n");
        final String[] massiveSize = rows[0].split("\\s");
        int m = Integer.parseInt(massiveSize[0]);
        int n = Integer.parseInt(massiveSize[1]);
        final String[] matrixRows = Arrays.copyOfRange(rows, 1, rows.length);

        MatrixAdapter matrix = MatrixAdapter.Factory.fromRows(matrixRows);

        if (m != matrix.rowsCount() || n != matrix.columnsCount())
            throw new IllegalArgumentException("Wrong matrix size");

        return findLettersInMatrix(text, matrix);
    }

    @NonNull
    private String findLettersInMatrix(String findingText, MatrixAdapter matrix) {
        String[] resultLettersWithIndexes = new String[findingText.length()];
        int findedLettersCount = 0;
        for (int y = 0; y < matrix.rowsCount(); y++) {
            for (int x = 0; x < matrix.columnsCount(); x++) {
                char letter = matrix.getItem(x, y);
                for (int k = 0; k < findingText.length(); k++) {
                    if (findingText.charAt(k) == letter && resultLettersWithIndexes[k] == null) {
                        resultLettersWithIndexes[k] = letter + " - (" + y + ", " + x + ")\n";
                        findedLettersCount++;
                        if (findedLettersCount == findingText.length())
                            return StringHelper.foldArrayToString(resultLettersWithIndexes);
                        break;
                    }
                }
            }
        }
        return RESULT_IMPOSSIBLE;
    }

    @NonNull
    private String spiralStringFromMatrix(String input) {
        return new MatrixSpiralTransformer().transform(MatrixController.fromString(input));
    }
}
