import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/** This class is managing the login of any user and shows the appropriate messages
 * in case the login wasn't successful. If it was successful, it calls one of the main
 * menu classes, depending on the type of the account (admin, provider, customer)*/

public class LogIn extends StartMenu {

    JLabel password, username, label2;
    JTextField Username;
    JButton button;
    JPasswordField Password;
    ActionListener buttonListener;

    public LogIn(){}

    public void LoginGUI() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(BackgroundColor);
        frame.setTitle("Log In");
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.setResizable(false);
        frame.setSize(new Dimension(500, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        username = new JLabel("Username");
        username.setBounds(150, 75, 70, 20);
        username.setBackground(BackgroundColor);
        username.setForeground(DarkCyan);
        panel.add(username);
        Username = new JTextField();
        Username.setBounds(150, 100, 193, 28);
        panel.add(Username);
        password = new JLabel("Password");
        password.setBounds(150, 150, 70, 20);
        password.setBackground(BackgroundColor);
        password.setForeground(DarkCyan);
        panel.add(password);
        Password = new JPasswordField();
        Password.setBounds(150, 175, 193, 28);
        panel.add(Password);
        button = new JButton("Log In");
        button.setBounds(200, 250, 90, 25);
        button.setForeground(Color.WHITE);
        button.setBackground(DarkCyan);
        button.setFocusPainted(false);
        button.addActionListener((buttonListener));
        panel.add(button);
        label2 = new JLabel();
        label2.setForeground(Color.RED);
        label2.setBounds(150, 215, 400, 20);
        panel.add(label2);

        buttonListener = e -> {
            label2.setText(" ");
            String Username1 = Username.getText();
            String Password1 = Password.getText();
            if ((Username1.isBlank()) || (Password1.isBlank())){
                //error
                label2.setText("One or more fields are blank");
            }
            else{
                Account user=logIn(Username1,Password1);
                switch (user.getName()) {
                    case "!username" -> {
                        label2.setText("Username doesn't exist");
                        user = null;
                    }
                    case "!password" -> {
                        label2.setText("Wrong Password");
                        user = null;
                    }
                    case "!approved" -> {
                        label2.setText("Waiting for Approval");
                        user = null;
                    }
                }
                if (user!=null && user.getType().equals("admin")){
                    // admin 's main menu
                    frame.dispose();
                    AdminMainMenuGUI a = new AdminMainMenuGUI();
                    a.AdminMainGUI(user);
                }
                else if (user!=null && user.getType().equals("provider")){
                    // provider's main menu
                    frame.dispose();
                    ProviderMainMenuGUI p= new ProviderMainMenuGUI();
                    p.ProviderMainGUI(user);
                }
                else if (user!=null) {
                    // customer's main menu
                    frame.dispose();
                    CustomerMainMenuGUI c = new CustomerMainMenuGUI();
                    c.CustomerMainGUI(user);
                }
            }
        };
        button.addActionListener(buttonListener);
        frame.setVisible(true);
    }

    public Account logIn(String username, String password){
        /* if the user chose log in option*/
        Account a = new Account();
        boolean match;
        try{
            match=false;
            /* we check if the username exists*/
            ArrayList<Account> accounts = FileHandler.loadFile("accounts", Account.class);
            for (Account ac:accounts)
                if (ac.getUsername().equals(username)){
                    /* if the username exists, we save it*/
                    a=ac;
                    match=true;
                }
            if (!match)
                return new Account("!username",null,null,null,false);
            /* since we found a matching username, we ask and check the password*//*we check if the password is valid, if not they retry*/
            if (!a.getPassword().equals(password)){
                return new Account("!password",null,null,null,false);
            }
            Activity ac = new Activity();
            if (a.getApproved()) {
                ac.saveAct(a, " logged in");
                return a;
            }
            else {
                ac.saveAct(a, " tried to log in");
                return new Account("!approved",null,null,null,false);
            }
        } catch (IOException | ClassNotFoundException fileNotFoundException) {
            System.err.println(fileNotFoundException);
        }
        return null;
    }

}