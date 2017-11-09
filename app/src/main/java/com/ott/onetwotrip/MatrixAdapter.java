package com.ott.onetwotrip;



interface MatrixAdapter {
    char getItem(int x, int y);

    int rowsCount();

    int columnsCount();

    public static class Factory {
        public static MatrixAdapter fromRows(final String[] rows){
            return new MatrixAdapter() {
                @Override
                public char getItem(int x, int y) {
                    return rows[y].charAt(x);
                }

                @Override
                public int rowsCount() {
                    return rows.length;
                }

                @Override
                public int columnsCount() {
                    return rows[0].length();
                }
            };
        }
    }
}
