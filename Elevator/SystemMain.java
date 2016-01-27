import java.io.FileNotFoundException;

    public class SystemMain {
				
		public static void main(String[] args) throws FileNotFoundException {
			
			int numElevators = Integer.parseInt(args[0]);            // number of elevators can be used later if we make array of elevators
			int elevatorCap = Integer.parseInt(args[1]);             // capacity
			int Floors = Integer.parseInt(args[2]);                  // number of floors
	        int numCallsInDay = Integer.parseInt(args[3]);           // how many calls
	        int maxTime = Integer.parseInt(args[4]);                 // number of time constants
			int timeConstant = 0;                                    // initialize time constant
			int numDays = Integer.parseInt(args[5]);                 // number of days in a trial
			boolean rushHourSim = Boolean.parseBoolean(args[6]);;    // sets rush hour sim
		    
			
			if (rushHourSim == true){ // true runs rush hour
			    SequenceGeneratorRushHour inputText = new SequenceGeneratorRushHour(); // object for input text file for sims to read
				inputText.createText(maxTime,numCallsInDay,Floors, numDays);           // creates file 
			}
			if(rushHourSim == false){                                      // false runs random sequence
			  Sequencegenerator inputText = new Sequencegenerator();       // object for input text file for sims to read
			  inputText.createText(maxTime,numCallsInDay,Floors, numDays); // creates file 
			}
					
			SimulationValues sim = new SimulationValues();                 // Creates Base Case
			sim.setSimulation(numElevators,elevatorCap,Floors,             // Starts Base Case
					numCallsInDay,maxTime,timeConstant,numDays,sim);
			
			AdvancedSimulationValues AdvancedSim = new AdvancedSimulationValues();  // Creates Base Case
			AdvancedSim.setSimulation(numElevators,elevatorCap,Floors,              // Starts Base Case
					numCallsInDay,maxTime,timeConstant,numDays,AdvancedSim);
			
			
			AIsimulation AIsim = new AIsimulation();                               // Creates AI Case
			AIsim.setSimulation(numElevators,elevatorCap,Floors,                   // Starts AI Case
					numCallsInDay,maxTime,timeConstant,numDays,AIsim);
			
			System.out.println("^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^^");
			System.out.println("Statistics of the Advanced Elevator to the Base Case:");
			Outputs.printPercentImprovement(AdvancedSim.getTotalResults(), sim.getTotalResults(),AdvancedSim.getOverallTotalTime(),sim.getOverallTotalTime()); // Creates output
			System.out.println();
			System.out.println("Statistics of the Intelligent Elevator to the Base Case:");
			Outputs.printPercentImprovement(AIsim.getTotalResults(), sim.getTotalResults(),AIsim.getOverallTotalTime(),sim.getOverallTotalTime()); // Creates output
	}
	}
