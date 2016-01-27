import java.io.File;
import java.io.FileNotFoundException;


public class Output {
	private String [] timeStorage; //Initializes the array storing the time and other information
	
	//Should be called at the beginning of the program
	public void initializer(int elements){
		timeStorage = new String[elements]; //Sets the size of the array
		return;
	}
	
	//Should be called everytime the elevator is moved
	public void recieveTime(int day, int people, int floor) {
		String allInformation = day + "," + people + "," + floor;
		int time = 14400; //Constant increment time
		boolean found = false; //Check for if a value exists in the array already
		
		//Goes through each element and updates the information if the person has already been logged
		for (String currentElement : timeStorage) {
			if(allInformation.contains(currentElement)){
				//Stores the current time post increment
				time = Integer.valueOf(currentElement.substring(0, currentElement.indexOf(","))) + time;
				//Input that new time
				currentElement =  time + "," + allInformation;
				found = true;
				break;
			}
		}
		
		//Creates a new space for a person if they do not exist in the array yet
		if(found == false){
			allInformation = 0 + "," + day + "," + people + "," + floor;
			for(String currentElement : timeStorage) {
				//Finds the first empty spot in the array and stores the new person
				if(currentElement.isEmpty()){
					currentElement = allInformation;
					break;
				}
			}
		}
		return;
	}
	
	//Should be called at the end of the day after everyone has reached there desired floor
	public void sendToFile() throws FileNotFoundException {
		File file = new File ("TimeText.txt"); // creates a time output file
        java.io.PrintStream output = new java.io.PrintStream(file);
        if(file.exists()){
        	//outputs each time according to how each person appeared
            for(String currentElement : timeStorage){
            	output.print(currentElement.substring(0, currentElement.indexOf(",")) + ", ");
            }
        }
		return;
	}
}
