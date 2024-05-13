/*
 * Prototype for SearchResultsPanel - Alex Comeau
 * 
 */
package com.example.frontend;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import com.example.backend.Chemical;
import com.example.backend.Room;
import com.example.backend.Shelf;
import com.example.backend.TakeOutSlip;

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

        DefaultTableModel model = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };

        this.table = new JTable(model);
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

    public JTable getTable(){
        return this.table;
    }
}