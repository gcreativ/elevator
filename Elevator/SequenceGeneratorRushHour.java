
/*
 * Outputs the sequence in the following pattern: "Time, FloorFrom, FloorTo" into the file "SampleSequences.txt"
 * Output is separated by commas'.
 * Sequences are separated by semi-columns.
 * Reads command line arguments in the following order: "UnitOfDay, TrafficOfPeople, #OfFloors, NumberOfSequencesRequiredToprint"
*/
import java.util.Arrays;
import java.util.Random; //importing random number generator
import java.io.*; //importing input/output streams

public class SequenceGeneratorRushHour{
    
    public void createText(int days,int people,int floors, int numSeq) throws FileNotFoundException{
        
        //Input data
        int Day = days; // Input your unit of one day
        int People = people; // Input amount of people traffic per day
        int Floors = floors; // Input amount of floors
        int NumberOfSequences =  numSeq; //the amount of random sequences are generated.
       
      //Outputting time
        File file = new File ("SortedSequences.txt"); // creates a sample output file
        java.io.PrintStream ps = new java.io.PrintStream(file);
        
        if(file.exists()){
            for(int p = 0; p < NumberOfSequences; p++){
            	int[] ApperanceTime = new int[People]; // Time of an individual appearing on a particular floor 
                int[] CallFrom = new int[People]; // Floor on which an individual has appeared 
                int[] CallTo = new int[People]; // Floor to which an individual wants to go
                // new Random generator
                Random rnd = new Random();  
                
                //filling up random choices 
                for(int i = 0; i < People; i++){ 
                    ApperanceTime[i] = rnd.nextInt(Day)+1;
                    CallFrom[i] = 1; 
                    CallTo[i] = rnd.nextInt(Floors)+1;
                }
                
               Arrays.sort(ApperanceTime);
         	
         
                for(int l = 0; l < People; l++){
                	ps.print(ApperanceTime[l] + " ");
                    ps.print(CallFrom[l] + " ");
                    ps.print(CallTo[l]);
                    ps.println("");
                }
            }
                ps.print(" ");
            }
       ps.close();
        }

    }
    