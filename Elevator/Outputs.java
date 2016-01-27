
    import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collections;


	public class Outputs {
	     public static double averageWaitTime(ArrayList<Integer> results){ // Returns average
			double average = 0;
			for (int i = 0; i < results.size(); i++) {
			    average = average + results.get(i);
			}
			average = average/results.size();
	    	 return average;
	     }
	    
	     public static double getLargestValue(ArrayList<Integer> results){// Longest Time
			double largest = 0;
			Collections.sort(results);
			largest = results.get(results.size()-1);
	    	 return largest;
	     }
	    
	     public static int getSmallestValue(ArrayList<Integer> results){ // Shortest time
	 		int smallest = 0;
	 		Collections.sort(results);
	 		smallest = results.get(0);
	     	 return smallest;
	      }
	     
	     public static void printTimes (ArrayList<Integer> results){ // Time on elevator
	    	 System.out.print("Times on the eleveator were : " );
	    	 for (int i = 0; i < results.size(); i++) {
				    int value = results.get(i);
				    System.out.print(value);
				    if(i < results.size()-1){
				    	System.out.print(", " );
				    }
				}
	    	 System.out.println("");
	     }
	     public static double getStandardDeviation(ArrayList<Integer> results){ // returns standard deviation
		 		double mean = averageWaitTime(results);
		 		double variance = 0;
		 		double deviation = 0;
		 		for (int i = 0; i < results.size(); i++) {
				    variance =  variance + ((results.get(i)-mean)*(results.get(i)-mean));
				}
		 		variance = variance/results.size();
		 		deviation = Math.sqrt(variance);		 		
		     	 return deviation;
		      }
	     public static double getPercentImprovement(double improved, double baseCase){ // returns percent 
			double percent = 0;
			percent = 100-(improved/baseCase) * 100;
	    	 return percent;
	    	 
	     }
	     public static double getPercentImprovement(int improved, int baseCase){ // returns percent 
				double percent = 0;
				double improvedDob = improved;
				double baseCaseDob=baseCase;
				percent = (improvedDob/baseCaseDob) * 100;
		    	 return percent;
		    	 
		     }
	     
	     
	     public static void printPercentImprovement(ArrayList<Integer> improved, ArrayList<Integer> baseCase,
	    		 ArrayList<Integer> totalTimeAdvanced,ArrayList<Integer> totalTimeBase) {
	    	 DecimalFormat formatter = new DecimalFormat ("#0.00");
	    	 
	    	 System.out.println("The average wait time was reduced by " + 
	                            formatter.format(getPercentImprovement(averageWaitTime(improved),
	                            averageWaitTime(baseCase))) + " %");
	    	 System.out.println("The longest wait time was reduced by: "  + 
	    			 formatter.format(getPercentImprovement(getLargestValue(improved), 
	    			            getLargestValue(baseCase))) + " %");
	    	 
	    	 System.out.println("The standard deviation was reduced by: "  +
	    			 formatter.format(getPercentImprovement(getStandardDeviation(improved), 
	    			     		 getStandardDeviation(baseCase))) + " %");
	    	 if(averageWaitTime(totalTimeAdvanced) <= averageWaitTime(totalTimeBase)){
	    	 System.out.println("Average wait for an elevator decreased: " + 
	    			 formatter.format(getPercentImprovement(averageWaitTime(totalTimeAdvanced),averageWaitTime(totalTimeBase)) )+ "%");
	    	 }
	    	 if(averageWaitTime(totalTimeAdvanced) > averageWaitTime(totalTimeBase)){
		    	 System.out.println("Average wait for an elevator increased: " + 
		    			 formatter.format(getPercentImprovement(averageWaitTime(totalTimeAdvanced),averageWaitTime(totalTimeBase)) )+ "%");
		    	 }
	     }
	     public static void printStandardOutput (ArrayList<Integer> results,SimulationValues sim, ArrayList<Integer> totalTime ){
	    	 DecimalFormat formatter = new DecimalFormat ("#0.00");
	    	 
	    	    System.out.println("	Total Delivered " + results.size());
	    	    System.out.println("	Percent Delivered " + formatter.format(getPercentImprovement(results.size()
	    	    		                                                         ,sim.getNumCallsInDay())) + "%");
				
				System.out.println("	Average total wait time: " + averageWaitTime(results));
				System.out.println("	The longest wait time: "  + getLargestValue(results));
				System.out.println("	Shortest wait time: " + getSmallestValue(results));
				System.out.println("	The Standard Deviation was: " + getStandardDeviation(results));
				 System.out.println("	Average wait time from appearing to destination: " + averageWaitTime(totalTime));
				System.out.println("");
	     }
	     
	}


