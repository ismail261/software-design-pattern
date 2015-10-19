package edu.uta.controller;

import javax.swing.JComponent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import edu.uta.gui.RelationTypeDialog;

//Actions of Class-Relationship Transfer Handler
class RelationshipTableTransferHandler extends StringTransferHandler {
    private int[] rows = null;
    private int addIndex = -1; //Location where items were added
    private int addCount = 0;  //Number of items added.
    private boolean validDrop = false; // Drop Location is valid
    private int sourceLocation = 0;
    
    TableExpert expert = new TableExpert();

    // This method will pack the data whenever words are dragged out of Class-Relationship table
    protected String exportString(JComponent c) {
        JTable table = (JTable)c;
        rows = table.getSelectedRows();
        int selectedCol = table.getSelectedColumn();
        int colCount = table.getColumnCount();
        
        StringBuffer buff = new StringBuffer();
        buff.append("3\n");
        buff.append(selectedCol + "\n");
        
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
    
    // This method will unpack and process the data whenever words are dragged into of Class-Relationship table
    protected void importString(JComponent c, String str) {
        JTable target = (JTable)c;
        DefaultTableModel model = (DefaultTableModel)target.getModel();
        int index = target.getSelectedRow();
        int indexofcol = target.getSelectedColumn();
        int countRow = target.getRowCount();
        int colCount = target.getColumnCount();
        boolean actPerformed = false;
        
        String[] values = str.split("\n");
        addCount = values.length-2;
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
	        case 2:
	    		for(int i = 1; i < values.length; i++) {
	    			String[] readLine=values[i].split("&");
	    			if(!readLine[0].equals("")) {
	    				inputStream = inputStream + readLine[0];
	    				inputStream = inputStream + "&";
	    			}
	    		}
	    		break;
        	case 3:
        		//Prevent the user from dropping data back on itself.
                //For example, if the user is moving rows #4,#5,#6 and #7 and
                //attempts to insert the rows after row #5, this would
                //be problematic when removing the original rows.
                //So this is not allowed.
                if (rows != null && index >= rows[0] - 1 &&
                      index <= rows[rows.length - 1] && sourceLocation==2) {
                    rows = null;
                    validDrop=false;
                    sourceLocation=0;
                    return;
                }
		        for (int i = 2; i < values.length; i++) {
		        	String[] rowValue = values[i].split("&");
		        	expert.insertTableRow3Col(model, index);
		            
		            for(int j=0; j<3; j++) {
	            		if(j<rowValue.length){
			            	expert.updateCellValue(model, rowValue[j], index, j);
	            		}
		            }
		            index++;
		        }
		        validDrop=true;
		        break;
        }
        if(sourceLocation==1 || sourceLocation==2) {
	        	if(target.getValueAt(index-1, 0).equals("") && 
	        			target.getValueAt(index-1, 1).equals("") 
	        			&& target.getValueAt(index-1, 2).equals("")) {
	        		index--;
	        	} else {
	        		expert.insertTableRow3Col(model, index);
	        	}
	        	// Separate Source Class and Dest Class from the dropped string set
	            String srcClass = expert.getTableVlue(model, index, 0);
	    		String nameOfRelation = expert.getTableVlue(model, index, 1);
	    		String desClass = expert.getTableVlue(model, index, 2);
	        	String[] indivValue=inputStream.split("&");
	    		for(int i = 0; i < indivValue.length; i++) {
		    			switch (indexofcol) {
		    			case 0:
		    				srcClass = indivValue[i];
		    				break;
		    				
		    			case 1:
		    				nameOfRelation = indivValue[i];
		    				break;
		    				
		    			case 2:
		    				desClass = indivValue[i];
		    				break;
		    				
		    			default:
		    				break;
		    			}
		    			// Relationship option dialog is called for each words
		    			RelationTypeDialog relation = new RelationTypeDialog();
		    			String outputValue;
		    			String[] indivOutput;
		    			switch (relation.getOption()) {
		    			// When Association is selected
		    			case 0:
		    				// Add it to the Model Concept list first
		    				AddAssociation asso = new AddAssociation(srcClass, nameOfRelation, desClass);
		    				asso.add(index, indexofcol);
		    				outputValue = asso.finalParameter;
		    				indivOutput = outputValue.split("&");
		    				// If the request to add to model concept list is success
		    				// Only then update that item to the gui table
		    				if(indivOutput[0].equals("Ok")) {
			    				for(int k=1;k<indivOutput.length;k++) {
			    					expert.updateCellValue(model, indivOutput[k], index, k-1);
			    				}
			    				actPerformed = true;
		    				}
		    				break;
		    			// When Aggregation is selected
		    			case 1:
		    				AddAggregation aggr = new AddAggregation(srcClass, nameOfRelation, desClass);
		    				aggr.add(index, indexofcol);
		    				outputValue = aggr.finalParameter;
		    				indivOutput = outputValue.split("&");
		    				if(indivOutput[0].equals("Ok")) {
			    				for(int k=1;k<indivOutput.length;k++) {
			    					expert.updateCellValue(model, indivOutput[k], index, k-1);
			    				}
			    				actPerformed = true;
		    				}
		    				break;
		    			// When Inheritance is selected	
		    			case 2:
		    				AddInheritance inher = new AddInheritance(srcClass, desClass);
		    				inher.add(index, indexofcol);
		    				outputValue = inher.finalParameter;
		    				indivOutput = outputValue.split("&");
		    				if(indivOutput[0].equals("Ok")) {
			    				for(int k=1;k<indivOutput.length;k++) {
			    					expert.updateCellValue(model, indivOutput[k], index, k-1);
			    				}
			    				actPerformed = true;
		    				}
		    				break;
		    			// When Association Class is selected
		    			// Pre-req: Association should have been added
		    			case 3:
		    				AddAssociationClass assocl = new AddAssociationClass(srcClass, nameOfRelation, desClass);
		    				assocl.add(index, indexofcol);
		    				outputValue = assocl.finalParameter;
		    				indivOutput = outputValue.split("&");
		    				if(indivOutput[0].equals("Ok")) {
			    				for(int k=1;k<indivOutput.length;k++) {
			    					expert.updateCellValue(model, indivOutput[k], index, k-1);
			    				}
			    				actPerformed = true;
		    				}
		    				break;

		    			default:
		    				break;
		    			}
		    			if((i != indivValue.length-1) && actPerformed) {
		    				index++;
		    				expert.insertTableRow3Col(model, index);
		    			}
	    		}
        }
    }
    
    // Clean Up process once the drag and drop is done
    protected void cleanup(JComponent c, boolean remove) {
        JTable source = (JTable)c;
        if (sourceLocation==3 && validDrop && rows != null) {
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
