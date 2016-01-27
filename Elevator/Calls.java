

public class Calls {
	private int appearTime ;
	private int fromFloor ;
	private int toFloor;
	private int timeOnElevator;
	private int timeOffElevator;
	private boolean directionTravel;
	
	 //**************************Getters********************************//
	
	public int getAppearTime () {  
	       return this.appearTime;
	   }
	   public int getFromFloor () { 
	       return this.fromFloor;
	   }
	   public int getToFloor() { 
		   return this.toFloor;
       }
	   public int getTimeOnElevator() { 
		   return this.timeOnElevator;
       }
	   public int getTimeOffElevator() { 
		   return this.timeOffElevator;
       }
	   public boolean getDirectionTravel() { 
		   return this.directionTravel;
       }
	   //**************************Getters********************************//
	   //**************************Setters********************************//
	   
	   void setAppearTime (int Appear) { 
	       this.appearTime = Appear;
	   }
	   void setFromFloor (int from) {
	       this.fromFloor = from;
	   }
	   void setToFloor(int to) {
		   this.toFloor = to;
       }
	   protected void setTimeOnElevator(int on) {
		   this.timeOnElevator = on;
       }
	   protected void setTimeOffElevator(int off) {
		   this.timeOffElevator = off;
       }
	   void setDirectionTravel(boolean direction) {
		   this.directionTravel = direction;
       }
	   //**************************Setters********************************//
	   //**************************Initialize********************************//
	   public void PopulateCalls(int appear, int onFloor,
			                     int toFloor,int timeIn, 
			                     int timeOff){                          // populates calls
		   setAppearTime(appear);                                       // sets appear time
		   setFromFloor(onFloor);                                       // sets from floor 
		   setToFloor(toFloor);                                         // sets to floor
		   setTimeOnElevator(timeIn);                                   // sets time on elevator
		   setTimeOffElevator(timeOff);                                 // sets time off
		   if (onFloor <= toFloor){                                     // if on floor is less than the to floor direcction is up
			   setDirectionTravel(true);
		   }
		   if (onFloor > toFloor){                                      // if on floor is more than the to floor direction is down
			   setDirectionTravel(false);
		   }
	   }
	   public int getTimeOverall() {                                    // time constant stamp of when getting off the elevator
		   return this.timeOffElevator;
       }
	   
	   
	   //**************************Initialize********************************//
	   
	   
}    