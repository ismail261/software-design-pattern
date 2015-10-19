package edu.uta.controller;

import javax.swing.*;

import java.awt.event.*;

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

public class AddAssociation {
	static final long serialVersionUID = 1L;

	private String srcClass = "";
	private String nameOfRelation = "association";
	private String desClass = "";
	public String finalParameter = "";

	public AddAssociation(String srcClass, String nameOfRelation,
			String desClass) {
		// TODO Auto-generated constructor stub
		this.srcClass = srcClass;
		this.nameOfRelation = nameOfRelation;
		this.desClass = desClass;
	}

//	public AddAssociation() {
//		super("Association");
//	}

//	public void actionPerformed(ActionEvent e) {
//	}

	public void add(int row, int column) {
		int srcNum = 0;
		int desNum = 1;
		String classes[] = DomainModel.getInstance().getClasses();
		Gui gui = Gui.getInstance();
		AssociationDialog associationDialog = new AssociationDialog(
				gui.getJFrame(), row, column);
		AssociationOkAction ok = new AssociationOkAction(associationDialog);
		associationDialog.setOkAction(ok);
		associationDialog.setClasses(classes);
		if (classes.length > 1) {
			associationDialog.setName(nameOfRelation);
			associationDialog.setSrcClass(srcClass);
			associationDialog.setDestClass(desClass);
			associationDialog.setSrcRole("");
			associationDialog.setDestRole("");
			associationDialog.setSrcMultiplicity("");
			associationDialog.setDestMultiplicity("");
			associationDialog.display();
		} else {
			JOptionPane
			.showMessageDialog(
					gui.getJFrame(),
					"Not enough classes for a binary\n"
							+ " association. Only binary associations are currently supported.");
			return;
		}
		// Return the final Parameter and action status
		finalParameter = ok.actionStatus + "&" + ok.finalRowContent ;
		// AggregationDialog aggregationDialog = new AggregationDialog(new
		// JFrame());
	}
}
