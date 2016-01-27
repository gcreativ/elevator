import java.util.ArrayList;


public class SimulationValues {
	
	private ArrayList<Integer> results = new ArrayList <Integer>();         //holds results for the day on elevator
	private ArrayList<Integer> totalTime= new ArrayList <Integer>();        // holds total time waiting form appearance to destination
	private ArrayList<Integer> totalResults = new ArrayList <Integer>();    // holds all results for multiple days
	private ArrayList<Integer> overallTotalTime= new ArrayList <Integer>(); // holds all values for time waiting from appearance to destination
	private int numElevators;                                               // number of elevators can be used later if we make array of elevators
	private int elevatorCap;                                                // capacity
	private int Floors ;                                                    // number of floors
	private int numCallsInDay;                                              // how many calls
	private int maxTime ;                                                   // number of time constants
	private int timeConstant = 0;                                           // initialize time constant
	private Building building;                                              // creates a building object
	private GenericElevator [] arrayElevators;                              // array of elevators to use in sim
	private int numDays;                                                    // number of days the sim will run
	private int dayCount = 1;                                               // value to keep track of days
	
	//**********************************Setters***************************************//
	
	public ArrayList<Integer> getTotalTime() {
		return totalTime;
	}
	public int getDayCount() {
		return this.dayCount;
	}
	public int getNumElevators(){
		return this.numElevators;
	}
	public int getElevatorCap(){
		return this.elevatorCap;
	}
	public int getFloors(){
		return this.Floors;
	}
	public int getNumCallsInDay(){
		return this.numCallsInDay;
	}
	public int getMaxTime(){
		return this.maxTime;
	}
	public int getTimeConstant(){
		return this.timeConstant;
	}
	public ArrayList<Integer> getResults(){
		return this.results;
	}
	public Building getBuilding(){
		return this.building;
	}
	public GenericElevator getArrayElevatorsPosition(int position) {
		return this.arrayElevators[position];
	}
	public GenericElevator[] getArrayElevators() {
		return this.arrayElevators;
	}
	public int getNumDays() {
		return numDays;
	}
	public ArrayList<Integer> getTotalResults() {
		return totalResults;
	}
	public ArrayList<Integer> getOverallTotalTime() {
		return overallTotalTime;
	}
	//**********************************Getters***************************************//
	//********************************************************************************//
	//**********************************Setters***************************************//
	public void setTotalTime(int num) {
		this.totalTime.add(num);
	}
	public void setDayCount(int num) {
		this.dayCount= num;
	}
	public void setNumDays(int numDays) {
		this.numDays = numDays;
	}
		protected void setNumElevators(int numElevators){
		this.numElevators = numElevators;
	}
	protected void setElevatorCap(int elevatorCap){
		this.elevatorCap = elevatorCap;
	}
	protected void setFloors(int Floors){
		this.Floors = Floors;
	}
	protected void setNumCallsInDay(int numCallsInDay){
		this.numCallsInDay = numCallsInDay;
	}
	protected void setMaxTime(int maxTime){
		this.maxTime = maxTime;
	}
	protected void setTimeConstant(int timeConstant){
		this.timeConstant = timeConstant;
	}
	protected void setResults(int num){
		
		this.results.add(num);
	}
    protected void setResultsTotal(int num){
		
		this.totalResults.add(num);
	}
    protected void setOverallTotalTime(int num){
	
	    this.overallTotalTime.add(num);
    }
	protected void setBuilding(Building build){
		this.building = build;
	}
	protected void setArrayElevators(int numEle) {
		this.setArrayElevators(new GenericElevator [numEle]) ;
		
	}
	private void setArrayElevators(GenericElevator[] arrayElevators) {
		this.arrayElevators = arrayElevators;
	}
	protected void setElevatorsSize(int position,GenericElevator elevator) {
		this.arrayElevators[position] = elevator;
	}
	public void setOverallTotalTime(ArrayList<Integer> totaltotalTime) {
		overallTotalTime = totaltotalTime;
	}
	public void setTotalresults(ArrayList<Integer> totalresults) {
		totalResults = totalresults;
	}
		
		//**********************************Setters***************************************//
	
	
	
	protected void intializeArrayElevators(){                                 // Creates and sets values for every elevator in the elevator Array
		for(int i = 0; i <= getArrayElevators().length-1;i++){
			GenericElevator elevator = new GenericElevator();                 // elevator to build on
			setElevatorsSize(i,elevator);                                     // sets elevator array size in class
			getArrayElevatorsPosition(i).setCap(getElevatorCap());            // sets capacity int
			getArrayElevatorsPosition(i).setPeopleInside (getElevatorCap());  // sets the actual capacity of the elevator array
			getArrayElevatorsPosition(i).setActualCap (0);                    // sets actual cap
			getArrayElevatorsPosition(i).setElevatorFloor(((i)% 
					getNumElevators())*(getFloors()/getNumElevators()));      // spreads elevator start point
			if ( i % 2 < 1 ){                                                 // makes every other elevator same direction
			getArrayElevatorsPosition(i).setElevatorDirection(true);
			}
			else {
				getArrayElevatorsPosition(i).setElevatorDirection(false);
			}
			getArrayElevatorsPosition(i).fillInsideCallsWithEmpties(elevator); // fills inside with default values
		}
	}
	
	protected void setSimulation(int numElevators, int elevatorCap,  // Sets all values in the simulation
			                 int Floors, int numCallsInDay,
			                 int maxTime, int timeConstant,
			                 int numDays, SimulationValues sim){
		setNumDays(numDays);                              // sets number of days
		setNumElevators(numElevators);                    // sets number of elevators
		setElevatorCap(elevatorCap);                      // sets elevator capacity
	    setFloors(Floors);                                // sets number of floors
		setNumCallsInDay(numCallsInDay);                  // sets number of calls in a day
		setMaxTime(maxTime);                              // sets the total time of the simulated day
		setTimeConstant(timeConstant);                    // sets the time counter
		setArrayElevators(numElevators);                  // sets the array size to the number of elevators
		Building build = new Building();                  // Creates building
		build.PopulateBuilding(Floors,numElevators,
				numCallsInDay,build);                     // initalizes the building
		setBuilding(build);                               // sets building
		intializeArrayElevators();                        // Initializes all elevators in the array
		
		runSimulation(sim);                               // runs the simulation
		}
	protected void runSimulation(SimulationValues sim){   // method for running simulation
						
		System.out.println("**********************************************************");  // separates elevators
		for(int i=1; i <= getNumDays(); i++){                                              // number of days to be looped
			getTotalTime().clear();                                                        // clears the total time on the elevator to start the day
			getResults().clear();                                                          // clears the time on the elevator to start the day
			Building.fillActiveCallsWithEmpties(getBuilding());                            // fills building with empty active calls that might be from the day before
			CreateCalls.createCases( getNumCallsInDay(), 
					getBuilding(),getDayCount());                                          // Creates the call from the text file in main
			intializeArrayElevators();                                                     // intitalizes elevators
			Traffic.simulation(getMaxTime(),getTimeConstant(), 
					getBuilding(),getArrayElevators(),sim);                                // runs simulation
			System.out.println("Advanced Elevator Day " + i + ":");
		Outputs.printStandardOutput(sim.getResults(),sim,sim.getTotalTime());              // Outputs Advanced results
		setTimeConstant(0);                                                                // sets time constant at zero for next day
		setDayCount(getDayCount()+1);                                                      // increases day count
		
		}
	}
	
}
