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

    // basic testing stub
    public static void main(String[] args) {
        // Create a new JFrame
        JFrame frame = new JFrame("Science Department Inventory");

        // Set the size of the frame
        frame.setSize(400, 300);

        // Set the default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //print as array
        System.out.print(Arrays.toString(getExampleData().get(0).asArray()));
        // Create a new SearchResultsPanel
        SearchResultsPanel SearchResultsPanel = new SearchResultsPanel(getExampleData());

        // Add the SearchResultsPanel to the frame
        frame.add(SearchResultsPanel);

        // Set the visibility of the frame
        frame.setVisible(true);
    }

    private static ArrayList<Chemical> getExampleData() {

        List<Room> roomsStoredAt = new ArrayList<>();
        roomsStoredAt.add(new Room(1,2));

        List<Shelf> shelvesStoredAt = new ArrayList<>();
        shelvesStoredAt.add(new Shelf(1,2));

        ArrayList<Chemical> chemicals = new ArrayList<>();
        chemicals.add(new Chemical("Acetone", 500, "ml", "Flammable", true, "sdsSheet", "manufacturer",
                "purchaseDate", "expirationDate"));
        chemicals.add(new Chemical("Ethanol", 500, "ml", "Flammable", true, "sdsSheet", "manufacturer",
                "purchaseDate", "expirationDate"));
        chemicals.add(new Chemical("Water", 500, "ml", "Non-flammable", false, "sdsSheet", "manufacturer",
                "purchaseDate", "expirationDate"));
        chemicals.add(new Chemical("Sulfur", 500, "ml", "Non-flammable", false, "sdsSheet", "manufacturer",
                "purchaseDate", "expirationDate"));
        chemicals.add(new Chemical("Tomato", 500, "ml", "Non-flammable", false, "sdsSheet", "manufacturer",
                "purchaseDate", "expirationDate"));
        chemicals.add(new Chemical("Salsa", 500, "ml", "Non-flammable", false, "sdsSheet", "manufacturer",
                "purchaseDate", "expirationDate"));
        return chemicals;
    }
}