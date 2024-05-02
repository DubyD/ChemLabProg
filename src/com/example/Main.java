package com.example;

import com.example.frontend.SceneSwitcher;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        // Create a new JFrame
        JFrame frame = new JFrame("Science Department Inventory");

        // Set the size of the frame
        frame.setSize(800, 600);

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


/*

//Some other version of code, mostly for testing. By SJ

package com.example;

import com.example.frontend.SceneSwitcher;

import frontend.SceneSwitcher;

import javax.swing.JFrame;

public class Main {
    public static void main(String[] args) {
        // Create a new JFrame
        JFrame frame = new JFrame("Science Department Inventory");

        // Set the default close operation
        frame.setSize(600, 400);

        // Set the size of the frame
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        new SceneSwitcher(frame);

        // Set the visibility of the frame
        frame.setVisible(true);

        // TODO make and trigger the login here

        // post login

        //setup the screen
    }
}

*/
