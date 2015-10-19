package edu.uta.controller;

import javax.swing.table.DefaultTableModel;

// This class deals with all the actions related to JTable
// Insert, Delete, GetValue, etc
public class TableExpert {

	public void updateCellValue(DefaultTableModel model, String value, int row, int column){
		model.setValueAt(value, row, column);
	}
	
	public void removeTableRow(DefaultTableModel model, int rowNo){
		model.removeRow(rowNo);
	}
	
	public void insertTableRow3Col(DefaultTableModel model, int index){
		model.insertRow(index, new String[]{"","",""});
	}
	
	public void insertTableRow2Col(DefaultTableModel model, int index){
		model.insertRow(index, new String[]{"",""});
	}
	
	public String getTableVlue(DefaultTableModel model, int row, int column) {
		String value = (String) model.getValueAt(row, column);
		return value;
	}

}

