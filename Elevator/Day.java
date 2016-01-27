

public class Day {/////////////////////////////////////////////////////
	
	   private double[] morning; // array the size of floors holds number of time a person appeared
	   private double[] midDay;  // array the size of floors holds number of time a person appeared
	   private double[] evening; // array the size of floors holds number of time a person appeared
	   
	
	 //************************************************Setters****************************************//
    
	public void setMorningPosition(int pos, double value) {
		this.morning[pos]= value;
	}
	public void setMidDayPosition(int pos, double value) {
		this.midDay[pos]= value;
	}
	public void setEveningPosition(int pos, double value) {
		this.evening[pos]= value;
	}
	public void setMorning(double[] morningProbablily) {
		this.morning = morningProbablily;
	}
	public void setMidDay(double[] midDay) {
		this.midDay = midDay;
	}
	public void setEvening(double[] evening) {
		this.evening = evening;
	}
	//************************************************Setters****************************************//
	//***********************************************************************************************//
	//************************************************Getters****************************************//
	public double[] getEvening() {
		return this.evening;
	}
	public double[] getMidDay() {
		return this.midDay;
	}
	public double[] getMorning() {
		return morning;
	}
	public double getMorningPos(int pos) {
		return morning[pos];
	}
	public double getMidDayPos(int pos) {
		return midDay[pos];
	}
	public double getEveningPos(int pos) {
		return evening[pos];
	}
	//************************************************Getters****************************************//
	}