import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class ModifyEmployeeLogic {

	static ExcelLogic excellObj = new ExcelLogic();
	
	public void addEmployeeToExcel(String theName, String theDept) {
		
		try {
			
			excellObj.AddEmployee(theDept, theName);
			nameAddedMessage(theName);
			
		}	
		 catch (InvalidFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	};
	
	public void deleteEmployeeFromExcell(String theName, String theDept) {
		
		try {
			
			excellObj.deleteEmployee(theDept, theName);
			
			
		}	
		 catch (InvalidFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	};
	
	public void removeEmptyRowsFromExcell(String theDept) {
		
		try {
			
			excellObj.removeEmptyRows(theDept);
		}	
		 catch (InvalidFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	};
	
	public void updateEmpsNameChoicebox(ChoiceBox<String> theEmployeeNamesChoiceBox, String theDept) {
		try {
			
			theEmployeeNamesChoiceBox.getItems().clear();

			theEmployeeNamesChoiceBox.getItems().setAll(excellObj.read(theDept)); 
			
		}	
		 catch (InvalidFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	};
	
	public static void updateEmpsNameChoicebox2(ChoiceBox<String> theEmployeeNamesChoiceBox, String theDept) {
		try {

			theEmployeeNamesChoiceBox.getItems().setAll(excellObj.read(theDept)); 			
		}	
		 catch (InvalidFormatException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	};
			  	
		
	public boolean isValidName(String theInput) {
		
		String regex = "^[\\p{L} .'-]+$";
		
		Pattern pattern = Pattern.compile(regex);
		
		Matcher matcher = pattern.matcher(theInput);
		
		if(matcher.find() && matcher.group().equals(theInput)) {
			
			return true;
		
		}else {
			
			return false;
		}
		
	}


	public void invalidNameMessage(String userInput) {
	
	
		Stage errorWindow = new Stage();
			errorWindow.setTitle("Invalid Input Error");
	
			BorderPane layout = new BorderPane();
	
		Label invalidTextfield = new Label();
	
			if(userInput.equals("")) {
		
				invalidTextfield.setText("Textfield cannot not be left blank.");
				layout.setCenter(invalidTextfield);
			}else {
		
				invalidTextfield.setText(userInput + " is not a valid Employee Name.");
				layout.setCenter(invalidTextfield);
			}
	
		Scene errorScene = new Scene(layout, 250, 250);
		errorWindow.setScene(errorScene);
		errorWindow.showAndWait();
	}

	public void nameAddedMessage(String userInput) {
	
		Stage errorWindow = new Stage();
			errorWindow.setTitle("Invalid Input Error");
	
		BorderPane layout = new BorderPane();
		Label invalidDoubleLabel = new Label(userInput + " was successfully added to Employee list");
			layout.setCenter(invalidDoubleLabel);
	
		Scene errorScene = new Scene(layout, 250, 250);
			errorWindow.setScene(errorScene);
			errorWindow.showAndWait();
}

public static class MyChangeListener implements ChangeListener<String>{

	private ChoiceBox<String> jobCb;
	private ChoiceBox<String> empCb;

	MyChangeListener(ChoiceBox<String>jobCb, ChoiceBox<String>empCb){
	
	this.jobCb = jobCb;
	this.empCb = empCb;
}

		public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
		
			updateEmpsNameChoicebox2(empCb, newValue);
					
			if(!empCb.getItems().isEmpty()) {
			
				empCb.setDisable(false);
				empCb.getSelectionModel().selectFirst();
			
			}else {
			
			empCb.setDisable(true);
			
			}		
		}
	}
}
