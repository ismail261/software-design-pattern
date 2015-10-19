package edu.uta.controller;

import javax.swing.AbstractAction;

import java.awt.event.ActionEvent;

import edu.uta.model.*;
import edu.uta.gui.*;

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
public class AddAggregation{
	private String wholeClass;
	private String nameofRelation;
	private String partClass;
	public String finalParameter = "";
	
   public AddAggregation(String wholeClass, String nameOfRelation,
			String partClass) {
	   this.wholeClass = wholeClass;
	   this.nameofRelation = nameOfRelation;
	   this.partClass = partClass;
   }
   public void actionPerformed(ActionEvent e) {
   }
   
   public void add(int row, int colume) {
	   String classes[]=DomainModel.getInstance().getClasses();
	   Gui gui=Gui.getInstance(); //Gui.getInstance();
	   AggregationDialog aggregationDialog=new AggregationDialog(gui.getJFrame(), row, colume);
	   AggregationOkAction ok=new AggregationOkAction(aggregationDialog);
	   aggregationDialog.setOkAction(ok);
	   aggregationDialog.setName(nameofRelation);
	   aggregationDialog.setClasses(classes);
	   if (classes.length>1) {
		   aggregationDialog.setWholeClass(wholeClass);
		   aggregationDialog.setPartClass(partClass);
		   aggregationDialog.setMultiplicity("");
		   aggregationDialog.display();
	   } else {
		   javax.swing.JOptionPane.showMessageDialog(gui.getJFrame(),
				   "Not enough classes for an aggregation relationship.");
		   return;
	   }
	   finalParameter = ok.actionStatus + "&" + ok.finalRowContent ;
	
}
}
