/*
 * Name: Jacky Ly
 * Student ID: 500890960
 * Section: CPS209-031
 */

/*
 * Import statements
 */
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

public class Transaction {
    /*
     * Initialize instance variables
     */
    private int transID;
    private GregorianCalendar transDate;
    private Car car;
    private String salesPerson, transType;
    private double salePrice;

    /**
     * Default constructor for the class
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

    /**
     * Displays the transaction information
     * @return the transaction in a String
     */
    public String display() {
        SimpleDateFormat df = new SimpleDateFormat("EEE, MMM dd, YYYY");
        return String.format("%-8d %-5s %-7d %-13s %-13.2f %s", transID, transType, car.getVIN(), salesPerson, salePrice, df.format(transDate.getTime()));
    }

    /**
     * Gets the type of transaction
     * @return the transType
     */
    public String getTransType() {
        return transType;
    }

    /**
     * Gets the transaction ID
     * @return the transID
     */
    public int getTransID() {
        return transID;
    }

    /**
     * Gets the date of the transaction
     * @return the transDate
     */
    public GregorianCalendar getTransDate() {
        return transDate;
    }

    /**
     * Gets the Car object of the transaction
     * @return the car
     */
    public Car getCar() {
        return car;
    }

    /**
     * Gets the sales person of the transaction
     * @return the salesPerson
     */
    public String getSalesPerson() {
        return salesPerson;
    }

    /**
     * Gets the sales price of the transaction
     * @return the salePrice
     */
    public double getSalePrice() {
        return salePrice;
    }
}