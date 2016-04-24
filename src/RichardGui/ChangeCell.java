
package RichardGui;

import java.awt.Color;
import java.awt.Component;

import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;

public class ChangeCell extends DefaultTableCellRenderer {

	/*
	 * whenever we have a block falling down, we can check to see which block it is, or what tetronimo it is. 
	 * This will help us select a color for that block.
	 */
	
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        super.getTableCellRendererComponent(table, "", false, false, row, column);
        setOpaque(true);
        if (value != null) {
            if ((Integer)value == 0) {
                setBackground(Color.WHITE);
            } else if ((Integer)value == 1) {
                setBackground(Color.RED);
            } else if ((Integer)value == 2) {
                setBackground(Color.GREEN);
            } else if ((Integer)value == 3) {
            	// mess with this to see if the color works
                setBackground(Color.GREEN);
            } else if ((Integer)value == 4) {
                setBackground(Color.YELLOW);
            }
        } else {
            setBackground(Color.DARK_GRAY);
        }
        return this;
    }
}
