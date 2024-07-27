import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Date;

public class RentalGUI extends StartMenu {

    public RentalGUI() {
    }

    public void RentalCostumerGUI(int i, Account user, JPanel panel2, Rental r, Date cin, Date cout) {

        int y = 20 + (i * 295);
        if ((y + 295) > panel2.getHeight())
            panel2.setPreferredSize(new Dimension(1100, y + 295));

        try {
            BufferedImage myPicture = ImageIO.read(new File(r.getImage()));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            picLabel.setBounds(50, y, 350, 290);
            panel2.add(picLabel);
        } catch (IOException e) {
            JLabel picLabel = new JLabel("No Image");
            picLabel.setBounds(50, y, 350, 300);
            panel2.add(picLabel);
        }

        JLabel label1 = new JLabel("Name:");
        label1.setForeground(DarkCyan);
        label1.setBounds(450, y, 150, 30);
        panel2.add(label1);
        JTextField txt1 = new JTextField(r.getNameOfPlace());
        txt1.setEditable(false);
        txt1.setBounds(600, y, 150, 30);
        txt1.setBackground(BackgroundColor);
        txt1.setForeground(DarkCyan);
        panel2.add(txt1);

        y += 50;
        JLabel label2 = new JLabel("Location:");
        label2.setForeground(DarkCyan);
        label2.setBounds(450, y, 150, 30);
        panel2.add(label2);
        JTextField txt2 = new JTextField(r.getLocation());
        txt2.setEditable(false);
        txt2.setBounds(600, y, 150, 30);
        txt2.setBackground(BackgroundColor);
        txt2.setForeground(DarkCyan);
        panel2.add(txt2);

        y += 50;
        JLabel label3 = new JLabel("Type:");
        label3.setForeground(DarkCyan);
        label3.setBounds(450, y, 150, 30);
        panel2.add(label3);
        JTextField txt3 = new JTextField(r.getTypeOfRental());
        txt3.setEditable(false);
        txt3.setBounds(600, y, 150, 30);
        txt3.setBackground(BackgroundColor);
        txt3.setForeground(DarkCyan);
        panel2.add(txt3);

        JButton button = new JButton("Reserve");
        button.setBounds(850, y, 100, 30);
        button.setBackground(DarkCyan);
        button.setForeground(BackgroundColor);
        panel2.add(button);

        y += 50;
        JLabel label4 = new JLabel("Size:");
        label4.setForeground(DarkCyan);
        label4.setBounds(450, y, 150, 30);
        panel2.add(label4);
        JTextField txt4 = new JTextField(String.valueOf(r.getSize()));
        txt4.setEditable(false);
        txt4.setBounds(600, y, 150, 30);
        txt4.setBackground(BackgroundColor);
        txt4.setForeground(DarkCyan);
        panel2.add(txt4);

        y += 50;
        JLabel label5 = new JLabel("Price:");
        label5.setForeground(DarkCyan);
        label5.setBounds(450, y, 150, 30);
        panel2.add(label5);
        JTextField txt5 = new JTextField(String.valueOf(r.getPrice()));
        txt5.setEditable(false);
        txt5.setBounds(600, y, 150, 30);
        txt5.setBackground(BackgroundColor);
        txt5.setForeground(DarkCyan);
        panel2.add(txt5);

        buttonListener = e -> {
            CustomerMainMenu c = new CustomerMainMenu();
            try {
                c.makeReservation(user, r, cin, cout);
                panel2.removeAll();
                panel2.revalidate();
                panel2.repaint();
                JLabel label6 = new JLabel("Booked Successfully");
                label6.setBounds(250, 250, 200, 50);
                label6.setForeground(Color.GREEN);
                panel2.add(label6);
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        };

        button.addActionListener(buttonListener);

    }

    public void RentalAdminGUI(int i, JPanel panel2, Rental r, Account user) {

        int y = 20 + (i * 395);
        if ((y + 350) > panel2.getHeight())
            panel2.setPreferredSize(new Dimension(1100, y + 395));

        try {
            BufferedImage myPicture = ImageIO.read(new File(r.getImage()));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            picLabel.setBounds(100, y + 30, 350, 290);
            panel2.add(picLabel);
        } catch (IOException e) {
            JLabel picLabel = new JLabel("No Image");
            picLabel.setBounds(100, y + 30, 350, 350);
            picLabel.setForeground(DarkCyan);
            panel2.add(picLabel);
        }

        JLabel label = new JLabel("Code:");
        label.setForeground(DarkCyan);
        label.setBounds(550, y, 150, 30);
        panel2.add(label);
        JTextField txt = new JTextField(String.valueOf(r.getCode()));
        txt.setEditable(false);
        txt.setBounds(700, y, 200, 30);
        txt.setBackground(BackgroundColor);
        txt.setForeground(DarkCyan);
        panel2.add(txt);

        y += 50;
        JLabel label1 = new JLabel("Name:");
        label1.setForeground(DarkCyan);
        label1.setBounds(550, y, 150, 30);
        panel2.add(label1);
        JTextField txt1 = new JTextField(r.getNameOfPlace());
        txt1.setEditable(false);
        txt1.setBounds(700, y, 200, 30);
        txt1.setBackground(BackgroundColor);
        txt1.setForeground(DarkCyan);
        panel2.add(txt1);

        y += 50;
        JLabel label2 = new JLabel("Location:");
        label2.setForeground(DarkCyan);
        label2.setBounds(550, y, 150, 30);
        panel2.add(label2);
        JTextField txt2 = new JTextField(r.getLocation());
        txt2.setEditable(false);
        txt2.setBounds(700, y, 200, 30);
        txt2.setBackground(BackgroundColor);
        txt2.setForeground(DarkCyan);
        panel2.add(txt2);

        y += 50;
        JLabel label3 = new JLabel("Type:");
        label3.setForeground(DarkCyan);
        label3.setBounds(550, y, 150, 30);
        panel2.add(label3);
        JTextField txt3 = new JTextField(r.getTypeOfRental());
        txt3.setEditable(false);
        txt3.setBounds(700, y, 200, 30);
        txt3.setBackground(BackgroundColor);
        txt3.setForeground(DarkCyan);
        panel2.add(txt3);

        JButton button = new JButton("Delete");
        button.setBackground(DarkCyan);
        button.setForeground(BackgroundColor);
        button.setBounds(950, y, 100, 30);
        panel2.add(button);
        y += 25;
        JLabel result2 = new JLabel("Deleted Successfully");
        result2.setForeground(BackgroundColor);
        result2.setBounds(950, y, 200, 30);
        panel2.add(result2);

        y += 25;
        JLabel label4 = new JLabel("Size:");
        label4.setForeground(DarkCyan);
        label4.setBounds(550, y, 150, 30);
        panel2.add(label4);
        JTextField txt4 = new JTextField(String.valueOf(r.getSize()));
        txt4.setEditable(false);
        txt4.setBounds(700, y, 200, 30);
        txt4.setBackground(BackgroundColor);
        txt4.setForeground(DarkCyan);
        panel2.add(txt4);

        y += 50;
        JLabel label5 = new JLabel("Price:");
        label5.setForeground(DarkCyan);
        label5.setBounds(550, y, 150, 30);
        panel2.add(label5);
        JTextField txt5 = new JTextField(String.valueOf(r.getPrice()));
        txt5.setEditable(false);
        txt5.setBounds(700, y, 200, 30);
        txt5.setBackground(BackgroundColor);
        txt5.setForeground(DarkCyan);
        panel2.add(txt5);

        y += 50;
        JLabel label6 = new JLabel("Owner's Username:");
        label6.setForeground(DarkCyan);
        label6.setBounds(550, y, 150, 30);
        panel2.add(label6);
        JTextField txt6 = new JTextField(r.getOwnerUsername());
        txt6.setEditable(false);
        txt6.setBounds(700, y, 200, 30);
        txt6.setBackground(BackgroundColor);
        txt6.setForeground(DarkCyan);
        panel2.add(txt6);

        buttonListener = e -> {
            AdminMainMenu a = new AdminMainMenu();
            try {
                a.deleteRental(user, r.getCode());
                result2.setForeground(Color.GREEN);
            } catch (IOException | ClassNotFoundException ex) {
                ex.printStackTrace();
            }
        };
        button.addActionListener(buttonListener);
    }

    public void RentalProGUI(Account user, int i, JPanel panel2, Rental r) {

        int y = 20 + (i * 350);
        if ((y + 350) > panel2.getHeight())
            panel2.setPreferredSize(new Dimension(1100, y + 350));

        try {
            BufferedImage myPicture = ImageIO.read(new File(r.getImage()));
            JLabel picLabel = new JLabel(new ImageIcon(myPicture));
            picLabel.setBounds(20, y, 350, 290);
            panel2.add(picLabel);
        } catch (IOException e) {
            JLabel picLabel = new JLabel("No Image");
            picLabel.setBounds(20, y, 350, 350);
            picLabel.setForeground(DarkCyan);
            panel2.add(picLabel);
        }

        JLabel label = new JLabel("Code:");
        label.setForeground(DarkCyan);
        label.setBounds(470, y, 150, 30);
        panel2.add(label);
        JTextField txt = new JTextField(String.valueOf(r.getCode()));
        txt.setEditable(false);
        txt.setBounds(620, y, 200, 30);
        txt.setBackground(BackgroundColor);
        txt.setForeground(DarkCyan);
        panel2.add(txt);

        y += 50;
        JLabel label1 = new JLabel("Name:");
        label1.setForeground(DarkCyan);
        label1.setBounds(470, y, 150, 30);
        panel2.add(label1);
        JTextField txt1 = new JTextField(r.getNameOfPlace());
        txt1.setEditable(true);
        txt1.setBounds(620, y, 200, 30);
        txt1.setBackground(BackgroundColor);
        txt1.setForeground(DarkCyan);
        panel2.add(txt1);

        y += 50;
        JLabel label2 = new JLabel("Location:");
        label2.setForeground(DarkCyan);
        label2.setBounds(470, y, 150, 30);
        panel2.add(label2);
        JTextField txt2 = new JTextField(r.getLocation());
        txt2.setEditable(true);
        txt2.setBounds(620, y, 200, 30);
        txt2.setBackground(BackgroundColor);
        txt2.setForeground(DarkCyan);
        panel2.add(txt2);

        JButton button = new JButton("Update");
        button.setBackground(DarkCyan);
        button.setForeground(BackgroundColor);
        button.setBounds(950, y, 100, 30);
        panel2.add(button);

        y += 25;
        JLabel result = new JLabel("Invalid input");
        result.setForeground(BackgroundColor);
        result.setBounds(950, y, 200, 30);
        panel2.add(result);

        y += 25;
        JButton button2 = new JButton("Delete");
        button2.setBackground(DarkCyan);
        button2.setForeground(BackgroundColor);
        button2.setBounds(950, y, 100, 30);
        panel2.add(button2);

        y += 25;
        JLabel result2 = new JLabel("Success");
        result2.setForeground(BackgroundColor);
        result2.setBounds(950, y, 200, 30);
        panel2.add(result2);

        y -= 25;
        JLabel label3 = new JLabel("Type:");
        label3.setForeground(DarkCyan);
        label3.setBounds(470, y, 100, 30);
        panel2.add(label3);
        JRadioButton house = new JRadioButton("House");
        house.setBounds(620, y, 100, 30);
        house.setBackground(BackgroundColor);
        house.setForeground(DarkCyan);
        JRadioButton hotel = new JRadioButton("Hotel");
        hotel.setBounds(720, y, 100, 30);
        hotel.setBackground(BackgroundColor);
        hotel.setForeground(DarkCyan);
        JRadioButton apartment = new JRadioButton("Apartment");
        apartment.setBounds(800, y, 100, 30);
        apartment.setBackground(BackgroundColor);
        apartment.setForeground(DarkCyan);
        ButtonGroup group = new ButtonGroup();
        if (r.getTypeOfRental().equals("House"))
            house.setSelected(true);
        else if (r.getTypeOfRental().equals("Hotel"))
            hotel.setSelected(true);
        else
            apartment.setSelected(true);
        group.add(house);
        group.add(hotel);
        group.add(apartment);
        panel2.add(house);
        panel2.add(hotel);
        panel2.add(apartment);

        y += 50;
        JLabel label4 = new JLabel("Size:");
        label4.setForeground(DarkCyan);
        label4.setBounds(470, y, 150, 30);
        panel2.add(label4);
        JTextField txt4 = new JTextField(String.valueOf(r.getSize()));
        txt4.setEditable(true);
        txt4.setBounds(620, y, 200, 30);
        txt4.setBackground(BackgroundColor);
        txt4.setForeground(DarkCyan);
        panel2.add(txt4);

        y += 50;
        JLabel label5 = new JLabel("Price:");
        label5.setForeground(DarkCyan);
        label5.setBounds(470, y, 150, 30);
        panel2.add(label5);
        JTextField txt5 = new JTextField(String.valueOf(r.getPrice()));
        txt5.setEditable(true);
        txt5.setBounds(620, y, 200, 30);
        txt5.setBackground(BackgroundColor);
        txt5.setForeground(DarkCyan);
        panel2.add(txt5);


        buttonListener = e -> {
            result.setForeground(BackgroundColor);
            result2.setForeground(BackgroundColor);
            if (e.getActionCommand().equals(button.getText())) {
                try {
                    String type;
                    String name = txt1.getText();
                    String location = txt2.getText();
                    double size = Double.parseDouble(txt4.getText());
                    double price = Double.parseDouble(txt5.getText());
                    if ((name.isBlank()) || (location.isBlank()) || (price == 0) || (size == 0) || ((!house.isSelected()) & (!hotel.isSelected()) & (!apartment.isSelected()))) {
                        result.setForeground(Color.RED);
                    } else {
                        if (house.isSelected())
                            type = "House";
                        else if (hotel.isSelected())
                            type = "Hotel";
                        else
                            type = "Apartment";
                        ProviderMainMenu p = new ProviderMainMenu();
                        boolean found = p.editRental(user, name, location, size, price, type, r);
                        if (found)
                            result2.setForeground(Color.GREEN);
                        else
                            result.setForeground(Color.RED);
                    }
                } catch (NumberFormatException ex) {
                    result.setForeground(Color.RED);
                } catch (IOException | ClassNotFoundException ioException) {
                    ioException.printStackTrace();
                }
            } else {
                ProviderMainMenu p = new ProviderMainMenu();
                try {
                    boolean found = p.deleteRental(user, r.getCode());
                    if (found)
                        result2.setForeground(Color.GREEN);
                    else
                        result.setForeground(Color.RED);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            }

        };
        button.addActionListener(buttonListener);
        button2.addActionListener(buttonListener);

    }

    int code = 0;

    public void AddRentalGUI(Account user, JPanel panel2) {

        JLabel label1 = new JLabel("Give Name of Rental:");
        label1.setForeground(DarkCyan);
        label1.setBounds(400, 25, 300, 30);
        panel2.add(label1);
        JTextField name = new JTextField();
        name.setBounds(400, 50, 300, 30);
        name.setForeground(DarkCyan);
        panel2.add(name);

        JLabel label2 = new JLabel("Give type of rental:");
        label2.setForeground(DarkCyan);
        label2.setBounds(400, 100, 300, 30);
        panel2.add(label2);
        JRadioButton house = new JRadioButton("House");
        house.setBounds(400, 125, 100, 30);
        house.setBackground(BackgroundColor);
        house.setForeground(DarkCyan);
        JRadioButton hotel = new JRadioButton("Hotel");
        hotel.setBounds(500, 125, 100, 30);
        hotel.setBackground(BackgroundColor);
        hotel.setForeground(DarkCyan);
        JRadioButton apartment = new JRadioButton("Apartment");
        apartment.setBounds(600, 125, 100, 30);
        apartment.setBackground(BackgroundColor);
        apartment.setForeground(DarkCyan);
        ButtonGroup group = new ButtonGroup();
        group.add(house);
        group.add(hotel);
        group.add(apartment);
        panel2.add(house);
        panel2.add(hotel);
        panel2.add(apartment);

        JLabel label3 = new JLabel("Give location of rental:");
        label3.setForeground(DarkCyan);
        label3.setBounds(400, 175, 300, 30);
        panel2.add(label3);
        JTextField location = new JTextField();
        location.setBounds(400, 200, 300, 30);
        location.setForeground(DarkCyan);
        panel2.add(location);

        JLabel label4 = new JLabel("Give size of rental:");
        label4.setForeground(DarkCyan);
        label4.setBounds(400, 250, 300, 30);
        panel2.add(label4);
        JTextField size = new JTextField("0");
        size.setBounds(600, 250, 100, 30);
        size.setForeground(DarkCyan);
        panel2.add(size);

        JLabel label5 = new JLabel("Give price of rental in Euro (€):");
        label5.setForeground(DarkCyan);
        label5.setBounds(400, 325, 300, 30);
        panel2.add(label5);
        JTextField price = new JTextField("0");
        price.setBounds(600, 325, 100, 30);
        price.setForeground(DarkCyan);
        panel2.add(price);

        JButton button = new JButton("Add Image");
        button.setBounds(400, 400, 300, 30);
        button.setBackground(DarkCyan);
        button.setForeground(BackgroundColor);
        panel2.add(button);
        JLabel label6 = new JLabel("Preferred size of image 350x290 (.jpg)");
        label6.setForeground(DarkCyan);
        label6.setBounds(400, 425, 200, 30);
        panel2.add(label6);

        JButton button2 = new JButton("Submit");
        button2.setBounds(400, 475, 300, 30);
        button2.setBackground(DarkCyan);
        button2.setForeground(BackgroundColor);
        panel2.add(button2);

        JLabel result = new JLabel("Added Successfully");
        result.setBounds(400, 525, 200, 30);
        result.setForeground(BackgroundColor);
        panel2.add(result);
        JLabel result2 = new JLabel("Invalid input. One or more fields are blank");
        result2.setBounds(400, 500, 300, 30);
        result2.setForeground(BackgroundColor);
        panel2.add(result2);


        buttonListener = e -> {
            if (e.getActionCommand().equals(button.getText())) {
                ProviderMainMenu p = new ProviderMainMenu();
                try {
                    code = p.addImage();
                    button.setText("Image Added");
                    button.setForeground(DarkCyan);
                    button.setBackground(BackgroundColor);
                } catch (IOException | ClassNotFoundException ex) {
                    ex.printStackTrace();
                }
            } else {
                result.setForeground(BackgroundColor);
                result2.setForeground(BackgroundColor);
                String Location = location.getText();
                String Name = name.getText();
                String Type;
                double Price = Double.parseDouble(price.getText());
                double Space = Double.parseDouble(size.getText());
                if ((Location.isBlank()) || (code == 0) || (Price == 0) || (Space == 0) || (Name.isBlank()) || ((!house.isSelected()) & (!hotel.isSelected()) & (!apartment.isSelected()))) {
                    //error
                    result2.setForeground(Color.RED);
                } else {
                    ProviderMainMenu p = new ProviderMainMenu();
                    if (house.isSelected())
                        Type = "House";
                    else if (hotel.isSelected())
                        Type = "Hotel";
                    else
                        Type = "Apartment";
                    try {
                        p.addRental(user, Name, Location, Type, Space, Price, code);
                        result.setForeground(Color.GREEN);
                    } catch (IOException | ClassNotFoundException ex) {
                        ex.printStackTrace();
                    }
                }
            }
        };
        button.addActionListener(buttonListener);
        button2.addActionListener(buttonListener);
    }

}