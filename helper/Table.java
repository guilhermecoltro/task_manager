package helper;

import javax.swing.*;
import javax.swing.table.*;
import java.awt.*;
import java.awt.event.*;

public class Table {

	public static void gen(JTable t, Object[][] r, String[] c){
		class MyTable extends AbstractTableModel{
			private String[] columns;
			private Object[][] data;

			public MyTable(String[] columns, Object[][] data){
				this.columns = columns;
				this.data = data;
			}

			public int getRowCount(){
				return this.data.length;
			}

  			public int getColumnCount(){
  				return this.columns.length;
  			}

  			public String getColumnName(int column){
  				return this.columns[column];
  			}

  			public Object getValueAt(int row, int column){
  				return this.data[row][column];
  			}

			@Override
			public boolean isCellEditable(int row, int column){
				return false;
			}
		}
		class MyCellRenderer extends DefaultTableCellRenderer {  
	        private Color whiteColor = new Color(254, 254, 254);  
	        private Color alternateColor = new Color(204, 204, 204);  
	        private Color selectedColor = new Color(61, 128, 223);  
	  
	        @Override  
	        public Component getTableCellRendererComponent(JTable table,  
	                Object value, boolean selected, boolean focused, int row,  
	                int column) {  
	  
	            super.getTableCellRendererComponent(table, value, selected, focused, row, column);  
	  
	            Color bg;  
	            if (!selected)  
	                bg = (row % 2 == 0 ? alternateColor : whiteColor);  
	            else  
	                bg = selectedColor;  
	  
	            setBackground(bg);  
	            setForeground(selected ? Color.white : Color.black);  
	            return this;  
	        }  
	    }

		t.setModel(new MyTable(c, r));
		t.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		t.setDefaultRenderer(Object.class, new MyCellRenderer());
	}

}