import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class PopulateDailyTipoutTableView{

	TableView<Person> table;
	Stage resultsWindow;
	TableColumn<Person,String> deptColumn;
	TableColumn<Person,String> firstNameColumn;
	TableColumn<Person,Double> hoursWorkedColumn;
	TableColumn<Person,Double> dailyTipoutColumn;
	VBox layout;

	public void displayResults(ObservableList<Person> resultsList) {
	
	resultsWindow = new Stage();	
		resultsWindow.initModality(Modality.APPLICATION_MODAL);
		resultsWindow.setTitle("TipShare");
		resultsWindow.setMinWidth(250);
	
	deptColumn = new TableColumn<>("Department");
		deptColumn.setMinWidth(100);
		deptColumn.setCellValueFactory(new PropertyValueFactory<>("department"));
	
	firstNameColumn = new TableColumn<>("First Name");
		firstNameColumn.setMinWidth(100);
		firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
		
	hoursWorkedColumn = new TableColumn<>("Hours Worked");
		hoursWorkedColumn.setMinWidth(100);
		hoursWorkedColumn.setCellValueFactory(new PropertyValueFactory<>("hoursWorked"));
		
	dailyTipoutColumn = new TableColumn<>("Daily Tipout");
		dailyTipoutColumn.setMinWidth(100);
		dailyTipoutColumn.setCellValueFactory(new PropertyValueFactory<>("dailyTipout"));
		
	table = new TableView<Person>();
		table.getColumns().addAll(deptColumn, firstNameColumn, hoursWorkedColumn, dailyTipoutColumn);
		table.setItems(resultsList);
		
	layout = new VBox(10);
		layout.getChildren().add(table);
		layout.setPadding(new Insets(20,20,20,20));
	
	Scene scene = new Scene(layout, 600, 300);
	
		resultsWindow.setScene(scene);
		resultsWindow.showAndWait();
	}	
}