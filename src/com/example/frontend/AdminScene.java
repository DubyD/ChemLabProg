/*
 Augustus: Make an adminScene class where if the user is an admin they can click a button to go and add new users, doesn’t have to do anything at the moment just a new scene that can switch back to the searchScene, (add a button in the SearchScene to switch to it (Use SceneSwitcher))
 @author Augustus 'Gus' Warmington
The adminScene class provides an exclusive scene for admins only, to add and remove users
*/
package com.example.frontend;

import com.example.backend.User;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.ListSelectionModel;

public class AdminScene {
    private JFrame frame;
    private JPanel panel;
    private ArrayList<User> unapprovedUsers;
    private ArrayList<User> approvedUsers;
    private JButton returnButton;

    private JList<User> cUserList;
    private JList<User> pUserList;
    private JScrollPane cListScroller;
    private JScrollPane pListScroller;

    public AdminScene(JFrame frame, ArrayList<User> unapprovedUsers, ArrayList<User> approvedUsers) {
        this.frame = frame;
        panel = new JPanel();

        if (unapprovedUsers == null) {
            this.unapprovedUsers = new ArrayList<>();
        } else {
            this.unapprovedUsers = unapprovedUsers;
        }
        if (approvedUsers == null) {
            this.approvedUsers = new ArrayList<>();
        } else {
            this.approvedUsers = approvedUsers;
        }

        runScene();
    }

    public AdminScene(JFrame frame) {
        this.frame = frame;
        panel = new JPanel();

        this.unapprovedUsers = new ArrayList<>();
        this.approvedUsers = new ArrayList<>();

        runScene();
    }

    public AdminScene() {
        this.frame = new JFrame("User Management");
        panel = new JPanel();

        this.unapprovedUsers = new ArrayList<>();

        this.approvedUsers = new ArrayList<>();

        runScene();
    }

    private void runScene() {
        unapprovedUsers.add(new User("Gus", "password"));
        // Temporary test user

        JButton approveUserButton = new JButton("Approve");
        approveUserButton.setVisible(false);
        JButton denyUserButton = new JButton("Deny");
        denyUserButton.setVisible(false);

        cUserList = new JList<User>((User[]) approvedUsers.toArray());
        cUserList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        pUserList = new JList<User>((User[]) unapprovedUsers.toArray());
        pUserList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);

        cListScroller = new JScrollPane(cUserList);
        cListScroller.setWheelScrollingEnabled(true);
        cListScroller.createVerticalScrollBar();
        cListScroller.setPreferredSize(new Dimension(200, 70));
        cListScroller.setVisible(false);

        pListScroller = new JScrollPane(pUserList);
        pListScroller.setWheelScrollingEnabled(true);
        pListScroller.createVerticalScrollBar();
        pListScroller.setPreferredSize(new Dimension(200, 70));
        pListScroller.setVisible(false);
        /*
         * pUserList.addListSelectionListener(event ->{
         * approveUserButton.setVisible(true);
         * denyUserButton.setVisible(true);
         * });
         */

        JButton currentUsersButton = new JButton("Active Users (" + approvedUsers.size() + ")");
        currentUsersButton.addActionListener(event -> {
            if (cListScroller.isVisible()) {
                cListScroller.setVisible(false);
            } else {
                cListScroller.setVisible(true);
            }
            pListScroller.setVisible(false);
            approveUserButton.setVisible(false);
            denyUserButton.setVisible(false);

            frame.revalidate();
        });

        JButton pendingUsersButton = new JButton("Pending Users (" + unapprovedUsers.size() + ")");
        pendingUsersButton.addActionListener(event -> {
            if (pListScroller.isVisible()) {
                pListScroller.setVisible(false);
                approveUserButton.setVisible(false);
                denyUserButton.setVisible(false);
            } else {
                pListScroller.setVisible(true);
                approveUserButton.setVisible(true);
                denyUserButton.setVisible(true);
            }
            cListScroller.setVisible(false);
            frame.revalidate();
        });

        panel.add(currentUsersButton);
        panel.add(pendingUsersButton);
        panel.add(cListScroller);
        panel.add(pListScroller);
        panel.add(approveUserButton);
        panel.add(denyUserButton);

        returnButton = new JButton("Return");

        panel.add(returnButton);
        frame.add(panel);
        frame.revalidate();
    }

    public void createNewUser(String username, String password) {
        // Not implemented yet
        User user = new User(username, password);
        unapprovedUsers.add(user);

    }

    {

    }

    private void saveNewUser(User user) {
        // Not implemented yet
        approvedUsers.add(user);
        unapprovedUsers.remove(user);

    }

    public JButton getReturnButton() {
        return returnButton;
    }

    public String toString() {
        return "This class holds a user management screen.";
        // Perhaps add user count
    }
}
