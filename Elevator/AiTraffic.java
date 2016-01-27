
public class AiTraffic extends Traffic {
	final static  void addPeopleWaiting(GenericElevator elevator,
			Building building, int timeConstant,AIsimulation sim){                          // adds people to the elevator
		
	  	for(int i = 0; i <= building.getCallLength()-1; i++ ){                              // cycles through all calls
	  		if (building.getActiveCallPosition(i).getFromFloor()==elevator.getElevatorFloor() 
	  		&& elevator.getNextPosition() >=0  
	  		&& building.getActiveCallPosition(i).getDirectionTravel()                  
	  		== elevator.getDirection()){                                                   /* checks to see if people are waiting outside in the right direction 
		  		                                                                              and can be added if they are getting on*/
	  			if(sim.getTimeConstant() <= sim.getMaxTime()/3){                           // Morning value at index of floor is incremented
	  				sim.getDay().setMorningPosition(building.getActiveCallPosition(i).getFromFloor(), 
	  						sim.getDay().getMorningPos(building.getActiveCallPosition(i).getFromFloor())+1);
	  				}
			    else if (sim.getMaxTime()/3< sim.getTimeConstant() 
			    		&& sim.getTimeConstant() <= (sim.getMaxTime()/3)*2){               // Mid Day value at index of floor is incremented
			    	sim.getDay().setMidDayPosition(building.getActiveCallPosition(i).getFromFloor(), 
	  						sim.getDay().getMidDayPos(building.getActiveCallPosition(i).getFromFloor())+1);
				    }
			    else {                                                                     // Evening value at index of floor is incremented
			    	sim.getDay().setEveningPosition(building.getActiveCallPosition(i).getFromFloor(), 
	  						sim.getDay().getEveningPos(building.getActiveCallPosition(i).getFromFloor())+1);
			        }
	  		building.getActiveCallPosition(i).setTimeOnElevator(timeConstant);             // sets time on elevator if true
			elevator.addPersonOn(elevator.getNextPosition(),building.getActiveCalls()[i]); // takes the active call and passes to the elevator
			Calls filler = new Calls();                                                    // creates call to fill active spot
			filler.PopulateCalls(-1, -1,-1,-1, -1);                                        //sets calls to fill active spot
			building.setActiveCallPosition(i,filler);                                      //sets spot with filler which cannot be reached by elevator;
			}
		 }
		for(int i = 0; i < elevator.getCapacity (); i++ ){                                  // runs through elevator capacity
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
	    public static void AIsimulation(int maxTime, int timeConstant,                      // Actual sim running
				Building building, GenericElevator[] elevators,
				AIsimulation sim) {
			    sim.clearElevators();                                                       // clears elevator
			    sim.setTimeConstant(0);                                                     // resets time constant
			for(int i = 0; i <= sim.getMaxTime(); i++){                                     // cycles until the total time for cycle 
	    		sim.setTimeConstant(sim.getTimeConstant() + 1);                             // counts time
	    		AdvancedTraffic.generatePeople(sim.getTimeConstant(),building);             // makes people appear through out each time constant
	    		for(int j = 0; j<= elevators.length-1;j++){                                 // deals with each elevator in the elevator array
	    			  if(sim.getTimeConstant()==1){                                         // sets resting floor for morning
		    				 elevators[j].setRestingPoint(sim.getRestingFloorMorning());
			    		}
	    			 if(sim.getTimeConstant()==(sim.getMaxTime())/3){                       // sets resting floor for mid day
	    				 elevators[j].setRestingPoint(sim.getRestingFloorMidDay());
		    			 }
	    			 if(sim.getTimeConstant()<=(2*sim.getMaxTime())/3){                     // sets resting floor for evening
	    				 elevators[j].setRestingPoint(sim.getRestingFloorEvening());
		    			 }
	    	    elevators[j].move(building);                                                // moves elevator
	    		AiTraffic.addPeopleWaiting(elevators[j],
	    				building,sim.getTimeConstant(),sim);                                // adds people waiting in and out
					    }
	    		    }
                }
			}


