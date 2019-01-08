package projekt.rdf;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.concurrent.Service;
import javafx.concurrent.Task;

public class IdentifyService extends Service<String> {

	private String source;
	
	public IdentifyService(String aSource) {
		source = aSource;
	}
	
	
	
	@Override
	protected Task<String> createTask() {
		return new Task() {

			@Override
			protected Object call() throws Exception {
				NifParser parser = new NifParser();
				String rawText = parser.getTextFromString(source);
				Stopwords stopwords = new Stopwords();
				String text = null;
				try {
					text = stopwords.deleteStopWords(rawText);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				RdfQueryManager queryManager = new RdfQueryManager();
				
				TextProcessing processing = new TextProcessing();
				HashMap<String, String> resultEntities = null;
				try {
					resultEntities = processing.processText(text);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				StringBuilder builder = new StringBuilder();
				builder.append(source + "\r\n");
				
				rawText = rawText.toLowerCase();
				
				Pattern pattern = Pattern.compile("<.*[0-9]>");
				Matcher matcher = pattern.matcher(source);
				String taskLink = "";
				if (matcher.find())
				{
				    taskLink = matcher.group();
				}
				Pattern charPattern = Pattern.compile("char=[0-9]*,[0-9]*");
				Matcher charMatcher = charPattern.matcher(taskLink);
				String charSection = "";
				if (charMatcher.find())
				{
				    charSection = charMatcher.group();
				}	
				for(Map.Entry<String, String> pair : resultEntities.entrySet())
				{
					int start = rawText.indexOf(pair.getKey().toLowerCase().replace("_", " "));
					int stop = start + pair.getKey().length();
					builder.append(taskLink.replaceAll(charSection, "char="+ start + "," + stop) + "\r\n");
					builder.append("        a                     nif:RFC5147String , nif:String , nif:Context ;\r\n");
					builder.append("        nif:anchorOf           \"" + pair.getKey() + "\"@en ;\r\n") ;
			        builder.append("        nif:beginIndex         \"" + start +"\"^^xsd:nonNegativeInteger ;\r\n");
			        builder.append("        nif:endIndex           \"" + stop + "\"^^xsd:nonNegativeInteger ;\r\n");
			        builder.append("        itsrdf:taIdentRef     dbpedia:"+ pair.getKey() + " .\r\n");
				}
				
				return builder.toString();
				
			}
			
		};
	}

}
