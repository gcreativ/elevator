
public class AdvancedSimulationValues extends SimulationValues {
	
	protected void runSimulation(SimulationValues sim){                                    // Starts Advanced Simulation
		System.out.println("**********************************************************");  // separates elevators
		for(int i=1; i <= getNumDays(); i++){                                              // number of days to be looped
			getTotalTime().clear();                                                        // clears the total time on the elevator to start the day
			getResults().clear();                                                          // clears the time on the elevator to start the day
			Building.fillActiveCallsWithEmpties(getBuilding());                            // fills building with empty active calls that might be from the day before
			CreateCalls.createCases( getNumCallsInDay(), 
					getBuilding(),getDayCount());                                          // Creates the call from the text file in main
			intializeArrayElevators();                                                     // intitalizes elevators
			AdvancedTraffic.simulation(getMaxTime(),getTimeConstant(), 
					getBuilding(),getArrayElevators(),sim);                                // runs simulation
			System.out.println("Generic Elevator Day " + i + ":");
		Outputs.printStandardOutput(sim.getResults(),sim,sim.getTotalTime());              // Outputs Advanced results
		setTimeConstant(0);                                                                // sets time constant at zero for next day
		setDayCount(getDayCount()+1);                                                      // increases day count
		
		}
	}
	protected void intializeArrayElevators(){                                  // Creates and sets values for every elevator in the elevator Array
		for(int i = 0; i <= getArrayElevators().length-1;i++){                 // goes through array
			AdvancedElevator elevator = new AdvancedElevator();                // makes an elevator to build on 
			setElevatorsSize(i,elevator);                                      // sets the size of the elevator
			getArrayElevatorsPosition(i).setCap(getElevatorCap());             // sets the capacity
			getArrayElevatorsPosition(i).setPeopleInside (getElevatorCap());   //sets the arrays up as empty inside the elevator
			getArrayElevatorsPosition(i).setActualCap (0);                     // actual capacity
			getArrayElevatorsPosition(i).setElevatorFloor(((i)%
					getNumElevators())*(getFloors()/getNumElevators()));       // spreads the elevator creation
			if ( i % 2 < 1 ){// makes every other elevator same direction      // makes every other elevator same direction
			    getArrayElevatorsPosition(i).setElevatorDirection(true);
			}
			else {
				getArrayElevatorsPosition(i).setElevatorDirection(false);
			}
			getArrayElevatorsPosition(i).fillInsideCallsWithEmpties(elevator); // sets inside calls empty
		}
	}
	protected void clearElevators(){                                           // clears the elevator calls inside
		for(int i = 0; i < getArrayElevators().length-1; i++){                 //loops through all elevators in the array
			getArrayElevatorsPosition(i).fillInsideCallsWithEmpties(getArrayElevatorsPosition(i));
		}
		
	}
}
