package com.shpp.cs.namesurfer;

/*
 * File: NameSurferGraph.java
 * ---------------------------
 * This class represents the canvas on which the graph of
 * names is drawn. This class is responsible for updating
 * (redrawing) the graphs whenever the list of entries changes
 * or the window is resized.
 */

import acm.graphics.*;

import java.awt.*;
import java.awt.event.*;
import java.util.*;

public class NameSurferGraph extends GCanvas
        implements NameSurferConstants, ComponentListener {

    /*List for storage active graphs*/
    private ArrayList<NameSurferEntry> entryList = new ArrayList<NameSurferEntry>();

    /**
     * Creates a new NameSurferGraph object that displays the data.
     */
    public NameSurferGraph() {
        addComponentListener(this);

    }


    /**
     * Clears the list of name surfer entries stored inside this class.
     */
    public void clear() {
        entryList.clear();
        update();
    }

	
	/* Method: addEntry(entry) */

    /**
     * Adds a new NameSurferEntry to the list of entries on the display.
     * Note that this method does not actually draw the graph, but
     * simply stores the entry; the graph is drawn by calling update.
     */
    public void addEntry(NameSurferEntry entry) {

        if (notPresent(entry)) { //prevents repeating and the imposition of the graphs
            entryList.add(entry);
            update();
        }

    }

    /**
     * This method checks for the presence of the received NameSurferEntry in the list of graphs
     *
     * @param entry Instance of NameSurferEntry class to check
     * @return True if received entry is currently present in list and False if not.
     * */
    private boolean notPresent(NameSurferEntry entry) {
        boolean result = true;
        for (NameSurferEntry current: entryList){
            if (current.equals(entry)){
                result = false;
            }
        }
        return result;
    }


    /**
     * Updates the display image by deleting all the graphical objects
     * from the canvas and then reassembling the display according to
     * the list of entries. Your application must call update after
     * calling either clear or addEntry; update is also called whenever
     * the size of the canvas changes.
     */
    public void update() {
        removeAll();
        drawGrid();
        drawGraphs();
    }

    /**
     * This method draw all graphs that listed in entryList.
     * Graph consists of lines between ranks in every decade and names with value of rank in this decade
     * */
    private void drawGraphs() {
        Color colors [] = {Color.BLUE, Color.RED, Color.MAGENTA, Color.ORANGE};
        int colorCounter = 0;
        for (NameSurferEntry entry: entryList){
            Color color = colors[colorCounter];
            if (entry != null) {
                drawCurrentGraph(entry, color);
                colorCounter++;
                if (colorCounter == colors.length) {
                    colorCounter = 0;
                }
            }


        }
    }

    /**
     * This method draw graph of received name with received color
     * It receive the instance of NameSurferEntry class and color for graph
     * Than calculate coordinates of every line of a graph and label coordinates
     * and draw this lines and labels
     *
     * @param entry Instance of NameSurferEntry class from which this method takes
     *              the name and rank in each decade
     * @param color Color of current graph
     * */
    private void drawCurrentGraph(NameSurferEntry entry, Color color) {

        /* Coefficient to transform rank to real coordinate Y*/
        double rankY = (double)(getHeight() - GRAPH_MARGIN_SIZE * 2) / MAX_RANK;

        /* Calculation of the first point of the graph*/
        double startX = 0;
        double startY = 0;
        if (entry.getRank(0) == 0){ // For 0 rating set coordinate Y to draw line downside the grid
            startY = getHeight() - GRAPH_MARGIN_SIZE;
        } else {
            startY = (entry.getRank(0) * rankY) + GRAPH_MARGIN_SIZE; //calculate real Y coordinate for line start
        }

        /* Calculation of all other points of the graph*/
        for (int i = 0; i < NDECADES; i++){
            if ( i == NDECADES - 1){
                break;
            }
            double endX = (i + 1) * getWidth() / NDECADES;

            double endY = 0;
            if (entry.getRank(i + 1) == 0){ // For 0 rating set coordinate Y to draw line downside the grid
                endY = getHeight() - GRAPH_MARGIN_SIZE;
            } else {
                endY = (entry.getRank(i + 1) * rankY) + GRAPH_MARGIN_SIZE; //calculate real Y coordinate for line start
            }



            GLine line = new GLine(startX, startY, endX, endY);
            line.setColor(color);
            line.setVisible(true);

            add(line);

            drawName(entry.getName(), entry.getRank(i), startX, startY, color);

            startX = endX;
            startY = endY;
        }

        /*Draws the last label with name and rank*/
        drawName(entry.getName(), entry.getRank(NDECADES - 1), startX, startY, color);

    }


    /**
     * This method draws name with his rank in current decade with received coordinates and color
     *
     * @param name Name to display
     * @param rank Rank of name in current decade
     * @param x The X coordinate of the label
     * @param y The Y coordinate of the label
     * @param color The color of the label
     * */
    private void drawName(String name, int rank, double x, double y, Color color) {
        GLabel label = new GLabel(name + " " + rank, x, y);
        label.setFont("SansSerif-11");

        /* For 0 rank change it to '*' symbol */
        if (rank == 0){
            label.setLabel(name + " *");
        }

        label.setColor(color);
        add(label);
    }

    /**
     * This method draws the grid with labeled years in every column
    * */
    private void drawGrid() {
        drawHorizontalLines();
        drawColumns();
    }

    /**
     * Draws four horizontal line of a grid using constant GRAPH_MARGIN_SIZE of NameSurferConstants Interface
    * */
    private void drawHorizontalLines() {
        add(new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE));
        add(new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE));
        add(new GLine(0, 0, getWidth(), 0));
        add(new GLine(0, getHeight(), getWidth(), getHeight()));
    }


    /**
     * Draws vertical lines of a grid and labels with years
     * */
    private void drawColumns() {
        double columnWidth = getWidth() / NDECADES;
        for (int i = 0; i < NDECADES; i++){
            double x = i * columnWidth;
            add(new GLine(x, 0, x, getHeight()));
            drawYear(columnWidth, i);
        }
    }

    /**
     * Draws label with year of decade begin downside of a column
     *
     * @param columnWidth Width of every new column to draw labels
     * @param i Number of column that then transform to year using
     * constant START_DECADE of NameSurferConstants Interface
     * */
    private void drawYear(double columnWidth, int i) {
        int year = START_DECADE + (i * 10);
        String text  = Integer.toString(year);
        GLabel label  = new GLabel(text);
        label.setFont("SansSerif-bold-13");
        double x = (columnWidth * i) + ((columnWidth - label.getWidth()) / 2);
        double y = getHeight() - GRAPH_MARGIN_SIZE + label.getAscent();
        add(label, x, y);
    }


    /* Implementation of the ComponentListener interface */
    public void componentHidden(ComponentEvent e) {
    }

    public void componentMoved(ComponentEvent e) {

    }

    public void componentResized(ComponentEvent e) {
       update();
    }

    public void componentShown(ComponentEvent e) {

    }
}
