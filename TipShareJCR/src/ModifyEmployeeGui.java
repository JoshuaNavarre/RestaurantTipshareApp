import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ModifyEmployeeGui extends ModifyEmployeeLogic{
	
		private Stage window;
		private GridPane layout;
		private Scene scene;
		protected ChoiceBox<String>jobChoiceBox;
		protected static ChoiceBox<String>employeeNamesChoiceBox;
		protected ChoiceBox<String>jobTitleChoiceBox;
		private Label empNameLabel;
		private Label jobTitleLabel;
		protected TextField addEmployeeTextfield;
		private Button addEmpButton;
		private Button deleteEmpButton;
			
	public void displayAddEmployeeWindow() {
			
		window = new Stage();
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Add Employee");
			window.setMinWidth(250);
	
		jobTitleChoiceBox = new ChoiceBox<String>();
			jobTitleChoiceBox.getItems().addAll("Expo", "Busser", "Salad");
			jobTitleChoiceBox.getSelectionModel().select("Expo");
		
		empNameLabel = new Label("Employee Name:");
		jobTitleLabel = new Label("Job Title:");
		
		addEmployeeTextfield = new TextField();
			addEmployeeTextfield.setPrefWidth(175);
			
		ModifyEmployeeLogic employeeLogicObj = new ModifyEmployeeLogic();
	
		addEmpButton =  new Button("Add");
			addEmpButton.setOnAction(e -> {
				
					String theName = addEmployeeTextfield.getText();
					String theDept = jobTitleChoiceBox.getValue().toString();
					
				if(isValidName(theName)) {
					
					employeeLogicObj.addEmployeeToExcel(theName, theDept);
					addEmployeeTextfield.clear();
				
				}else {
					
					invalidNameMessage(theName);
				}
			}	
			);
			
		layout = new GridPane();
			layout.setPadding(new Insets(20,20,20,20));
			layout.setMinSize(400,200);
			layout.setPadding(new Insets(10,10,10,10));
			layout.setHgap(10);
			layout.setVgap(10);
		
			layout.add(empNameLabel,0,0);
			layout.add(addEmployeeTextfield, 1, 0);
			layout.add(jobTitleLabel, 0, 1);
			layout.add(jobTitleChoiceBox, 1, 1);
			layout.add(addEmpButton, 0, 5);
	
		scene = new Scene(layout, 600, 300);
			window.setScene(scene);
			window.showAndWait();
		
	}
	public void displayDeleteEmployeeWindow() throws InvalidFormatException, IOException {
		
		window = new Stage();
			window.initModality(Modality.APPLICATION_MODAL);
			window.setTitle("Delete Employee");
			window.setMinWidth(250);
		
		employeeNamesChoiceBox = new ChoiceBox<String>();
			employeeNamesChoiceBox.setPrefWidth(200);
			employeeNamesChoiceBox.getItems().setAll(ExcelLogic.read("Expo"));
			if(employeeNamesChoiceBox.getItems().isEmpty()) {
				
				employeeNamesChoiceBox.setDisable(true);
				
			}else {
				
				employeeNamesChoiceBox.getSelectionModel().selectFirst();
			}
			
		jobChoiceBox = new ChoiceBox<String>();
			jobChoiceBox.setPrefWidth(200);
			jobChoiceBox.getItems().addAll("Expo", "Busser", "Salad");
			jobChoiceBox.getSelectionModel().select("Expo");
			jobChoiceBox.getSelectionModel().selectedItemProperty().addListener(new MyChangeListener(jobChoiceBox, employeeNamesChoiceBox));
		
		empNameLabel = new Label("Employee Name:");
		jobTitleLabel = new Label("Job Title:");
		
		deleteEmpButton =  new Button("Delete");
			deleteEmpButton.setOnAction(e -> {
				
			if(employeeNamesChoiceBox.getItems().isEmpty()) {
						
				invalidNameMessage("");
					
				}else {
								
					String theName = employeeNamesChoiceBox.getValue().toString();
					String theDept = jobChoiceBox.getValue().toString();
						
					deleteEmployeeFromExcell(theName, theDept);
					removeEmptyRowsFromExcell(theDept);
					updateEmpsNameChoicebox(employeeNamesChoiceBox, theDept);
								
				}	
					
					if(employeeNamesChoiceBox.getItems().isEmpty()) {
						
						employeeNamesChoiceBox.setDisable(true);
					
					}else {
						
						employeeNamesChoiceBox.getSelectionModel().selectFirst();
					}
				}	
			);
			
		layout = new GridPane();
			layout.setPadding(new Insets(20,20,20,20));
			layout.setMinSize(400,200);
			layout.setPadding(new Insets(10,10,10,10));
			layout.setHgap(10);
			layout.setVgap(10);
		
			layout.add(jobTitleLabel,0,0);
			layout.add(jobChoiceBox, 1, 0);
			layout.add(empNameLabel, 0, 1);
			layout.add(employeeNamesChoiceBox, 1, 1);
			layout.add(deleteEmpButton, 0, 5);
	
		scene = new Scene(layout, 600, 300);
			window.setScene(scene);
			window.showAndWait();
		
	}	
}
	
	

