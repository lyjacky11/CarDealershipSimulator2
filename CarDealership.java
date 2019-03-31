/*
 * Name: Jacky Ly
 * Student ID: 500890960
 * Section: CPS209-031
 */

// Import statements
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.GregorianCalendar;

public class CarDealership {

	// Instance variables
	private ArrayList<Car> cars;
	private AccountingSystem accSystem;
	private double minPrice, maxPrice;
	private boolean AWD, electric, price;
	public boolean isEmpty;
	public Car carLastBought;

	// Comparator class for safety rating
	class SRComparator implements Comparator<Car> {
		/**
		 * Compares two Car objects
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

	// Comparator class for max range
	class MRComparator implements Comparator<Car> {
		/**
		 * Compares two Car objects
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

	// Default constructor for class CarDealership
	public CarDealership () {
		cars = new ArrayList<Car>();
		this.isEmpty = true;
	}

	/**
	 * Adds cars from an array list
	 * @param newCars ArrayList of Car objects
	 */
	public void addCars(ArrayList<Car> newCars) {
		if (newCars.size() > 0) {
			cars.addAll(newCars);
			this.isEmpty = false;
		}
		// Throws an exception when the array list is empty
		else
			throw new IndexOutOfBoundsException("\nERROR: No cars to add found!");
	}

	/**
	 * Buys a car (remove car from array list) given car position (index)
	 * @param index of the car to buy
	 * @return carLastBought Car object
	 */
	// public Car buyCar(int index) {
	// 	if (index < cars.size()) {
	// 		carLastBought = cars.get(index);
	// 		cars.remove(index);
	// 		if (cars.size() <= 0)
	// 			this.isEmpty = true;
	// 		return carLastBought;
	// 	}
	// 	return null;
	// }

	public String buyCar(int VIN) {
		if (isEmpty)
			throw new IllegalArgumentException("\nERROR: Car inventory is empty!");
		for (int i = 0; i < cars.size(); i++) {
			if (cars.get(i).getVIN() == VIN) {
				carLastBought = cars.get(i);
				cars.remove(carLastBought);
				if (cars.size() <= 0)
					 this.isEmpty = true;
				String salesPerson = "Joe"; // PLACEHOLDER
				String transType = "BUY";
				int year = 2019;
				int month = (int) (Math.random() * 13) + 1;
				int day = (int) (Math.random() * 32) + 1;
				Calendar date = new GregorianCalendar(year, month, day);
				return accSystem.add(date, carLastBought, salesPerson, transType, carLastBought.getPrice());
			}
		}
		throw new IllegalArgumentException("\nERROR: Entered VIN is not a valid selection! Please check the input and try again!");
	}
	
	/**
	 * Returns last bought car (adds car back to array list)
	 * @param car to return
	 */
	public void returnCar(Car car) {
		if (car != null) {
			cars.add(car);
			this.isEmpty = false;
			carLastBought = null;
		}
		// Throws an exception when there is no car that was last bought
		else
			throw new IllegalArgumentException("\nERROR: No car found to return to inventory!");
	}

	// Displays the inventory of cars (prints the cars in the array list based on enabled filters)
	public void displayInventory() {
		for (int i = 0; i < cars.size(); i++) {
			Car currentCar = cars.get(i);
			String output = String.format("%-3d %s", i, currentCar.display());

			// FEL and FAW and FPR enabled
			if (electric && AWD && price) {
				if (currentCar.getPower() == Car.ELECTRIC_MOTOR && currentCar.isAWD() && currentCar.getPrice() >= minPrice && currentCar.getPrice() <= maxPrice)
					System.out.println(output);
			}
			// FEL and FPR enabled
			else if (electric && price) {
				if (currentCar.getPower() == Car.ELECTRIC_MOTOR && currentCar.getPrice() >= minPrice && currentCar.getPrice() <= maxPrice)
					System.out.println(output);
			}
			// FAW and FPR enabled
			else if (AWD && price) {
				if (currentCar.isAWD() && currentCar.getPrice() >= minPrice && currentCar.getPrice() <= maxPrice)
					System.out.println(output);
			}
			// FEL and FAW enabled
			else if (electric && AWD) {
				if(currentCar.getPower() == Car.ELECTRIC_MOTOR && currentCar.isAWD())
					System.out.println(output);
			}
			// FEL enabled
			else if (electric) {
				if(currentCar.getPower() == Car.ELECTRIC_MOTOR)
					System.out.println(output);
			}
			// FAW enabled
			else if (AWD) {
				if(currentCar.isAWD())
					System.out.println(output);
			}
			// FPR enabled
			else if (price) {
				if(currentCar.getPrice() >= minPrice && currentCar.getPrice() <= maxPrice)
					System.out.println(output);
			}
			// No filters enabled
			else
				System.out.println(output);
		}
	}

	// Filters vehicles by electric cars
	public void filterByElectric() {
		this.electric = true;
	}

	// Filters vehicles by AWD
	public void filterByAWD() {
		this.AWD = true;
	}

	/**
	 * Filters vehicles by price given minimum and maximum price
	 * @param minPrice
	 * @param maxPrice
	 */
	public void filterByPrice(double minPrice, double maxPrice) {
		this.price = true;
		this.minPrice = minPrice;
		this.maxPrice = maxPrice;
	}

	// Clears filters (sets all filters to false)
	public void FiltersClear() {
		this.AWD = false;
		this.electric = false;
		this.price = false;
		this.minPrice = Integer.MIN_VALUE;
		this.maxPrice = Integer.MAX_VALUE;
	}

	// Sorts array list by price
	public void sortByPrice() {
		Collections.sort(cars);
	}

	// Sorts array list by safety rating
	public void sortBySafetyRating() {
		Collections.sort(cars, new SRComparator());
	}

	// Sorts array list by max range
	public void sortByMaxRange() {
		Collections.sort(cars, new MRComparator());
	}
}