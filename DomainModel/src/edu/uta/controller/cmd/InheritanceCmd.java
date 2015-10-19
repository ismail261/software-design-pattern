package edu.uta.controller.cmd;

import edu.uta.model.*;
import edu.uta.gui.InheritanceDialog;
import edu.uta.controller.InheritanceOkAction;
import javax.swing.JFrame;

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
public class InheritanceCmd extends ActionCmd {
   public InheritanceCmd() {
      super();
   }
   /**
    * execute
    *
    * @todo Implement this edu.uta.controller.cmd.ActionCmd method
    */
   public void execute() {
      Inheritance inh=(Inheritance)mc;
      String classes[]=DomainModel.getInstance().getClasses();
      for(int i=0; i<classes.length; i++) System.out.println("EditListener: "+classes[i]);
    //just use 0, 0 to be arguments. maybe change later
      InheritanceDialog inheritanceDialog=new InheritanceDialog(new JFrame(), 0, 0);
      InheritanceOkAction ok=new InheritanceOkAction(inheritanceDialog);
      ok.setIndex(DomainModel.getInstance().getIndex(mc));
      inheritanceDialog.setOkAction(ok);
      inheritanceDialog.setClasses(classes);
      inheritanceDialog.setBaseClass(inh.getDstClass().getName());
      inheritanceDialog.setDerivedClass(inh.getSrcClass().getName());
      inheritanceDialog.display();
   }
}
