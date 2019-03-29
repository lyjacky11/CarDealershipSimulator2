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
        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, MMM DD, YYYY");
        return "\nTransaction ID: " + transID + "\nDate: " + dateFormat.format(transDate.getTime()) + "\nTransaction Type: " + transType + "\nCar: " + car.getVIN() + "\nSalesperson: " + salesPerson + "\nSale Price: " + salePrice;
    }
 }