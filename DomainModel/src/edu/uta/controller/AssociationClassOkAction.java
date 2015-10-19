package edu.uta.controller;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.util.*;

import edu.uta.model.*;
import edu.uta.gui.*;

/**
 * <p>
 * Title: Agile Unified Modeler
 * </p>
 *
 * <p>
 * Description: An Integrated Development Environment for OO analysis, design,
 * and more.
 * </p>
 *
 * <p>
 * Copyright: Copyright (c) 2009
 * </p>
 *
 * <p>
 * Company: Atruya Systems, Inc.
 * </p>
 *
 * @author David Kung
 * @version 1.0
 */
public class AssociationClassOkAction extends OkAction {
	AssociationClassDialog dialog;
	public String finalRowContent = "";
	public String actionStatus = "";

	public AssociationClassOkAction(AssociationClassDialog dialog) {
		super();
		this.dialog = dialog;
	}

	public void actionPerformed(ActionEvent e) {
		int row = dialog.getRow();
		int column = dialog.getColumn();
		String associationName = dialog.getName();
		String srcClass = dialog.getSrcClass();
		String destClass = dialog.getDestClass();
		DomainModel domainModel = DomainModel.getInstance();
		if (!domainModel.findAssoc(associationName.replace("_", ""))) {
			JOptionPane.showMessageDialog(new javax.swing.JFrame(),
					"Association not fount. Please check and try again.");
			return;
		}
		if (associationName.charAt(0) != '_')
			associationName = "_" + associationName;
		Association association = new Association(associationName, srcClass,
				destClass, "", "", "", "");
		if (mcIndex >= 0) {
			domainModel.remove(mcIndex);
			domainModel.getConcepts().trimToSize();
			domainModel.add(mcIndex, association);
		} else
			domainModel.add(association);
		// Gui.getInstance().highlightAssociation(associationName);

		associationName = "AssociationClass" + "(" + associationName + ")";
		
		finalRowContent = srcClass + "&" + associationName + "&" + destClass;
	    actionStatus = "Ok";

//		Gui.setRelationTableValue(srcClass, row, 0);
//		Gui.setRelationTableValue(associationName, row, 1);
//		Gui.setRelationTableValue(destClass, row, 2);

	}
}
