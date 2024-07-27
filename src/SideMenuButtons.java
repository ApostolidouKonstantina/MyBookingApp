import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/** This class creates the main menu buttons for every user*/
public class SideMenuButtons {

    public static final Color DarkCyan = new Color(10, 162, 162);
    public static final Color BackgroundColor = new Color(225, 234, 234, 255);
    //JButton b;

    public SideMenuButtons(){}

    public JButton SideMenuButton(JButton b){
        b.setForeground(BackgroundColor);
        b.setBackground(DarkCyan);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                // When the mouse moves over a label, the background color changed.
                b.setBackground(BackgroundColor);
                b.setForeground(DarkCyan);
            }
            public void mouseExited(MouseEvent e) {
                b.setForeground(BackgroundColor);
                b.setBackground(DarkCyan);
                b.setFocusPainted(false);
                b.setBorderPainted(false);
            }
        });
        return b;
    }
}
