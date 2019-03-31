/*
 * Name: Jacky Ly
 * Student ID: 500890960
 * Section: CPS209-031
 */

// Import statements
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
        int year = date.get(Calendar.YEAR);
        int month = date.get(Calendar.MONTH);
        int day = date.get(Calendar.DAY_OF_MONTH);
        GregorianCalendar gCalendar = new GregorianCalendar(year, month, day);
        Transaction trans = new Transaction(id, gCalendar, car, salesPerson, type, salePrice);
        transList.add(trans);
        lastTransID = trans.getTransID();
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
}