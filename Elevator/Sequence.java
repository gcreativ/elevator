import java.util.ArrayList;
import java.util.Collections;
import java.util.Random; //importing random number generator
import java.io.*; //importing input/output streams

public class Sequence {
    public ArrayList<String> ArrayStuff() throws FileNotFoundException {
        //Input data
        int Day = 5;//Integer.parseInt(args[0]); // Input your unit of one day
        int People = 6;//Integer.parseInt(args[1]); // Input amount of people traffic per day
        int Floors = 7;//Integer.parseInt(args[2]); // Input amount of floors
        int NumberOfSequences = 8;//Integer.parseInt(args[3]); //the amount of random sequences are generated.
        
        //Output data
        int[] ApperanceTime = new int[People]; // Time of an individual appearing on a particular floor 
        int[] CallFrom = new int[People]; // Floor on which an individual has appeared 
        int[] CallTo = new int[People]; // Floor to which an individual wants to go
        
        Random rnd = new Random(); // new Random generator 
        
        ArrayList<String> sequenceOrganizer = new ArrayList<String>(); //Stores the values from the txt file
        
        for(int i = 0; i < People; i++){ // looping though the people 
            ApperanceTime[i] = rnd.nextInt(Day)+1;
            CallFrom[i] = rnd.nextInt(Floors)+1; 
            CallTo[i] = rnd.nextInt(Floors)+1;
        }
        
        //Places all the information concerning the people floors and day inside an array list
        for(int p = 0; p < NumberOfSequences; p++){
            for(int l = 0; l < People; l++){
                sequenceOrganizer.add(ApperanceTime[l] + "," + CallFrom[l] + "," + CallTo[l] + ";");
            }
        }
        
        //Organizes the information within the array storing all the values
        Collections.sort(sequenceOrganizer);
        
        File file = new File ("SampleSequences.txt"); // creates a sample output file
        java.io.PrintStream ps = new java.io.PrintStream(file);
        if(file.exists()){
            for(int p = 0; p < sequenceOrganizer.size(); p++){
            	ps.print(sequenceOrganizer.get(p));
            }
        }

        return (sequenceOrganizer);
    }
}