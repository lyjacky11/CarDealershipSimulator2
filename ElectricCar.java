/*
 * Name: Jacky Ly
 * Student ID: 500890960
 * Section: CPS209-031
 */

public class ElectricCar extends Car {
	
	/*
     * Initialize instance variables
     */
	private int rechargeTime;
	private String batteryType;

	/**
	 * Default constructor for the class
	 * 
	 * @param mfr
	 * @param color
	 * @param power
	 * @param numWheels
	 * @param model
	 * @param maxRange
	 * @param safetyRating
	 * @param price
	 * @param aWD
	 * @param rechargeTime
	 * @param batteryType
	 */
	public ElectricCar (String mfr, String color, int power, int numWheels, int model, int maxRange, double safetyRating, double price, boolean aWD, int rechargeTime, String batteryType) {
			super(mfr, color, power, numWheels, model, maxRange, safetyRating, price, aWD);
			this.rechargeTime = rechargeTime;
			this.setBatteryType(batteryType);
	}
	/**
	 * Returns the recharge time of the electric car
	 * @return rechargeTime
	 */
	public int getRechargeTime() {
		return rechargeTime;
	}
	/**
	 * Sets the recharge time
	 * @param rechargeTime to set
	 */
	public void setRechargeTime(int rechargeTime) {
		this.rechargeTime = rechargeTime;
	}

	/**
	 * Returns the battery type of the electric car
	 * @return batteryType
	 */
	public String getBatteryType() {
		return batteryType;
	}
	/**
	 * Sets the battery type
	 * @param batteryType to set
	 */
	public void setBatteryType(String batteryType) {
		this.batteryType = batteryType;
	}

	/**
	 * Displays the electric car's details and specifications
	 * @return the ElectricCar object specifications
	 */
	public String display() {
		return String.format("%s %-3d %-5s", super.display(), rechargeTime, batteryType);
	}
}