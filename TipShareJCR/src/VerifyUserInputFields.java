import java.util.HashMap;
import javafx.collections.ObservableList;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class VerifyUserInputFields extends TipShareGui{
	
	//Hours are calculated with doubles per user request.
	protected double totalTipshare; 
	protected double lunchTipshare;
	protected double expo1Time = 0; 
	protected double expo2Time = 0; 
	protected double busser1Time = 0;
	protected double busser2Time = 0; 
	protected double saladTime = 0;
	protected final double barPercentage = .2758;
	protected final double runnerPercentage = .1724;
	
	ObservableList<Person> resultsList;
	boolean isTextfieldValid;
	boolean isTextfieldDouble;
	
	public void calculate() {
		
	VerifyUserInputFieldsLogic verifyTotal = new VerifyUserInputFieldsLogic();
	ErrorMessages errorMessageObj = new ErrorMessages();
	CalculateDailyTipouts  CalculateDailyTipoutsObj = new CalculateDailyTipouts();
	
	if(!verifyTotal.isDoubleOrEmpty(totalTipoutTextfield)) {
		
		errorMessageObj.invalidInputMessage(totalTipoutTextfield.getText().toString());
		
	}else {
		
		if(!verifyTotal.isDoubleOrEmpty(lunchTipoutTextfield)) {
			
			errorMessageObj.invalidInputMessage(lunchTipoutTextfield.getText().toString());
		
		}else {
			
			totalTipshare = Double.parseDouble(totalTipoutTextfield.getText());
			lunchTipshare = Double.parseDouble(lunchTipoutTextfield.getText());
		
			if(lunchTipshare > totalTipshare) {
				
				errorMessageObj.invalidTipshareTotals();
				
			}else {
				
				HashMap<ChoiceBox<String>, TextField> fieldsMap = new HashMap<ChoiceBox<String>, TextField>();
					fieldsMap.put(expoEmps1Choicebox, expoHours1Textfield);
					fieldsMap.put(expoEmps2Choicebox, expoHours2Textfield);
					fieldsMap.put(busserEmps1Choicebox, busserHours1Textfield);
					fieldsMap.put(busserEmps2Choicebox, busserHours2Textfield);
					fieldsMap.put(saladEmpsChoicebox, saladHoursTextfield);
				
					isTextfieldDouble = true;
				
					fieldsMap.forEach((k, v) -> { if(!verifyTotal.isDoubleOrEmpty(v)) { 
					
						isTextfieldDouble = false;
						errorMessageObj.invalidInputMessage(v.getText().toString());
						
						}
					});	
				
					if(isTextfieldDouble == true) {
						
						expo1Time = Double.parseDouble(expoHours1Textfield.getText().toString());
						expo2Time = Double.parseDouble(expoHours2Textfield.getText().toString());
						busser1Time = Double.parseDouble(busserHours1Textfield.getText().toString());
						busser2Time = Double.parseDouble(busserHours2Textfield.getText().toString());
						saladTime = Double.parseDouble(saladHoursTextfield.getText().toString());
					
						CalculateDailyTipoutsObj.theMath(totalTipshare, lunchTipshare, expo1Time, expo2Time,
												busser1Time,busser2Time,saladTime);		
						}
					}		
			}
		}
	}							
}
