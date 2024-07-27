import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class ProfileGUI extends StartMenu{

    public ProfileGUI(){}

    public void ProfileGUI1(int i, JPanel panel2, Account user){
        int y=20+(i*245);
        if ((y+250)>panel2.getHeight())
            panel2.setPreferredSize(new Dimension(1100,y+250));

        JLabel label1=new JLabel("Name:");
        label1.setForeground(DarkCyan);
        label1.setBounds(50,y,150,30);
        panel2.add(label1);
        JTextField txt1 = new JTextField(user.getName());
        txt1.setEditable(false);
        txt1.setBounds(200,y,200,30);
        txt1.setBackground(BackgroundColor);
        txt1.setForeground(DarkCyan);
        panel2.add(txt1);

        y+=50;
        JLabel label2=new JLabel("Username:");
        label2.setForeground(DarkCyan);
        label2.setBounds(50,y,150,30);
        panel2.add(label2);
        JTextField txt2 = new JTextField(user.getUsername());
        txt2.setEditable(false);
        txt2.setBounds(200,y,200,30);
        txt2.setBackground(BackgroundColor);
        txt2.setForeground(DarkCyan);
        panel2.add(txt2);

        y+=50;
        JLabel label3=new JLabel("Password:");
        label3.setForeground(DarkCyan);
        label3.setBounds(50,y,150,30);
        panel2.add(label3);
        JTextField txt3 = new JTextField(user.getPassword());
        txt3.setEditable(false);
        txt3.setBounds(200,y,200,30);
        txt3.setBackground(BackgroundColor);
        txt3.setForeground(DarkCyan);
        panel2.add(txt3);

        y+=50;
        JLabel label4=new JLabel("Type of Account:");
        label4.setForeground(DarkCyan);
        label4.setBounds(50,y,150,30);
        panel2.add(label4);
        JTextField txt4 = new JTextField(user.getType());
        txt4.setEditable(false);
        txt4.setBounds(200,y,200,30);
        txt4.setBackground(BackgroundColor);
        txt4.setForeground(DarkCyan);
        panel2.add(txt4);
    }

    public void ProfileGUI2(int i, JPanel panel2, Account user){
        int y=20+(i*245);
        if ((y+250)>panel2.getHeight())
            panel2.setPreferredSize(new Dimension(1100,y+250));

        JLabel label1=new JLabel("Name:");
        label1.setForeground(DarkCyan);
        label1.setBounds(50,y,150,30);
        panel2.add(label1);
        JTextField txt1 = new JTextField(user.getName());
        txt1.setEditable(false);
        txt1.setBounds(200,y,200,30);
        txt1.setBackground(BackgroundColor);
        txt1.setForeground(DarkCyan);
        panel2.add(txt1);

        y+=50;
        JLabel label2=new JLabel("Username:");
        label2.setForeground(DarkCyan);
        label2.setBounds(50,y,150,30);
        panel2.add(label2);
        JTextField txt2 = new JTextField(user.getUsername());
        txt2.setEditable(false);
        txt2.setBounds(200,y,200,30);
        txt2.setBackground(BackgroundColor);
        txt2.setForeground(DarkCyan);
        panel2.add(txt2);

        y+=25;
        JButton button=new JButton("Delete This Account");
        button.setForeground(BackgroundColor);
        button.setBackground(DarkCyan);
        button.setBounds(600,y,200,30);
        panel2.add(button);
        JLabel l = new JLabel("Deleted Successfully");
        y+=25;
        l.setBounds(600,y,200,30);
        l.setForeground(BackgroundColor);
        panel2.add(l);

        JLabel label3=new JLabel("Password:");
        label3.setForeground(DarkCyan);
        label3.setBounds(50,y,150,30);
        panel2.add(label3);
        JTextField txt3 = new JTextField(user.getPassword());
        txt3.setEditable(false);
        txt3.setBounds(200,y,200,30);
        txt3.setBackground(BackgroundColor);
        txt3.setForeground(DarkCyan);
        panel2.add(txt3);

        y+=50;
        JLabel label4=new JLabel("Type of Account:");
        label4.setForeground(DarkCyan);
        label4.setBounds(50,y,150,30);
        panel2.add(label4);
        JTextField txt4 = new JTextField(user.getType());
        txt4.setEditable(false);
        txt4.setBounds(200,y,200,30);
        txt4.setBackground(BackgroundColor);
        txt4.setForeground(DarkCyan);
        panel2.add(txt4);

        buttonListener = e -> {
            try {
                FileHandler.deleteObj("accounts",user);
                FileHandler.saveFile("activities","deleted an account");
                button.setForeground(DarkCyan);
                button.setBackground(BackgroundColor);
                l.setForeground(Color.GREEN);
            } catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
            }
        };
        button.addActionListener(buttonListener);
    }

    /** "ProfileGUIForApproval" shows the accounts and lets the admin choose
     * if they want to approve or dismiss the request*/
    public void ProfileGUIForApproval(int i, JPanel panel2, Account user){
        this.ProfileGUI1(i,panel2,user);
        int y=70+(i*245);
        if ((y+245)>panel2.getHeight())
            panel2.setPreferredSize(new Dimension(1100,y+245));

        JButton b1=new JButton("Approve");
        b1.setBounds(600,y,150,20);
        b1.setForeground(BackgroundColor);
        b1.setBackground(DarkCyan);
        b1.setFocusPainted(false);
        b1.setBorderPainted(false);
        panel2.add(b1);
        y+=50;
        JButton b2=new JButton("Dismiss");
        b2.setBounds(600,y,150,20);
        b2.setForeground(BackgroundColor);
        b2.setBackground(DarkCyan);
        b2.setFocusPainted(false);
        b2.setBorderPainted(false);
        panel2.add(b2);

        buttonListener = e -> {
            try {
                if (e.getActionCommand().equals("Approve")){
                    FileHandler.deleteObj("accounts",user);
                    user.setApproved(true);
                    FileHandler.saveFile("accounts", user);
                    b1.setBackground(BackgroundColor);
                    b1.setForeground(DarkCyan);
                }
                else{
                    FileHandler.deleteObj("accounts",user);
                    b2.setBackground(BackgroundColor);
                    b2.setForeground(DarkCyan);
                }
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        };
        b1.addActionListener(buttonListener);
        b2.addActionListener(buttonListener);
    }

}
