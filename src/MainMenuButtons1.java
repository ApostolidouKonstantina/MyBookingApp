import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/** This class creates the main menu buttons for every user*/

public class MainMenuButtons1 {

    public static final Color DarkCyan = new Color(10, 162, 162);
    public static final Color BackgroundColor = new Color(225, 234, 234, 255);

    public MainMenuButtons1(){}

    public JButton MainMenuButtons(String label){
        JButton b=new JButton(label);
        b.setForeground(DarkCyan);
        b.setBackground(BackgroundColor);
        b.setFocusPainted(false);
        b.setBorderPainted(false);
        b.addMouseListener(new MouseAdapter() {
            public void mouseEntered(MouseEvent e) {
                // When the mouse moves over a label, the background color changed.
                b.setBackground(DarkCyan);
                b.setForeground(BackgroundColor);
            }
            public void mouseExited(MouseEvent e) {
                b.setForeground(DarkCyan);
                b.setBackground(BackgroundColor);
                b.setFocusPainted(false);
                b.setBorderPainted(false);
            }
        });
        return b;
    }
}
