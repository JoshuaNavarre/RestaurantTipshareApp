import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ErrorMessages {

	public void invalidInputMessage(String theInput) {
		
		Stage errorWindow = new Stage();
			errorWindow.setTitle("Invalid Input Error");
		
		BorderPane layout = new BorderPane();
		Label invalidInputLabel = new Label();
		
		if(theInput.trim().equals("")) {
			
			invalidInputLabel.setText("Required fields cannot be left blank.");
			layout.setCenter(invalidInputLabel);
		
		}else {
			
			invalidInputLabel.setText(theInput + " is not a valid numerical number.");
			layout.setCenter(invalidInputLabel);	
		}
		
		Scene errorScene = new Scene(layout, 300, 150);
			errorWindow.setScene(errorScene);
			errorWindow.showAndWait();
	}
	
	public void invalidTipshareTotals() {
		
		Stage errorWindow = new Stage();
			errorWindow.setTitle("Tipout Total Error");

		BorderPane layout = new BorderPane();
		Label invalidTipshareTotals = new Label("Lunch tipout cannot be larger than total tipout.");
			layout.setCenter(invalidTipshareTotals);

		Scene errorScene = new Scene(layout, 300, 150);
			errorWindow.setScene(errorScene);
			errorWindow.showAndWait();
	}
	
	public void emptyFieldError() {
		
		Stage errorWindow = new Stage();
			errorWindow.setTitle("Empty Field Error");

		BorderPane layout = new BorderPane();
	
		Label emptyFieldError = new Label("Required fields cannot be left blank");
			layout.setCenter(emptyFieldError);

		Scene errorScene = new Scene(layout, 300, 150);
			errorWindow.setScene(errorScene);
			errorWindow.showAndWait();
	}
	
	public void emptyTimeTextfield() {
		
		Stage errorWindow = new Stage();
			errorWindow.setTitle("Empty Time Field Error");

		BorderPane layout = new BorderPane();
		Label emptyTimeError = new Label("Time fields can not be empty.");
			layout.setCenter(emptyTimeError);
			
		Scene errorScene = new Scene(layout, 300, 150);
			errorWindow.setScene(errorScene);
			errorWindow.showAndWait();
	}
	
public void ExcelExportError() {
		
		Stage errorWindow = new Stage();
			errorWindow.setTitle("Excel Export Error");

		BorderPane layout = new BorderPane();
		Label emptyTimeError = new Label("Could not export to excel");
			layout.setCenter(emptyTimeError);
			
		Scene errorScene = new Scene(layout, 300, 150);
			errorWindow.setScene(errorScene);
			errorWindow.showAndWait();
	}
}
