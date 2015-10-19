package edu.uta.controller;


import java.awt.Color;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;



import edu.uta.gui.Gui;

//Helps in autoidentificaiton of domain specific phrases.
public class IdentificationController {
		
	//Calls NLP tagger to tag text and then colors text.
	public String parseText(String input) throws ClassNotFoundException, IOException {
		   
		//Calls NLPExpert
		   NLPExpert nlpTagger = new NLPExpert();
		   //Obtains tag text from NLPExpert
		   String taggedText = nlpTagger.tagText(input);
		   //Calls the color function that colors text based on the tagged text
		   colorText(taggedText);
		   
		return taggedText;	  		  
	   }
	
	//Function to color the tagged text
	public void colorText(String taggedText){
		String arr[] = taggedText.split(" ");
		   Gui.getInstance().getEditorTextPane().setText("");
		   Color color = Color.BLACK;
		   ArrayList<String> arrayList = new ArrayList<String>();
		   /*DefaultListModel listModel = new DefaultListModel();
		   final HashMap<String, String> hashMap = new HashMap<>();*/
	       
		   for (String string : arr) {
			   arrayList.add(string);
		   }
		   
		   
		   Iterator<String> itr = arrayList.iterator();
		   while(itr.hasNext())
		   {
			   	String string=itr.next();
	 			String originalMsg = string.substring(0, string.lastIndexOf("_"));
	 			String tag = string.substring(string.lastIndexOf("_") + 1, string.length());
	 			//Clean the tagged text of special unwanted tags and then based on those tags color the tag
	 			switch(originalMsg)
	 			{
	 				case "-LRB-":
		 				originalMsg = "(";
		 				break;
	 				case "-RRB-":
		 				originalMsg = ")";
		 				break;
	 				case "-LSB-":
		 				originalMsg = "[";
		 				break;
	 				case "-RSB-":
		 				originalMsg = "]";
		 				break;
	 				case "-LCB-":
		 				originalMsg = "}";
		 				break;
	 				case "-RCB-":
		 				originalMsg = "}";
		 				break;
	 			}
	 			switch (tag) {
			   		// noun
			   		case "NN":
			   		case "NMP":
			   		case "NNPS":
			   		case "NNS":
			   			color = Color.RED;
			   			System.out.print(color.toString());
			   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg+" ", color);
			   			//Look for Noun is Noun patter of Noun has noun or Noun contains Noun patters and color accordingly
			   			if(itr.hasNext())
			   			{
			   				String string2=itr.next();
				 			String originalMsg2 = string2.substring(0, string2.lastIndexOf("_"));
				 			String tag2 = string2.substring(string2.lastIndexOf("_") + 1, string2.length());
				 			switch(originalMsg2)
				 			{
				 				case "-LRB-":
					 				originalMsg2 = "(";
					 				break;
				 				case "-RRB-":
					 				originalMsg2 = ")";
					 				break;
				 				case "-LSB-":
					 				originalMsg2 = "[";
					 				break;
				 				case "-RSB-":
					 				originalMsg2 = "]";
					 				break;
				 				case "-LCB-":
					 				originalMsg2 = "}";
					 				break;
				 				case "-RCB-":
					 				originalMsg2 = "}";
					 				break;
				 			}
				 			if(originalMsg2.equals("is") || originalMsg2.equals("of") || originalMsg2.equals("consists") || originalMsg2.equals("contains") || originalMsg2.equals("belongs") || originalMsg2.equals("consist") || originalMsg2.equals("contain") || originalMsg2.equals("belong"))
				 			{
				 				if(itr.hasNext())
				 				{
				 					String string3=itr.next();
						 			String originalMsg3 = string3.substring(0, string3.lastIndexOf("_"));
						 			String tag3 = string3.substring(string3.lastIndexOf("_") + 1, string3.length());
						 			switch(originalMsg3)
						 			{
						 				case "-LRB-":
							 				originalMsg3 = "(";
							 				break;
						 				case "-RRB-":
							 				originalMsg3 = ")";
							 				break;
						 				case "-LSB-":
							 				originalMsg3 = "[";
							 				break;
						 				case "-RSB-":
							 				originalMsg3 = "]";
							 				break;
						 				case "-LCB-":
							 				originalMsg3 = "}";
							 				break;
						 				case "-RCB-":
							 				originalMsg3 = "}";
							 				break;
						 			}
						 			if(tag3.equals("NN") || tag3.equals("NMP") || tag3.equals("NNPS") || tag3.equals("NNS"))
						 			{
						 				if(originalMsg2.equals("is"))
						 				{
							 				color = new Color(0,128,128);
							 				System.out.println(color.toString());
								   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg2+" ", color);
						 				}
						 				else if(originalMsg2.equals("of"))
						 				{
							 				color = new Color(64,128,128);
							 				System.out.println(color.toString());
								   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg2+" ", color);
						 				}
						 				else if(originalMsg2.equals("contains") || originalMsg2.equals("contain"))
						 				{
							 				color = new Color(148,0,211);
							 				System.out.println(color.toString());
								   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg2+" ", color);
						 				}
						 				color = Color.RED;
							   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg3+" ", color);
						 			}
						 			else if(originalMsg3.equals("of") || originalMsg3.equals("to"))
						 			{
						 				if(itr.hasNext())
						 				{
						 					String string4=itr.next();
								 			String originalMsg4 = string4.substring(0, string4.lastIndexOf("_"));
								 			String tag4 = string4.substring(string4.lastIndexOf("_") + 1, string4.length());
								 			switch(originalMsg4)
								 			{
								 				case "-LRB-":
									 				originalMsg4 = "(";
									 				break;
								 				case "-RRB-":
									 				originalMsg4 = ")";
									 				break;
								 				case "-LSB-":
									 				originalMsg4 = "[";
									 				break;
								 				case "-RSB-":
									 				originalMsg4 = "]";
									 				break;
								 				case "-LCB-":
									 				originalMsg4 = "}";
									 				break;
								 				case "-RCB-":
									 				originalMsg4 = "}";
									 				break;
								 			}
								 			if(tag4.equals("NN") || tag4.equals("NMP") || tag4.equals("NNPS") || tag4.equals("NNS"))
								 			{
								 				if((originalMsg2.equals("consists") || originalMsg2.equals("consist")) && originalMsg3.equals("of"))
								 				{
								 					color = new Color(199,21,133);
								 					System.out.println(color.toString());
										   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg2+" ", color);
										   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg3+" ", color);
								 				}
								 				else if((originalMsg2.equals("belongs") || originalMsg2.equals("belong")) && originalMsg3.equals("to"))
								 				{
								 					color = new Color(139,69,19);
								 					System.out.println(color.toString());
										   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg2+" ", color);
										   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg3+" ", color);
								 				}
								 				color = Color.RED;
									   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg4+" ", color);
								 			}
								 			else
								 			{
								 				switch (tag2) {		
							 					// noun
									   			case "NN":
										   		case "NMP":
										   		case "NNPS":
										   		case "NNS":
										   			color = Color.RED;
										   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg2+" ", color);
										   		//verb
										   		case "VB":
										   		case "VBD":
										   		case "VBG":
										   		case "VBN":
										   		case "VBP":
										   		case "VBZ":
										   			color = Color.BLUE;
										   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg2+" ", color);
										   			break;
										   		// numerical and list item
										   		case "CD":
										   		case "LS":
										   			color = new Color(0,128,0);
										   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg2+" ", color);
										   			break;
										   		// adjective
										   		case "JJ":
										   		case "JJS":
										   		case "JJR":
										   			color = new Color(213,106,0);
										   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg2+" ", color);
										   			break;
										   		default:
										   			color = Color.BLACK;
										   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg2+" ", color);
										   			break;
								 				}
								 				switch (tag3) {	
									 				// noun
											   		case "NN":
											   		case "NMP":
											   		case "NNPS":
											   		case "NNS":
											   			color = Color.RED;
											   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg3+" ", color);
											   		//verb
											   		case "VB":
											   		case "VBD":
											   		case "VBG":
											   		case "VBN":
											   		case "VBP":
											   		case "VBZ":
											   			color = Color.BLUE;
											   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg3+" ", color);
											   			break;
											   		// numerical and list item
											   		case "CD":
											   		case "LS":
											   			color = new Color(0,128,0);
											   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg3+" ", color);
											   			break;
											   		// adjective
											   		case "JJ":
											   		case "JJS":
											   		case "JJR":
											   			color = new Color(213,106,0);
											   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg3+" ", color);
											   			break;
											   		default:
											   			color = Color.BLACK;
											   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg3+" ", color);
											   			break;
								 				}
								 				switch (tag4) {	
									 				// noun
											   		case "NN":
											   		case "NMP":
											   		case "NNPS":
											   		case "NNS":
											   			color = Color.RED;
											   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg4+" ", color);
											   		//verb
											   		case "VB":
											   		case "VBD":
											   		case "VBG":
											   		case "VBN":
											   		case "VBP":
											   		case "VBZ":
											   			color = Color.BLUE;
											   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg4+" ", color);
											   			break;
											   		// numerical and list item
											   		case "CD":
											   		case "LS":
											   			color = new Color(0,128,0);
											   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg4+" ", color);
											   			break;
											   		// adjective
											   		case "JJ":
											   		case "JJS":
											   		case "JJR":
											   			color = new Color(213,106,0);
											   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg4+" ", color);
											   			break;
											   		default:
											   			color = Color.BLACK;
											   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg4+" ", color);
											   			break;
								 				}
								 			}
						 				}
						 			}
						 			else
						 			{
						 				switch (tag2) {		
						 					// noun
								   			case "NN":
									   		case "NMP":
									   		case "NNPS":
									   		case "NNS":
									   			color = Color.RED;
									   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg2+" ", color);
									   		//verb
									   		case "VB":
									   		case "VBD":
									   		case "VBG":
									   		case "VBN":
									   		case "VBP":
									   		case "VBZ":
									   			color = Color.BLUE;
									   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg2+" ", color);
									   			break;
									   		// numerical and list item
									   		case "CD":
									   		case "LS":
									   			color = new Color(0,128,0);
									   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg2+" ", color);
									   			break;
									   		// adjective
									   		case "JJ":
									   		case "JJS":
									   		case "JJR":
									   			color = new Color(213,106,0);
									   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg2+" ", color);
									   			break;
									   		default:
									   			color = Color.BLACK;
									   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg2+" ", color);
									   			break;
						 				}
						 				switch (tag3) {	
							 				// noun
									   		case "NN":
									   		case "NMP":
									   		case "NNPS":
									   		case "NNS":
									   			color = Color.RED;
									   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg3+" ", color);
									   		//verb
									   		case "VB":
									   		case "VBD":
									   		case "VBG":
									   		case "VBN":
									   		case "VBP":
									   		case "VBZ":
									   			color = Color.BLUE;
									   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg3+" ", color);
									   			break;
									   		// numerical and list item
									   		case "CD":
									   		case "LS":
									   			color = new Color(0,128,0);
									   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg3+" ", color);
									   			break;
									   		// adjective
									   		case "JJ":
									   		case "JJS":
									   		case "JJR":
									   			color = new Color(213,106,0);
									   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg3+" ", color);
									   			break;
									   		default:
									   			color = Color.BLACK;
									   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg3+" ", color);
									   			break;
						 				}
						 			}
				 				}
				 			}
				 			else
				 			{
				 				switch (tag2) {
						   		// noun
						   		case "NN":
						   		case "NMP":
						   		case "NNPS":
						   		case "NNS":
						   			color = Color.RED;	
						   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg2+" ", color);
						   			break;
						   		//verb
						   		case "VB":
						   		case "VBD":
						   		case "VBG":
						   		case "VBN":
						   		case "VBP":
						   		case "VBZ":
						   			color = Color.BLUE;
						   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg2+" ", color);
						   			break;
						   		// numerical and list item
						   		case "CD":
						   		case "LS":
						   			color = new Color(0,128,0);
						   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg2+" ", color);
						   			break;
						   		// adjective
						   		case "JJ":
						   		case "JJS":
						   		case "JJR":
						   			color = new Color(213,106,0);
						   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg2+" ", color);
						   			break;
						   		default:
						   			color = Color.BLACK;
						   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg2+" ", color);
						   			break;
				 			}
			   			}
			   		}
			   		break;
			   		//verb
			   		case "VB":
			   		case "VBD":
			   		case "VBG":
			   		case "VBN":
			   		case "VBP":
			   		case "VBZ":
			   			color = Color.BLUE;
			   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg+" ", color);
			   			break;
			   		// numerical and list item
			   		case "CD":
			   		case "LS":
			   			color = new Color(0,128,0);
			   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg+" ", color);
			   			break;
			   		// adjective
			   		case "JJ":
			   		case "JJS":
			   		case "JJR":
			   			color = new Color(213,106,0);
			   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg+" ", color);
			   			break;
			   		default:
			   			color = Color.BLACK;
			   			Gui.getInstance().appendToPane(Gui.getInstance().getEditorTextPane(), originalMsg+" ", color);
			   			break;
			   }
				   
	 			
			   //listModel.addElement(originalMsg);
		   }
	}

}
