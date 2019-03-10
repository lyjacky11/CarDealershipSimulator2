/*
 * Name: Jacky Ly
 * Student ID: 500890960
 * Section: CPS209-031
 */

import java.util.ArrayList;
import java.util.Scanner;
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