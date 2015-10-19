package edu.uta.controller;

import java.util.ArrayList;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.uta.model.Attribute;
import edu.uta.model.ClassObj;
import edu.uta.model.DomainModel;

// Actions of Class-Attribute Transfer Handler
class ClassTableTransferHandler extends StringTransferHandler {
    private int[] rows = null;
    private int addIndex = -1; //Location where items were added
    private int addCount = 0;  //Number of items added.
    private boolean validDrop = false; // Drop Location is valid
    private int sourceLocation = 0;
    private ArrayList<String> classList = new ArrayList<String>();
    
    TableExpert expert = new TableExpert();
    
    // This method will pack the data whenever words are dragged out of Class-Attribute table
    protected String exportString(JComponent c) {
        JTable table = (JTable)c;
        rows = table.getSelectedRows();
        int colCount = table.getColumnCount();
        
        StringBuffer buff = new StringBuffer();
        buff.append("2\n");
        
        for (int i = 0; i < rows.length; i++) {
            for (int j = 0; j < colCount; j++) {
                Object val = table.getValueAt(rows[i], j);
                buff.append(val == null ? "" : val.toString());
                if (j != colCount - 1) {
                    buff.append("&");
                }
            }
            if (i != rows.length - 1) {
                buff.append("\n");
            }
        }
        validDrop=false;
        sourceLocation=0;
        return buff.toString();
    }
    
    // This method will unpack and process the data whenever words are dragged into of Class-Attribute table
    protected void importString(JComponent c, String str) {
        JTable target = (JTable)c;
        DefaultTableModel model = (DefaultTableModel)target.getModel();
        int index = target.getSelectedRow();
        int indexofcol = target.getSelectedColumn();
        int countRow = target.getRowCount();
        int colCount = target.getColumnCount();
        boolean actPerformed = false;
        
        String[] values = str.split("\n");
        addCount = values.length-1;
        sourceLocation=Integer.parseInt(values[0]);
        String inputStream = "";

        // Check the Index is valid or not
        if (index < 0) {
            index = countRow;
        } else {
            index++;
            if (index > countRow) {
                index = countRow;
            }
        }
        // Validate where the data has been dropped, and get the data ready for processing
        addIndex = index;
        switch(sourceLocation) {
	        case 1:
	    		inputStream=values[1];
	    		break;
	        case 3:
	    		int targetColumn = Integer.parseInt(values[1]);
	    		for(int i = 2; i < values.length; i++) {
	    			String[] readLine=values[i].split("&");
	    			if(!readLine[targetColumn].equals("")) {
	    				inputStream = inputStream + readLine[targetColumn];
	    				inputStream = inputStream + "&";
	    			}
	    		}
	    		break;
        	case 2:
        		//Prevent the user from dropping data back on itself.
                //For example, if the user is moving rows #4,#5,#6 and #7 and
                //attempts to insert the rows after row #5, this would
                //be problematic when removing the original rows.
                //So this is not allowed.
                if (rows != null && index >= rows[0] - 1 &&
                      index <= rows[rows.length - 1]) {
                    rows = null;
                    validDrop=false;
                    sourceLocation=0;
                    return;
                }
                
		        for (int i = 1; i < values.length; i++) {
		        	String[] rowValue = values[i].split("&");
		        	expert.insertTableRow2Col(model, index);
		            
		            for(int j=0; j<2; j++) {
	            		if(j<rowValue.length){

			            	expert.updateCellValue(model, rowValue[j], index, j);
	            		}
		            }
		            index++;
		        }
		        validDrop=true;
		        break;
        }
        if(sourceLocation==1 || sourceLocation==3) {
        	// If the data is dropped in Class column
        	if(indexofcol==0) {
	        	if(target.getValueAt(index-1, 0).equals("") && 
	        			target.getValueAt(index-1, 1).equals("")) {
	        		index--;
	        	} else {
	        		expert.insertTableRow2Col(model, index);
	        	}
	        	String[] indivValue=inputStream.split("&");
	    		for(int i = 0; i < indivValue.length; i++) {
	    			boolean isExistClass = classList.contains(indivValue[i]);
	    			if(!isExistClass) {
		    			
	    				// Add Class to Model Concept list
		    			AddClass addclass = new AddClass(indivValue[i]);
						addclass.add(index, 0);
						String outputValue = addclass.finalParameter;
	    				String[] indivOutput = outputValue.split("&");
	    				if(indivOutput[0].equals("Ok") && (!classList.contains(indivOutput[1]))) {
		    				for(int k=1;k<indivOutput.length;k++) {
		    					expert.updateCellValue(model, indivOutput[k], index, k-1);
		    				}
		    				actPerformed = true;
		    				classList.add(indivOutput[1]);
	    				}
		    			if((i != indivValue.length-1) && actPerformed) {
		    				index++;
		    				expert.insertTableRow2Col(model, index);
		    			}
	    			}
	    		}
        	} 
        	// If the words are dropped into Attribute column
        	else if(indexofcol==1) {
        		index--;
        		String sourceClass = expert.getTableVlue(model, index, 0);
        		if((!sourceClass.equals("")) && (classList.contains(sourceClass)==true)){
        			ClassObj cObj = new ClassObj(sourceClass);
        			ClassObj cObj1 = (ClassObj) DomainModel.getInstance().find(cObj);
        			String[] indivValue=inputStream.split("&");
        			for(int i = 0; i < indivValue.length; i++) {
        				if(!indivValue[i].equals("")) {
        					Attribute a = new Attribute();
        					a.setName(indivValue[i]);
        					cObj1.addAttribute(a);
        				}
        			}
        			String descAtt = cObj1.toStrAttributes();
        			expert.updateCellValue(model, descAtt, index, 1);
        		}
        	}
        }
    }
    
    // Cleanup methods once drag and drop is done
    protected void cleanup(JComponent c, boolean remove) {
        JTable source = (JTable)c;
        if (sourceLocation==2 && validDrop && rows != null) {
            DefaultTableModel model =
                 (DefaultTableModel)source.getModel();

            //If we are moving items around in the same table, we
            //need to adjust the rows accordingly, since those
            //after the insertion point have moved.
            if (addCount > 0) {
                for (int i = 0; i < rows.length; i++) {
                    if (rows[i] > addIndex) {
                        rows[i] += addCount;
                    }
                }
            }
            for (int i = rows.length - 1; i >= 0; i--) {
                expert.removeTableRow(model, rows[i]);
            }
        }
        rows = null;
        addCount = 0;
        sourceLocation=0;
        validDrop=false;
        addIndex = -1;
    }
}
