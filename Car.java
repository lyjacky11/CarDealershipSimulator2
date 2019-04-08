/*
 * Name: Jacky Ly
 * Student ID: 500890960
 * Section: CPS209-031
 */

public class Car extends Vehicle implements Comparable<Car> {
	
	/*
     * Initialize instance variables and constants
     */
	private int model;
	private int maxRange;
	private double safetyRating, price;
	private boolean AWD;
	private String modelString;
	public static final int SEDAN = 0;
	public static final int SUV = 1;
	public static final int SPORTS = 2;
	public static final int MINIVAN = 3;

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
	 */
	public Car (String mfr, String color, int power, int numWheels, int model, int maxRange, double safetyRating, double price, boolean aWD) {
		super(mfr, color, power, numWheels);
		this.model = model;
		this.maxRange = maxRange;
		this.safetyRating = safetyRating;
		this.price = price;
		this.setAWD(aWD);
	}

	/**
	 * Returns the model of the car
	 * @return the model
	 */
	public int getModel() {
		return model;
	}
	/**
	 * Sets the model
	 * @param model to set
	 */
	public void setModel(int model) {
		this.model = model;
	}

	/**
	 * Returns the max range of the car
	 * @return maxRange
	 */
	public int getMaxRange() {
		return maxRange;
	}
	/**
	 * Sets the max range
	 * @param maxRange to set
	 */
	public void setMaxRange(int maxRange) {
		this.maxRange = maxRange;
	}
	
	/**
	 * Returns the safety rating of the car
	 * @return safetyRating
	 */
	public double getSafetyRating() {
		return safetyRating;
	}
	/**
	 * Sets the safety rating
	 * @param safetyRating to set
	 */
	public void setSafetyRating(double safetyRating) {
		this.safetyRating = safetyRating;
	}

	/**
	 * Returns the price of the car
	 * @return price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * Sets the price
	 * @param price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * Returns true if the car is AWD, else returns false
	 * @return AWD
	 */
	public boolean isAWD() {
		return AWD;
	}
	/**
	 * Sets the AWD boolean
	 * @param aWD to set
	 */
	public void setAWD(boolean aWD) {
		this.AWD = aWD;
	}

	/**
	 * Checks if two Car objects are equal to each other
	 * @return equality of two Car objects
	 */
	public boolean equals(Object other) {
		Car otherCar = (Car) other;
		return super.equals(otherCar) && this.model == otherCar.model && (this.AWD == otherCar.AWD);
	}

	/**
	 * Compares two Car objects
	 * @return comparision of two Car objects
	 */
	public int compareTo(Car other) {
		return new Double(this.price).compareTo(other.price);
	}
	
	/**
	 * Converts the model from an integer to its respective String value
	 * @param model
	 * @return modelString
	 */
	public String checkModel(int model) {
		switch (model) {
			case Car.SEDAN:
				modelString = "SEDAN";
				break;
			case Car.SUV:
				modelString = "SUV";
				break;
			case Car.SPORTS:
				modelString = "SPORTS";
				break;
			case Car.MINIVAN:
				modelString = "MINIVAN";
				break;
			default:
				break;
		}
		return modelString;
	}

	/**
	 * Converts AWD from an integer to its respective String value
	 * @param aWD boolean value
	 * @return "AWD" or "2WD" string value
	 */
	public String checkAWD(boolean aWD) {
		if (aWD) return "AWD";
		else return "2WD";
	}

	/**
	 * Displays the car's details and specifications
	 * @return the Car object specifications
	 */
	public String display() {
		return String.format("%s %-8s %-5d %-8.1f %-10.2f %-5s", super.display(), checkModel(model), maxRange, safetyRating, price, checkAWD(AWD));
	}
}