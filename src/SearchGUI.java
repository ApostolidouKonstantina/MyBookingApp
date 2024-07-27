import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/** This class constructs the search page of rentals, collects the user's criteria and then calls
 * "Reserve" method in order to look for the rentals that matches them. If none are found, or if
 * there is a wrong input, it prints the appropriate message on the screen.*/
public class SearchGUI extends StartMenu {

    String Location, Type;
    Date CheckIn,CheckOut;
    double MinPrice, MaxPrice, MinSpace, MaxSpace;

    public SearchGUI(){}

    public void SearchGUI1(Account user, JPanel panel2){
        JLabel label=new JLabel("What are you looking for?");
        label.setForeground(DarkCyan);
        label.setBounds(50,20,300,30);
        panel2.add(label);
        JRadioButton house=new JRadioButton("House");
        house.setBounds(50,50,100,30);
        house.setBackground(BackgroundColor);
        house.setForeground(DarkCyan);
        JRadioButton hotel=new JRadioButton("Hotel");
        hotel.setBounds(160,50,100,30);
        hotel.setBackground(BackgroundColor);
        hotel.setForeground(DarkCyan);
        JRadioButton apartment=new JRadioButton("Apartment");
        apartment.setBounds(260,50,100,30);
        apartment.setBackground(BackgroundColor);
        apartment.setForeground(DarkCyan);
        ButtonGroup group = new ButtonGroup();
        group.add(house);
        group.add(hotel);
        group.add(apartment);
        panel2.add(house);
        panel2.add(hotel);
        panel2.add(apartment);

        JLabel label2=new JLabel("Type the location you desire (city)");
        label2.setForeground(DarkCyan);
        label2.setBounds(50,100,300,30);
        panel2.add(label2);
        JTextField location=new JTextField();
        location.setBounds(50,125,300,30);
        location.setForeground(DarkCyan);
        panel2.add(location);

        JLabel label3=new JLabel("Type your check-in date (dd/MM/yyyy)");
        label3.setForeground(DarkCyan);
        label3.setBounds(50,175,300,30);
        panel2.add(label3);
        JTextField cin=new JTextField();
        cin.setBounds(50,200,300,30);
        cin.setForeground(DarkCyan);
        panel2.add(cin);

        JLabel label4=new JLabel("Type your check-out date (dd/MM/yyyy)");
        label4.setForeground(DarkCyan);
        label4.setBounds(50,250,300,30);
        panel2.add(label4);
        JTextField cout=new JTextField();
        cout.setBounds(50,275,300,30);
        cout.setForeground(DarkCyan);
        panel2.add(cout);
        //+check if dates are in right form

        JLabel label5=new JLabel("Select your price range in Euro (€)");
        label5.setForeground(DarkCyan);
        label5.setBounds(50,325,300,30);
        panel2.add(label5);
        JLabel label6=new JLabel("Minimum Price:");
        label6.setForeground(DarkCyan);
        label6.setBounds(50,350,100,30);
        panel2.add(label6);
        JTextField minPrice = new JTextField("0");
        minPrice.setBounds(160,350,100,30);
        minPrice.setForeground(DarkCyan);
        panel2.add(minPrice);
        JLabel label7=new JLabel("Maximum Price:");
        label7.setForeground(DarkCyan);
        label7.setBounds(300,350,100,30);
        panel2.add(label7);
        JTextField maxPrice = new JTextField("0");
        maxPrice.setForeground(DarkCyan);
        maxPrice.setBounds(430,350,100,30);
        panel2.add(maxPrice);

        JLabel label8=new JLabel("Select your space range");
        label8.setForeground(DarkCyan);
        label8.setBounds(50,400,300,30);
        panel2.add(label8);
        JLabel label9=new JLabel("Minimum Space:");
        label9.setForeground(DarkCyan);
        label9.setBounds(50,425,100,30);
        panel2.add(label9);
        JTextField minSpace = new JTextField("0");
        minSpace.setBounds(160,425,100,30);
        minSpace.setForeground(DarkCyan);
        panel2.add(minSpace);
        JLabel label10=new JLabel("Maximum Space:");
        label10.setForeground(DarkCyan);
        label10.setBounds(300,425,100,30);
        panel2.add(label10);
        JTextField maxSpace = new JTextField("0");
        maxSpace.setForeground(DarkCyan);
        maxSpace.setBounds(430,425,100,30);
        panel2.add(maxSpace);

        JButton search= new JButton("Search");
        search.setBounds(50,475,100,25);
        search.setBackground(DarkCyan);
        search.setForeground(BackgroundColor);
        panel2.add(search);

        JLabel label11= new JLabel("One or more fields are blank");
        label11.setBounds(50,525,300,25);
        label11.setBackground(BackgroundColor);
        label11.setForeground(BackgroundColor);
        panel2.add(label11);
        JLabel label12= new JLabel("Invalid input");
        label12.setBounds(50,550,300,25);
        label12.setBackground(BackgroundColor);
        label12.setForeground(BackgroundColor);
        panel2.add(label12);

        ActionListener buttonListener2 = e -> {
            boolean validin=true;
            label11.setForeground(BackgroundColor);
            label12.setForeground(BackgroundColor);
            try {
                Location = location.getText();
                String checkin = cin.getText();
                String checkout = cout.getText();
                MinPrice = Double.parseDouble(minPrice.getText());
                MaxPrice = Double.parseDouble(maxPrice.getText());
                MinSpace = Double.parseDouble(minSpace.getText());
                MaxSpace = Double.parseDouble(maxSpace.getText());
                if ((Location.isBlank()) || (checkin.isBlank()) || (checkout.isBlank()) || (MaxPrice==0) || (MaxSpace==0) || ((!house.isSelected())&(!hotel.isSelected())&(!apartment.isSelected()))){
                    //error
                    label11.setForeground(Color.RED);
                    validin=false;
                }
                else {
                    String format = "dd/MM/yyyy";
                    try {
                        CheckIn = new SimpleDateFormat(format).parse(checkin);
                        CheckOut = new SimpleDateFormat(format).parse(checkout);
                    } catch (ParseException ex) {
                        label12.setForeground(Color.RED);
                        validin = false;
                    }
                    Date date = new Date();
                    if ((CheckIn.after(CheckOut)) || (CheckIn.before(date))) {
                        label12.setForeground(Color.RED);
                        validin = false;
                    }
                }
            }
            catch (NumberFormatException ex) {
                label12.setForeground(Color.RED);
                validin=false;
            }
            if (validin){
                CustomerMainMenu c = new CustomerMainMenu();
                if (house.isSelected())
                    Type="House";
                else if (hotel.isSelected())
                    Type="Hotel";
                else
                    Type="Apartment";
                panel2.removeAll();
                panel2.validate();
                panel2.repaint();
                boolean found=c.Reserve(user,panel2,Location,Type,CheckIn,CheckOut,MinPrice,MaxPrice,MinSpace,MaxSpace);
                if (!found){
                    JLabel label1=new JLabel("No places that meet your requirements were found");
                    label1.setForeground(Color.RED);
                    label1.setBounds(50,50,300,30);
                    panel2.add(label1);

                }
            }
        };
        search.addActionListener(buttonListener2);
    }

    public void AdminSearchGUI(Account user, JPanel panel2){
        JTextField searchbar = new JTextField();
        searchbar.setBounds(25,25,150,30);
        searchbar.setForeground(DarkCyan);
        panel2.add(searchbar);

        JRadioButton usern=new JRadioButton("User");
        usern.setBounds(200,25,100,30);
        usern.setBackground(BackgroundColor);
        usern.setForeground(DarkCyan);
        JRadioButton rental=new JRadioButton("Rental");
        rental.setBounds(300,25,100,30);
        rental.setBackground(BackgroundColor);
        rental.setForeground(DarkCyan);
        ButtonGroup group = new ButtonGroup();
        group.add(usern);
        group.add(rental);
        panel2.add(usern);
        panel2.add(rental);

        JButton button = new JButton("Search");
        button.setBounds(500,25,100,30);
        button.setBackground(DarkCyan);
        button.setForeground(BackgroundColor);
        panel2.add(button);

        JLabel label11= new JLabel("One or more fields are blank");
        label11.setBounds(25,50,300,25);
        label11.setForeground(BackgroundColor);
        panel2.add(label11);
        JLabel label12= new JLabel("Invalid input");
        label12.setBounds(25,75,300,25);
        label12.setForeground(BackgroundColor);
        panel2.add(label12);

        ActionListener buttonListener = e ->{
            label12.setForeground(BackgroundColor);
            label11.setForeground(BackgroundColor);
            try {
                String bar = searchbar.getText();
                if ((bar.isBlank())  |((!usern.isSelected())&(!rental.isSelected()))){
                    //error
                    label11.setForeground(Color.RED);
                }
                else {
                    panel2.removeAll();
                    panel2.validate();
                    panel2.repaint();
                    String type;
                    boolean found= false;
                    AdminMainMenu a = new AdminMainMenu();
                    if (usern.isSelected()) {
                        found = a.searchUser(user, panel2, bar);
                    }
                    else {
                        found = a.searchRental(user, panel2, bar);
                    }
                    if (!found){
                        JLabel l=new JLabel("Not found");
                        l.setBounds(100,100,100,30);
                        l.setForeground(Color.RED);
                        panel2.add(l);
                    }
                }
            }
            catch (NumberFormatException ex) {
                label12.setForeground(Color.RED);
            } catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
            }
        };
        button.addActionListener(buttonListener);
    }
}
