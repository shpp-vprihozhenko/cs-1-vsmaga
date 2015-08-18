package com.shpp.cs.namesurfer;

/*
 * File: NameSurferEntry.java
 * --------------------------
 * This class represents a single entry in the database.  Each
 * NameSurferEntry contains a name and a list giving the popularity
 * of that name for each decade stretching back to 1900.
 */

import java.util.*;

public class NameSurferEntry implements NameSurferConstants {

    /* */
    private String name = null;
    private ArrayList<Integer> ranks = new ArrayList<Integer>();

	/* Constructor: NameSurferEntry(line) */

    /**
     * Creates a new NameSurferEntry from a data line as it appears
     * in the data file.  Each line begins with the name, which is
     * followed by integers giving the rank of that name for each
     * decade.
     */
    public NameSurferEntry(String line) {
        StringTokenizer tokenizer = new StringTokenizer(line);
        int counter = 0;
        String buffer = null;
        while (true){
            if (tokenizer.hasMoreElements()){
                if (counter == 0){
                    name = tokenizer.nextToken();

                }
                else {
                    buffer = tokenizer.nextToken();
                    ranks.add(Integer.parseInt(buffer));
                }
                counter++;
            }
            else break;
        }
    }

	/* Method: getName() */

    /**
     * Returns the name associated with this entry.
     */
    public String getName() {

        return name;
    }

	/* Method: getRank(decade) */

    /**
     * Returns the rank associated with an entry for a particular
     * decade.  The decade value is an integer indicating how many
     * decades have passed since the first year in the database,
     * which is given by the constant START_DECADE.  If a name does
     * not appear in a decade, the rank value is 0.
     */
    public int getRank(int decade) {

        return ranks.get(decade);
    }

	/* Method: toString() */

    /**
     * Returns a string that makes it easy to see the value of a
     * NameSurferEntry.
     */
    public String toString() {
        String result = name + " " + ranks;
        return result;
    }
}

