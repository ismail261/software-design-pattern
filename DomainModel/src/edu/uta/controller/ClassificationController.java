package edu.uta.controller;

import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.StringSelection;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.JTextPane;
import javax.swing.TransferHandler;
import javax.swing.table.DefaultTableModel;

import edu.uta.gui.Gui;

public class ClassificationController {
	
	public ClassificationController(){
		// Do Nothing
	}
	
	// It will retrieve all the selected words from GUI
	// Iterator method was planned to implement here in Iteration 3
	public HashMap<String,String> getHashMap(){
		return Gui.getInstance().getHashMap();
	}
	
	// When the words are dropped to Class-Attribute table
	public void DragToClassAttributeTable(JTable table) {
		table.setTransferHandler(new ClassTableTransferHandler());
	}
	
	// When the words are dropped to Class-Relationship table
	public void DragToClassRelationTable(JTable table) {
		table.setTransferHandler(new RelationshipTableTransferHandler());
	}
	
	public void TransferTextAreaAction(JTextPane area) {
		area.setTransferHandler(new TextPaneTransferHandler());
	}
}

// An abstract class which form as a base for all the TransferHandler classed
abstract class StringTransferHandler extends TransferHandler {
    
	// Get the input string bundle from the Hashmap
	ClassificationController dnd = new ClassificationController();
	
	// Import and Export String options are different	
    protected abstract String exportString(JComponent c);
    protected abstract void importString(JComponent c, String str);
    protected abstract void cleanup(JComponent c, boolean remove);
    
    protected Transferable createTransferable(JComponent c) {
        return new StringSelection(exportString(c));
    }
    
    public int getSourceActions(JComponent c) {
        return COPY_OR_MOVE;
    }
    
    public HashMap<String, String> getHashMap(){
    	return dnd.getHashMap();
    }
    
    public boolean importData(JComponent c, Transferable t) {
        if (canImport(c, t.getTransferDataFlavors())) {
            try {
                String str = (String)t.getTransferData(DataFlavor.stringFlavor);
                importString(c, str);
                return true;
            } catch (UnsupportedFlavorException ufe) {
            } catch (IOException ioe) {
            }
        }

        return false;
    }
    
    protected void exportDone(JComponent c, Transferable data, int action) {
        cleanup(c, action == COPY);
    }
    
    public boolean canImport(JComponent c, DataFlavor[] flavors) {
        for (int i = 0; i < flavors.length; i++) {
            if (DataFlavor.stringFlavor.equals(flavors[i])) {
                return true;
            }
        }
        return false;
    }
}
