package design;

import java.awt.Color;
import javax.swing.JTable;
import java.awt.Component;
import java.awt.Font;
import javax.swing.plaf.nimbus.*;
import javax.swing.table.TableCellRenderer;

public class myTable extends JTable {
	Color gray = new Color(231, 234, 237);
	
	public myTable() {
		getTableHeader().setOpaque(false);
		setForeground(new Color(0, 0, 0));
		setFillsViewportHeight(true);
		
		getTableHeader().setBackground(new Color(32, 136, 203));
		getTableHeader().setForeground(new Color(0, 0, 0));
		getTableHeader().setAlignmentX(CENTER_ALIGNMENT);
		getTableHeader().setAlignmentY(CENTER_ALIGNMENT);
		setRowHeight(20);
		getTableHeader().setFont(new Font("sans-serif", Font.BOLD,12));
		this.setFont(new Font("sans-serif", Font.PLAIN, 12));
	}
	public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
		Component c = super.prepareRenderer(renderer, row, column);

		if (!isRowSelected(row)) {
			c.setBackground(row % 2 == 0 ? getBackground() : gray);
		}

		return c;
	}

}
