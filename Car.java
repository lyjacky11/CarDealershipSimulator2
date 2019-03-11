/*
 * Name: Jacky Ly
 * Student ID: 500890960
 * Section: CPS209-031
 */

public class Car extends Vehicle implements Comparable<Car> {
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
	 * @return the model
	 */
	public int getModel() {
		return model;
	}
	/**
	 * @param model to set
	 */
	public void setModel(int model) {
		this.model = model;
	}

	/**
	 * @return maxRange
	 */
	public int getMaxRange() {
		return maxRange;
	}
	/**
	 * @param maxRange to set
	 */
	public void setMaxRange(int maxRange) {
		this.maxRange = maxRange;
	}
	
	/**
	 * @return safetyRating
	 */
	public double getSafetyRating() {
		return safetyRating;
	}
	/**
	 * @param safetyRating to set
	 */
	public void setSafetyRating(double safetyRating) {
		this.safetyRating = safetyRating;
	}

	/**
	 * @return price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * @return AWD
	 */
	public boolean isAWD() {
		return AWD;
	}
	/**
	 * @param aWD to set
	 */
	public void setAWD(boolean aWD) {
		this.AWD = aWD;
	}

	/**
	 * @return equality of two Car objects
	 */
	public boolean equals(Object other) {
		Car otherCar = (Car) other;
		return super.equals(otherCar) && this.model == otherCar.model && (this.AWD == otherCar.AWD);
	}

	/**
	 * @return comparision of two Car objects
	 */
	public int compareTo(Car other) {
		return new Double(this.price).compareTo(other.price);
	}
	
	/**
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
	 * @param aWD boolean value
	 * @return "AWD" or "2WD" string value
	 */
	public String checkAWD(boolean aWD) {
		if (aWD) return "AWD";
		else return "2WD";
	}

	/**
	 * @return the Car object specifications
	 */
	public String display() {
		return String.format("%s %-8s %-5d %-8.2f %-10.2f %-5s", super.display(), checkModel(model), maxRange, safetyRating, price, checkAWD(AWD));
	}
}