import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;

public class ReservationGUI extends StartMenu{
    public ReservationGUI(){}

    public void ReservationProGUI(int i, JPanel panel2, Reservation r) throws IOException, ClassNotFoundException {
        int y=20+(445*i);
        if ((y+445)>panel2.getHeight())
            panel2.setPreferredSize(new Dimension(1100,y+450));

        JLabel label=new JLabel("Code of reservation:");
        label.setForeground(DarkCyan);
        label.setBounds(50,y,150,30);
        panel2.add(label);
        JTextField txt = new JTextField(String.valueOf(r.getCodeOfReservation()));
        txt.setEditable(false);
        txt.setBounds(200,y,200,30);
        txt.setBackground(BackgroundColor);
        txt.setForeground(DarkCyan);
        panel2.add(txt);

        ArrayList<Rental> array;
        Rental rental=null;
        array= FileHandler.loadFile("rentals", Rental.class);
        for(Rental re: array){
            if (!(re.getCode()==r.getCodeOfRental())){
                rental=re;
                break;
            }
        }
        y+=50;
        JLabel label11=new JLabel("Name of Place:");
        label11.setForeground(DarkCyan);
        label11.setBounds(50,y,150,30);
        panel2.add(label11);
        assert rental != null;
        JTextField txt11 = new JTextField(rental.getNameOfPlace());
        txt11.setEditable(false);
        txt11.setBounds(200,y,200,30);
        txt11.setBackground(BackgroundColor);
        txt11.setForeground(DarkCyan);
        panel2.add(txt11);

        y+=50;
        JLabel label12=new JLabel("Location:");
        label12.setForeground(DarkCyan);
        label12.setBounds(50,y,150,30);
        panel2.add(label12);
        JTextField txt12 = new JTextField(rental.getLocation());
        txt12.setEditable(false);
        txt12.setBounds(200,y,200,30);
        txt12.setBackground(BackgroundColor);
        txt12.setForeground(DarkCyan);
        panel2.add(txt12);

        y+=50;
        JLabel label1=new JLabel("Customer Username:");
        label1.setForeground(DarkCyan);
        label1.setBounds(50,y,150,30);
        panel2.add(label1);
        JTextField txt1 = new JTextField(r.getCustomer());
        txt1.setEditable(false);
        txt1.setBounds(200,y,200,30);
        txt1.setBackground(BackgroundColor);
        txt1.setForeground(DarkCyan);
        panel2.add(txt1);

        y+=50;
        JLabel label2=new JLabel("Code of Rental:");
        label2.setForeground(DarkCyan);
        label2.setBounds(50,y,150,30);
        panel2.add(label2);
        JTextField txt2 = new JTextField(String.valueOf(r.getCodeOfRental()));
        txt2.setEditable(false);
        txt2.setBounds(200,y,200,30);
        txt2.setBackground(BackgroundColor);
        txt2.setForeground(DarkCyan);
        panel2.add(txt2);

        y+=50;
        JLabel label3=new JLabel("Check-In:");
        label3.setForeground(DarkCyan);
        label3.setBounds(50,y,150,30);
        panel2.add(label3);
        JTextField txt3 = new JTextField(String.valueOf(r.getCheckIn()));
        txt3.setEditable(false);
        txt3.setBounds(200,y,200,30);
        txt3.setBackground(BackgroundColor);
        txt3.setForeground(DarkCyan);
        panel2.add(txt3);

        y+=50;
        JLabel label4=new JLabel("Check-Out:");
        label4.setForeground(DarkCyan);
        label4.setBounds(50,y,150,30);
        panel2.add(label4);
        JTextField txt4 = new JTextField(String.valueOf(r.getCheckOut()));
        txt4.setEditable(false);
        txt4.setBounds(200,y,200,30);
        txt4.setBackground(BackgroundColor);
        txt4.setForeground(DarkCyan);
        panel2.add(txt4);

        y+=50;
        JLabel label5=new JLabel("Active:");
        label5.setForeground(DarkCyan);
        label5.setBounds(50,y,150,30);
        panel2.add(label5);
        JTextField txt5 = new JTextField(String.valueOf(r.getActivity()));
        txt5.setEditable(false);
        txt5.setBounds(200,y,200,30);
        txt5.setBackground(BackgroundColor);
        txt5.setForeground(DarkCyan);
        panel2.add(txt5);
    }

    public void ReservationCustomerGUI(int i,JPanel panel2, Reservation r, Account user) throws IOException, ClassNotFoundException {
        int y=20+(345*i);
        if ((y+345)>panel2.getHeight())
            panel2.setPreferredSize(new Dimension(1100,y+345));

        JLabel label=new JLabel("Code of reservation:");
        label.setForeground(DarkCyan);
        label.setBounds(50,y,150,30);
        panel2.add(label);
        JTextField txt = new JTextField(String.valueOf(r.getCodeOfReservation()));
        txt.setEditable(false);
        txt.setBounds(200,y,200,30);
        txt.setBackground(BackgroundColor);
        txt.setForeground(DarkCyan);
        panel2.add(txt);

        y+=50;
        JLabel label1=new JLabel("Owner Username:");
        label1.setForeground(DarkCyan);
        label1.setBounds(50,y,150,30);
        panel2.add(label1);
        JTextField txt1 = new JTextField(r.getOwner());
        txt1.setEditable(false);
        txt1.setBounds(200,y,200,30);
        txt1.setBackground(BackgroundColor);
        txt1.setForeground(DarkCyan);
        panel2.add(txt1);

        ArrayList<Rental> array;
        Rental rental=null;
        array= FileHandler.loadFile("rentals", Rental.class);
        for(Rental re: array){
            if (!(re.getCode()==r.getCodeOfRental())){
                rental=re;
                break;
            }
        }
        y+=50;
        JLabel label2=new JLabel("Name Of Rental:");
        label2.setForeground(DarkCyan);
        label2.setBounds(50,y,150,30);
        panel2.add(label2);
        assert rental != null;
        JTextField txt2 = new JTextField(rental.getNameOfPlace());
        txt2.setEditable(false);
        txt2.setBounds(200,y,200,30);
        txt2.setBackground(BackgroundColor);
        txt2.setForeground(DarkCyan);
        panel2.add(txt2);

        y+=25;
        JButton button=new JButton("Cancel This Reservation");
        button.setBounds(500,y,200,30);
        button.setForeground(BackgroundColor);
        button.setBackground(DarkCyan);
        panel2.add(button);
        y+=25;
        JLabel label21=new JLabel("Something went wrong...");
        label21.setBounds(500,y,400,30);
        label21.setForeground(BackgroundColor);
        panel2.add(label21);
        y+=25;
        JLabel label22=new JLabel("Deleted Successfully");
        label22.setBounds(500,y,400,30);
        label22.setForeground(BackgroundColor);
        panel2.add(label22);

        y-=25;
        JLabel label3=new JLabel("Check-In:");
        label3.setForeground(DarkCyan);
        label3.setBounds(50,y,150,30);
        panel2.add(label3);
        JTextField txt3 = new JTextField(String.valueOf(r.getCheckIn()));
        txt3.setEditable(false);
        txt3.setBounds(200,y,200,30);
        txt3.setBackground(BackgroundColor);
        txt3.setForeground(DarkCyan);
        panel2.add(txt3);

        y+=50;
        JLabel label4=new JLabel("Check-Out:");
        label4.setForeground(DarkCyan);
        label4.setBounds(50,y,150,30);
        panel2.add(label4);
        JTextField txt4 = new JTextField(String.valueOf(r.getCheckOut()));
        txt4.setEditable(false);
        txt4.setBounds(200,y,200,30);
        txt4.setBackground(BackgroundColor);
        txt4.setForeground(DarkCyan);
        panel2.add(txt4);

        y+=50;
        JLabel label5=new JLabel("Active:");
        label5.setForeground(DarkCyan);
        label5.setBounds(50,y,150,30);
        panel2.add(label5);
        JTextField txt5 = new JTextField(String.valueOf(r.getActivity()));
        txt5.setEditable(false);
        txt5.setBounds(200,y,200,30);
        txt5.setBackground(BackgroundColor);
        txt5.setForeground(DarkCyan);
        panel2.add(txt5);

        buttonListener = e -> {
            label2.setForeground(BackgroundColor);
            label21.setForeground(BackgroundColor);
            int code=Integer.parseInt(txt.getText());
            CustomerMainMenu c = new CustomerMainMenu();
            try {
                boolean found=c.CancelReservation(user,code);
                if (found)
                    label22.setForeground(Color.GREEN);
                else
                    label21.setForeground(Color.RED);
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        };
        button.addActionListener(buttonListener);
    }

    public void ReservationAdminGUI(int i, JPanel panel2, Reservation r) throws IOException, ClassNotFoundException {
        int y=20+(i*395);
        if ((y+395)>panel2.getHeight())
            panel2.setPreferredSize(new Dimension(1100,y+395));

        JLabel label11=new JLabel("Code of reservation:");
        label11.setForeground(DarkCyan);
        label11.setBounds(50,y,150,30);
        panel2.add(label11);
        JTextField txt11 = new JTextField(String.valueOf(r.getCodeOfReservation()));
        txt11.setEditable(false);
        txt11.setBounds(200,y,200,30);
        txt11.setBackground(BackgroundColor);
        txt11.setForeground(DarkCyan);
        panel2.add(txt11);

        y+=50;
        JLabel label=new JLabel("Owner Username:");
        label.setForeground(DarkCyan);
        label.setBounds(50,y,150,30);
        panel2.add(label);
        JTextField txt = new JTextField(r.getOwner());
        txt.setEditable(false);
        txt.setBounds(200,y,200,30);
        txt.setBackground(BackgroundColor);
        txt.setForeground(DarkCyan);
        panel2.add(txt);

        y+=50;
        JLabel label1=new JLabel("Customer Username:");
        label1.setForeground(DarkCyan);
        label1.setBounds(50,y,150,30);
        panel2.add(label1);
        JTextField txt1 = new JTextField(r.getCustomer());
        txt1.setEditable(false);
        txt1.setBounds(200,y,200,30);
        txt1.setBackground(BackgroundColor);
        txt1.setForeground(DarkCyan);
        panel2.add(txt1);

        y+=50;
        JLabel label2=new JLabel("Code Of Rental:");
        label2.setForeground(DarkCyan);
        label2.setBounds(50,y,150,30);
        panel2.add(label2);
        JTextField txt2 = new JTextField(String.valueOf(r.getCodeOfRental()));
        txt2.setEditable(false);
        txt2.setBounds(200,y,200,30);
        txt2.setBackground(BackgroundColor);
        txt2.setForeground(DarkCyan);
        panel2.add(txt2);

        ArrayList<Rental> array;
        Rental rental=null;
        array= FileHandler.loadFile("rentals", Rental.class);
        for(Rental re: array){
            if (!(re.getCode()==r.getCodeOfRental())){
                rental=re;
                break;
            }
        }
        y+=50;
        JLabel label111=new JLabel("Name of Place:");
        label111.setForeground(DarkCyan);
        label111.setBounds(50,y,150,30);
        panel2.add(label111);
        assert rental != null;
        JTextField txt111 = new JTextField(rental.getNameOfPlace());
        txt111.setEditable(false);
        txt111.setBounds(200,y,200,30);
        txt111.setBackground(BackgroundColor);
        txt111.setForeground(DarkCyan);
        panel2.add(txt111);

        y+=50;
        JLabel label12=new JLabel("Location:");
        label12.setForeground(DarkCyan);
        label12.setBounds(50,y,150,30);
        panel2.add(label12);
        JTextField txt12 = new JTextField(rental.getLocation());
        txt12.setEditable(false);
        txt12.setBounds(200,y,200,30);
        txt12.setBackground(BackgroundColor);
        txt12.setForeground(DarkCyan);
        panel2.add(txt12);

        y+=50;
        JLabel label3=new JLabel("Check-In:");
        label3.setForeground(DarkCyan);
        label3.setBounds(50,y,150,30);
        panel2.add(label3);
        JTextField txt3 = new JTextField(String.valueOf(r.getCheckIn()));
        txt3.setEditable(false);
        txt3.setBounds(200,y,200,30);
        txt3.setBackground(BackgroundColor);
        txt3.setForeground(DarkCyan);
        panel2.add(txt3);

        y+=50;
        JLabel label4=new JLabel("Check-Out:");
        label4.setForeground(DarkCyan);
        label4.setBounds(50,y,150,30);
        panel2.add(label4);
        JTextField txt4 = new JTextField(String.valueOf(r.getCheckOut()));
        txt4.setEditable(false);
        txt4.setBounds(200,y,200,30);
        txt4.setBackground(BackgroundColor);
        txt4.setForeground(DarkCyan);
        panel2.add(txt4);

        y+=50;
        JLabel label5=new JLabel("Active:");
        label5.setForeground(DarkCyan);
        label5.setBounds(50,y,150,30);
        panel2.add(label5);
        JTextField txt5 = new JTextField(String.valueOf(r.getActivity()));
        txt5.setEditable(false);
        txt5.setBounds(200,y,200,30);
        txt5.setBackground(BackgroundColor);
        txt5.setForeground(DarkCyan);
        panel2.add(txt5);
    }

}
