package projekt.rdf;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.apache.commons.lang3.ArrayUtils;

public class TextProcessing {

	private static int WINDOW_SIZE = 3;

	public void processText(String aInputText) throws FileNotFoundException, IOException {
		Stopwords stopwords = new Stopwords();
		String deletedStopwordsText = stopwords.deleteStopWords(aInputText);
		String[] splitedText = deletedStopwordsText.split(" ");

		

		HashMap<String, String> resultEntities = new HashMap<>();

		for (int i = WINDOW_SIZE; i > 0; i--) {
			splitedText = filterText(splitedText);
			splitedText = checkText(splitedText, i, resultEntities);
		}

		System.out.println(resultEntities);
	}



	public String[] checkText(String[] aText,int aWindowSize,HashMap<String,String> aFoundEntities)
	{
		boolean isTextEnd = false;
		int windowPosition = 0;
		List<Integer> indexesWhereFoundType = new ArrayList<>();
		while(!isTextEnd)
		{
			boolean isWindowEnd = false;
			int currentWordIndex = windowPosition;
			StringBuilder textToCheck = new StringBuilder();
			RdfQueryManager queryManager = new RdfQueryManager();
			while(!isWindowEnd)
			{
				if(currentWordIndex < aText.length )
				{
					if(currentWordIndex - windowPosition < aWindowSize)
					{
						textToCheck.append(aText[currentWordIndex] + "_" );
						currentWordIndex += 1;
					}
					else
					{
						isWindowEnd = true;
						textToCheck = textToCheck.delete(textToCheck.length()-1, textToCheck.length());
						String phrase = textToCheck.toString();
						Optional<String> foundType = queryManager.checkPhrase(phrase);
						if(foundType.isPresent())
						{
							aFoundEntities.put(phrase, foundType.get());
							for(int i = 1;i<=aWindowSize;i++)
							{
								indexesWhereFoundType.add(currentWordIndex - i);
							}
						}
						windowPosition += 1;
					}
				}else
				{
					isTextEnd = true;
					isWindowEnd = true;
					textToCheck = textToCheck.delete(textToCheck.length()-1, textToCheck.length());
					String phrase = textToCheck.toString();
					Optional<String> foundType = queryManager.checkPhrase(phrase);
					if(foundType.isPresent())
					{
						aFoundEntities.put(phrase, foundType.get());
						for(int i = 0;i<aWindowSize;i++)
						{
							indexesWhereFoundType.add(windowPosition - i);
						}
					}
				}
			}
		
		}
		String[] nonRecognizedText = aText;

		for(Integer index : indexesWhereFoundType)
		{
			nonRecognizedText[index] = "";
		}
		
		return nonRecognizedText;
	
	}

	
	private String[] filterText(String[] splitedText) {
		return (String[]) Arrays.stream(splitedText).filter(word -> {
			if (word.length() > 1)
				return true;
			else
				return false;
		}).map(word -> {
			return word.substring(0, 1).toUpperCase() + word.substring(1).toLowerCase();
		}).toArray(String[]::new);
	}
}
