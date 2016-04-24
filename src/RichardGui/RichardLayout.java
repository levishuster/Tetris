package RichardGui;

import javax.swing.table.AbstractTableModel;

public class RichardLayout extends AbstractTableModel {

    private int[][] values;

    public RichardLayout() {
        values = new int[20][10];
    }

    public int getRowCount() {
        return values.length;
    }

    public int getColumnCount() {
        return values[0].length;
    }

    public Class<?> getColumnClass(int columnIndex) {
        return Integer.class;
    }

    public Object getValueAt(int rowIndex, int columnIndex) {
        return values[rowIndex][columnIndex];
    }

    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        values[rowIndex][columnIndex] = (int) aValue;
        fireTableCellUpdated(rowIndex, columnIndex);
    }
}