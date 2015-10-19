package edu.uta.controller;


//import edu.uta.gui.*;

import java.awt.event.ActionEvent;

import javax.swing.*;

import edu.uta.gui.*;
import edu.uta.model.*;


/**
 * <p>Title: Agile Unified Modeler</p>
 *
 * <p>Description: An Integrated Development Environment for OO analysis,
 * design, and more.</p>
 *
 * <p>Copyright: Copyright (c) 2009</p>
 *
 * <p>Company: Atruya Systems, Inc.</p>
 *
 * @author David Kung
 * @version 1.0
 */

public class AddClass extends AbstractAction{
	
	private String classString;
	public String finalParameter = "";
	// private String attribute;
//	private String attributes[] = { "a", "b", "c", "d" };

	public AddClass(String classString) {
		this.classString = classString;
	}
	
	
   public void actionPerformed(ActionEvent e) {
	}

	public void add(int row, int column) {
		// classDialog.setAttributes(attributes);
		Gui gui = Gui.getInstance();
		ClassObj c = new ClassObj(
				(gui.getSelectedText() != null) ? gui.getSelectedText() : "");
		String[] attributes = new String[] {};
		if (DomainModel.getInstance().find(c) != null) {
			ClassObj c1 = (ClassObj) DomainModel.getInstance().find(c);
			attributes = new String[c1.getAttributes().size()];
			for (int i = 0; i < attributes.length; i++) {
				Attribute a = (Attribute) c1.getAttributes().get(i);
				attributes[i] = a.getName();
			}
		}
		ClassDialog classDialog = new ClassDialog(gui.getJFrame(), row, column);
		classDialog.setRow(row);
		classDialog.setColumn(column);
		classDialog.setName(classString);
		ClassOkAction ok = new ClassOkAction(classDialog);
		classDialog.setOkAction(ok);
		classDialog.setName(gui.getSelectedText());
		classDialog.setName(classString);
		classDialog.setAttributes(attributes);
		classDialog.clearAtribute();
		classDialog.display();
		// Return the final output and action status back to controller
		finalParameter = ok.actionStatus + "&" + ok.finalRowContent;
	}
}
