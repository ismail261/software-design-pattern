package org.dm.test;

import static org.junit.Assert.*;

import java.awt.Color;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import org.junit.Test;

import edu.uta.controller.IdentificationController;
import edu.uta.controller.NLPExpert;

public class DomainModelTest {

	@Test
	public void testParseTextNounNN() throws ClassNotFoundException, IOException {
		
		// check noun
		NLPExpert nlp = new NLPExpert();
		assertEquals("user_NN ", nlp.tagText("user"));
	}
	
	@Test
	public void testParseTextNounNNP() throws ClassNotFoundException, IOException {
		
		// check noun
		NLPExpert nlp = new NLPExpert();
		assertEquals("Motown_NNP ", nlp.tagText("Motown"));
	}
	
	@Test
	public void testParseTextNounNNPS() throws ClassNotFoundException, IOException {
		
		// check noun
		NLPExpert nlp = new NLPExpert();
		assertEquals("Americans_NNPS ", nlp.tagText("Americans"));
	}
	
	@Test
	public void testParseTextNounNNS() throws ClassNotFoundException, IOException {
		
		// check noun
		NLPExpert nlp = new NLPExpert();
		assertEquals("undergraduates_NNS ", nlp.tagText("undergraduates"));
	}
	
	@Test
	public void testParseTextVerbVBG() throws ClassNotFoundException, IOException {
		
		// check verb
		NLPExpert nlp = new NLPExpert();
		assertEquals("vending_VBG ", nlp.tagText("vending"));
	}
	
	@Test
	public void testParseTextVerbVB() throws ClassNotFoundException, IOException {
		
		// check verb
		NLPExpert nlp = new NLPExpert();
		assertEquals("ask_VB ", nlp.tagText("ask"));
	}
	
	@Test
	public void testParseTextVerbVBN() throws ClassNotFoundException, IOException {
		
		// check verb
		NLPExpert nlp = new NLPExpert();
		assertEquals("registered_VBN ", nlp.tagText("registered"));
	}
	
	@Test
	public void testParseTextAdjectiveJJ() throws ClassNotFoundException, IOException {
		
		// check verb
		NLPExpert nlp = new NLPExpert();
		assertEquals("regrettable_JJ ", nlp.tagText("regrettable"));
	}
	
	@Test
	public void testParseTextAdjectiveJJR() throws ClassNotFoundException, IOException {
		
		// check verb
		NLPExpert nlp = new NLPExpert();
		assertEquals("cheaper_JJR ", nlp.tagText("cheaper"));
	}
	
	@Test
	public void testParseTextNumeric() throws ClassNotFoundException, IOException {
		
		// check verb
		NLPExpert nlp = new NLPExpert();
		assertEquals("1_CD ", nlp.tagText("1"));
	}
	
	@Test
	public void testNoun() throws ClassNotFoundException, IOException{
		IdentificationController identificationController = new IdentificationController();
    	String actualOutput = "", expectedOutput = "";
    	PrintStream resetBack = System.out;
    	
    	// Steps for Test 1 
    	ByteArrayOutputStream outputPrint1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputPrint1));
        identificationController.colorText("noun_NN");
        actualOutput = outputPrint1.toString();
        expectedOutput = Color.RED.toString();
        assertEquals(expectedOutput, actualOutput);
        System.out.flush();
        System.setOut(resetBack); // Inorder to print the next line in console
	}
	
	@Test
	public void testNounIsNoun() throws ClassNotFoundException, IOException{
		IdentificationController identificationController = new IdentificationController();
    	String actualOutput = "", expectedOutput = "";
    	PrintStream resetBack = System.out;
    	
    	ByteArrayOutputStream outputPrint1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputPrint1));
        identificationController.colorText("noun_NN is_AB noun_NN");
        actualOutput = outputPrint1.toString();
        Color color = new Color(0,128,128);
        expectedOutput = Color.RED.toString()+color.toString()+"\r\n";
        assertEquals(expectedOutput, actualOutput);
        System.out.flush();
        System.setOut(resetBack); // Inorder to print the next line in console
	}
	
	@Test
	public void testXOfY() throws ClassNotFoundException, IOException{
		IdentificationController identificationController = new IdentificationController();
    	String actualOutput = "", expectedOutput = "";
    	PrintStream resetBack = System.out;
    	
    	ByteArrayOutputStream outputPrint1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputPrint1));
        identificationController.colorText("noun_NN of_AB noun_NN");
        actualOutput = outputPrint1.toString();
        Color color = new Color(64,128,128);
        expectedOutput = Color.RED.toString()+color.toString()+"\r\n";
        assertEquals(expectedOutput, actualOutput);
        System.out.flush();
        System.setOut(resetBack); // Inorder to print the next line in console
	}
	
	@Test
	public void testNounContainsNoun() throws ClassNotFoundException, IOException{
		IdentificationController identificationController = new IdentificationController();
    	String actualOutput = "", expectedOutput = "";
    	PrintStream resetBack = System.out;
    	
    	ByteArrayOutputStream outputPrint1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputPrint1));
        identificationController.colorText("noun_NN contains_AB noun_NN");
        actualOutput = outputPrint1.toString();
        Color color = new Color(148,0,211);
        expectedOutput = Color.RED.toString()+color.toString()+"\r\n";
        assertEquals(expectedOutput, actualOutput);
        System.out.flush();
        System.setOut(resetBack); // Inorder to print the next line in console
	}
	
	@Test
	public void testNounContainNoun() throws ClassNotFoundException, IOException{
		IdentificationController identificationController = new IdentificationController();
    	String actualOutput = "", expectedOutput = "";
    	PrintStream resetBack = System.out;
    	
    	ByteArrayOutputStream outputPrint1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputPrint1));
        identificationController.colorText("noun_NN contain_AB noun_NN");
        actualOutput = outputPrint1.toString();
        Color color = new Color(148,0,211);
        expectedOutput = Color.RED.toString()+color.toString()+"\r\n";
        assertEquals(expectedOutput, actualOutput);
        System.out.flush();
        System.setOut(resetBack); // Inorder to print the next line in console
	}
	
	@Test
	public void testNounConsistsNoun() throws ClassNotFoundException, IOException{
		IdentificationController identificationController = new IdentificationController();
    	String actualOutput = "", expectedOutput = "";
    	PrintStream resetBack = System.out;
    	
    	ByteArrayOutputStream outputPrint1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputPrint1));
        identificationController.colorText("noun_NN consists_AB of_AC noun_NN");
        actualOutput = outputPrint1.toString();
        Color color = new Color(199,21,133);
        expectedOutput = Color.RED.toString()+color.toString()+"\r\n";
        assertEquals(expectedOutput, actualOutput);
        System.out.flush();
        System.setOut(resetBack); // Inorder to print the next line in console
	}
	
	@Test
	public void testNounConsistNoun() throws ClassNotFoundException, IOException{
		IdentificationController identificationController = new IdentificationController();
    	String actualOutput = "", expectedOutput = "";
    	PrintStream resetBack = System.out;
    	
    	ByteArrayOutputStream outputPrint1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputPrint1));
        identificationController.colorText("noun_NN consist_AB of_AC noun_NN");
        actualOutput = outputPrint1.toString();
        Color color = new Color(199,21,133);
        expectedOutput = Color.RED.toString()+color.toString()+"\r\n";
        assertEquals(expectedOutput, actualOutput);
        System.out.flush();
        System.setOut(resetBack); // Inorder to print the next line in console
	}
	
	
	@Test
	public void testNounBelongsNoun() throws ClassNotFoundException, IOException{
		IdentificationController identificationController = new IdentificationController();
    	String actualOutput = "", expectedOutput = "";
    	PrintStream resetBack = System.out;
    	
    	ByteArrayOutputStream outputPrint1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputPrint1));
        identificationController.colorText("noun_NN belongs_AB to_AB noun_NN");
        actualOutput = outputPrint1.toString();
        Color color = new Color(139,69,19);
        expectedOutput = Color.RED.toString()+color.toString()+"\r\n";
        assertEquals(expectedOutput, actualOutput);
        System.out.flush();
        System.setOut(resetBack); // Inorder to print the next line in console
	}
	
	@Test
	public void testNounBelongNoun() throws ClassNotFoundException, IOException{
		IdentificationController identificationController = new IdentificationController();
    	String actualOutput = "", expectedOutput = "";
    	PrintStream resetBack = System.out;
    	
    	ByteArrayOutputStream outputPrint1 = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputPrint1));
        identificationController.colorText("noun_NN belong_AB to_AB noun_NN");
        actualOutput = outputPrint1.toString();
        Color color = new Color(139,69,19);
        expectedOutput = Color.RED.toString()+color.toString()+"\r\n";
        assertEquals(expectedOutput, actualOutput);
        System.out.flush();
        System.setOut(resetBack); // Inorder to print the next line in console
	}

}
