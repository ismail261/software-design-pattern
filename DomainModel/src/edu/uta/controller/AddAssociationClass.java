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

public class AddAssociationClass{
	private String srcClass; 
	private String nameOfRelation;
	private String desClass;
	public String finalParameter = "";
	
   public AddAssociationClass(String srcClass, String nameOfRelation, String desClass) {
	   this.srcClass = srcClass;
	   this.nameOfRelation = nameOfRelation;
	   this.desClass = desClass;
   }
   public void actionPerformed(ActionEvent e) {
   }
   public void add(int row, int column) {
	   String classes[] = DomainModel.getInstance().getClasses();
	   Gui gui=Gui.getInstance();
	   AssociationClassDialog associationClassDialog=new AssociationClassDialog(gui.getJFrame(), row, column);
	   AssociationClassOkAction ok=new AssociationClassOkAction(associationClassDialog);
	   associationClassDialog.setOkAction(ok);
	   associationClassDialog.setName(nameOfRelation);
	   associationClassDialog.setClasses(classes);
	   if (classes.length>1) {
		   associationClassDialog.setSrcClass(srcClass);
		   associationClassDialog.setDestClass(desClass);
		   associationClassDialog.display();
	   } else {
		   JOptionPane.showMessageDialog(gui.getJFrame(), "Not enough classes for a binary\n"+
				   " association class link.");
		   return;
	   }
	   finalParameter = ok.actionStatus + "&" + ok.finalRowContent ;
	
}
}
