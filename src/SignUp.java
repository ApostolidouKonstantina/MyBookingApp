import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/** This class manages the SignUp mechanism and prints the appropriate messages.
 * The username can't exist twice, it can't have special characters,
 * the code has to be 8 to 16 characters and no account can log in immediately
 * because they have to wait for the approval from an admin*/
public class SignUp extends StartMenu {

    JLabel password, username, name, label2;
    JRadioButton customer,provider;
    JTextField Username, Name;
    JButton button;
    JPasswordField Password;
    ActionListener buttonListener;

    public SignUp(){}

    public void SignupGUI() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        panel.setBackground(BackgroundColor);
        frame.setTitle("Sign Up");
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.setResizable(false);
        frame.setSize(new Dimension(500, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        name = new JLabel("Name");
        name.setBounds(150, 40, 70, 20);
        name.setBackground(BackgroundColor);
        name.setForeground(DarkCyan);
        panel.add(name);
        Name = new JTextField();
        Name.setBounds(150, 60, 193, 28);
        panel.add(Name);
        username = new JLabel("Username");
        username.setBounds(150, 100, 70, 20);
        username.setBackground(BackgroundColor);
        username.setForeground(DarkCyan);
        panel.add(username);
        Username = new JTextField();
        Username.setBounds(150, 120, 193, 28);
        panel.add(Username);
        password = new JLabel("Password");
        password.setBounds(150, 160, 70, 20);
        password.setBackground(BackgroundColor);
        password.setForeground(DarkCyan);
        panel.add(password);
        Password = new JPasswordField();
        Password.setBounds(150, 180, 193, 28);
        panel.add(Password);
        button = new JButton("Sign Up");
        button.setBounds(200, 275, 90, 25);
        button.setForeground(Color.WHITE);
        button.setBackground(DarkCyan);
        button.setFocusPainted(false);
        customer=new JRadioButton("Customer");
        customer.setBounds(150,220,100,30);
        customer.setBackground(BackgroundColor);
        customer.setForeground(DarkCyan);
        provider=new JRadioButton("Provider");
        provider.setBounds(250,220,100,30);
        provider.setBackground(BackgroundColor);
        provider.setForeground(DarkCyan);
        ButtonGroup group = new ButtonGroup();
        group.add(customer);
        group.add(provider);
        panel.add(customer);
        panel.add(provider);
        label2 = new JLabel();
        label2.setForeground(Color.RED);
        label2.setBounds(160, 250, 400, 20);
        panel.add(label2);

        button.addActionListener((buttonListener));
        panel.add(button);

        buttonListener = e -> {
            label2.setText(" ");
            label2.setForeground(BackgroundColor);
            String Name1 = Name.getText();
            String Username1 = Username.getText();
            String Password1 = Password.getText();
            if ((Name1.isBlank()) || (Username1.isBlank()) || (Password1.isBlank()) || ((!customer.isSelected())&(!provider.isSelected()))){
                //error
                label2.setText("One or more fields are blank");
            }
            else {
                Account user;
                if (customer.isSelected()) {
                    user = signUp(Name1, Username1, Password1, "costumer");
                } else {
                    user = signUp(Name1, Username1, Password1, "provider");
                }
                switch (user.getName()) {
                    case "!taken" -> {
                        label2.setForeground(Color.RED);
                        label2.setText("The Username is taken");
                    }
                    case "!character" -> {
                        label2.setForeground(Color.RED);
                        label2.setText("Special Character exits in the Username");
                    }
                    case "!password" -> {
                        label2.setForeground(Color.RED);
                        label2.setText("Password has to be 8 to 16 characters");
                    }
                    default -> {
                        label2.setForeground(Color.GREEN);
                        label2.setText("Your request was submitted");
                    }
                }
            }
        };
        button.addActionListener((buttonListener));
        frame.setVisible(true);
    }

    public Account signUp(String name, String username,String password, String type){
        try{
            ArrayList<Account> accounts =FileHandler.loadFile("accounts", Account.class);
            for (Account ac:accounts){
                if (ac.getUsername().equals(username)){
                    return new Account("!taken",null,null,null,false);
                }
            }
            /* we check for special characters until we get a valid username*/
            String specialCharacters = "!@#$%&*()'+,-./:;<=>?[]^_`{|} ";
            for (int i=0; i < username.length() ; i++){
                char ch = username.charAt(i);
                if(specialCharacters.contains(Character.toString(ch))) {
                    return new Account("!character",null,null,null,false);
                }
            }
            /*since the username is valid, we check if the password is valid*/
            if (password.length()<8 || password.length()>16){
                return new Account("!password",null,null,null,false);
            }
            /* if the username and password are valid, we move on and set a password and the type of the account*/
            /* we create new object*/
            Account a = new Account();
            a.setName(name);
            a.setType(type);
            a.setUsername(username);
            a.setPassword(password);
            a.setApproved(false);
            /* we finally add it into the array*/
            FileHandler.saveFile("accounts", a);
            /*we take the timestamp and save it to the activities*/
            Activity ac= new Activity();
            ac.saveAct(a, " signed up and is waiting for approval");
            return a;
        } catch (ClassNotFoundException | IOException fileNotFoundException) {
            System.err.println(fileNotFoundException);
        }
        return null;
    }
}
