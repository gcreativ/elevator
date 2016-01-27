import java.util.ArrayList;

public class AIsimulation extends AdvancedSimulationValues {
	private Day day;                                                 // holds values to determine resting point
	private int dayCounter;                                          // holds the day
	private ArrayList<Integer> totalTime = new ArrayList <Integer>();// holds total time from appearing to arrival
	private int restingFloorMorning;                                 // holds resting floor for the morning
	private int restingFloorMidDay;                                  // holds resting floor for the morning
	private int restingFloorEvening;                                 // holds resting floor for the morning
	
	//*******************************************SETTERS*********************************//

	public void setRestingFloorMorning(int restingFloor) {
		this.restingFloorMorning = restingFloor;
	}
	public void setDayCounter(int dayCounter) {
		this.dayCounter = dayCounter;
	}
	
	public void setRestingFloorMidDay(int restingFloorMidDay) {
		this.restingFloorMidDay = restingFloorMidDay;
	}
	public void	setDay(Day temp){
		this.day = temp;
    }
	public void setRestingFloorEvening(int restingFloorEvening) {
		this.restingFloorEvening = restingFloorEvening;
	}
	public void setTotalTime(int num) {
		this.totalTime.add(num);
	}
	//*******************************************SETTERS*********************************//
	//***********************************************************************************//
	//*******************************************GETTERS*********************************//
	public ArrayList<Integer> getTotalTime() {
		return totalTime;
	}
	public Day getDay() {
		return day;
	}
	public int getRestingFloorMidDay() {
		return this.restingFloorMidDay;
	}
   	public int getDayCounter() {
		return dayCounter;
	}
	public int getRestingFloorMorning() {
		return this.restingFloorMorning;
	}
	public int getRestingFloorEvening() {
		return this.restingFloorEvening;
	}
	//*******************************************GETTERS*********************************//
	
	public Day intiateDay(Building building){                   // create size floors + 1 arrays 
		Day temp = new Day();                                   // Day to build on
		double [] tempDob = new double[building.getFloors()+1]; // creates double array to be place in position
		  	temp.setMorning(tempDob);                           // sets morning array size
		  	temp.setMidDay(tempDob);                            // sets mid day array size
			temp.setEvening(tempDob);                           // sets evening array size
		return temp;                                            // returns the day filled with right size arrays
		}
	
	protected void setSimulation(int numElevators, int elevatorCap,  // Sets all values in the simulation
            int Floors, int numCallsInDay,
            int maxTime, int timeConstant,
            int numDays, AIsimulation sim){
		
		setNumDays(numDays);             // sets number of days
		setNumElevators(numElevators);   // sets number of elevators
		setElevatorCap(elevatorCap);     // sets elevator capacity
	    setFloors(Floors);               // sets number of floors
		setNumCallsInDay(numCallsInDay); // sets number of calls in a day
		setMaxTime(maxTime);             // sets the total time of the simulated day
		setTimeConstant(timeConstant);   // sets the time counter
		setArrayElevators(numElevators); // sets the array size to the number of elevators
		Building build = new Building(); // Creates building
		build.PopulateBuilding(Floors,
				numElevators,numCallsInDay,
				build);                   // sets the building
		setBuilding(build);               // sets building
		intializeArrayAIElevators();      // Initializes all elevators in the array
		setDay(intiateDay(build));        // sets arrays in days so they are they size of floors
		runSimulation(sim);               // runs the simulation
		}
	
	protected void intializeArrayAIElevators(){                               // Creates and sets values for every elevator in the elevator Array
		for(int i = 0; i <= getArrayElevators().length-1;i++){                // goes through array
			AIelevator elevator = new AIelevator();                           // makes an elevator to build on 
			setElevatorsSize(i,elevator);                                     // sets the size of the elevator
			getArrayElevatorsPosition(i).setCap(getElevatorCap());            // sets the capacity
			getArrayElevatorsPosition(i).setPeopleInside (getElevatorCap());  //sets the arrays up as empty inside the elevator
			getArrayElevatorsPosition(i).setActualCap (0);                    // actual capacity
			getArrayElevatorsPosition(i).setElevatorFloor(((i)% 
					getNumElevators())*(getFloors()/getNumElevators()));      // spreads the elevator creation
			if ( i % 2 < 1 ){                                                 // makes every other elevator same direction
			    getArrayElevatorsPosition(i).setElevatorDirection(true); 
			}
			else {
			    getArrayElevatorsPosition(i).setElevatorDirection(false);
			}
			getArrayElevatorsPosition(i).fillInsideCallsWithEmpties(elevator); // sets inside calls empty
		}
	}
	
	protected void runSimulation(AIsimulation sim){                               // Starts Advanced Simulation
		System.out.println("**************************************************"); // separates elevators
		for(int i=1; i <= getNumDays(); i++){                                     // number of days to be looped
			intializeArrayAIElevators();                                          // intitalizes elevators
			Building.fillActiveCallsWithEmpties(getBuilding());                   // fills building with empty active calls that might be from the day before
			getTotalTime().clear();                                               // clears the total time on the elevator to start the day
			getResults().clear();                                                 // clears the time on the elevator to start the day
			CreateCalls.createCases( getNumCallsInDay(), 
					getBuilding(),getDayCount());                                 // Creates the call from the text file in main
			AiTraffic.AIsimulation(getMaxTime(),getTimeConstant(),
					getBuilding(),getArrayElevators(),sim);                       // runs simulation
			System.out.println("AI Elevator Day " + i + ":");
			Outputs.printStandardOutput(sim.getResults(),sim,sim.getTotalTime()); // Outputs Advanced results
			setTimeConstant(0);                                                   // sets time constant at zero for next day
			setDayCount(getDayCount()+1);                                         // increases day count
		    }
			}
				
			
			
			protected void clearElevators(){                                                               // clears elevators inside positions
				for(int i = 0; i < getArrayElevators().length-1; i++){                                     // goes through array of elevators
					getArrayElevatorsPosition(i).fillInsideCallsWithEmpties(getArrayElevatorsPosition(i)); // fills with empties
				}
				
			}
	protected void setRestingFloor(AIelevator elevator){
		double maxMorn = 0;                                         // holds max Morning value
		double maxMid = 0;                                          // holds max Mid Day value
		double maxEven = 0;                                         // holds max Evening value
		int maxMornPos = 0;                                         // holds max Morning position in array
		int maxMidPos = 0;                                          // holds max Mid Day position in array
		int maxEvenPos = 0;                                         // holds max Evening position in array
		for (int i = 0; i < getDay().getMorning().length;i++){      // Goes through array and determines Max value
			double tempMorn = getDay().getMorning()[i];             // sets the morning values inside array to variable to keep from looking messy
			double tempMid = getDay().getMidDay()[i];               // sets the mid day values inside array to variable to keep from looking messy
			double tempEven = getDay().getEvening()[i];             // sets the evening values inside array to variable to keep from looking messy
			if(tempMorn>maxMorn){                                   // compares new value to know max 
				maxMorn =tempMorn;                                  // reassigns if new value is found
				maxMornPos = i;                                     // keeps position where max was found
			    }
			if(tempMid>maxMid){                                     // compares new value to know max 
				maxMid =tempMid;                                    // reassigns if new value is found
				maxMidPos = i;                                      // keeps position where max was found
			    }
			if(tempEven>maxEven){                                   // compares new value to know max 
				maxEven =tempEven;                                  // reassigns if new value is found
				maxEvenPos = i;                                     // keeps position where max was found
			    }
		    }
		setRestingFloorMorning(maxMornPos);    // sets max
		setRestingFloorMidDay(maxMidPos);      // sets max
		setRestingFloorEvening(maxEvenPos);    // sets max
	    }
	
	
	}
