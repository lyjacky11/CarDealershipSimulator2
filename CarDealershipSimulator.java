/*
 * Name: Jacky Ly
 * Student ID: 500890960
 * Section: CPS209-031
 */

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Collections;
import java.util.Comparator;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CarDealershipSimulator 
{
  /**
   * @param header string for use with displayInventory()
   */
  private static void addHeader(String header) {
	System.out.println();
	System.out.println(header);
	for (int i = 0; i < header.length() + 2; i++) {
		System.out.print("-");
	}
	System.out.println();
	}

	/**
	 * @param filename of text file to read from
	 * @param cars ArrayList of Car objects
	 */
	private static void readAddCars(String filename, ArrayList<Car> cars) throws FileNotFoundException {
		try {
			Scanner scan = new Scanner(new File(filename));
			ArrayList<Object> specsList = new ArrayList<Object>();
			final String BATTERY_TYPE = "Lithium";
			final int NUM_WHEELS = 4;
			int modelInt, powerInt, rechargeTimeFile = 0;
		
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				Scanner specsLine = new Scanner(line);
				while (specsLine.hasNext()) {
					String specs = specsLine.next();
					specsList.add(specs);
				}
				specsLine.close();

				String brandFile = (String)specsList.get(0);
				String colorFile = (String)specsList.get(1);

				String modelFile = (String)specsList.get(2);
				switch (modelFile) {
				case "SEDAN":
					modelInt = Car.SEDAN;
					break;
				case "SUV":
					modelInt = Car.SUV;
					break;
				case "SPORTS":
					modelInt = Car.SPORTS;
					break;
				case "MINIVAN":
					modelInt = Car.MINIVAN;
					break;
				default:
					modelInt = -1;
					break;
				}

				String powerFile = (String)specsList.get(3);
				switch (powerFile) {
				case "ELECTRIC_MOTOR":
					powerInt = Car.ELECTRIC_MOTOR;
					break;
				case "GAS_ENGINE":
					powerInt = Car.GAS_ENGINE;
					break;
				default:
					powerInt = -1;
					break;
				}

				double safetyRatingFile = Double.parseDouble((String)specsList.get(4));
				int maxRangeFile = Integer.parseInt((String)specsList.get(5));
				String aWDFile = (String)specsList.get(6);
				
				boolean aWDValue = false;
				if (aWDFile.equals("AWD"))
					aWDValue = true;

				int priceFile = Integer.parseInt((String)specsList.get(7));

				if (specsList.size() == 9) {
					rechargeTimeFile = Integer.parseInt((String)specsList.get(8));
					ElectricCar car = new ElectricCar(brandFile, colorFile, powerInt, NUM_WHEELS, modelInt, maxRangeFile, safetyRatingFile, priceFile,
							aWDValue, rechargeTimeFile, BATTERY_TYPE);
					cars.add(car);
				}
				else {
					Car car = new Car(brandFile, colorFile, powerInt, 4, modelInt, maxRangeFile, safetyRatingFile, priceFile, aWDValue);
					cars.add(car);
				}
				specsList.clear();
			}
			scan.close();
			return;
		}
		catch (FileNotFoundException e) {
			System.out.println("The file name specified is not found! Please check the file location and try again!");
			System.exit(0);
		}
	}

	// Display commands menu
	private static void commandsMenu() {
		System.out.println();
		System.out.println("COMMANDS MENU");
		System.out.printf("%7s  |  %-40s %-33s %-30s %s\n", "General", "ADD - Add Cars To Inventory", "L - Load Inventory", "Q - Quit Program", "HELP - Display Commands Menu");
		System.out.printf("%7s  |  %-40s %s\n", "Actions", "BUY [num] - Buy 'num' Car", "RET - Return Car");
		System.out.printf("%7s  |  %-40s %-33s %s\n", "Sort", "SPR - Sort By Price", "SSR - Sort By Safety Rating", "SMR - Sort By Max Range");
		System.out.printf("%7s  |  %-40s %-33s %-30s %s\n", "Filter", "FPR [min] [max] - Filter By Price", "FEL - Filter By Electric", "FAW - Filter By AWD", "FCL - Clear Filters");
	}
	
  public static void main(String[] args) throws IOException
  {
	// Create a CarDealership object
	CarDealership newDealer = new CarDealership();
	// Then create an (initially empty) array list of type Car
	ArrayList<Car> cars = new ArrayList<Car>();
	// Then create some new car objects of different types
	String header = String.format("%-4s %-11s %-6s %-8s %-5s %-8s %-10s %-6s %-3s %-7s", "Pos", "Brand", "Color", "Model", "MaxR", "SafetyR", "Price ($)", "AWD?", "RT", "Battery");
	// See the cars file for car object details
	String filename = "cars.txt", line = "", command = "";

	// Add the car objects to the array list
	readAddCars(filename, cars);

  	// The ADD command should hand this array list to CarDealership object via the addCars() method
	
	// Create a scanner object
	Scanner input = new Scanner(System.in);
	System.out.println("Welcome to Car Dealership Simulator!");
	commandsMenu();
	System.out.println();
	System.out.println("Run the 'ADD' command to get started!");
	System.out.print("Enter a command: (HELP for commands menu): ");
	// while the scanner has another line
	while (input.hasNextLine()) {
	//  read the input line
		line = input.nextLine();
	//  create another scanner object (call it "commandLine" or something) using the input line instead of System.in
		Scanner commandLine = new Scanner(line);
	//  read the next word from the commandLine scanner
		command = commandLine.next();
		//	check if the word (i.e. string) is equal to one of the commands and if so, call the appropriate method via the CarDealership object  
		switch (command) {
			case "L":
				if (!newDealer.isEmpty) {
					addHeader(header);
					newDealer.displayInventory();
					System.out.println("\nInventory loaded successfully.");
				}
				else
					System.out.println("\nERROR: Inventory is empty!");
				break;
			case "Q":
				commandLine.close();
				System.out.println("\nThank you for shopping at Car Dealership Simulator! Have a great day!");
				return;
			case "BUY":
				int index = commandLine.nextInt();
				Car currentCar = newDealer.buyCar(index);
				if (currentCar != null) {
					System.out.println("\nCar Details:");
					addHeader(header);
					System.out.printf("%-4d %s\n", index, currentCar.display());
					System.out.println("\nCar at position " + index + " bought successfully.");
				}
				else
					System.out.println("\nERROR: Invalid car selection!");
				break;
			case "RET":
				Car returnCar = newDealer.carLastBought;
				newDealer.returnCar(returnCar);
				if (returnCar != null)
					System.out.println("\nReturned last car bought to inventory.");
				else
					System.out.println("\nERROR: No car found to return to inventory!");
				break;
			case "ADD":
				if (cars.size() > 0) {
					newDealer.addCars(cars);
					System.out.println("\nAdded cars from file to dealership inventory.");
				}
				else
					System.out.println("\nERROR: No cars from file found!");
				break;
			case "SPR":
				newDealer.sortByPrice();
				System.out.println("\nInventory sorted by price.");
				break;
			case "SSR":
				newDealer.sortBySafetyRating();
				System.out.println("\nInventory sorted by safety rating.");
				break;
			case "SMR":
				newDealer.sortByMaxRange();
				System.out.println("\nInventory sorted by max range.");
				break;
			case "FPR":
				if (commandLine.hasNextInt()) {
					int minPrice = commandLine.nextInt();
					if (commandLine.hasNextInt()) {
						int maxPrice = commandLine.nextInt();
						newDealer.filterByPrice(minPrice, maxPrice);
						System.out.println("\nInventory filtered by price between $" + minPrice + " and $" + maxPrice + ".");
					}
					else
					System.out.println("\nERROR: Max price not specified!");
				}
				else
					System.out.println("\nERROR: No price range specified!");
				break;
			case "FEL":
				newDealer.filterByElectric();
				System.out.println("\nInventory filtered by electric cars.");
				break;
			case "FAW":
				newDealer.filterByAWD();
				System.out.println("\nInventory filtered by AWD cars.");
				break;
			case "FCL":
				newDealer.FiltersClear();
				System.out.println("\nFilters cleared successfully.");
				break;
			case "HELP":
				commandsMenu();
				break;
			default:
				System.out.println("\nERROR: Unknown command. Please try again!");
				break;
			}
			commandLine.close();
			System.out.print("\nEnter another command (HELP for commands menu): ");
		}
		input.close();
	}
}

class CarDealership {
	private ArrayList<Car> cars;
	private ArrayList<Integer> filterCars;
	private double minPrice, maxPrice;
	private boolean AWD, electric, price;
	public boolean isEmpty;
	public Car carLastBought;

	class SRComparator implements Comparator<Car> {
		/**
		 * @param car1
		 * @param car2
		 * @return int value of result
		 */
		public int compare(Car car1, Car car2) {
			if (car1.getSafetyRating() < car2.getSafetyRating()) return -1;
			if (car1.getSafetyRating() > car1.getSafetyRating()) return 1;
			return 0;
		}
	}

	class MRComparator implements Comparator<Car> {
		/**
		 * @param car1
		 * @param car2
		 * @return int value of result
		 */
		public int compare(Car car1, Car car2) {
			if (car1.getMaxRange() < car2.getMaxRange()) return -1;
			if (car1.getMaxRange() > car1.getMaxRange()) return 1;
			return 0;
		}
	}

	public CarDealership () {
		cars = new ArrayList<Car>();
		filterCars = new ArrayList<Integer>();
		this.isEmpty = true;
	}

	/**
	 * @param newCars ArrayList of Car objects
	 */
	public void addCars(ArrayList<Car> newCars) {
		cars.addAll(newCars);
		this.isEmpty = false;
		for (int i = 0; i < cars.size(); i++) {
			filterCars.add(i);
		}
	}

	/**
	 * @param index of the car to buy
	 * @return carLastBought Car object
	 */
	public Car buyCar(int index) {
		if (index < cars.size()) {
			carLastBought = cars.get(index);
			cars.remove(index);
			filterCars.remove(new Integer(cars.size() - 1));
			if (cars.size() <= 0)
				this.isEmpty = true;
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
			filterCars.add(new Integer(cars.indexOf(car)));
			this.isEmpty = false;
			carLastBought = null;
		}
	}

	/* Filter has some bugs (with price as second filter) */
	public void displayInventory() {
		for (int i = 0; i < cars.size(); i++) {
			Car currentCar = cars.get(i);
			if (filterCars.contains(i)) {
				String output = String.format("%-4d %s", i, currentCar.display());
				if (electric) {
					if(currentCar.getPower() == Car.ELECTRIC_MOTOR)
						System.out.println(output);
					else
						filterCars.remove(new Integer(i));
				}
				else if (AWD) {
					if(currentCar.isAWD())
						System.out.println(output);
					else
						filterCars.remove(new Integer(i));
				}
				else if (price) {
					if(currentCar.getPrice() >= minPrice && currentCar.getPrice() <= maxPrice)
						System.out.println(output);
					else
						filterCars.remove(new Integer(i));
				}
				else if (electric && AWD) {
					if(currentCar.getPower() == Car.ELECTRIC_MOTOR && currentCar.isAWD())
						System.out.println(output);
					else
						filterCars.remove(new Integer(i));
				}
				else if (AWD && price) {
					if (currentCar.isAWD()) {
						if (currentCar.getPrice() >= minPrice && currentCar.getPrice() <= maxPrice)
							System.out.println(output);
						else
							filterCars.remove(new Integer(i));
					}
					else
						filterCars.remove(new Integer(i));
				}
				else if (electric && price) {
					if (currentCar.getPower() == Car.ELECTRIC_MOTOR) {
						if (currentCar.getPrice() >= minPrice && currentCar.getPrice() <= maxPrice)
							System.out.println(output);
						else
							filterCars.remove(new Integer(i));
					}
					else
						filterCars.remove(new Integer(i));
				}
				else if (electric && AWD && price) {
					if (currentCar.getPower() == Car.ELECTRIC_MOTOR && currentCar.isAWD()) {
						if (currentCar.getPrice() >= minPrice && currentCar.getPrice() <= maxPrice)
							System.out.println(output);
						else
							filterCars.remove(new Integer(i));
					}
					else
						filterCars.remove(new Integer(i));
				}
				else
					System.out.println(output);
			}
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
		this.minPrice = Integer.MIN_VALUE;
		this.maxPrice = Integer.MAX_VALUE;
		filterCars.clear();
		for (int i = 0; i < cars.size(); i++) {
			filterCars.add(i);
		}
	}

	public void sortByPrice() {
		Collections.sort(cars);
	}

	public void sortBySafetyRating() {
		Collections.sort(cars, new SRComparator());
	}

	public void sortByMaxRange() {
		Collections.sort(cars, new MRComparator());
	}
}

class Vehicle {
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

class Car extends Vehicle implements Comparable<Car> {
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
		return String.format("%s %-8s %-5d %-8.2f %-10.2f %-6s", super.display(), checkModel(model), maxRange, safetyRating, price, checkAWD(AWD));
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
	public ElectricCar (String mfr, String color, int power, int numWheels, int model, int maxRange, double safetyRating, double price, boolean aWD, int rechargeTime, String batteryType) {
			super(mfr, color, power, numWheels, model, maxRange, safetyRating, price, aWD);
			this.rechargeTime = rechargeTime;
			this.setBatteryType(batteryType);
	}
	/**
	 * @return rechargeTime
	 */
	public int getRechargeTime() {
		return rechargeTime;
	}
	/**
	 * @param rechargeTime to set
	 */
	public void setRechargeTime(int rechargeTime) {
		this.rechargeTime = rechargeTime;
	}

	/**
	 * @return batteryType
	 */
	public String getBatteryType() {
		return batteryType;
	}
	/**
	 * @param batteryType to set
	 */
	public void setBatteryType(String batteryType) {
		this.batteryType = batteryType;
	}

	/**
	 * @return the ElectricCar object specifications
	 */
	public String display() {
		return String.format("%s %-3d %-5s", super.display(), rechargeTime, batteryType);
	}
}