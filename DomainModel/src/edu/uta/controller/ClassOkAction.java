package edu.uta.controller;

import edu.uta.model.Attribute;
import java.util.List;
import java.awt.event.ActionEvent;
import edu.uta.model.*;
import javax.swing.AbstractAction;

import edu.uta.gui.*;
import java.util.ArrayList;

public class ClassOkAction extends OkAction {
   ClassDialog dialog;
   TableExpert expert = new TableExpert();
   private static final long serialVersionUID=1L;
   public String finalRowContent = "";
   public String actionStatus = "";
   
   public ClassOkAction(ClassDialog dialog) {
      super();
      this.dialog=dialog;
   }
   public void actionPerformed(ActionEvent e) {
	   int row = dialog.getRow();
	   int column = dialog.getColumn();
	   
	   String className = dialog.getName();
	   
	   ClassObj classObj=new ClassObj(className);
	   String attributes[]=dialog.getAttributes();
	   String attr = new String();
	   for (int i=0; i<attributes.length; i++) {
		   Attribute attribute=new Attribute();
		   attribute.setName(attributes[i]);
		   classObj.addAttribute(attribute);
		   attr = attr + attributes[i] + ",";
	   }
	   
	   // Adding the Class and it's corresponding attributed and return the final output to display in gui
	   actionStatus = "Ok";
	   finalRowContent = className + "&" + attr;
	   
	   DomainModel domainModel=DomainModel.getInstance();
	   if (mcIndex>=0) {
		   DomainModel.getInstance().remove(mcIndex);
		   domainModel.getConcepts().trimToSize();
		   domainModel.add(mcIndex, classObj);
	   } else {
		   domainModel.add(classObj);
	   }
	   
   }
//      String className=dialog.getName();
//      updateList();
//      //Gui.getInstance().highlightClass(className);
}
