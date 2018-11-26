package projekt.rdf;

import org.nlp2rdf.parser.Document;
import org.nlp2rdf.parser.NIFParser;

public class NifParser {

	
	public String parse(String aNif)
	{
		NIFParser parser = new NIFParser(aNif);
		Document document = parser.getDocumentFromNIFString(aNif);
		return document.getText();
	}
	
	
	
}
