/*
 * Name: Jacky Ly
 * Student ID: 500890960
 * Section: CPS209-031
 */

// Import statements
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Transaction {
    
    // Instance variables
    private int transID;
    private GregorianCalendar transDate;
    private Car car;
    private String salesPerson, transType;
    private double salePrice;

	public int getTransID() {
		return this.transID;
	}

	public GregorianCalendar getTransDate() {
		return this.transDate;
	}

	public Car getCar() {
		return this.car;
	}

	public String getSalesPerson() {
		return this.salesPerson;
	}

	public double getSalePrice() {
		return this.salePrice;
	}

    /**
     * Default constructor for class Transaction
     * 
     * @param transID
     * @param transDate
     * @param car
     * @param salesPerson
     * @param transType
     * @param salePrice
     */
    public Transaction (int transID, GregorianCalendar transDate, Car car, String salesPerson, String transType, double salePrice) {
        this.transID = transID;
        this.transDate = transDate;
        this.car = car;
        this.salesPerson = salesPerson;
        this.transType = transType;
        this.salePrice = salePrice;
    }

    public String display() {
        SimpleDateFormat df = new SimpleDateFormat("EEE, MMM dd, YYYY");
        return "Transaction ID: " + transID + "\nDate: " + df.format(transDate.getTime()) + "\nTransaction Type: " + transType + "\nCar VIN #: " + car.getVIN() + "\nSalesperson: " + salesPerson + "\nSale Price: $" + salePrice;
    }
}