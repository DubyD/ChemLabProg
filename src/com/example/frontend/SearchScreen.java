package com.example.frontend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

public class SearchScreen extends JPanel implements ActionListener {
    private JButton logOutButton, searchButton;
    private JTextField searchBar;
    private JComboBox searchMenu;
    private SearchResultPanel resultPnel;

    public SearchScreen() {
        //Jonathan Murphy
        setPreferredSize(new Dimension(800, 600));//just for now
        this.setLayout(new BorderLayout());
        logOutButton = new JButton("EXIT");
        searchButton = new JButton("SEARCH");
        searchBar = new JTextField("Search Chemical");
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

        this.add(searchPanel);
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
}