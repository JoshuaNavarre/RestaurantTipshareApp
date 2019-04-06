import java.io.IOException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import javafx.collections.FXCollections;

public class CalculateDailyTipouts extends VerifyUserInputFields {
	
	private double netTipout;
	private double barTipout;
	private double saladTipout;
	private double expoTipout1;
	private double expoTipout2;
	private double busserTipout1;
	private double busserTipout2;
	private double runnerTipout;
	private double totalHours;
	private double remainingTipout;
	
	public void theMath(double totalTipshare, double lunchTipshare, double expo1Time, double expo2Time,
						double busser1Time, double busser2Time, double saladTime){
		
	DecimalFormat df = new DecimalFormat(".##");
		
		netTipout = (totalTipshare - lunchTipshare);
		barTipout = (netTipout * barPercentage);
		runnerTipout = (netTipout * runnerPercentage);
				
		remainingTipout = netTipout - (barTipout + runnerTipout);
		totalHours = expo1Time + expo2Time + busser1Time + busser2Time + saladTime;
		
		expoTipout1 = (expo1Time / totalHours) * remainingTipout;
		expoTipout2 = (expo2Time / totalHours) * remainingTipout;
		busserTipout1 = (busser1Time / totalHours) * remainingTipout;
		busserTipout2 = (busser2Time / totalHours) * remainingTipout;
		saladTipout = (saladTime / totalHours) * remainingTipout;
			
		Person runners = new Person("Runners","", 0, Double.parseDouble(df.format(runnerTipout)));
		Person bar =  new Person("Bar", "", 0, Double.parseDouble(df.format(barTipout)));
		Person expo1 = new Person("Expo", expoEmps1Choicebox.getValue().toString(), expo1Time, Double.parseDouble(df.format(expoTipout1)));
		Person expo2 = new Person("Expo", expoEmps2Choicebox.getValue().toString(), expo2Time, Double.parseDouble(df.format(expoTipout2)));
		Person busser1 = new Person("Busser", busserEmps1Choicebox.getValue().toString(), busser1Time, Double.parseDouble(df.format(busserTipout1)));
		Person busser2 = new Person("Busser",busserEmps2Choicebox.getValue().toString(), busser2Time, Double.parseDouble(df.format(busserTipout2)));
		Person salad = new Person("Salad", saladEmpsChoicebox.getValue().toString(), saladTime, Double.parseDouble(df.format(saladTipout)));
		
		ArrayList<Person> theEmps = new ArrayList<Person>();
			theEmps.add(runners);
			theEmps.add(bar);
			theEmps.add(expo1);
			theEmps.add(expo2);
			theEmps.add(busser1);
			theEmps.add(busser2);
			theEmps.add(salad);
		
		resultsList = FXCollections.observableArrayList();
		
		for(int i = 0; i < theEmps.size(); i++) {
			
			if(theEmps.get(i).getDailyTipout() > 0) {
				
				resultsList.add(theEmps.get(i));
			}	
		}	
		
		ExcelLogic excelObj = new ExcelLogic();
		ErrorMessages errorMessageObj = new ErrorMessages();
		
		try {
			excelObj.exportDailyTipshare(resultsList);
		} catch (InvalidFormatException e) {
			errorMessageObj.ExcelExportError();
		} catch (IOException e) {
			errorMessageObj.ExcelExportError();
		}
		
		PopulateDailyTipoutTableView tableViewObj = new PopulateDailyTipoutTableView();
			tableViewObj.displayResults(resultsList);
			
	}
}
