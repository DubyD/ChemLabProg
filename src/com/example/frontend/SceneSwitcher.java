/*
@author Salim Jday
*/
package com.example.frontend;
import com.example.backend.User;

import javax.swing.*;

public class SceneSwitcher {
    private JFrame frame;
    private LoginScreen loginScreen;
    private MainTabbedPane mainTabbedPane;

    public SceneSwitcher(JFrame frame) {
        this.frame = frame;

        initializeScreens();
        setupLoginScreenActions();
    }

    private void initializeScreens() {
        this.loginScreen = new LoginScreen(this.frame);
        this.frame.setContentPane(this.loginScreen);
        this.frame.revalidate();
        this.frame.repaint();
        this.frame.setVisible(true);
        this.mainTabbedPane = new MainTabbedPane();

        // this.userSheet = new userSheet();

    }
    //login with both clicking Login and pressing the Enter key on keyboard
    private void setupLoginScreenActions() {
        this.loginScreen.getLoginButton().addActionListener(e -> {
            loginToSearch();
        });
        this.loginScreen.getPassField().addActionListener(e -> {
            loginToSearch();
        });
    }

    private void loginToSearch(){
        User user = new User();
        String username = this.loginScreen.getUserField().getText();
        String password = String.valueOf(this.loginScreen.getPassField().getPassword());
        if (user.login(username, password)) {
            if(user.isAdmin()){
                this.mainTabbedPane.addAdminPane();
            }
                //Puts the UserName in top right
            this.mainTabbedPane.setUserName(user.getUsername());
            showMainTabbedPane();
            mainTabbedPane.getLogoutButton().addActionListener(e -> {
                user.logout();
                showLoginScreen();
            });
        } else {
            if(User.getPendingDatabase().userExists(username)){
            this.loginScreen.getMessageLabel().setText("<html>Account has not been activated.<br/>Contact the administrator.</html>");    
            }else{
                if(User.getDatabase().userExists(username)){
             this.loginScreen.getMessageLabel().setText("Wrong password, please try again.");
                }
                else{
                    this.loginScreen.getMessageLabel().setText("Account not found, please try again.");
                }
            }
            
        }
    }


        //UserDataBase has a verify log in method
    /*private boolean authenticate(String username, String password) {

        return this.userSheet.verifyLogin(username, password);

    }*/

    private void showMainTabbedPane() {
        this.frame.setContentPane(mainTabbedPane);

        this.frame.revalidate();
        this.frame.repaint();
    }

    private void showLoginScreen() {
        this.frame.setContentPane(loginScreen);
        this.mainTabbedPane = new MainTabbedPane();
        loginScreen.getUserField().setText("");
        loginScreen.getPassField().setText("");

        this.frame.revalidate();
        this.frame.repaint();
    }


}
/**private void showSearchScreen() {
        //Exits Application
        mainTabbedPane.getSearchScreen().getLogOutButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loginScreen.loginArea();
                frame.setContentPane(loginScreen);
            }
        });

        this.frame.setContentPane(mainTabbedPane);
        this.frame.revalidate();
    }
}
 */