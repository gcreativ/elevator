
import java.io.File;
import java.util.Scanner;

public class CreateCalls {
    
    private Scanner sc;                                                // creates scanner
                
    public void openFile(){                                            // opens the file
        try{                                                           // tries to open the file
            sc = new Scanner(new File("SortedSequences.txt"));       
        }
        catch(Exception e){                                            // if file could not be found
            System.out.print("Could not find file!");
        }
    }
    public void readFile(int numCalls,                                 
    		Building building, int numDays){
        for(int i = 0; i <(numDays-1)*building.getCallLength(); i++){  // cycles through old values already used on the text file
    		sc.nextInt();                                              // old time
    		sc.nextInt();                                              // old from floor
    		sc.nextInt();                                              // old to floor
    	}
        for(int i = 0;i <= building.getCallLength()-1;i++){            // takes next values and assigns it to calls and loops for total people
        	
        	
            int time = sc.nextInt();                                   // gets next time
            int floorFrom = sc.nextInt();                              // gets next from floor
            int floorTo = sc.nextInt();                                // gets next to floor
            
            
               Calls person = new Calls();                             // creates call to build
  			   person.PopulateCalls(time,floorFrom,floorTo,0,0);       // takes the values and makes a call
  			   building.setCall(i,person);                             // sets person in the building
            
        }
    }
    public void closeFile(){                                           // closes file
        sc.close();
    }
    
    public static void createCases(int numCalls,
    		Building building, int numDays){                           // actually creates the object and calls methods
    	
        CreateCalls l = new CreateCalls();                             // creates an object to read file
        l.openFile();                                                  // opens the file
        l.readFile(numCalls,building,numDays);                         // reads the fiel and creates calls
        l.closeFile();                                                 // closes file
        
    }
}