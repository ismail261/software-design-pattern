package edu.uta.gui;




import javax.swing.JOptionPane;

public class RelationTypeDialog extends JOptionPane{
	Object[] relation = {"Association", "Aggregation", "Inheritance", "Association Class"};
	public int getOption(){
		return RelationTypeDialog.showOptionDialog(null, "Please choose the relationship", "Relationship Type",
				JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, relation, relation[0]);
	}
	
}