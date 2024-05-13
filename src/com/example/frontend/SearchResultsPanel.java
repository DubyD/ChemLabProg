/*
 * Prototype for SearchResultsPanel - Alex Comeau
 * 
 */
package com.example.frontend;

import java.awt.BorderLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;

import com.example.backend.Chemical;
import com.example.backend.Room;
import com.example.backend.Shelf;

public class SearchResultsPanel extends JPanel {

    private ArrayList<Chemical> chemicals;
    private String[][] data;
    private JTable table;
    private JScrollPane scrollPane;

    public SearchResultsPanel(ArrayList<Chemical> chemicals) {
        // Set the layout of the panel
        this.chemicals = chemicals;
        setLayout(new BorderLayout());

        // Populate the data
        populateData();

        createTable();

    }

    private final String[] columnNames = { "Has SDS?", "Chemical", "Company", "Room", "Location", "Amount of Jars", "Amount",
            "Unit", "CAS #s", "Hazard" };

    private void createTable() {

        table = new JTable(data, columnNames);
        table.setPreferredScrollableViewportSize(table.getPreferredSize());

        scrollPane = new JScrollPane(table);

        
        add(scrollPane);
    }

    private void populateData(){
        data = new String[chemicals.size()][columnNames.length];

        for(int i = 0; i<data.length; i++){
            data[i] = chemicals.get(i).asArray();
        }
    }

    public JTable getTable() {
        return table;
    }
}