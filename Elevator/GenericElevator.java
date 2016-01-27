
public class GenericElevator {
	   
	   private int capacity ; // max capacity
	   private int elevatorActualCapacity ; // actual capacity
	   private int elevatorFloor ; // current floor
	   private boolean direction; // true up, false down
	   private Calls[] peopleOn; // array of calls of people on the elevator
	   private boolean [] callsFor; // boolean value for people calling the elevator
	   private int restingPoint;
	   
	   //**************************Getters********************************//   
	   public int getCapacity () { // gets max capacity
	       return this.capacity;
	   }
	   public int getActualCap () { // gets actual capacity
	       return this.elevatorActualCapacity;
	   }
	   public int getElevatorFloor() { // gets the floor of the elevator
		   return this.elevatorFloor;
	  }
	   public boolean getDirection() { //gets direction of movement
		   return this.direction;
	  }
	   public Calls getCallsInside (int Cap) { // get people Calls on the elevator
	      return this.peopleOn[Cap] ;
	   }
	   public boolean getCallsFor (int Cap) { // get people Calls for the elevator
		      return this.callsFor[Cap] ;
		   }
	   public int getRestingPoint() {
			return restingPoint;
		}
	   //**************************Getters********************************//
	   //**************************Setters********************************//
		public void setRestingPoint(int restingPoint) {
			this.restingPoint = restingPoint;
		}
	  	   protected void setCap (int Cap) { // set actual capacity
	       this.capacity = Cap ;
	   }
	   protected void setPeopleInside (int Cap) { // set peopleOn size
	       this.peopleOn = new Calls [Cap] ;
	   }
	   protected void setPositionInside (Calls person,int Cap) { // set peopleOn spot in array
	       this.peopleOn[Cap] =  person ;
	   }
	   protected void setCallsOutside (int Cap) { // set peopleOn size
	       this.callsFor = new boolean [Cap] ;
	   }
	   protected void setActualCap (int actCap) { // set actual Cap
		   this.elevatorActualCapacity = actCap;
	   }
	   protected void setElevatorFloor(int startFloor) { // set start floor
		   this.elevatorFloor = startFloor;
	   }
	   protected void setElevatorDirection(boolean direction) { // set start floor
		   this.direction = direction;
	   }
	   
	   //**************************Setters********************************//
	   
	   public void move(Building building){                      // will actually move the elevator
		   if (getDirection() == true ){                         // checks direction
			   if (getElevatorFloor() <= building.getFloors()){  // makes sure not to exceed
				   setElevatorFloor(getElevatorFloor() + 1);     // sets new floor
			       }
			   else {setElevatorDirection(false);                // if it does exceed it flips direction
		           }
		    }
		if (getDirection() == false){                            // checks direction
			   if (getElevatorFloor() - 1 >= 1){                 // makes sure not to exceed
			       setElevatorFloor(getElevatorFloor() - 1);     // sets new floor
	               }
			   else{ setElevatorDirection(true);                 // if it does exceed it flips direction
	               }
		    }
	     }
	    public int getNextPosition (){                           // Gets next position for placing someone in the array
		   int next = -1;                                        // thought that if it returns -1 there is no spot
		   
		     for (int i=0; i <= getCapacity()-1;i++){            // walks through array using capacity
		    	 if (getCallsInside(i).getAppearTime() <= 0){    // if it finds an empty uses -1 as indicator
		    		next = i;                                    // tells the empty spot
		    		break;                                       // break to return value
		    	}
		   }
		     return next; // returns the value that shows position 
	   }
	   
	    public void addPersonOn ( int positionOn, Calls personOn) {    // adds person onto the elevator
		   this.peopleOn[positionOn] = personOn;                      // places objects into Elevator 
		   this.setActualCap(this.getActualCap() + 1);                // adds one to Aactual capacity
		   }
	  
	   public void removePersonOn ( int positionOn) {                 // empties spot of person
		   this.setActualCap(this.getActualCap() - 1);                //sets actual cap of elevator
		   this.peopleOn[positionOn].PopulateCalls(-1,-1,-1,-1,-1);   // takes objects out of the Elevator and replaces with -1 indicating empty
		   }
	  
	   protected final void fillInsideCallsWithEmpties(GenericElevator elevator){
			for(int i = 0; i <= elevator.getCapacity() -1; i++){      // populates the buildings call for the day
	  			   Calls person = new Calls();
	  			   person.PopulateCalls(-1,-1,-1,-1,-1);              // default values
	  			   elevator.setPositionInside(person,i);              // sets default values in every spot in the elevator
			}
		}
	  }


