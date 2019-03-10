/*
 * Name: Jacky Ly
 * Student ID: 500890960
 * Section: CPS209-031
 */

public class Vehicle {
	private String mfr, color, powerString;
	private int power, numWheels;
	public static final int ELECTRIC_MOTOR = 0;
	public static final int GAS_ENGINE = 1;
	
	/**
	 * @param mfr
	 * @param color
	 * @param power
	 * @param numWheels
	 */
	public Vehicle (String mfr, String color, int power, int numWheels) {
		this.mfr = mfr;
		this.color = color;
		this.power = power;
		this.numWheels = numWheels;
	}

	/**
	 * @return the mfr
	 */
	public String getMfr() {
		return mfr;
	}
	/**
	 * @param mfr the mfr to set
	 */
	public void setMfr(String mfr) {
		this.mfr = mfr;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the power
	 */
	public int getPower() {
		return power;
	}
	/**
	 * @param power the power to set
	 */
	public void setPower(int power) {
		this.power = power;
	}

	/**
	 * @return the numWheels
	 */
	public int getNumWheels() {
		return numWheels;
	}
	/**
	 * @param numWheels the numWheels to set
	 */
	public void setNumWheels(int numWheels) {
		this.numWheels = numWheels;
	}

	/**
	 * @param power
	 * @return powerString
	 */
	public String checkPower(int power) {
		switch (power) {
			case Car.ELECTRIC_MOTOR:
				powerString = "ELECTRIC_MOTOR";
				break;
			case Car.GAS_ENGINE:
				powerString = "GAS_ENGINE";
				break;
			default:
				powerString = null;
				break;
		}
		return powerString;
	}

	/**
	 * @return the equality of two vehicles
	 */
	public boolean equals(Object other) {
		Vehicle otherVehicle = (Vehicle) other;
		return (this.mfr.equals(otherVehicle.mfr)) && (this.power == otherVehicle.power) && (this.numWheels == otherVehicle.numWheels);
	}

	/** 
	 * @return the Vehicle object specifications
	 */
	public String display() {
		return String.format("%-11s %-6s", mfr, color);
	}
}