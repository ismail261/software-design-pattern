package edu.uta.controller;

import java.util.Set;

import javax.swing.JComponent;

class TextPaneTransferHandler extends StringTransferHandler {
	
	//Bundle up the selected string(s) from the TextArea
    //as a single string with ',' separator, for export.
    protected String exportString(JComponent c) {
    	String exportingString = "1\n";
    	Set<String> keys = getHashMap().keySet();
    	for(String key : keys){
    		exportingString = exportingString + key + "&";
    	}

    	if (exportingString.substring(exportingString.length()-1).equals("&"))
        {
    		exportingString = exportingString.substring(0, exportingString.length()-1);
        }
    	return exportingString;
    }
    
    // No action needs to be done when some string is dropped to TextArea
    protected void importString(JComponent c, String str) {
    	// No big action required.
    }
    
    // Clear the Highlighted words from TextArea
    protected void cleanup(JComponent c, boolean remove) {
    	// No action
    }
}
