public class Person {
	
	private String firstName;
	private String department;
	private double hoursWorked;
	private double dailyTipout;
	
	public Person(String department, String firstName, double hoursWorked, double dailyTipout) {
		
		this.department = department;
		this.firstName = firstName;
		this.hoursWorked = hoursWorked;
		this.dailyTipout = dailyTipout;
	}
	
	 public String getFirstName() {
         
		 return firstName;
     }

     public void setFirstName(String firstName) {
         
    	 this.firstName = firstName; 
     }
     
     public String getDepartment() {
         
		 return department;
     }

     public void setDepartment(String department) {
         
    	 this.department = department; 
     }

     public double getHoursWorked() {
         
    	 return hoursWorked;
     }

     public void setHoursWorked(double hoursWorked) {
        
    	 this.hoursWorked = hoursWorked;
     }
     
     public double getDailyTipout() {
         
    	 return dailyTipout;
     }

     public void setDailyTipout(double dailyTipout) {
        
    	 this.dailyTipout = dailyTipout;
     }    
 }

	

