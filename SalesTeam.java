/*
 * Name: Jacky Ly
 * Student ID: 500890960
 * Section: CPS209-031
 */

 /*
 * Import statements
 */
import java.util.LinkedList;
import java.util.ListIterator;

    public class SalesTeam {

    private LinkedList<String> salesTeam;
    private ListIterator<String> salesIterator;

    public SalesTeam() {
        salesTeam = new LinkedList<String>();
        salesTeam.addLast("Bob");
        salesTeam.addLast("Alice");
        salesTeam.addLast("Dylan");
        salesTeam.addLast("Paula");
        salesTeam.addLast("Nathan");
        salesTeam.addLast("Jayce");
    }

    public String getRandomSP() {
        int random = (int) (Math.random() * salesTeam.size());
        salesIterator = salesTeam.listIterator(random);
        String salesperson = salesIterator.next();
        return salesperson;
    }

    public String getAllSP() {
        String team = "";
        salesIterator = salesTeam.listIterator();
        for (int i = 0; i < salesTeam.size(); i++) {
            team += salesIterator.next() + " ";
        }
        return team;
    }

    public String getSalesP(int index) {
        salesIterator = salesTeam.listIterator(index);
        String salesperson = salesIterator.next();
        return salesperson;
    }

    public int getNumSP() {
        return salesTeam.size();
    }

    /**
     * @return the salesTeam
     */
    public LinkedList<String> getSalesTeam() {
        return salesTeam;
    }

    /**
     * @param salesTeam the salesTeam to set
     */
    public void setSalesTeam(LinkedList<String> salesTeam) {
        this.salesTeam = salesTeam;
    }

    /**
     * @return the salesIterator
     */
    public ListIterator<String> getSalesIterator() {
        return salesIterator;
    }

    /**
     * @param salesIterator the salesIterator to set
     */
    public void setSalesIterator(ListIterator<String> salesIterator) {
        this.salesIterator = salesIterator;
    }
}