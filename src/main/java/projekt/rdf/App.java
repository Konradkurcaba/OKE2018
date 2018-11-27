package projekt.rdf;

import java.io.File;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
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

		NifParser parser = new NifParser();
		String text = parser.getTextFromFile(new File("NIF.xml"));
		Stopwords stopwords = new Stopwords();
		text = stopwords.deleteStopWords(text);
		RdfQueryManager queryManager = new RdfQueryManager();
		
		Arrays.stream(text.split(" "))
		.forEach(word -> {
			if(queryManager.checkWord(word)) System.out.println(word);
		});
 
	}
}
