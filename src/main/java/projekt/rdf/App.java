package projekt.rdf;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.apache.jena.rdf.model.Model;
import org.nlp2rdf.parser.NIFParser;
import org.nlp2rdf.NIF;
import org.nlp2rdf.bean.NIFBean;
import org.nlp2rdf.bean.NIFType;
import org.nlp2rdf.nif20.impl.NIF20;
import org.nlp2rdf.nif21.impl.NIF21;
import org.nlp2rdf.parser.Document;;

/**
 * Hello world!
 *
 */
public class App {
	public static void main(String[] args) throws IOException {

		RdfQueryManager queryManager = new RdfQueryManager();
		System.out.println(queryManager.checkWord("Poland"));
 
	}

	public String getText(File aFile) throws IOException {
		InputStream in = new FileInputStream(aFile);
		String theString = IOUtils.toString(in);
		NIFParser parser = new NIFParser(theString);
		Document document = parser.getDocumentFromNIFString(theString);
		return document.getText();
	}

}
