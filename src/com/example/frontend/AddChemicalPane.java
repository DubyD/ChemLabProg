package com.example.frontend;

import java.awt.BorderLayout;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;

public class AddChemicalPane extends JPanel{
    public AddChemicalPane(){
        // Set the layout of the panel
        setLayout(new BorderLayout());

        //thow a basic text box and button in as a placeholder
        JTextField textField = new JTextField();
        JButton button = new JButton("Add Chemical");
        button.addActionListener(event -> {
            //send an alert "chemical added"
            JPopupMenu popup = new JPopupMenu();
        });

        add(textField, BorderLayout.CENTER);
        add(button, BorderLayout.SOUTH);
        
    }
    
}
