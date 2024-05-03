package com.example.frontend;

import com.example.backend.Chemical;
import com.example.backend.Room;
import com.example.backend.Shelf;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.util.ArrayList;
import java.util.List;

public class SearchScreen extends JPanel implements ActionListener {
    private JButton logOutButton, searchButton;
    private JTextField searchBar;
    private JComboBox searchMenu;
    private SearchResultsPanel resultPanelTest; //test SearchResultPanel

    public SearchScreen() {
        //Jonathan Murphy
        setPreferredSize(new Dimension(800, 600));//just for now
        this.setLayout(new BorderLayout());
        logOutButton = new JButton("EXIT");
        searchButton = new JButton("SEARCH");
        searchBar = new JTextField("Search Chemical");
        //instantiating tester resultsPanel with example data
        resultPanelTest = new SearchResultsPanel(getExampleData());
        searchBar.setPreferredSize(new Dimension(200, 20));
        String[] searchCats = {"Select a Category", "All Chemicals", "Rooms", "Hazards", "Flammable", "Corrosive", "WetLab"};
        //change categories when we figure out the approach we're taking
        searchMenu = new JComboBox(searchCats);

        searchBar.addFocusListener(new FocusListener() {
            @Override
            public void focusGained(FocusEvent e) {
                if(searchBar.getText().equals("Search Chemical")) {
                    searchBar.setText("");
                }
            }
            @Override
            public void focusLost(FocusEvent e) {
                if(searchBar.getText().equals("")) {
                    searchBar.setText("Search Chemical");
                }
            }
        });
        searchMenu.setSelectedIndex(0);
        searchMenu.addActionListener(this);

        JPanel searchPanel = new JPanel();
        searchPanel.add(searchBar);
        searchPanel.add(searchButton);
        searchPanel.add(searchMenu);
        searchPanel.setBounds(this.getWidth()/2, 200, 500,100);

        JPanel logOutPanel = new JPanel();
        logOutPanel.add(logOutButton);

        this.add(searchPanel, BorderLayout.NORTH);
        //adding to test resultPanel to Search Scene
        this.add(resultPanelTest, BorderLayout.CENTER);
        this.add(logOutPanel, BorderLayout.PAGE_END);
        this.setVisible(true);
    }

    public JButton getLogOutButton() {
        return logOutButton;
    }

    //Jonathan Murphy
    @Override
    public void actionPerformed(ActionEvent event) {
        if(event.getSource().equals(searchMenu)) {
            JComboBox b = (JComboBox) event.getSource();
            searchMenu.setSelectedItem(b.getSelectedItem());
        }
    }
    //Example data for showcase
    private static ArrayList<Chemical> getExampleData() {

        java.util.List<Room> roomsStoredAt = new ArrayList<>();
        roomsStoredAt.add(new Room(1,2));

        List<Shelf> shelvesStoredAt = new ArrayList<>();
        shelvesStoredAt.add(new Shelf(1,2));

        ArrayList<Chemical> chemicals = new ArrayList<>();
        // chemicals.add(new Chemical("Acetone", 500, "ml", "Flammable", true, "sdsSheet", "manufacturer",
        //         "purchaseDate", "expirationDate"));
        // chemicals.add(new Chemical("Ethanol", 500, "ml", "Flammable", true, "sdsSheet", "manufacturer",
        //         "purchaseDate", "expirationDate"));
        // chemicals.add(new Chemical("Water", 500, "ml", "Non-flammable", false, "sdsSheet", "manufacturer",
        //         "purchaseDate", "expirationDate"));
        // chemicals.add(new Chemical("Sulfur", 500, "ml", "Non-flammable", false, "sdsSheet", "manufacturer",
        //         "purchaseDate", "expirationDate"));
        // chemicals.add(new Chemical("Tomato", 500, "ml", "Non-flammable", false, "sdsSheet", "manufacturer",
        //         "purchaseDate", "expirationDate"));
        // chemicals.add(new Chemical("Salsa", 500, "ml", "Non-flammable", false, "sdsSheet", "manufacturer",
        //         "purchaseDate", "expirationDate"));
        return chemicals;
    }
}

    /*public static void main(String[] args) {
        // Create a new JFrame
        JFrame frame = new JFrame("Science Department Inventory");

        // Set the size of the frame
        frame.setSize(800, 600);

        // Set the default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //new SceneSwitcher(frame);
        SearchScreen n = new SearchScreen();
        frame.setContentPane(n);
        // Set the visibility of the frame
        frame.setVisible(true);
        frame.revalidate();

        // TODO make and trigger the login here

        // post login

        //setup the screen
    }*/