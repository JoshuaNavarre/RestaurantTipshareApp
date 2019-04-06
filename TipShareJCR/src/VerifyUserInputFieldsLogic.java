import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javafx.scene.control.TextField;

public class VerifyUserInputFieldsLogic extends TipShareGui{

	public boolean isDoubleOrEmpty(TextField theTextField) {
		
		String theInput = theTextField.getText();
		String regex = "[0-9]*\\.?[0-9]+";
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(theInput);
		
			if(matcher.find() && matcher.group().equals(theInput)) {
			
				return true;
		
			}else {
				
				return false;
			}
		}
	}
