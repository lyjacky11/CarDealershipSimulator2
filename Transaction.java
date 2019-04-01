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
     
    /**
     * @param transID the transID to set
     */
    public void setTransID(int transID) {
        this.transID = transID;
    }

    /**
     * @param transDate the transDate to set
     */
    public void setTransDate(GregorianCalendar transDate) {
        this.transDate = transDate;
    }

    /**
     * @param car the car to set
     */
    public void setCar(Car car) {
        this.car = car;
    }

    /**
     * @param salesPerson the salesPerson to set
     */
    public void setSalesPerson(String salesPerson) {
        this.salesPerson = salesPerson;
    }

    /**
     * @return the transType
     */
    public String getTransType() {
        return transType;
    }

    /**
     * @param transType the transType to set
     */
    public void setTransType(String transType) {
        this.transType = transType;
    }

    /**
     * @param salePrice the salePrice to set
     */
    public void setSalePrice(double salePrice) {
        this.salePrice = salePrice;
    }

    /**
     * @return the transID
     */
    public int getTransID() {
        return transID;
    }

    /**
     * @return the transDate
     */
    public GregorianCalendar getTransDate() {
        return transDate;
    }

    /**
     * @return the car
     */
    public Car getCar() {
        return car;
    }

    /**
     * @return the salesPerson
     */
    public String getSalesPerson() {
        return salesPerson;
    }

    /**
     * @return the salePrice
     */
    public double getSalePrice() {
        return salePrice;
    }

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
        return String.format("%-8d %-5s %-7d %-13s %-13.2f %s", transID, transType, car.getVIN(), salesPerson, salePrice, df.format(transDate.getTime()));
    }
}