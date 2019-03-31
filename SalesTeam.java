/*
 * Name: Jacky Ly
 * Student ID: 500890960
 * Section: CPS209-031
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
        // add more
    }

    public String getSalesperson() {
        int random = (int) (Math.random() * salesTeam.size());
        salesIterator = salesTeam.listIterator(random);
        String salesperson = salesIterator.next();
        return salesperson;
    }
}