/*
 * Author: William Gordon, wgordon2013@my.fit.edu
 * Course: CSE 2120, Section 01, Spring 2015
 * Project: Proj 01, Elevator 
 */

import java.util.Collections;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.io.PrintStream;

public class SortingValues {

	public void grabbingValues() throws FileNotFoundException {
		ArrayList<String> sequenceOrganizer = new ArrayList<String>(); //Stores the values from the txt file
		
		String txtFilePath = ""; //Stores the information from the txt file
		
		
		File text  = new File("C:/fill this part in/SampleSequences.txt"); //Accesses the initial unsorted file 
		File newtext = new File("newSampleSequences.txt"); //Creates a new file to store the sorted file
		
		//Initializes the Scanner for the unorganized file
		@SuppressWarnings("resource")
		Scanner reader = new Scanner(text);
		
		//Initializes the Scanner for the organized file
		@SuppressWarnings("resource")
		PrintStream newReader = new PrintStream("newSampleSequences.txt");
		
		//Reads in the information from the initial unorganized file
		txtFilePath = reader.nextLine();
		
		//Uses a delimiter to separate out the string that is read in from the file and stores it in an array
		String [] sequenceGroup = txtFilePath.split(";");
		
		//Adds all the elements from array into an array list
		for(int i = 0; i <= (sequenceGroup.length - 1); i++){
			sequenceOrganizer.add(sequenceGroup[i]);
		}
		
		//Organizes the information from txt file in numerical order for the first set of information
		Collections.sort(sequenceOrganizer);
		
		//Checks if the output file exists and then outputs the information 
		if(newtext.exists()){
			for(int a = 0; a < sequenceOrganizer.size(); a++){
            	newReader.print(sequenceOrganizer.indexOf(a));
                newReader.print(";");
            }
        }

		else{
			System.out.println("Error, no output file.");
		}

		
		
	}

}
