/*
 * Name: Jacky Ly
 * Student ID: 500890960
 * Section: CPS209-031
 */

 // import statements
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CarDealershipSimulator
{
	// Main method of class CarDealershipSimulator
	public static void main(String[] args) throws IOException
	{
	CarDealership newDealer = new CarDealership(); // Creates a CarDealership object
	ArrayList<Car> cars = new ArrayList<Car>(); // Creates an (initially empty) array list of type Car

	// Initialize variables
	final String header = String.format("%-3s %-11s %-6s %-8s %-5s %-8s %-10s %-5s %-3s %-7s", "#", "Brand", "Color", "Model", "MaxR", "SafetyR", "Price ($)", "AWD?", "RT", "Battery");
	String filename = "cars.txt", line = "", command = "";

	// Reads data from file and continues if data is valid
	if(addFromFile(filename, cars)) {
		Scanner input = new Scanner(System.in); // Creates a scanner object for keyboard input
		System.out.println("\nWelcome to Car Dealership Simulator!");
		commandsMenu(); // Displays commands menu
		System.out.println();
		System.out.println("Run the 'ADD' command to get started!");
		System.out.print("Enter a command (HELP for commands menu): ");

		// While the scanner has another line
		while (input.hasNextLine()) {
			try {
				line = input.nextLine();
				Scanner commandLine = new Scanner(line); // Creates another scanner object to read from the line
				command = commandLine.next();
				
				//	Checks if the word (i.e. string) is equal to one of the commands and if so, call the appropriate method via the CarDealership object  
				switch (command) {
					// Displays the inventory in the console
					case "L":
						if (!commandLine.hasNext()) {
							if (!newDealer.isEmpty) {
								addHeader(header);
								newDealer.displayInventory();
								System.out.println("\nInventory loaded successfully.");
							}
							else
								System.out.println("\nERROR: Inventory is empty!");
							break;
						}
						// Any command other than 'L' by itself will throw an exception
						commandLine.close();
						throw new NoSuchElementException();
					
					// Terminates the program
					case "Q":
						if (!commandLine.hasNext()) {
							commandLine.close();
							System.out.println("\nThank you for using Car Dealership Simulator! Have a great day!");
							return;
						}
						// Any command other than 'Q' by itself will throw an exception
						commandLine.close();
						throw new NoSuchElementException();
					
					// Buys a car given the car position
					case "BUY":
						if (commandLine.hasNextInt()) { // Checks for an integer value after 'BUY'
							int index = commandLine.nextInt();
							if (!commandLine.hasNext()) {
								Car currentCar = newDealer.buyCar(index);
								if (currentCar != null) {
									System.out.println("\nCar Details:");
									addHeader(header);
									System.out.printf("%-3d %s\n", index, currentCar.display());
									System.out.println("\nCar at position " + index + " bought successfully.");
								}
								else
									System.out.println("\nERROR: Invalid car selection!");
								break;
							}
							// Any command other than 'BUY' followed by an integer will throw an exception
							commandLine.close();
							throw new NoSuchElementException();
						}
						else
							System.out.println("\nERROR: Invalid car # position or not specified!");
						break;
					
					// Returns the last bought car
					case "RET":
						if (!commandLine.hasNext()) {
							Car returnCar = newDealer.carLastBought;
							newDealer.returnCar(returnCar);
							System.out.println("\nReturned last bought car to inventory.");
							break;
						}
						// Any command other than 'RET' by itself will throw an exception
						commandLine.close();
						throw new NoSuchElementException();
					
					// Adds the cars from the array list to the dealership inventory
					case "ADD":
						if (!commandLine.hasNext()) {
							newDealer.addCars(cars);
							System.out.println("\nAdded cars to dealership inventory.");
							break;
						}
						// Any command other than 'ADD' by itself will throw an exception
						commandLine.close();
						throw new NoSuchElementException();
					
					// Sorts the list by price (from least to most expensive)
					case "SPR":
						if (!commandLine.hasNext()) {
							if (!newDealer.isEmpty) {
								newDealer.sortByPrice();
								System.out.println("\nInventory sorted by price.");
							}
							else
								System.out.println("\nERROR: Inventory is empty!");
							break;
						}
						// Any command other than 'SPR' by itself will throw an exception
						commandLine.close();
						throw new NoSuchElementException();
					
					// Sorts the list by safety rating (lowest to highest)
					case "SSR":
						if (!commandLine.hasNext()) {
							if (!newDealer.isEmpty) {
								newDealer.sortBySafetyRating();
								System.out.println("\nInventory sorted by safety rating.");
							}
							else
								System.out.println("\nERROR: Inventory is empty!");
							break;
						}
						// Any command other than 'SSR' by itself will throw an exception
						commandLine.close();
						throw new NoSuchElementException();
					
					// Sorts the list by max range (from min to max)
					case "SMR":
						if (!commandLine.hasNext()) {
							if (!newDealer.isEmpty) {
								newDealer.sortByMaxRange();
								System.out.println("\nInventory sorted by max range.");
							}
							else
								System.out.println("\nERROR: Inventory is empty!");
							break;
						}
						// Any command other than 'SMR' by itself will throw an exception
						commandLine.close();
						throw new NoSuchElementException();
					
					// Filters the list by price given a minimum and maximum price
					case "FPR":
						if (commandLine.hasNextInt()) { // Checks for first integer after 'FPR'
							int minPrice = commandLine.nextInt();
							if (commandLine.hasNextInt()) { // Checks for second integer after 'FPR'
								int maxPrice = commandLine.nextInt();
								if (!commandLine.hasNext()) {
									if (!newDealer.isEmpty) {
										newDealer.filterByPrice(minPrice, maxPrice);
										System.out.println("\nInventory filtered by price between $" + minPrice + " and $" + maxPrice + ".");
									}
									else
										System.out.println("\nERROR: Inventory is empty!");
									break;
								}
								// Any command other than 'FPR' followed by two integers will throw an exception
								commandLine.close();
								throw new NoSuchElementException();
							}
							// Second argument is invalid or missing
							else
								System.out.println("\nERROR: Invalid max price or not specified!");
						}
						// First argument is invalid or missing
						else
							System.out.println("\nERROR: Invalid price range or not specified!");
						break;
					
					// Filters the list by electric cars (only shows cars that are electric)
					case "FEL":
						if (!commandLine.hasNext()) {
							if (!newDealer.isEmpty) {
								newDealer.filterByElectric();
								System.out.println("\nInventory filtered by electric cars.");
							}
							else
								System.out.println("\nERROR: Inventory is empty!");
							break;
						}
						// Any command other than 'FEL' by itself will throw an exception
						commandLine.close();
						throw new NoSuchElementException();
					
					// Filters the list by AWD (only shows cars that are AWD)
					case "FAW":
						if (!commandLine.hasNext()) {
							if (!newDealer.isEmpty) {
								newDealer.filterByAWD();
								System.out.println("\nInventory filtered by AWD cars.");
							}
							else
								System.out.println("\nERROR: Inventory is empty!");
							break;
						}
						// Any command other than 'FAW' by itself will throw an exception
						commandLine.close();
						throw new NoSuchElementException();
					
					// Clears the filters currently set (all filters are reset to false)
					case "FCL":
						if (!commandLine.hasNext()) {
							if (!newDealer.isEmpty) {
								newDealer.FiltersClear();
								System.out.println("\nFilters cleared successfully.");
							}
							else
								System.out.println("\nERROR: Inventory is empty!");
							break;
						}
						// Any command other than 'FCL' by itself will throw an exception
						commandLine.close();
						throw new NoSuchElementException();
					
					// Displays the commands list in the console
					case "HELP":
						if (!commandLine.hasNext()) {
							commandsMenu();
							break;
						}
						// Any command other than 'HELP' by itself will throw an exception
						commandLine.close();
						throw new NoSuchElementException();
					
					// **EASTER EGG** //
					case "F":
						if (!commandLine.hasNext()) {
							System.out.println("\nYou have paid your respects.");
							break;
						}
						// Any command other than 'F' by itself will throw an exception
						commandLine.close();
						throw new NoSuchElementException();
					
					// Any other unknown commands will throw an exception
					default:
						commandLine.close();
						throw new NoSuchElementException();
					}
					commandLine.close();
					line = "";
				}
				// Catches any command that is not recognized
				catch (NoSuchElementException NSE) {
					System.out.println("\nERROR: '" + line + "' is an unrecognized command! Please check the commands list and try again!");
					line = "";
				}
				// Catches the exception where an index is being accessed that is greater than the size of the object
				catch (IndexOutOfBoundsException IOB) {
					System.out.println(IOB.getMessage());
				}
				// Catches wrong data type for any arguments
				catch (IllegalArgumentException IAE) {
					System.out.println(IAE.getMessage());
				}
				// Catches any other exception
				catch (Exception ex) {
					System.out.println("\nERROR: " + ex + " has occurred! Please try again!");
				}
				System.out.print("\nEnter another command (HELP for commands menu): ");
			}
			input.close();
		}
	}
	/**
	 * Prints header string to be used with displayInventory()
	 * @param header
	 */
	private static void addHeader(String header) {
	System.out.println();
	System.out.println(header);
	for (int i = 0; i < header.length() + 2; i++) {
		System.out.print("-");
	}
	System.out.println();
	}

	// Displays the commands menu
	private static void commandsMenu() {
		System.out.println();
		System.out.println("COMMANDS MENU");
		System.out.printf("%7s  |  %-37s %-33s %-30s %s\n", "General", "ADD - Add Cars To Inventory", "L - Load Inventory", "Q - Quit Program", "HELP - Display Commands Menu");
		System.out.printf("%7s  |  %-37s %s\n", "Actions", "BUY [num] - Buy 'num' Car", "RET - Return Car");
		System.out.printf("%7s  |  %-37s %-33s %s\n", "Sort", "SPR - Sort By Price", "SSR - Sort By Safety Rating", "SMR - Sort By Max Range");
		System.out.printf("%7s  |  %-37s %-33s %-30s %s\n", "Filter", "FPR [min] [max] - Filter By Price", "FEL - Filter By Electric", "FAW - Filter By AWD", "FCL - Clear Filters");
	}

	/**
	 * Reads from a text file and converts the data to Car objects, then add them to the array list
	 * @param filename of text file to read from
	 * @param cars ArrayList of Car objects
	 */
	private static boolean addFromFile(String filename, ArrayList<Car> cars) throws FileNotFoundException {
		try {
			Scanner scan = new Scanner(new File(filename)); // Creates a new scanner to read from a file
			ArrayList<Object> specsList = new ArrayList<Object>(); // Array list to store the data
			
			// Constant variables
			final String BATTERY_TYPE = "Lithium";
			final int NUM_WHEELS = 4;

			// Initialize variables
			int modelInt, powerInt, rechargeTimeFile = 0;
			boolean aWDValue;
			
			System.out.println("Importing from " + filename + "...");
			// While the file still has lines remaining
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				Scanner specsLine = new Scanner(line); // Creates a new scanner to read from the line
				while (specsLine.hasNext()) {
					String specs = specsLine.next();
					specsList.add(specs);
				}
				specsLine.close();

				/* 
				 * Extracts the data from the array list and prepares the data to construct a Car object
				 */
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
				default: // Invalid data values will throw an exception
					throw new IllegalArgumentException("\nERROR: The file contains an invalid model!");
				}

				String powerFile = (String)specsList.get(3);
				switch (powerFile) {
				case "ELECTRIC_MOTOR":
					powerInt = Car.ELECTRIC_MOTOR;
					break;
				case "GAS_ENGINE":
					powerInt = Car.GAS_ENGINE;
					break;
				default: // Invalid data values will throw an exception
					throw new IllegalArgumentException("\nERROR: THe file contains an invalid power type!");
				}

				double safetyRatingFile = Double.parseDouble((String)specsList.get(4));
				int maxRangeFile = Integer.parseInt((String)specsList.get(5));
				String aWDFile = (String)specsList.get(6);
				switch(aWDFile) {
					case "AWD":
						aWDValue = true;
						break;
					case "2WD":
						aWDValue = false;
						break;
					default: // Invalid data values will throw an exception
						throw new IllegalArgumentException("\nERROR: THe file contains an invalid AWD value!");
				}

				int priceFile = Integer.parseInt((String)specsList.get(7));
				if (specsList.size() == 9) {
					rechargeTimeFile = Integer.parseInt((String)specsList.get(8));
					// Constructs an electric car
					ElectricCar car = new ElectricCar(brandFile, colorFile, powerInt, NUM_WHEELS, modelInt, maxRangeFile, safetyRatingFile, priceFile, aWDValue, rechargeTimeFile, BATTERY_TYPE);
					cars.add(car); // Adds the car object to the array list
				}
				else {
					// Constructs a car object
					Car car = new Car(brandFile, colorFile, powerInt, 4, modelInt, maxRangeFile, safetyRatingFile, priceFile, aWDValue);
					cars.add(car); // Adds the car object to the array list
				}
				specsList.clear(); // Clears the array list for the next line of data
			}
			scan.close();
			return true;
		}
		// Catches invalid data type or values
		catch (IllegalArgumentException IAE) {
			System.out.println("\n" + IAE);
			return false;
		}
		// Catches invalid file name
		catch (FileNotFoundException e) {
			System.out.println("\nERROR: " + e + "\nPlease check the file location and try again!");
			return false;
		}
	}
}