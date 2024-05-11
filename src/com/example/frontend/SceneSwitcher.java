/*
@author Salim Jday
*/
package com.example.frontend;
import com.example.backend.User;
import com.example.backend.UserDatabase;

import javax.swing.*;

public class SceneSwitcher {
    private JFrame frame;
    private LoginScreen loginScreen;
    private MainTabbedPane mainTabbedPane;

    private UserDatabase users;

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
        if (user.login(this.loginScreen.getUserField().getText(), this.loginScreen.getPassField().getText())) {
            if(user.isAdmin()){
                this.mainTabbedPane.addAdminPane();
            }
            showMainTabbedPane();
        } else {
            this.loginScreen.getMessageLabel().setText("Invalid credentials, please try again.");
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