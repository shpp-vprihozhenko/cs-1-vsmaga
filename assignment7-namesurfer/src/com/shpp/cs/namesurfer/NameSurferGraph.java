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
        entryList.add(entry);
        update();

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

    private void drawCurrentGraph(NameSurferEntry entry, Color color) {
        double coef = (double)(getHeight() - GRAPH_MARGIN_SIZE * 2) / MAX_RANK;
        double startX = 0;
        double startY = 0;

        if (entry.getRank(0) == 0){
            startY = getHeight() - GRAPH_MARGIN_SIZE;
        } else {
            startY = (entry.getRank(0) * coef) + GRAPH_MARGIN_SIZE;
        }


        for (int i = 0; i < NDECADES; i++){
            if ( i == NDECADES - 1){
                break;
            }
            double endX = (i + 1) * getWidth() / NDECADES;

            double endY = 0;
            if (entry.getRank(i + 1) == 0){
                endY = getHeight() - GRAPH_MARGIN_SIZE;
            } else {
                endY = (entry.getRank(i + 1) * coef) + GRAPH_MARGIN_SIZE;
            }



            GLine line = new GLine(startX, startY, endX, endY);
            line.setColor(color);
            line.setVisible(true);

            add(line);

            drawName(entry.getName(), entry.getRank(i), startX, startY, color);

            startX = endX;
            startY = endY;
        }
    }



    private void drawName(String name, int rank, double startX, double startY, Color color) {
        GLabel label = new GLabel(name + " " + rank, startX, startY);
        label.setFont("SansSerif-bold-13");
        if (rank == 0){
            label.setLabel(name + " *");
        }
        label.setColor(color);
        add(label);
    }


    private void drawGrid() {
        drawHorizontalLines();
        drawColumns();
    }

    private void drawHorizontalLines() {
        add(new GLine(0, GRAPH_MARGIN_SIZE, getWidth(), GRAPH_MARGIN_SIZE));
        add(new GLine(0, getHeight() - GRAPH_MARGIN_SIZE, getWidth(), getHeight() - GRAPH_MARGIN_SIZE));
        add(new GLine(0, 0, getWidth(), 0));
        add(new GLine(0, getHeight(), getWidth(), getHeight()));
    }

    private void drawColumns() {
        double columnWidth = getWidth() / NDECADES;
        for (int i = 0; i < NDECADES; i++){
            double x = i * columnWidth;
            add(new GLine(x, 0, x, getHeight()));
            drawYear(columnWidth, i);
        }
    }

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
