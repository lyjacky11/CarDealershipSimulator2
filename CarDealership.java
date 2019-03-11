/*
 * Name: Jacky Ly
 * Student ID: 500890960
 * Section: CPS209-031
 */

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class CarDealership {
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

	public void displayInventory() {
		for (int i = 0; i < cars.size(); i++) {
			Car currentCar = cars.get(i);
			//if (filterCars.contains(i)) {
				String output = String.format("%-3d %s", i, currentCar.display());
				if (electric && AWD && price) {
					if (currentCar.getPower() == Car.ELECTRIC_MOTOR && currentCar.isAWD() && currentCar.getPrice() >= minPrice && currentCar.getPrice() <= maxPrice)
						System.out.println(output);
					else
						filterCars.remove(new Integer(i));
				}
				else if (electric && price) {
					if (currentCar.getPower() == Car.ELECTRIC_MOTOR && currentCar.getPrice() >= minPrice && currentCar.getPrice() <= maxPrice)
						System.out.println(output);
					else
						filterCars.remove(new Integer(i));
				}
				else if (AWD && price) {
					if (currentCar.isAWD() && currentCar.getPrice() >= minPrice && currentCar.getPrice() <= maxPrice)
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
				else if (electric) {
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
				else
					System.out.println(output);
			//}
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