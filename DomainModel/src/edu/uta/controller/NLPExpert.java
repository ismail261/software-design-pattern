package edu.uta.controller;

import java.io.IOException;

import edu.stanford.nlp.tagger.maxent.MaxentTagger;

public class NLPExpert {
	//NLPTagger is a 3rd party tool used in our project, developed by stanford. 
	//It tags text in the form of noun or verd or other domain specific phrases.
	public String tagText(String input) throws ClassNotFoundException, IOException {
		MaxentTagger tagger = new MaxentTagger("english-left3words-distsim.tagger");
		//tags the text
		String taggedText = tagger.tagString(input);
		return taggedText;
	}
}
