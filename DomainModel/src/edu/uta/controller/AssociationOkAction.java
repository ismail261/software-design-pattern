package edu.uta.controller;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.util.*;
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
public class AssociationOkAction extends OkAction {
   AssociationDialog dialog;
   private int row;
   private int column;
   public String finalRowContent = "";
   public String actionStatus = "";
   
   
   public AssociationOkAction(AssociationDialog dialog) {
      super();
      this.dialog=dialog;
   }
   public void actionPerformed(ActionEvent e) {
	   int row = dialog.getRow();
	   int column = dialog.getColumn();
	   
      String associationName=dialog.getName(); // hhh null pointer exception
      String srcClass=dialog.getSrcClass();
      String destClass=dialog.getDestClass();
      String srcRole=dialog.getSrcRole();
      String destRole=dialog.getDestRole();
      String srcMultiplicity=dialog.getSrcMultiplicity();
      String destMultiplicity=dialog.getDestMultiplicity();
      Association association=new Association(associationName, srcClass,
         destClass, srcRole, destRole, srcMultiplicity, destMultiplicity);
      DomainModel domainModel=DomainModel.getInstance();
      if (mcIndex>=0) {
         DomainModel.getInstance().remove(mcIndex);
         domainModel.getConcepts().trimToSize();
         domainModel.add(mcIndex, association);
      } else
         domainModel.add(association);
      Gui.getInstance().
      highlightAssociation(associationName);
      
      // Adding the Association and Return the output formatted string for gui display
      srcClass = srcClass + "(" + srcRole + ")" + "(" + srcMultiplicity + ")";
      destClass = destClass + "(" + destRole + ")" + "(" + destMultiplicity + ")";
      associationName = "Association" + "(" + associationName + ")";
      
      finalRowContent = srcClass + "&" + associationName + "&" + destClass;
      actionStatus = "Ok";
      
//      Gui.setRelationTableValue(srcClass, row, 0);
//      Gui.setRelationTableValue(associationName, row, 1);
//      Gui.setRelationTableValue(destClass, row, 2);
//      
   }
}
