import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;

public class CarDealershipSimulator 
{
  public static void main(String[] args)
  {
	  // Create a CarDealership object
	  CarDealership newDealer = new CarDealership();
		// Then create an (initially empty) array list of type Car
		ArrayList<Car> cars = new ArrayList<Car>();
		// Then create some new car objects of different types
		Car car1 = new Car("Toyota", "blue", 1, 4, "SEDAN", 500, 9.5, 25000, false);
		Car car2 = new Car("Honda", "red", 1, 4, "SPORTS", 450, 9.2, 30000, false);
	  // See the cars file for car object details
		// Add the car objects to the array list
		cars.add(car1);
		cars.add(car2);
    // The ADD command should hand this array list to CarDealership object via the addCars() method
		// Create a scanner object
		String line = "", command = "";
		Scanner s = new Scanner(System.in);
		System.out.print("Enter a command: ");
		// while the scanner has another line
		while (s.hasNextLine()) {
		//    read the input line
			line = s.nextLine();
		//    create another scanner object (call it "commandLine" or something) using the input line instead of System.in
		Scanner commandLine = new Scanner(line);
		//    read the next word from the commandLine scanner
		command = commandLine.next();
			//	check if the word (i.e. string) is equal to one of the commands and if so, call the appropriate method via the CarDealership object  

			switch (command) {
				case "L":
					newDealer.displayInventory();
					break;
				case "Q":
					commandLine.close();
					System.out.println("Program has been terminated.");
					return;
				case "BUY":
					int index = commandLine.nextInt();
					Car currentCar = newDealer.buyCar(index);
					if (currentCar != null) {
						System.out.println(currentCar.display());
						System.out.println("Car at position " + index + " bought successfully!");
					}
					break;
				case "RET":
					Car currentCar2 = newDealer.getCarBought();
					newDealer.returnCar(currentCar2);
					break;
				case "ADD":
					newDealer.addCars(cars);
					break;
				case "SPR":
					newDealer.sortByPrice();
					break;
				case "SSR":
					newDealer.sortBySafetyRating();
					break;
				case "SMR":
					newDealer.sortByMaxRange();
					break;
				case "FPR":
					int minPrice = commandLine.nextInt();
					int maxPrice = commandLine.nextInt();
					newDealer.filterByPrice(minPrice, maxPrice);
					break;
				case "FEL":
					newDealer.filterByElectric();
					break;
				case "FAW":
					newDealer.filterByAWD();
					break;
				case "FCL":
					newDealer.FiltersClear();
					break;
				default:
					System.out.println("Unknown command. Please try again!");
					break;
			}
			commandLine.close();
			System.out.print("\nEnter another command: ");
		}
		s.close();
	}
}

class CarDealership {
	private ArrayList<Car> cars;
	private double minPrice, maxPrice;
	private boolean AWD, electric, price;
	private Car carLastBought;

	public CarDealership () {
		cars = new ArrayList<Car>();
	}

	/**
	 * @param newCars ArrayList of Car objects
	 */
	public void addCars(ArrayList<Car> newCars) {
		cars.addAll(newCars);
	}

	/**
	 * @param index of the car to buy
	 * @return Car object
	 */
	public Car buyCar(int index) {
		if (index < cars.size() - 1) {
			carLastBought = cars.get(index);
			cars.remove(index);
			return carLastBought;
		}
		return null;
	}
	
	/**
	 * @param car to return
	 */
	public void returnCar(Car car) {
		if (car != null) {
			cars.add(car);
			carLastBought = null;
		}
	}

	/**
	 * @return carLastBought
	 */
	public Car getCarBought() {
		return carLastBought;
	}

	/* TODO */
	public void displayInventory() {
		for (int i = 0; i < cars.size(); i++) {
			Car currentCar = cars.get(i);
			System.out.println(i + " " + currentCar.display());
		}
	}

	public void filterByElectric() {
		this.electric = true;
	}

	public void filterByAWD() {
		this.AWD = true;
	}

	/**
	 * @param minPrice
	 * @param maxPrice
	 */
	public void filterByPrice(double minPrice, double maxPrice) {
		this.price = true;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}

	public void FiltersClear() {
		this.AWD = false;
		this.electric = false;
		this.price = false;
	}

	public void sortByPrice() {
		Collections.sort(cars);
	}

	/* TODO */
	public void sortBySafetyRating() {

	}

	/* TODO */
	public void sortByMaxRange() {
		
	}
}

class Vehicle {
	private String mfr, color;
	private int power, numWheels;
	public final int ELECTRIC_MOTOR = 0;
	public final int GAS_ENGINE = 1;

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
	 * @return the equality of two vehicles
	 */
	public boolean equals(Object other) {
		Vehicle otherVehicle = (Vehicle) other;
		return (this.mfr.equals(otherVehicle.mfr)) && (this.power == otherVehicle.power) && (this.numWheels == otherVehicle.numWheels);
	}

	/** 
	 * @return manufacturer name and color
	 */
	public String display() {
		return mfr + " " + color;
	}
}

class Car extends Vehicle implements Comparable<Car> {
	private String model;
	private int maxRange;
	private double safetyRating, price;
	private boolean AWD;
	private final String SEDAN = "";
	private final String SUV = "";
	private final String SPORTS = "";
	private final String MINIVAN = "";

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
	public Car (String mfr, String color, int power, int numWheels, String model, int maxRange, double safetyRating, double price, boolean aWD) {
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
	public String getModel() {
		return model;
	}
	/**
	 * @param model the model to set
	 */
	public void setModel(String model) {
		this.model = model;
	}

	/**
	 * @return the maxRange
	 */
	public int getMaxRange() {
		return maxRange;
	}
	/**
	 * @param maxRange the maxRange to set
	 */
	public void setMaxRange(int maxRange) {
		this.maxRange = maxRange;
	}
	
	/**
	 * @return the safetyRating
	 */
	public double getSafetyRating() {
		return safetyRating;
	}
	/**
	 * @param safetyRating the safetyRating to set
	 */
	public void setSafetyRating(double safetyRating) {
		this.safetyRating = safetyRating;
	}

	/**
	 * @return the price
	 */
	public double getPrice() {
		return price;
	}
	/**
	 * @param price the price to set
	 */
	public void setPrice(double price) {
		this.price = price;
	}
	
	/**
	 * @return the aWD
	 */
	public boolean isAWD() {
		return AWD;
	}
	/**
	 * @param aWD the aWD to set
	 */
	public void setAWD(boolean aWD) {
		this.AWD = aWD;
	}

	/**
	 * @return equality of Car object
	 */
	public boolean equals(Object other) {
		Car otherCar = (Car) other;
		return super.equals(otherCar) && this.model.equals(otherCar.model) && (this.AWD == otherCar.AWD);
	}

	/**
	 * @return comparsion value of two Car objects
	 */
	public int compareTo(Car other) {
		return new Double(this.price).compareTo(other.price);
	}

	/**
	 * @return the car specs
	 */
	public String display() {
		return super.display() + " " + model + " " + maxRange + " " + safetyRating + " " + price + " " + AWD;
	}
}

class ElectricCar extends Car {
	private int rechargeTime;
	private String batteryType;

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
	 * @param rechargeTime
	 * @param batteryType
	 */
	public ElectricCar (String mfr, String color, int power, int numWheels, String model, int maxRange, double safetyRating, double price, boolean aWD, int rechargeTime, String batteryType) {
			super(mfr, color, power, numWheels, model, maxRange, safetyRating, price, aWD);
			this.rechargeTime = rechargeTime;
			this.setBatteryType(batteryType);
	}
	/**
	 * @return the rechargeTime
	 */
	public int getRechargeTime() {
		return rechargeTime;
	}
	/**
	 * @param rechargeTime the rechargeTime to set
	 */
	public void setRechargeTime(int rechargeTime) {
		this.rechargeTime = rechargeTime;
	}

	/**
	 * @return the batteryType
	 */
	public String getBatteryType() {
		return batteryType;
	}
	/**
	 * @param batteryType the batteryType to set
	 */
	public void setBatteryType(String batteryType) {
		this.batteryType = batteryType;
	}

	public String display() {
		return super.display() + " " + rechargeTime + " " + batteryType;
	}
}