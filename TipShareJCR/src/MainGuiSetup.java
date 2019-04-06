import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;

import javafx.scene.Scene;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class MainGuiSetup {

	
	public static void setupMainGui(Stage primaryStage) throws Exception {
	
	Stage window;
	window = primaryStage;
	window.setTitle("Clark's Fish Camp");
	
	ModifyEmployeeGui employeeModGuiObj = new ModifyEmployeeGui();
	
	Image companyLogo = new Image(new FileInputStream("C:\\Users\\Ashlee\\eclipse-workspace\\TipShareJCR\\logo.png"));
	BackgroundImage backgroundImage = new BackgroundImage(companyLogo,  
        BackgroundRepeat.NO_REPEAT,  
        BackgroundRepeat.NO_REPEAT,  
        BackgroundPosition.CENTER,  
        BackgroundSize.DEFAULT);
	Background background = new Background(backgroundImage);
	
	Menu fileMenu = new Menu("File");
	Menu reportsMenu = new Menu("Reports");
	Menu settingsMenu = new Menu("Settings");
	
	MenuItem tipShareMenuItem =  new MenuItem("Tipshare");
		fileMenu.getItems().add(tipShareMenuItem);
		tipShareMenuItem.setOnAction(e -> TipShareGui.setUpGui());
	
	MenuItem reportsMenuItem = new MenuItem("Daily Report");		
		reportsMenu.getItems().add(reportsMenuItem);
		reportsMenu.setOnAction(e -> {
			try {
				ExcelLogic.generateDailyReport();
			} catch (InvalidFormatException | IOException e2) {
				e2.printStackTrace();
			}
		});
	
	MenuItem addEmployeeMenuItem = new MenuItem("Add Employee");
		settingsMenu.getItems().add((addEmployeeMenuItem));
		addEmployeeMenuItem.setOnAction(e -> employeeModGuiObj.displayAddEmployeeWindow());
	
	MenuItem deleteEmployee = new MenuItem("Delete Employee");
		settingsMenu.getItems().add(deleteEmployee);
		deleteEmployee.setOnAction(e -> {
			try {
				employeeModGuiObj.displayDeleteEmployeeWindow();
			} catch (InvalidFormatException e1) {
				
				e1.printStackTrace();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
		});
			
	MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(fileMenu, reportsMenu, settingsMenu);
	
	BorderPane layout = new BorderPane();
		layout.setTop(menuBar);
		layout.setBackground(background);
	
	Scene scene = new Scene(layout, 750, 500);
	
		window.setScene(scene);
		window.show();	
		
	}

}
	
	

