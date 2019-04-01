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
import java.util.GregorianCalendar;

public class AccountingSystem {

    // Instance variables
    private ArrayList<Transaction> transList;
    public int lastTransID;
    // private Calendar date;
    // private Car car;
    // private String salesPerson, type;
    // private double salePrice;

    public AccountingSystem () {
        transList = new ArrayList<Transaction>();
    }

    public String add(Calendar date, Car car, String salesPerson, String type, double salePrice) {
        int id = (int) (Math.random() * 100) + 1;
        // int year = date.get(Calendar.YEAR);
        // int month = date.get(Calendar.MONTH);
        // int day = date.get(Calendar.DAY_OF_MONTH);
        // GregorianCalendar gCalendar = new GregorianCalendar(year, month, day);
        //if (type.equals("BUY"))
            lastTransID = id;
        Transaction trans = new Transaction(id, (GregorianCalendar) date, car, salesPerson, type, salePrice);
        transList.add(trans);
        return trans.display();
    }

    public Transaction getTransaction(int id) {
        for (int i = 0; i < transList.size(); i++) {
            Transaction currentTrans = transList.get(i);
            if (currentTrans.getTransID() == id)
                return currentTrans;
        }
        return null;
    }

    public void getAllTrans() {
        if (transList.size() > 0) {
            for (int i = 0; i < transList.size(); i++) {
                System.out.println(transList.get(i).display());
            }
        }
        else
            throw new IllegalArgumentException("\nERROR: No transactions found!");
    }

    public void getMonthTrans(int month) {
        if (month >= 0 && month < 12) {
            if (transList.size() > 0) {
                for (int i = 0; i < transList.size(); i++) {
                    Transaction currentTrans = transList.get(i);
                    GregorianCalendar transDate = currentTrans.getTransDate();
                    int transMonth = transDate.get(Calendar.MONTH);
                    if (transMonth == month) {
                        System.out.println(transList.get(i).display());
                    }
                }
            }
            else
                throw new IllegalArgumentException("\nERROR: No transactions found!");
        }
        else
            throw new IllegalArgumentException("\nERROR: Invalid month! Please try again!");
    }

    /**
     * @return the transList
     */
    public ArrayList<Transaction> getTransList() {
        return transList;
    }

    /**
     * @param transList the transList to set
     */
    public void setTransList(ArrayList<Transaction> transList) {
        this.transList = transList;
    }

    /**
     * @return the lastTransID
     */
    public int getLastTransID() {
        return lastTransID;
    }

    /**
     * @param lastTransID the lastTransID to set
     */
    public void setLastTransID(int lastTransID) {
        this.lastTransID = lastTransID;
    }
}