package com.shpp.cs.namesurfer;

/*
 * File: NameSurfer.java
 * ---------------------
 * When it is finished, this program will implements the viewer for
 * the baby-name database described in the assignment handout.
 */

import acm.program.*;
import com.shpp.cs.a.simple.SimpleProgram;

import java.awt.event.*;
import javax.swing.*;

public class NameSurfer extends SimpleProgram implements NameSurferConstants {
    private JTextField nameField;
    private JButton graphButton;
    private JButton clearButton;
    private NameSurferDataBase dB;
    private NameSurferGraph graph;
	/* Method: init() */

    /**
     * This method has the responsibility for reading in the data base
     * and initializing the interactors at the top of the window.
     */
    public void init() {
        nameField = new JTextField(20);
        graphButton = new JButton("Graph");
        clearButton = new JButton("Clear");
        add(new JLabel("Name: "), NORTH);
        add(nameField, NORTH);
        add(graphButton, NORTH);
        add(clearButton, NORTH);
        nameField.setActionCommand("enter");
        nameField.addActionListener(this);
        addActionListeners();
		dB = new NameSurferDataBase(NAMES_DATA_FILE);
        graph = new NameSurferGraph();
        add(graph, CENTER);


        // You fill this in, along with any helper methods //
    }

	/* Method: actionPerformed(e) */

    /**
     * This class is responsible for detecting when the buttons are
     * clicked, so you will have to define a method to respond to
     * button actions.
     */
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        if (command.equals("enter")){
            drawGraph(nameField.getText());
        }
        else if (e.getSource() == graphButton){
            drawGraph(nameField.getText());
        }
        else if (e.getSource() == clearButton){
            graph.clear();
        }
    }

    private void drawGraph(String text) {
        if (text != null){
            text = filterString(text);
            graph.addEntry(dB.findEntry(text));

            /*String buffer = dB.findEntry(text).toString();
            if (buffer != null) {

                println(buffer);
            }
*/
        }
    }

    private String filterString(String word) {
        String res = "";
        for (int i = 0; i < word.length(); i++){
            if (Character.isLetter(word.charAt(i))){
                res += Character.toLowerCase(word.charAt(i));
            }
        }
        return res;

    }
}
