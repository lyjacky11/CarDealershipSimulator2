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

public class AccountingSystem {

    /*
     * Initialize instance variables and constants
     */
    private ArrayList<Transaction> transList;
    private SalesTeam salesTeam;
    private int lastTransID;
    public final String[] monthNames = {"January", "February", "March", "April", "May", "June", "July", "August", "September", "October", "November", "December"};
    
    /*
	 * Default constructor for the class
	 */
    public AccountingSystem () {
        transList = new ArrayList<Transaction>();
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
        transList.add(trans);
        return trans.display();
    }

    /**
     * Finds a transaction in the list given the transaction ID
     * 
     * @param id
     * @return the transaction if found
     */
    public Transaction getTransaction(int id) {
        for (int i = 0; i < transList.size(); i++) {
            Transaction currentTrans = transList.get(i);
            if (currentTrans.getTransID() == id)
                return currentTrans;
        }
        return null;
    }

    /*
     * Displays a list of all the transactions
     */
    public void getAllTrans() {
        if (transList.size() > 0) {
            for (int i = 0; i < transList.size(); i++) {
                System.out.println(transList.get(i).display());
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
        if (transList.size() > 0) {
            for (int i = 0; i < transList.size(); i++) {
                Transaction currentTrans = transList.get(i);
                GregorianCalendar transDate = currentTrans.getTransDate();
                int transMonth = transDate.get(Calendar.MONTH);
                if (transMonth == month)
                    System.out.println(transList.get(i).display());
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
        if (transList.size() > 0) {
            for (int i = 0; i < transList.size(); i++) {
                total += transList.get(i).getSalePrice();
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
        if (transList.size() > 0) {
            for (int i = 0; i < transList.size(); i++) {
                if (transList.get(i).getTransType().equals("BUY")) total++;
                else total--;
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
        if (transList.size() > 0) {
            for (int i = 0; i < transList.size(); i++) {
                if (transList.get(i).getTransType().equals("RET"))
                    total++;
            }
            return total;
        }
        else
            throw new IllegalArgumentException("\nERROR: Transactions list is empty! No transactions found!");
    }

    /*
     * Finds the month with the highest sales (in terms of 'BUY' transactions)
     */
    public String getHighestMonth() {
        ArrayList<Integer> monthSales = new ArrayList<Integer>();
        if (transList.size() > 0) {
            for (int i = 0; i < 12; i++) {
                int counter = 0;
                for (int j = 0; j < transList.size(); j++) {
                    Transaction currentTrans = transList.get(j);
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
                            result += monthNames[i] + ": " + maxVal + "\n";
                    }
                }
                else {
                    int maxIndex = monthSales.indexOf(maxVal);
                    result = monthNames[maxIndex] + ": " + maxVal + "\n";
                }
                return result;
            }
            throw new IllegalArgumentException("\nERROR: All cars sold have been returned!");
        }
        throw new IllegalArgumentException("\nERROR: Transactions list is empty! No transactions found!");
    }

    /*
     * Finds the salesperson who sold the most cars ('BUY' transactions)
     */
    public String getTopSP() {
        ArrayList<Integer> numSales = new ArrayList<Integer>();
        if (transList.size() > 0) {
            for (int i = 0; i < salesTeam.getSalesTeam().size(); i++) {
                int counter = 0;
                for (int j = 0; j < transList.size(); j++) {
                    Transaction trans = transList.get(j);
                    if (trans.getSalesPerson().equals(salesTeam.getSalesTeam().get(i)) && trans.getTransType().equals("BUY"))
                        counter++;
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
    public ArrayList<Transaction> getTransList() {
        return transList;
    }

    /**
     * Gets the last transaction's ID
     * @return the lastTransID
     */
    public int getLastTransID() {
        return lastTransID;
    }
}