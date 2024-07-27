import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import javax.swing.JFrame;
import javax.swing.border.EmptyBorder;

/** Admin's main menu is created here. When the user makes a choice, then the menu
 * moves to the left of the frame ("SideMenuButtons") and, in a new panel, we show the requested page
 * and call the right method for it. When the user logs out the app stops.
 * */

public class AdminMainMenuGUI extends StartMenu {

    JButton b1,b2,b3,b4,b5,b6,b7,b8,b9;
    MainMenuButtons1 b=new MainMenuButtons1();

    public AdminMainMenuGUI(){}

    public void AdminMainGUI(Account user) {
        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(0,1));
        frame.setPreferredSize(new Dimension(1110, 700));
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setBackground(BackgroundColor);
        panel.setBackground(BackgroundColor);
        frame.setTitle("My Booking");
        frame.setLocationRelativeTo(null);
        frame.add(panel);
        frame.setResizable(false);
        b1 = new JButton();
        b1=b.MainMenuButtons("All Accounts");
        panel.add(b1);
        b2 = new JButton();
        b2=b.MainMenuButtons("All Rentals");
        panel.add(b2);
        b3 = new JButton();
        b3=b.MainMenuButtons("App Activity");
        panel.add(b3);
        b4 = new JButton();
        b4=b.MainMenuButtons("All Reservations");
        panel.add(b4);
        b5 = new JButton();
        b5=b.MainMenuButtons("Active Reservations");
        panel.add(b5);
        b6 = new JButton();
        b6=b.MainMenuButtons("Cancelled Reservations");
        panel.add(b6);
        b9=new JButton();
        b9=b.MainMenuButtons("Search");
        panel.add(b9);
        b7 = new JButton();
        b7=b.MainMenuButtons("Sign Up Requests");
        panel.add(b7);
        b8 = new JButton();
        b8=b.MainMenuButtons("Log Out");
        panel.add(b8);

        buttonListener = e -> {
            JPanel panel2 = new JPanel();
            panel2.setLayout(null);
            panel2.setPreferredSize(new Dimension(1100,620));
            JPanel content = new JPanel();
            content.setBorder(new EmptyBorder(5, 0, 0, 0));
            frame.setContentPane(content);
            content.setBackground(DarkCyan);
            JScrollPane scroll = new JScrollPane(panel2, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
            scroll.setPreferredSize(new Dimension(1100, 625));
            scroll.setViewportView(panel2);
            panel2.setBackground(BackgroundColor);
            panel.setVisible(false);

            SideMenuButtons sb = new SideMenuButtons();
            b1=sb.SideMenuButton(b1);
            b2=sb.SideMenuButton(b2);
            b3=sb.SideMenuButton(b3);
            b4=sb.SideMenuButton(b4);
            b5=sb.SideMenuButton(b5);
            b6=sb.SideMenuButton(b6);
            b9=sb.SideMenuButton(b9);
            b7=sb.SideMenuButton(b7);
            b8=sb.SideMenuButton(b8);

            content.add(b1);
            content.add(b2);
            content.add(b3);
            content.add(b4);
            content.add(b5);
            content.add(b6);
            content.add(b9);
            content.add(b7);
            content.add(b8);

            content.add(scroll);
            frame.pack();

            AdminMainMenu a = new AdminMainMenu();
            if (e.getActionCommand().equals(b1.getText())){
                try {
                    a.ViewProfiles(user, panel2);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
            else if (e.getActionCommand().equals(b2.getText())){
                try {
                    a.ViewRentals(user,panel2);
                } catch (IOException | ClassNotFoundException | URISyntaxException ex) {
                    ex.printStackTrace();
                }
            }
            else if (e.getActionCommand().equals(b3.getText())){
                try {
                    a.ViewActivity(user,panel2);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
            else if (e.getActionCommand().equals(b4.getText())){
                try {
                    AdminMainMenu ad = new AdminMainMenu();
                    boolean found = ad.ViewReservations(user,panel2);
                    if (!found){
                        JLabel label1=new JLabel("No reservations found");
                        label1.setForeground(Color.RED);
                        label1.setBounds(50,50,300,30);
                        panel2.add(label1);
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
            else if (e.getActionCommand().equals(b5.getText())){
                try {
                    AdminMainMenu ad = new AdminMainMenu();
                    boolean found = ad.ViewPartReservations(user,panel2,true);
                    if (!found){
                        JLabel label1=new JLabel("No active reservations found");
                        label1.setForeground(Color.RED);
                        label1.setBounds(50,50,300,30);
                        panel2.add(label1);
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
            else if (e.getActionCommand().equals(b6.getText())){
                try {
                    AdminMainMenu ad = new AdminMainMenu();
                    boolean found = ad.ViewPartReservations(user,panel2,false);
                    if (!found){
                        JLabel label1=new JLabel("No cancelled reservations found");
                        label1.setForeground(Color.RED);
                        label1.setBounds(50,50,300,30);
                        panel2.add(label1);
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
            else if (e.getActionCommand().equals(b9.getText())){
                SearchGUI s=new SearchGUI();
                s.AdminSearchGUI(user,panel2);
            }
            else if (e.getActionCommand().equals(b7.getText())){
                try {
                    boolean found = a.EditRequests(user,panel2);
                    if (!found){
                        JLabel label1=new JLabel("No requests found");
                        label1.setForeground(Color.RED);
                        label1.setBounds(50,50,300,30);
                        panel2.add(label1);
                    }
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }
            else if (e.getActionCommand().equals(b8.getText())){
                Activity ac = new Activity();
                ac.saveAct(user, " logged out");
                System.exit(1);
            }
        };
        b1.addActionListener(buttonListener);
        b2.addActionListener(buttonListener);
        b3.addActionListener(buttonListener);
        b4.addActionListener(buttonListener);
        b5.addActionListener(buttonListener);
        b6.addActionListener(buttonListener);
        b7.addActionListener(buttonListener);
        b8.addActionListener(buttonListener);
        b9.addActionListener(buttonListener);
        frame.setVisible(true);
        frame.pack();
    }

}
