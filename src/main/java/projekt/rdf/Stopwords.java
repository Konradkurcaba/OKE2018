package projekt.rdf;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Stopwords {

	public String deleteStopWords(String aText) throws FileNotFoundException, IOException
	{
		
		List<String> stopWordsList = readStopwords();
		for (String stopWord : stopWordsList)
		{
			aText = aText.replace(stopWord," ");
		}
	
		return aText;
		
	}
	
	
	
	private List<String> readStopwords() throws FileNotFoundException, IOException
	{
		List<String> stopWordsList = new ArrayList<>();
		try (BufferedReader br = new BufferedReader(new FileReader("src\\main\\resources\\stopwords.txt"))) {
		    String line;
		    while ((line = br.readLine()) != null) {
		    	stopWordsList.add(line);
		    	}
		}
		return stopWordsList;
		
	}
	
	
	
	
	
}
