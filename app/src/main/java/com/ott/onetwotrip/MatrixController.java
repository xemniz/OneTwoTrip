package com.ott.onetwotrip;



public class MatrixController {
    private MatrixAdapter adapter;

    public MatrixController(MatrixAdapter adapter) {
        this.adapter = adapter;
        x = 0;
        y = 0;
    }

    private int x;
    private int y;

    char getCurrentItem() {
        return adapter.getItem(x, y);
    }

    void shiftCursorVertically(int shift) {
        y += shift;
    }

    void shiftCursorHorizontally(int shift) {
        x += shift;
    }

    public int rows() {
        return adapter.rowsCount();
    }

    public int columns() {
        return adapter.columnsCount();
    }

    public void setCursorToCenter() {
        x = (adapter.columnsCount() - 1) / 2;
        y = (adapter.rowsCount() - 1) / 2;
    }

    public static MatrixController fromString(String input) {
        final String[] rows = input.split("\\n");
        return new MatrixController(MatrixAdapter.Factory.fromRows(rows));
    }
}
