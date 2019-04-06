import java.io.IOException;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TipShareGui{
	
	private static Stage tipShareWindow = new Stage();
	private static Scene tipShareScene;
	
	private static GridPane gridLayout;
	
	protected static TextField totalTipoutTextfield;
	protected static TextField lunchTipoutTextfield;
	protected static TextField expoHours1Textfield;
	protected static TextField expoHours2Textfield;
	protected static TextField busserHours1Textfield;
	protected static TextField busserHours2Textfield;
	protected static TextField saladHoursTextfield;
	
	protected static ChoiceBox<String>expoEmps1Choicebox;
	protected static ChoiceBox<String>expoEmps2Choicebox;
	protected static ChoiceBox<String>busserEmps1Choicebox;
	protected static ChoiceBox<String>busserEmps2Choicebox;
	protected static ChoiceBox<String>saladEmpsChoicebox;
	
	protected static Button calculate;
	protected static String emptyEmp = "-Empty-";
	
	protected static Label TotalTipoutLabel;
	protected static Label lunchTipoutLabel;
	protected static Label expoLabel;
	protected static Label busserLabel;
	protected static Label saladLabel;
	protected static Label totalHoursLabel;
	protected static Label EmployeeLabel;
	
	public static void setUpGui() {
		
		totalTipoutTextfield = new TextField();
		lunchTipoutTextfield = new TextField();
		expoHours1Textfield = new TextField();
		expoHours2Textfield = new TextField();
		busserHours1Textfield = new TextField();
		busserHours2Textfield = new TextField();
		saladHoursTextfield = new TextField();
		
		expoHours1Textfield.setEditable(false);
		expoHours1Textfield.setText("0");
		
		expoHours2Textfield.setEditable(false);
		expoHours2Textfield.setText("0");
		
		busserHours1Textfield.setEditable(false);
		busserHours1Textfield.setText("0");
		
		busserHours2Textfield.setEditable(false);
		busserHours2Textfield.setText("0");
		
		saladHoursTextfield.setEditable(false);
		saladHoursTextfield.setText("0");
		
		TotalTipoutLabel = new Label("Total Tipout:");
		lunchTipoutLabel = new Label("Lunch Tipout:");
		expoLabel =  new Label("Expo:");
		busserLabel = new Label("Busser:");
		saladLabel = new Label("Salad:");
		totalHoursLabel = new Label("Hours Worked");
		EmployeeLabel = new Label("Employee");
		
		expoEmps1Choicebox = new ChoiceBox<String>();
			expoEmps1Choicebox.getItems().add(emptyEmp);
			addEmployeeNamesToChoiceBox(expoEmps1Choicebox, "Expo");
			expoEmps1Choicebox.getSelectionModel().selectFirst();
			expoEmps1Choicebox.getSelectionModel().selectedItemProperty().addListener(new MyChangeListener(expoEmps1Choicebox, expoHours1Textfield));
		
		expoEmps2Choicebox = new ChoiceBox<>();
			expoEmps2Choicebox.getItems().add(emptyEmp);
			addEmployeeNamesToChoiceBox(expoEmps2Choicebox, "Expo");
			expoEmps2Choicebox.getSelectionModel().selectFirst();
			expoEmps2Choicebox.getSelectionModel().selectedItemProperty().addListener(new MyChangeListener(expoEmps2Choicebox, expoHours2Textfield));
		
		busserEmps1Choicebox = new ChoiceBox<>();
			busserEmps1Choicebox.getItems().add(emptyEmp);
			addEmployeeNamesToChoiceBox(busserEmps1Choicebox, "Busser");
			busserEmps1Choicebox.getSelectionModel().selectFirst();
			busserEmps1Choicebox.getSelectionModel().selectedItemProperty().addListener(new MyChangeListener(busserEmps1Choicebox, busserHours1Textfield));
		
		busserEmps2Choicebox = new ChoiceBox<>();
			busserEmps2Choicebox.getItems().add(emptyEmp);
			addEmployeeNamesToChoiceBox(busserEmps2Choicebox, "Busser");
			busserEmps2Choicebox.getSelectionModel().selectFirst();
			busserEmps2Choicebox.getSelectionModel().selectedItemProperty().addListener(new MyChangeListener(busserEmps2Choicebox, busserHours2Textfield));
		
		saladEmpsChoicebox = new ChoiceBox<>();
			saladEmpsChoicebox.getItems().add(emptyEmp);
			addEmployeeNamesToChoiceBox(saladEmpsChoicebox, "Salad");
			saladEmpsChoicebox.getSelectionModel().selectFirst();
			saladEmpsChoicebox.getSelectionModel().selectedItemProperty().addListener(new MyChangeListener(saladEmpsChoicebox, saladHoursTextfield));
		
		VerifyUserInputFields verifyUserInputObj = new VerifyUserInputFields();
		calculate = new Button("Calculate");
			calculate.setOnAction(e -> verifyUserInputObj.calculate());
		
		gridLayout = new GridPane();
			gridLayout.setMinSize(400,200);
			gridLayout.setPadding(new Insets(10,10,10,10));
			gridLayout.setHgap(10);
			gridLayout.setVgap(10);
		
			gridLayout.add(TotalTipoutLabel, 0, 0);
			gridLayout.add(totalTipoutTextfield, 1, 0);
			gridLayout.add(lunchTipoutLabel, 2, 0);
			gridLayout.add(lunchTipoutTextfield, 3, 0);
			gridLayout.add(EmployeeLabel, 1, 1);
			gridLayout.add(totalHoursLabel, 3, 1);
			gridLayout.add(expoHours1Textfield, 3, 2);
			gridLayout.add(expoHours2Textfield, 3, 3);
			gridLayout.add(busserHours1Textfield, 3, 4);
			gridLayout.add(busserHours2Textfield, 3, 5);
			gridLayout.add(saladHoursTextfield, 3, 6);
			gridLayout.add(expoLabel, 0, 2);
			gridLayout.add(expoEmps1Choicebox, 1, 2);
			gridLayout.add(expoEmps2Choicebox, 1, 3);
			gridLayout.add(busserLabel, 0, 4);
			gridLayout.add(busserEmps1Choicebox, 1, 4);
			gridLayout.add(busserEmps2Choicebox, 1, 5);
			gridLayout.add(saladLabel, 0, 6);
			gridLayout.add(saladEmpsChoicebox, 1, 6);
			gridLayout.add(calculate, 3, 10);
				
		tipShareScene = new Scene(gridLayout, 750, 500);
			tipShareWindow.setTitle("Clark's Fish Camp Tipshare Program");
			tipShareWindow.setScene(tipShareScene);
			tipShareWindow.showAndWait();
	}
	
	public static void addEmployeeNamesToChoiceBox(ChoiceBox<String> theChoiceBox, String theDepartment) {
		
		try {
			
			theChoiceBox.getItems().addAll(ExcelLogic.read(theDepartment));
		
		} catch (InvalidFormatException e1) {
			
			e1.printStackTrace();
		} catch (IOException e1) {
			
			e1.printStackTrace();
		}
		
	}

private static class MyChangeListener implements ChangeListener<String>{
	
	private TextField tf;
	
	MyChangeListener(ChoiceBox<String>cb, TextField tf){
		
		this.tf = tf;
	}
	
	public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
    	
    		if(newValue.equals(emptyEmp)) {
				  
				  tf.setText("0");
	        	  tf.setEditable(false);
	         
	          }else {
	        	  
	        	  tf.setText("");
	        	  tf.setEditable(true);
	          }       
    	}
	}
}	
