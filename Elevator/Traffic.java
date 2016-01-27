	public class Traffic {
		private static  void addPeopleWaiting(GenericElevator elevator, 
				Building building, int timeConstant,SimulationValues sim){                        // adds people to the elevator
		  	for(int i = 0; i <= building.getCallLength()-1; i++ ){                                // cycles through all calls
				
			 if (building.getActiveCallPosition(i).getFromFloor()==elevator.getElevatorFloor() && 
					 elevator.getNextPosition() >=0){                                             // checks to see if people are waiting outside
				building.getActiveCallPosition(i).setTimeOnElevator(timeConstant);                // sets time on elevator if true
				elevator.addPersonOn(elevator.getNextPosition(),building.getActiveCalls()[i]);    // takes the active call and passes to the elevator
				Calls filler = new Calls();                                                       // creates call to fill active spot
				filler.PopulateCalls(-1, -1,-1,-1, -1);                                           //sets calls to fill active spot
				building.setActiveCallPosition(i,filler);                                         // sets active position inactive
				}
			  }
		  	for(int i = 0; i < elevator.getCapacity (); i++ ){                              // runs through elevator capacity
				if (elevator.getCallsInside(i).getToFloor()==elevator.getElevatorFloor()){  // looks for people to remove if are at their floor
					elevator.getCallsInside(i).setTimeOffElevator(timeConstant);            // sets time off elevator
					sim.setOverallTotalTime(elevator.getCallsInside(i).getTimeOffElevator() // subtracts the time it is when they get off with appear time
					   		                - elevator.getCallsInside(i).getAppearTime());  // stores the value in overallTotalTime
					sim.setResultsTotal(elevator.getCallsInside(i).getTimeOffElevator()     // subtracts the time it is when they get off with time on elevator
					 		            - elevator.getCallsInside(i).getTimeOnElevator());  // stores the value in resultsTotal
					sim.setTotalTime(elevator.getCallsInside(i).getTimeOffElevator()        // subtracts the time it is when they get off with appear time
							         - elevator.getCallsInside(i).getAppearTime());         // stores the value in TotalTime
					sim.setResults(elevator.getCallsInside(i).getTimeOffElevator()          // subtracts the time it is when they get off with appear time
							       - elevator.getCallsInside(i).getTimeOnElevator());       // adds time to results 
					elevator.removePersonOn(i);                                             // removes person
					}
			     }
			  }
							
		    protected static  void generatePeople(int timeConstant, Building building){ // Takes people from Array of calls and adds them active when the timeConstant is hit
		    	for(int i = 0; i <= building.getCallLength()-1; i++ ){                         // search through the calls and breaks when it reaches a time higher than constant
		    		if (timeConstant  == building.getCall(i).getAppearTime() 
		    				&& i <= building.getCallLength()-1){                               // checks to see appear time
			    		building.setActiveCallPosition(i,building.getCall(i));                 // adds person to active array of Calls
						}
				    if(building.getCall(i).getAppearTime() > timeConstant){                    /* Since the calls are sorted it will break after 
				                                                                                  finding a time more than time constant*/
					   break;
				     }
				}
			  }
		     static  void simulation(int maxTime,int timeConstant, Building building,
		    		GenericElevator[] elevator, SimulationValues sim){                         // actual simulation running
		    	for(int i = 0; i <= sim.getMaxTime(); i++){                                    // cycles until the total time for cycle 
		    		sim.setTimeConstant(sim.getTimeConstant() + 1);                            // counts time
		    		Traffic.generatePeople(sim.getTimeConstant(),building);                    // makes people appear through out each time constant
		    		for(int j = 0; j<= elevator.length-1;j++){                                 // deals with each elevator in the elevator array
			    		elevator[j].move(building);                                            // moves elevator
						Traffic.addPeopleWaiting(elevator[j],
								building,sim.getTimeConstant(),sim);                           // adds people waiting in and out
					    }
					}
		    	}
	     }


