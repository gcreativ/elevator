
public class Building {
	   private int floors ;
	   private int numElevators ;
	   private Calls[] dailyCalls;   // all calls for the day
	   private Calls [] active;  // where calls that pass the time constant are stored
	  
	 //******************************Setters***************************//
	   private void setDailyCalls(Calls[] dailyCalls) {
			this.dailyCalls = dailyCalls;
	   }
	  
	   protected void setActiveCallPosition(int position,Calls addActive) {
			this.active[position] = addActive;
	   }
	   
	   private void setFloors (int floors) { // set actual capacity
	       this.floors = floors;
	   }
	  
	   private void setNumElevators (int numElev) { // set actual Cap
		   this.numElevators = numElev;
	   }
	  
	   private void setActiveCallsNumSize (int Cap) { // set peopleOn size
	       this.setActiveCalls(new Calls [Cap]) ;
	       }
	     		   
	   	   
	   private void setActiveCalls(Calls[] activeCalls) {
			this.active = activeCalls;
	   }
	  
	   
	   private void setCallsDay (int Cap) { // set peopleOn size
	       this.setDailyCalls(new Calls [Cap]) ;
	   }
	
	   protected void setCall(int Call, Calls person) {
		   
		this.getDailyCalls()[Call] = person;
	   }
	 //******************************Setters***************************//
	 //******************************************************************//
	 //******************************Getters***************************//
	   public int getFloors () {
  		   return this.floors;
       }

       public int getNumFloors () {
           return this.numElevators;
       }
	   
       public Calls getCall(int position) {
			return this.getDailyCalls()[position];
		}
	   
       public Calls getActiveCallPosition(int position) {
			return this.getActiveCalls()[position];
		}
       
	   public int getCallLength() {
			return this.getDailyCalls().length;
		}
	
	   public Calls[] getDailyCalls() {
		return dailyCalls;
	    }
	   
	   public Calls[] getActiveCalls() {
			return active;
		    }
	 //******************************Getters***************************//
	   
	   void PopulateBuilding(int Floors, int numElevators,int numCallsInDay,Building building){  // fills value in for building
		    setFloors (Floors);                                                                  // creates floors
			setNumElevators(numElevators);                                                       // number of elevators in a building
			setCallsDay(numCallsInDay);                                                          // number of calls a day
			setActiveCallsNumSize(numCallsInDay);                                                // sets size of Calls Array for the day
			fillActiveCallsWithEmpties(building);                                                // Fills active calls with default empty values
	       }
	  final static void fillActiveCallsWithEmpties(Building building){                           // makes a call with default values
			for(int i = 0; i <= building.getCallLength()-1; i++){                                // populates the buildings call for the day
				Calls person = new Calls();                                                      // creates call to build
	  			   person.PopulateCalls(-1,-1,-1,-1,-1);                                         // default values
	  			   building.setActiveCallPosition(i,person);
			}
	   
	   }
	   }

	   
	   