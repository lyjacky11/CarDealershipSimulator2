/*
 * Name: Jacky Ly
 * Student ID: 500890960
 * Section: CPS209-031
 */

public class Vehicle {

	/*
     * Initialize instance variables and constants
     */
	private String mfr, color, powerString;
	private int power, numWheels, VIN;
	public static final int ELECTRIC_MOTOR = 0;
	public static final int GAS_ENGINE = 1;

	/**
	 * Default constructor for class Vehicle
	 * 
	 * @param mfr
	 * @param color
	 * @param power
	 * @param numWheels
	 */
	public Vehicle(String mfr, String color, int power, int numWheels) {
		this.mfr = mfr;
		this.color = color;
		this.power = power;
		this.numWheels = numWheels;
		this.VIN = (int) (Math.random() * 900) + 100;
	}

	/**
	 * Returns the manufacturer of the vehicle
	 * 
	 * @return mfr
	 */
	public String getMfr() {
		return mfr;
	}
	/**
	 * Sets the manufacturer 
	 * @param mfr the mfr to set
	 */
	public void setMfr(String mfr) {
		this.mfr = mfr;
	}

	/**
	 * Returns the color of the vehicle
	 * @return color
	 */
	public String getColor() {
		return color;
	}
	/**
	 * Sets the color
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * Returns the power type of the vehicle
	 * @return power
	 */
	public int getPower() {
		return power;
	}
	/**
	 * Sets the power
	 * @param power the power to set
	 */
	public void setPower(int power) {
		this.power = power;
	}

	/**
	 * Returns the number of wheels
	 * @return numWheels
	 */
	public int getNumWheels() {
		return numWheels;
	}
	/**
	 * Sets the number of wheels
	 * @param numWheels the numWheels to set
	 */
	public void setNumWheels(int numWheels) {
		this.numWheels = numWheels;
	}

	/**
	 * @return the VIN
	 */
	public int getVIN() {
		return VIN;
	}

	/**
	 * @param vIN the VIN to set
	 */
	public void setVIN(int vIN) {
		this.VIN = vIN;
	}

	/**
	 * Converts the power type from an integer to its respective String value
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
	 * Checks if two Vehicle objects are equal to each other
	 * @return the equality of two vehicles
	 */
	public boolean equals(Object other) {
		Vehicle otherVehicle = (Vehicle) other;
		return (this.mfr.equals(otherVehicle.mfr)) && (this.power == otherVehicle.power) && (this.numWheels == otherVehicle.numWheels);
	}

	/** 
	 * Displays the vehicle's manufacturer and color
	 * @return the Vehicle object specifications
	 */
	public String display() {
		return String.format("%-5d %-11s %-7s", VIN, mfr, color);
	}
}