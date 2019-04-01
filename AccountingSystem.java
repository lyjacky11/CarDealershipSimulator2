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

    // Instance variables
    private ArrayList<Transaction> transList;
    private SalesTeam salesTeam;
    public int lastTransID;

    public AccountingSystem () {
        transList = new ArrayList<Transaction>();
        salesTeam = new SalesTeam();
    }

    public String add(Calendar date, Car car, String salesPerson, String type, double salePrice) {
        int id = (int) (Math.random() * 100) + 1;
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

    public double getTotalSales() {
        double total = 0;
        if (transList.size() > 0) {
            for (int i = 0; i < transList.size(); i++) {
                total += transList.get(i).getSalePrice();
            }
            return total;
        }
        else
            throw new IllegalArgumentException("\nERROR: No transactions found!");
    }

    public double getAvgSales() {
        return getTotalSales() / 12;
    }

    public int getTotalSold() {
        int total = 0;
        if (transList.size() > 0) {
            for (int i = 0; i < transList.size(); i++) {
                if (transList.get(i).getTransType().equals("BUY"))
                    total++;
                // else
                //     total--;
            }
            return total;
        }
        else
            throw new IllegalArgumentException("\nERROR: No transactions found!");
    }

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
            throw new IllegalArgumentException("\nERROR: No transactions found!");
    }

    public int getHighestMonth() {
        ArrayList<Integer> monthSales = new ArrayList<Integer>();
        if (transList.size() > 0) {
            for (int i = 0; i < 12; i++) {
                int counter = 0;
                for (int j = 0; j < transList.size(); j++) {
                    if (transList.get(j).getTransDate().get(Calendar.MONTH) == i)
                        counter++;
                }
                monthSales.add(counter);
            }
            int maxVal = Collections.max(monthSales);
            return monthSales.indexOf(maxVal);
        }
        else
            throw new IllegalArgumentException("\nERROR: No transactions found!");
    }

    public void getTopSP() {
        ArrayList<Integer> numSales = new ArrayList<Integer>();
        for (int i = 0; i < salesTeam.getSalesTeam().size(); i++) {
            int counter = 0;
            for (int j = 0; j < transList.size(); j++) {
                String transSP = transList.get(j).getSalesPerson();
                if (transSP.equals(salesTeam.getSalesTeam().get(i)))
                    counter++;
            }
            numSales.add(counter);
        }
        int maxVal = Collections.max(numSales);
        if (Collections.frequency(numSales, maxVal) > 1) {
            for (int i = 0; i < numSales.size(); i++) {
                if (numSales.get(i) == maxVal) {
                    System.out.println("Name: " + salesTeam.getSalesTeam().get(i));
                    System.out.println("Number of cars sold: " + maxVal);
                }
            }
        }
        else {
            int maxIndex = numSales.indexOf(maxVal);
            System.out.println("Name: " + salesTeam.getSalesTeam().get(maxIndex));
            System.out.println("Number of cars sold: " + maxVal);
        }
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