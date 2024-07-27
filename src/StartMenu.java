import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

/** This class is used to get the user's choice in the beginning,
 * if they want to log in or sign up, and contains objects
 * that most of the other classes use (colors, frame, buttonListener)*/
public class StartMenu extends JFrame {

    ActionListener buttonListener;
    JFrame frame = new JFrame();
    public static final Color DarkCyan = new Color(10, 162, 162);
    public static final Color BackgroundColor = new Color(225, 234, 234, 255);

    public void Start() {
        JPanel panel = new JPanel();
        panel.setLayout(null);
        frame.setLocationRelativeTo(null);
        frame.setTitle("Log In / Sign Up");
        frame.setLocationRelativeTo(null);
        panel.setBackground(BackgroundColor);
        frame.add(panel);
        frame.setResizable(false);
        frame.setSize(new Dimension(300, 400));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JButton button1, button2;
        JLabel label;
        button1 = new JButton("Log In");
        button1.setBounds(100, 100, 90, 25);
        button1.setForeground(Color.WHITE);
        button1.setBackground(DarkCyan);
        button1.setFocusPainted(false);
        label = new JLabel("OR");
        label.setForeground(DarkCyan);
        label.setBounds(135, 150, 70, 20);
        button2 = new JButton("Sign Up");
        button2.setBounds(100, 200, 90, 25);
        button2.setForeground(Color.WHITE);
        button2.setBackground(DarkCyan);
        button2.setFocusPainted(false);
        panel.add(button1);
        panel.add(label);
        panel.add(button2);


        buttonListener = e -> {
            String action = e.getActionCommand();
            if (action.equals("Log In")) {
                frame.dispose();
                LogIn login = new LogIn();
                login.LoginGUI();
            }
            else {
                frame.dispose();
                SignUp signup = new SignUp();
                signup.SignupGUI();
            }

        };
        button1.addActionListener((buttonListener));
        button2.addActionListener((buttonListener));
        frame.setVisible(true);
    }
}