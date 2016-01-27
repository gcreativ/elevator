


	public class AdvancedTraffic extends Traffic {
		final static  void addPeopleWaiting(GenericElevator elevator, Building building, int timeConstant,SimulationValues sim){ // need active calls
		  	for(int i = 0; i <= building.getCallLength()-1; i++ ){ // cycles through all calls
				
			 if (building.getActiveCallPosition(i).getFromFloor()==elevator.getElevatorFloor() && 
					 elevator.getNextPosition() >=0  && building.getActiveCallPosition(i).getDirectionTravel() == elevator.getDirection()){ // checks to see if people are waiting outside
				building.getActiveCallPosition(i).setTimeOnElevator(timeConstant); // sets time on elevator if true
				elevator.addPersonOn(elevator.getNextPosition(),building.getActiveCalls()[i]); // takes the active call and passes to the elevator
				Calls filler = new Calls(); // creates call to fill active spot
				filler.PopulateCalls(-1, -1,-1,-1, -1); //sets calls to fill active spot
				building.setActiveCallPosition(i,filler); //sets spot with filler which cannot be reached by elevator;
				}
			  }
			for(int i = 0; i < elevator.getCapacity (); i++ ){ // runs through elevator capacity
				if (elevator.getCallsInside(i).getToFloor()==elevator.getElevatorFloor()){ // adds people on if they are waiting
				    elevator.getCallsInside(i).setTimeOffElevator(timeConstant); // sets time off elevator
				    int timeOff = elevator.getCallsInside(i).getTimeOffElevator() - elevator.getCallsInside(i).getTimeOnElevator(); // gets total time on
				    sim.setOverallTotalTime(elevator.getCallsInside(i).getTimeOffElevator() - elevator.getCallsInside(i).getAppearTime());
				    sim.setResultsTotal(elevator.getCallsInside(i).getTimeOffElevator() - elevator.getCallsInside(i).getTimeOnElevator());
				    sim.setTotalTime(elevator.getCallsInside(i).getTimeOffElevator() - elevator.getCallsInside(i).getAppearTime());
				    sim.setResults(elevator.getCallsInside(i).getTimeOffElevator() - elevator.getCallsInside(i).getTimeOnElevator());  // adds time to results array
				    elevator.removePersonOn(i); // removes person
				    }
			     }
			  }
							
		    
		    static  void simulation(int maxTime,int timeConstant, Building building,
		    		                GenericElevator[] elevator, SimulationValues sim){ // actual simulation running
		    	for(int i = 0; i <= sim.getMaxTime(); i++){ // cycles until the total time for cycle 
		    		sim.setTimeConstant(sim.getTimeConstant() + 1); // counts time
		    		AdvancedTraffic.generatePeople(sim.getTimeConstant(),building); // makes people appear through out each time constant
		    		  for(int j = 0; j<= elevator.length-1;j++){ // deals with each elevator in the elevator array
			    		elevator[j].move(building); // moves elevator
						AdvancedTraffic.addPeopleWaiting(elevator[j], building,sim.getTimeConstant(),sim); // adds people waiting in and out
					    }
		    	
		    }
	}
	}


