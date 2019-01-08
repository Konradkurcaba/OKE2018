package projekt.rdf;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.apache.commons.io.IOUtils;
import org.nlp2rdf.parser.Document;
import org.nlp2rdf.parser.NIFParser;

public class NifParser {

	public String parse(String aNif) {
		NIFParser parser = new NIFParser(aNif);
		Document document = parser.getDocumentFromNIFString(aNif);
		return document.getText();
	}

	public String getTextFromFile(File aFile) throws IOException {

		InputStream in = new FileInputStream(aFile);
		String theString = IOUtils.toString(in);
		NIFParser parser = new NIFParser(theString);
		Document document = parser.getDocumentFromNIFString(theString);
		return document.getText();
	}
	
	public String getTextFromString(String aText)
	{
		NIFParser parser = new NIFParser(aText);
		Document document = parser.getDocumentFromNIFString(aText);
		return document.getText();
	}

}
