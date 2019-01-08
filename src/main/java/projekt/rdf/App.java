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
import org.nlp2rdf.parser.Document;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.application.Application;
/**
 * Hello world!
 *
 */
public class App extends Application {
	
	public static void main(String... args) {
	        launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("/Oke2018Gui.fxml"));
		Parent root = loader.load();
		GuiController controller = loader.getController();
		controller.init();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
	}
}
