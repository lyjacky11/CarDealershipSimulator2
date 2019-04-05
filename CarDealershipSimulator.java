/*
 * Name: Jacky Ly
 * Student ID: 500890960
 * Section: CPS209-031
 */

/*
 * Import statements
 */
import java.util.ArrayList;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

public class CarDealershipSimulator
{
	/**
	 * Displays the header given the string and format
	 * 
	 * @param header
	 */
	private static void addHeader(String header) {
		System.out.println();
		System.out.println(header);
		for (int i = 0; i < header.length() + 14; i++) {
			System.out.print("-");
		}
		System.out.println();
	}
	
	/*
	 * Displays the commands list
	 */
	private static void commandsMenu() {
		System.out.println();
		System.out.println("COMMANDS MENU");
		System.out.printf("%7s  |  %-40s %-50s %-40s %s\n", "General", "ADD - Add Cars To Inventory", "L - Load Inventory", "Q - Quit Program", "HELP - Display Commands Menu");
		System.out.printf("%7s  |  %-40s %s\n", "Actions", "BUY [VIN] - Buy Car By VIN #", "RET - Return Last Transaction");
		System.out.printf("%7s  |  %-40s %-50s %s\n", "Sort", "SPR - Sort By Price", "SSR - Sort By Safety Rating", "SMR - Sort By Max Range");
		System.out.printf("%7s  |  %-40s %-50s %-40s %s\n", "Filter", "FPR [min] [max] - Filter By Price", "FEL - Filter By Electric", "FAW - Filter By AWD", "FCL - Clear Filters");
		System.out.printf("%7s  |  %-40s %-50s %s\n            %-40s %s\n", "Sales", "SALES - All Transactions (2019)", "SALES [m] - All Transactions By Month (0-11)", "SALES TEAM - List of Sales Team Names", "SALES TOPSP - Top Salesperson(s)", "SALES STATS - Sales Statistics");
	}

	/**
	 * Main method of the class
	 * 
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException
	{
	/*
	 * Initialize class objects and variables
	 */
	CarDealership newDealer = new CarDealership();
	ArrayList<Car> cars = new ArrayList<Car>();
	SalesTeam salesTeam = new SalesTeam();
	final String carHeader = String.format("%-3s %-4s %-11s %-6s %-8s %-5s %-8s %-10s %-5s %-3s %-7s", "#", "VIN", "Brand", "Color", "Model", "MaxR", "SafetyR", "Price ($)", "AWD?", "RT", "Battery");
	final String transHeader = String.format("%-8s %-5s %-7s %-13s %-13s %s", "TransID", "Type", "VIN #", "Salesperson", "Price ($)", "Date");
	final String salesHeader = String.format("%-15s %15s", "Salesperson", "# of Cars Sold");
	final String[] monthNames = newDealer.getAccSystem().monthNames;
	String filename = "cars.txt", line = "", command = "";
	/*
	 * Reads data from file and continues if the data is valid
	 */
	if(addFromFile(filename, cars)) {
		Scanner input = new Scanner(System.in);
		System.out.println("\nWelcome to Car Dealership Simulator 2!");
		commandsMenu();
		System.out.println("\nRun the 'ADD' command to get started!");
		System.out.print("Enter a command (HELP for commands menu): ");
		/*
		 * When there is another command entered, process the command
		 */
		while (input.hasNextLine()) {
			try {
				line = input.nextLine();
				Scanner commandLine = new Scanner(line);
				command = commandLine.next();
				/*
				 * If the input is equal to one of the commands, call the appropriate method(s)
				 */
				switch (command) {
					/**
					 * Adds cars to the dealership inventory
					 */ 
					case "ADD":
						if (!commandLine.hasNext()) {
							if (newDealer.isEmpty) {
								newDealer.addCars(cars);
								System.out.println("\nAdded cars to dealership inventory.");
								break;
							}
							commandLine.close();
							throw new IllegalArgumentException("\nERROR: Cars already added to the inventory!");
						}
						/*
						 * Any command with other arguments will throw an exception
						 */ 
						commandLine.close();
						throw new NoSuchElementException();
					/*
					 * Displays the car inventory in the console
					 */
					case "L":
						if (!commandLine.hasNext()) {
							if (!newDealer.isEmpty) {
								System.out.println("\nDEALERSHIP INVENTORY:");
								addHeader(carHeader);
								newDealer.displayInventory();
								System.out.println("\nInventory loaded successfully.");
								break;
							}
							commandLine.close();
							throw new IllegalArgumentException("\nERROR: Car inventory is empty!");
						}
						/*
						 * Any command with other arguments will throw an exception
						 */ 
						commandLine.close();
						throw new NoSuchElementException();
					/*
					 * Buys a car given the VIN number
					 */ 
					case "BUY":
						if (commandLine.hasNextInt()) {
							int VIN = commandLine.nextInt();
							if (!commandLine.hasNext()) {
								String currentCar = newDealer.buyCar(VIN);
								System.out.println("\nTRANSACTION INFO:");
								addHeader(transHeader);
								System.out.println(currentCar);
								System.out.println("\nCar with VIN #" + VIN + " bought successfully.");
								break;
							}
							/*
						 	 * Any command with other arguments will throw an exception
						 	 */ 
							commandLine.close();
							throw new NoSuchElementException();
						}
						commandLine.close();
						throw new IllegalArgumentException("\nERROR: Invalid VIN # or not specified!");
					/**
					 * Returns the last 'BUY' transaction
					 */ 
					case "RET":
						if (!commandLine.hasNext()) {
							int id = newDealer.lastTransID;
							System.out.println("\nTRANSACTION INFO:");
							System.out.println("-----------------------------------------------------");
							newDealer.returnCar(id);
							break;
						}
						/*
						 * Any command with other arguments will throw an exception
						 */ 
						commandLine.close();
						throw new NoSuchElementException();
					/*
					 * Sorts the list by price (from least to most expensive)
					 */ 
					case "SPR":
						if (!commandLine.hasNext()) {
							if (!newDealer.isEmpty) {
								newDealer.sortByPrice();
								System.out.println("\nInventory sorted by price.");
								break;
							}
							commandLine.close();
							throw new IllegalArgumentException("\nERROR: Car inventory is empty!");
						}
						/*
						 * Any command with other arguments will throw an exception
						 */ 
						commandLine.close();
						throw new NoSuchElementException();
					/*
					 * Sorts the list by safety rating (lowest to highest)
					 */ 
					case "SSR":
						if (!commandLine.hasNext()) {
							if (!newDealer.isEmpty) {
								newDealer.sortBySafetyRating();
								System.out.println("\nInventory sorted by safety rating.");
								break;
							}
							commandLine.close();
							throw new IllegalArgumentException("\nERROR: Car inventory is empty!");
						}
						/*
						 * Any command with other arguments will throw an exception
						 */ 
						commandLine.close();
						throw new NoSuchElementException();
					/*
					 * Sorts the list by max range (from min to max)
					 */ 
					case "SMR":
						if (!commandLine.hasNext()) {
							if (!newDealer.isEmpty) {
								newDealer.sortByMaxRange();
								System.out.println("\nInventory sorted by max range.");
								break;
							}
							commandLine.close();
							throw new IllegalArgumentException("\nERROR: Car inventory is empty!");
						}
						/*
						 * Any command with other arguments will throw an exception
						 */ 
						commandLine.close();
						throw new NoSuchElementException();
					/*
					 * Filters the list by price given a minimum and maximum price
					 */ 
					case "FPR":
						if (commandLine.hasNextInt()) {
							int minPrice = commandLine.nextInt();
							if (commandLine.hasNextInt()) {
								int maxPrice = commandLine.nextInt();
								if (!commandLine.hasNext()) {
									if (!newDealer.isEmpty) {
										newDealer.filterByPrice(minPrice, maxPrice);
										System.out.println("\nInventory filtered by price between $" + minPrice + " and $" + maxPrice + ".");
										break;
									}
									commandLine.close();
									throw new IllegalArgumentException("\nERROR: Car inventory is empty!");
								}
								/*
						 		* Any command with other arguments will throw an exception
						 		*/ 
								commandLine.close();
								throw new NoSuchElementException();
							}
							/*
							 * If second argument is invalid or missing
							 */ 
							commandLine.close();
							throw new IllegalArgumentException("\nERROR: Invalid max price or not specified!");
						}
						/*
						 * If first argument is invalid or missing
						 */ 
						commandLine.close();
						throw new IllegalArgumentException("\nERROR: Invalid price range or not specified!");
					/*
					 * Filters the list by electric cars (only shows cars that are electric)
					 */ 
					case "FEL":
						if (!commandLine.hasNext()) {
							if (!newDealer.isEmpty) {
								newDealer.filterByElectric();
								System.out.println("\nInventory filtered by electric cars.");
								break;
							}
							commandLine.close();
							throw new IllegalArgumentException("\nERROR: Car inventory is empty!");
						}
						/*
						 * Any command with other arguments will throw an exception
						 */ 
						commandLine.close();
						throw new NoSuchElementException();
					/*
					 * Filters the list by AWD (only shows cars that are AWD)
					 */ 
					case "FAW":
						if (!commandLine.hasNext()) {
							if (!newDealer.isEmpty) {
								newDealer.filterByAWD();
								System.out.println("\nInventory filtered by AWD cars.");
								break;
							}
							commandLine.close();
							throw new IllegalArgumentException("\nERROR: Car inventory is empty!");
						}
						/*
						 * Any command with other arguments will throw an exception
						 */ 
						commandLine.close();
						throw new NoSuchElementException();
					/*
					 * Clears the filters currently set (all filters are reset to false)
					 */
					case "FCL":
						if (!commandLine.hasNext()) {
							if (!newDealer.isEmpty) {
								newDealer.FiltersClear();
								System.out.println("\nFilters cleared successfully.");
								break;
							}
							commandLine.close();
							throw new IllegalArgumentException("\nERROR: Car inventory is empty!");
						}
						/*
						 * Any command with other arguments will throw an exception
						 */ 
						commandLine.close();
						throw new NoSuchElementException();
					/*
					 * Commands for sales and transactions
					 */
					case "SALES":
						/*
						 * Prints all transactions for the year (2019)
						 */
						if (!commandLine.hasNext()) {
							if (newDealer.getAccSystem().getTransList().size() > 0) {
								System.out.println("\nALL TRANSACTIONS (2019):");
								addHeader(transHeader);
							}
							newDealer.getAccSystem().getAllTrans();
						}
						/*
						 * Prints all transactions for the given month
						 */
						else if (commandLine.hasNextInt()) {
							int month = commandLine.nextInt();
							if (!commandLine.hasNext()) {
								if (newDealer.getAccSystem().getTransList().size() > 0) {
									if (month >= 0 && month < 12) {
										System.out.println("\nTRANSACTIONS for " + monthNames[month]);
										addHeader(transHeader);
									}
									else {
										commandLine.close();
										throw new IllegalArgumentException("\nERROR: Invalid month selection! Please enter a number from 0-11!");
									}
								}
								newDealer.getAccSystem().getMonthTrans(month);
								break;
							}
							/*
							 * Any command with other arguments will throw an exception
							 */
							commandLine.close();
							throw new NoSuchElementException();
						}
						else {
							String arg = commandLine.next();
							/*
							 * Prints all the salesperson names
							 */
							if (arg.equals("TEAM")) {
								if (!commandLine.hasNext()) {
									System.out.println("\nSALES TEAM:");
									System.out.println("--------------------------------------------------------------");
									System.out.println(salesTeam.getAllSP());
									break;
								}
								/*
								 * Any command with other arguments will throw an exception
								 */
								commandLine.close();
								throw new NoSuchElementException();
							}
							/*
							 * Prints the sales person who sold the most cars of the year
							 */
							else if (arg.equals("TOPSP")) {
								if (!commandLine.hasNext()) {
									if (newDealer.getAccSystem().getTransList().size() > 0) {
										System.out.println("\nTOP SALES PERSON(S):");
										addHeader(salesHeader);
									}
									System.out.println(newDealer.getAccSystem().getTopSP());
									break;
								}
								/*
								 * Any command with other arguments will throw an exception
								 */
								commandLine.close();
								throw new NoSuchElementException();
							}
							/*
							 * Prints the stats of the sales team
							 */
							else if (arg.equals("STATS")) {
								if (!commandLine.hasNext()) {
									System.out.println("\nSALES STATISTICS:");
									System.out.println("----------------------------------------");
									System.out.printf("Total Sales: $%.2f\n", newDealer.getAccSystem().getTotalSales());
									System.out.printf("Average Sales Per Month: $%.2f\n", newDealer.getAccSystem().getAvgSales());
									System.out.printf("Total Cars Sold: %d\n", newDealer.getAccSystem().getTotalSold());
									System.out.printf("Total Car Returns: %d\n", newDealer.getAccSystem().getTotalReturns());
									System.out.printf("\nHighest Sales Month(s):\n%s", newDealer.getAccSystem().getHighestMonth());
									break;
								}
								/*
								 * Any command with other arguments will throw an exception
								 */
								commandLine.close();
								throw new NoSuchElementException();
							}
							/*
							 * Any command with other arguments will throw an exception
							 */
							commandLine.close();
							throw new NoSuchElementException();
						}
					/*
					 * Displays the commands list in the console
					 */
					case "HELP":
						if (!commandLine.hasNext()) {
							commandsMenu();
							break;
						}
						/*
						 * Any command with other arguments will throw an exception
						 */ 
						commandLine.close();
						throw new NoSuchElementException();
					/*
					 * Terminates the program
					 */ 
					case "Q":
						if (!commandLine.hasNext()) {
							commandLine.close();
							System.out.println("\nThank you for using Car Dealership Simulator! Have a great day!");
							return;
						}
						/*
						 * Any command with other arguments will throw an exception
						 */
						commandLine.close();
						throw new NoSuchElementException();
					/*
					 * Any other unknown commands will throw an exception
					 */ 
					default:
						commandLine.close();
						throw new NoSuchElementException();
					}
					commandLine.close();
					line = "";
				}
				/*
				 * Catches any command that is not recognized
				 */
				catch (NoSuchElementException NSE) {
					System.out.println("\nERROR: '" + line + "' is an unrecognized command! Please check the commands list and try again!");
					line = "";
				}
				/*
				 * Catches the exception where an index is being accessed that is greater or less than the size of the object
				 */
				catch (IndexOutOfBoundsException IOB) {
					System.out.println(IOB.getMessage());
				}
				/*
				 * Catches wrong data type for any arguments
				 */ 
				catch (IllegalArgumentException IAE) {
					System.out.println(IAE.getMessage());
				}
				/**
				 * Catches any other exception
				 */ 
				catch (Exception ex) {
					System.out.println("\nERROR: " + ex + " has occurred! Please try again!");
				}
				System.out.print("\nEnter another command (HELP for commands menu): ");
			}
			input.close();
		}
	}

	/**
	 * Reads from a text file and converts the data to Car objects, then add them to the array list
	 * 
	 * @param filename of text file to read from
	 * @param cars ArrayList of Car objects
	 * @throws FileNotFoundException
	 */
	private static boolean addFromFile(String filename, ArrayList<Car> cars) throws FileNotFoundException {
		try {
			Scanner scan = new Scanner(new File(filename));
			ArrayList<Object> specsList = new ArrayList<Object>();
			/*
			 * Initialize variables and constants
			 */
			final String BATTERY_TYPE = "Lithium";
			final int NUM_WHEELS = 4;
			int modelInt, powerInt, rechargeTimeFile = 0;
			boolean aWDValue;
			/*
			 * While the file still has data to be imported
			 */
			while (scan.hasNextLine()) {
				String line = scan.nextLine();
				Scanner specsLine = new Scanner(line);
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
				default:
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
				default:
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
					default:
						throw new IllegalArgumentException("\nERROR: THe file contains an invalid AWD value!");
				}
				int priceFile = Integer.parseInt((String)specsList.get(7));
				if (specsList.size() == 9) {
					rechargeTimeFile = Integer.parseInt((String)specsList.get(8));
					/*
					 * Constructs an electric car object with imported data
					 */ 
					ElectricCar car = new ElectricCar(brandFile, colorFile, powerInt, NUM_WHEELS, modelInt, maxRangeFile, safetyRatingFile, priceFile, aWDValue, rechargeTimeFile, BATTERY_TYPE);
					cars.add(car);
				}
				else {
					/*
					 * Constructs a car object with imported data
					 */ 
					Car car = new Car(brandFile, colorFile, powerInt, 4, modelInt, maxRangeFile, safetyRatingFile, priceFile, aWDValue);
					cars.add(car);
				}
				specsList.clear();
			}
			scan.close();
			return true;
		}
		/*
		 * Catches invalid data type or values
		 */ 
		catch (IllegalArgumentException IAE) {
			System.out.println("\n" + IAE);
			return false;
		}
		/*
		 * Catches invalid file name
		 */
		catch (FileNotFoundException e) {
			System.out.println("\nERROR: " + e + "\nPlease check the file location and try again!");
			return false;
		}
	}
}