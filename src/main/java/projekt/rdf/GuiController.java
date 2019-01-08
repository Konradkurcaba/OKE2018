package projekt.rdf;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressIndicator;
import javafx.scene.control.TextArea;

public class GuiController {

	@FXML
	private TextArea sourceArea;
	
	@FXML
	private TextArea targetArea;
	
	@FXML
	private Button okButton;
	
	@FXML
	private ProgressIndicator progressIndicator;
	
	public void init()
	{
		progressIndicator.setVisible(false);
		okButton.setOnAction(event ->{
			progressIndicator.setVisible(true);
			String sourceText = sourceArea.getText();
			
			IdentifyService identifyService = new IdentifyService(sourceText);
			identifyService.setOnSucceeded(success ->{
				targetArea.setText(identifyService.getValue());
				progressIndicator.setVisible(false);
			});
			identifyService.setOnFailed(failed ->{
				
			});
			identifyService.start();
			
		});
	}

	
}
