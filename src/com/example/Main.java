package com.example;

import com.example.frontend.SceneSwitcher;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        // Create a new JFrame
        JFrame frame = new JFrame("Swing Application");

        // Set the size of the frame
        frame.setSize(400, 300);

        // Set the default close operation
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        new SceneSwitcher(frame);

        // Set the visibility of the frame
        frame.setVisible(true);

        // TODO make and trigger the login here

        // post login

        //setup the screen
    }
}