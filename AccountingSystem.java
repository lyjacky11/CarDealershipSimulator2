/*
 * Name: Jacky Ly
 * Student ID: 500890960
 * Section: CPS209-031
 */

/*
 * Import statements
 */
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class AccountingSystem {

    /*
     * Initialize instance variables and constants
     */
    private Map<Integer, Transaction> transMap;
    private Set<Integer> transIDs;
    private Iterator<Integer> transIterator;
    //private ArrayList<Transaction> transList;
    private SalesTeam salesTeam;
    private int lastTransID;
    public final String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    
    /*
	 * Default constructor for the class
	 */
    public AccountingSystem () {
        transMap = new TreeMap<Integer, Transaction>();
        transIDs = transMap.keySet();
        //transList = new ArrayList<Transaction>();
        salesTeam = new SalesTeam();
    }

    /**
     * Adds a transaction to the list of existing transactions
     * 
     * @param date
     * @param car
     * @param salesPerson
     * @param type
     * @param salePrice
     * @return the transaction details
     */
    public String add(Calendar date, Car car, String salesPerson, String type, double salePrice) {
        int id = (int) (Math.random() * 999) + 1;
        lastTransID = id;
        Transaction trans = new Transaction(id, (GregorianCalendar) date, car, salesPerson, type, salePrice);
        transMap.put(id, trans);
        //transList.add(trans);
        return trans.display();
    }

    /**
     * Finds a transaction in the list given the transaction ID
     * 
     * @param id
     * @return the transaction if found
     */
    public Transaction getTransaction(int id) {
        transIterator = transIDs.iterator();
        while (transIterator.hasNext()) {
            int transID = transIterator.next();
            Transaction currentTrans = transMap.get(transID);
            if (currentTrans.getTransID() == id)
                return currentTrans;
        }
        return null;
    }

    /*
     * Displays a list of all the transactions
     */
    public void getAllTrans() {
        transIterator = transIDs.iterator();
        if (transMap.size() > 0) {
            while (transIterator.hasNext()) {
                int transID = transIterator.next();
                System.out.println(transMap.get(transID).display());
            }
        }
        else
            throw new IllegalArgumentException("\nERROR: Transactions list is empty! No transactions found!");
    }

    /**
     * Displays a list of transactions in the given month
     * 
     * @param month
     */
    public void getMonthTrans(int month) {
        transIterator = transIDs.iterator();
        if (transMap.size() > 0) {
            while (transIterator.hasNext()) {
                int transID = transIterator.next();
                Transaction currentTrans = transMap.get(transID);
                GregorianCalendar transDate = currentTrans.getTransDate();
                int transMonth = transDate.get(Calendar.MONTH);
                if (transMonth == month)
                    System.out.println(currentTrans.display());
            }
        }
        else
            throw new IllegalArgumentException("\nERROR: Transactions list is empty! No transactions found!");
    }

    /**
     * Calculates the total sales ($) of all transactions
     * @return the total result
     */
    public double getTotalSales() {
        double total = 0;
        transIterator = transIDs.iterator();
        if (transMap.size() > 0) {
            while (transIterator.hasNext()) {
                int transID = transIterator.next();
                total += transMap.get(transID).getSalePrice();
            }
            return total;
        }
        else
            throw new IllegalArgumentException("\nERROR: Transactions list is empty! No transactions found!");
    }

    /*
     * Finds the average of the total sales per month
     */
    public double getAvgSales() {
        return getTotalSales() / 12;
    }

    public int getTotalSold() {
        int total = 0;
        transIterator = transIDs.iterator();
        if (transMap.size() > 0) {
            while (transIterator.hasNext()) {
                int transID = transIterator.next();
                if (transMap.get(transID).getTransType().equals("BUY")) total++;
                // else total--;
            }
            return total;
        }
        else
            throw new IllegalArgumentException("\nERROR: Transactions list is empty! No transactions found!");
    }

    /*
     * Finds the total number of returns in the transactions list
     */
    public int getTotalReturns() {
        int total = 0;
        transIterator = transIDs.iterator();
        if (transMap.size() > 0) {
            while (transIterator.hasNext()) {
                int transID = transIterator.next();
                if (transMap.get(transID).getTransType().equals("RET"))
                    total++;
            }
            return total;
        }
        else
            throw new IllegalArgumentException("\nERROR: Transactions list is empty! No transactions found!");
    }

    /*
     * Return the month(s) with the highest sales (in terms of 'BUY' transactions)
     */
    public String getHighestMonth() {
        ArrayList<Integer> monthSales = new ArrayList<Integer>();
        if (transMap.size() > 0) {
            for (int i = 0; i < 12; i++) {
                transIterator = transIDs.iterator();
                int counter = 0;
                while (transIterator.hasNext()) {
                    int transID = transIterator.next();
                    Transaction currentTrans = transMap.get(transID);
                    if (currentTrans.getTransDate().get(Calendar.MONTH) == i) {
                        if (currentTrans.getTransType().equals("BUY")) counter++;
                        else counter--;
                    }
                }
                monthSales.add(counter);
            }
            int maxVal = Collections.max(monthSales);
            if (maxVal != 0) {
                String result = "";
                if (Collections.frequency(monthSales, maxVal) > 1) {
                    for (int i = 0; i < monthSales.size(); i++) {
                        if (monthSales.get(i) == maxVal)
                            result += String.format("  %-10s | %6d\n", monthNames[i], maxVal);
                    }
                }
                else {
                    int maxIndex = monthSales.indexOf(maxVal);
                    result += String.format("  %-10s | %6d\n", monthNames[maxIndex], maxVal);
                }
                return result;
            }
            else
                return "None\n";
        }
        throw new IllegalArgumentException("\nERROR: Transactions list is empty! No transactions found!");
    }

    /*
     * Finds the salesperson who sold the most cars ('BUY' transactions)
     */
    public String getTopSP() {
        ArrayList<Integer> numSales = new ArrayList<Integer>();
        if (transMap.size() > 0) {
            for (int i = 0; i < salesTeam.getSalesTeam().size(); i++) {
                transIterator = transIDs.iterator();
                int counter = 0;
                while (transIterator.hasNext()) {
                    int transID = transIterator.next();
                    Transaction trans = transMap.get(transID);
                    if (trans.getSalesPerson().equals(salesTeam.getSalesTeam().get(i))) {
                        if (trans.getTransType().equals("BUY")) counter++;
                        else counter--;
                    }
                }
                numSales.add(counter);
            }
            int maxVal = Collections.max(numSales);
            String result = "";
            if (Collections.frequency(numSales, maxVal) > 1) {
                for (int i = 0; i < numSales.size(); i++) {
                    if (numSales.get(i) == maxVal) {
                        String name = salesTeam.getSalesTeam().get(i);
                        result += String.format("%-15s %15d\n", name, maxVal);
                    }
                }
            }
            else {
                int maxIndex = numSales.indexOf(maxVal);
                String name = salesTeam.getSalesTeam().get(maxIndex);
                result = String.format("%-15s %15d\n", name, maxVal);
            }
            return result;
        }
        else
            throw new IllegalArgumentException("\nERROR: Transactions list is empty! No transactions found!");
    }

    /**
     * Gets the transaction array list
     * @return the transList
     */
    public Map<Integer, Transaction> getTransMap() {
        return transMap;
    }

    /**
     * Gets the last transaction's ID
     * @return the lastTransID
     */
    public int getLastTransID() {
        return lastTransID;
    }

    /**
     * Gets the transaction iterator for the object
     * @return transIterator
     */
    public Iterator<Integer> getIterator() {
        return transIterator;
    }
}