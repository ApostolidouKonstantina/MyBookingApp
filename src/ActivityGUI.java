import javax.swing.*;
import java.awt.*;

public class ActivityGUI extends StartMenu{

    public ActivityGUI(){}

    public void ActivityGUI1(int i, JPanel panel2, Activity ac){
        int y=20+(i*40);
        if ((y+40)>panel2.getHeight())
            panel2.setPreferredSize(new Dimension(1100,y+40));

        JLabel label1=new JLabel(ac.getTime()+ "  user -"+ac.getUsername()+"- "+ac.getAction());
        label1.setForeground(DarkCyan);
        label1.setBounds(50,y,500,30);
        panel2.add(label1);
    }
}
