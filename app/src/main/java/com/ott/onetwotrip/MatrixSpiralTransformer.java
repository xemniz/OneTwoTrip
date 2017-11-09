package com.ott.onetwotrip;



public class MatrixSpiralTransformer {
    public String transform(MatrixController matrix) {
        int size = matrix.rows();
        if (!MatrixHelper.isSquare(matrix) && !MathHelper.isOdd(size)) {
            throw new IllegalArgumentException("We need square matrix with odd size");
        }

        char[] resultList = new char[size * size];
        int resultListIndex = 0;

        matrix.setCursorToCenter();
        resultList[resultListIndex] = matrix.getCurrentItem();
        resultListIndex++;

        int shiftStepDirection = 1;
        for (int shiftStepsCount = 1; shiftStepsCount < size; shiftStepsCount++) {
            shiftStepDirection *= -1;
            for (int j = 0; j < shiftStepsCount; j++) {
                matrix.shiftCursorHorizontally(shiftStepDirection);
                resultList[resultListIndex] = matrix.getCurrentItem();
                resultListIndex++;
            }
            for (int j = 0; j < shiftStepsCount; j++) {
                matrix.shiftCursorVertically(shiftStepDirection);
                resultList[resultListIndex] = matrix.getCurrentItem();
                resultListIndex++;
            }
        }

        for (int j = 0; j < size - 1; j++) {
            matrix.shiftCursorHorizontally(-1);
            resultList[resultListIndex] = matrix.getCurrentItem();
            resultListIndex++;
        }

        return StringHelper.foldCharArrayToString(resultList);
    }
}
