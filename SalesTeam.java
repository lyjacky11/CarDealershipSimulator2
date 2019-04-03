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
    /*
     * Initialize instance variables
     */
    private LinkedList<String> salesTeam;
    private ListIterator<String> salesIterator;
    /*
	 * Default constructor for the class
	 */
    public SalesTeam() {
        salesTeam = new LinkedList<String>();
        salesTeam.addLast("Bob");
        salesTeam.addLast("Alice");
        salesTeam.addLast("Dylan");
        salesTeam.addLast("Paula");
        salesTeam.addLast("Nathan");
        salesTeam.addLast("Jayce");
    }

    /*
     * Gets a random sales person from the list
     */
    public String getRandomSP() {
        int random = (int) (Math.random() * salesTeam.size());
        salesIterator = salesTeam.listIterator(random);
        String salesperson = salesIterator.next();
        return salesperson;
    }

    /*
     * Displays all the people of the sales team
     */
    public String getAllSP() {
        String team = "";
        salesIterator = salesTeam.listIterator();
        for (int i = 0; i < salesTeam.size(); i++) {
            team += (i + 1) + ". " + salesIterator.next() + "   ";
        }
        return team;
    }

    /*
     * Gets a sales person given their position in the list
     */
    public String getSalesP(int index) {
        salesIterator = salesTeam.listIterator(index);
        String salesperson = salesIterator.next();
        return salesperson;
    }

    /*
     * Gets the number of sales people on the team
     */
    public int getNumSP() {
        return salesTeam.size();
    }

    /**
     * Gets the list of the sales team
     * @return the salesTeam
     */
    public LinkedList<String> getSalesTeam() {
        return salesTeam;
    }
}