package projekt.rdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class GuiController {

	@FXML
	private TextArea sourceArea;
	
	@FXML
	private TextArea targetArea;
	
	@FXML
	private Button okButton;
	
	
	public void init()
	{
		okButton.setOnAction(event ->{
			
			String source = sourceArea.getText();
			
			NifParser parser = new NifParser();
			String text = parser.getTextFromString(source);
			Stopwords stopwords = new Stopwords();
			try {
				text = stopwords.deleteStopWords(text);
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
			
			targetArea.setText(resultEntities.toString());
			
		});
	}

	
}
