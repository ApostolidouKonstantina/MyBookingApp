import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


/**
 * If every file is empty, we Initialize accounts, rentals and reservations
 * Then the "start" proceeds with the start menu
 */

public class UserInterface {


    public void Initialize(){
        try{
            Account a = new Account("First Admin", "FirstAdmin", "12345678", "admin", true);
            FileHandler.saveFile("accounts", a);
            a = new Account("First Costumer", "FirstCustomer", "12345678", "customer", true);
            FileHandler.saveFile("accounts", a);
            a = new Account("First Provider", "FirstProvider", "12345678", "provider", true);
            FileHandler.saveFile("accounts", a);
            Rental r= new Rental("FirstProvider", "Place1", "House", "Athens", 60.0, 100.0, 1);
            FileHandler.saveFile("rentals", r);
            r= new Rental("OwnerX", "Place2", "Hotel", "Thessaloniki", 30.0, 60.0,2);
            FileHandler.saveFile("rentals", r);
            r= new Rental("FirstProvider", "Place3", "Hotel", "Krete", 30.0, 80.0,3);
            FileHandler.saveFile("rentals", r);
            r= new Rental("OwnerY", "Place4", "House", "Athens", 60.0, 80.0,4);
            FileHandler.saveFile("rentals", r);
            r= new Rental("OwnerZ", "Place5", "Apartment", "Mykonos", 60.0, 180.0, 5);
            FileHandler.saveFile("rentals", r);
            String format = "dd/MM/yyyy" ;
            Date cin=new SimpleDateFormat(format).parse("15/05/2022");
            Date cout=new SimpleDateFormat(format).parse("25/06/2022");
            Reservation re= new Reservation("FirstCustomer", "FirstProvider",cin,cout,true, r.getCode(),1);
            FileHandler.saveFile("reservations", re);
        } catch (IOException fileNotFoundException) {
            System.err.println(fileNotFoundException);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }


    public void start() throws IOException, ClassNotFoundException {
        /*if the array is empty(the app just started) we create three accounts, one of each type*/
        FileHandler h = new FileHandler();
        if (FileHandler.loadFile("accounts", Account.class).isEmpty() && (FileHandler.loadFile("rentals", Rental.class).isEmpty() && (FileHandler.loadFile("reservations", Rental.class).isEmpty()))){
            Initialize();
        }
        StartMenu s=new StartMenu();
        s.Start();
    }

}