import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class TipShareResults extends CalculateDailyTipouts {

	public void displayResults() {
	
	Stage resultsWindow = new Stage();	
		resultsWindow.initModality(Modality.APPLICATION_MODAL);
		resultsWindow.setTitle("TipSharewtf");
		resultsWindow.setMinWidth(250);
			
	TableColumn<Person, String> firstNameColumn = new TableColumn<>("First Name");
		firstNameColumn.setMinWidth(100);
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
	
	TableColumn<Person, String> departmentColumn = new TableColumn<>("Department");
		departmentColumn.setMinWidth(100);
		departmentColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
		
	TableColumn<Person, Double> hoursWorkedColumn = new TableColumn<>("Hours Worked");
		hoursWorkedColumn.setMinWidth(100);
		hoursWorkedColumn.setCellValueFactory(new PropertyValueFactory<>("hoursWorked"));
		
	TableColumn<Person, Double> dailyTipoutColumn = new TableColumn<>("Tipout");
		dailyTipoutColumn.setMinWidth(100);
		dailyTipoutColumn.setCellValueFactory(new PropertyValueFactory<>("dailyTipout"));
	
	TableView<Person> table = new TableView<>();
		table.getColumns().addAll(firstNameColumn, departmentColumn, hoursWorkedColumn, dailyTipoutColumn);
		
	VBox layout = new VBox(10);
		layout.getChildren().add(table);
		layout.setPadding(new Insets(20,20,20,20));
	
	Scene scene = new Scene(layout, 600, 300);
	
		resultsWindow.setScene(scene);
		resultsWindow.showAndWait();

	}	
}
