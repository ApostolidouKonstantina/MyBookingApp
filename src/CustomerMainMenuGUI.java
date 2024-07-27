import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.io.IOException;

/** Customer's main menu is created here. When the user makes a choice, then the menu
 * moves to the left of the frame ("SideMenuButtons") and, in a new panel, we show the requested page
 * and call the right method for it. When the user logs out the app stops.
 * */
public class CustomerMainMenuGUI extends StartMenu{

    JButton b1,b2,b4,b5;
    MainMenuButtons1 b=new MainMenuButtons1();

    public CustomerMainMenuGUI(){}

    public void CustomerMainGUI(Account user){
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
        b1=b.MainMenuButtons("Search For A Rental");
        panel.add(b1);
        b2 = new JButton();
        b2=b.MainMenuButtons("My Reservations");
        panel.add(b2);
        b4 = new JButton();
        b4=b.MainMenuButtons("My Profile");
        panel.add(b4);
        b5 = new JButton();
        b5=b.MainMenuButtons("Log Out");
        panel.add(b5);

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
            b4=sb.SideMenuButton(b4);
            b5=sb.SideMenuButton(b5);

            content.add(b1);
            content.add(b2);
            content.add(b4);
            content.add(b5);

            content.add(scroll);
            frame.pack();
            if (e.getActionCommand().equals(b1.getText())){
                SearchGUI s = new SearchGUI();
                s.SearchGUI1(user,panel2);
            }
            else if (e.getActionCommand().equals(b2.getText())){
                try {
                    CustomerMainMenu c = new CustomerMainMenu();
                    boolean found = c.ViewReservations(user,panel2);
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
            else if (e.getActionCommand().equals(b4.getText())){
                ProfileGUI p=new ProfileGUI();
                p.ProfileGUI1(0,panel2, user);
                Activity a= new Activity();
                a.saveAct(user, " viewed their profile");
            }
            else if (e.getActionCommand().equals(b5.getText())){
                Activity a= new Activity();
                a.saveAct(user, " logged out");
                System.exit(1);
            }
        };
        b1.addActionListener(buttonListener);
        b2.addActionListener(buttonListener);
        b4.addActionListener(buttonListener);
        b5.addActionListener(buttonListener);
        frame.setVisible(true);
        frame.pack();
    }

}
