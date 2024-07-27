import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

/** After the provider makes their choice in their main menu, the appropriate method is called.
 * After every action we write it down to "activities"*/

public class ProviderMainMenu extends Thread{

    /** This method goes through all reservations in the file, and shows
     * the ones that belong to this particular provider (user.getUsername().equals(r.getOwnerUsername())).
     * Note: The username can't exist twice*/
    public boolean ViewMyRentals(Account user, JPanel panel2) throws IOException, ClassNotFoundException, URISyntaxException {
        int cn=0;
        ArrayList<Rental> rentals = FileHandler.loadFile("rentals", Rental.class);
        for(Rental r: rentals) {
            if (user.getUsername().equals(r.getOwnerUsername()))
            {
                RentalGUI ren= new RentalGUI();
                ren.RentalProGUI(user,cn,panel2,r);
                cn++;
            }
        }
        /* we take the timestamp and save it to the activities*/
        Activity a= new Activity();
        a.saveAct(user, " viewed their places");
        return cn != 0;
    }

    /** This method deletes the editing rental from the file and
     * adds the new updated one*/
    public boolean editRental(Account user, String name, String location, double size, double price, String type, Rental r) throws IOException, ClassNotFoundException {
        ArrayList<Rental> array = FileHandler.loadFile("rentals", Rental.class);
        Rental rental=null;
        for(Rental re: array){
            if (re.getCode()==r.getCode()){
                rental=re;
                break;
            }
        }
        if (rental!=null){
            FileHandler.deleteObj("rentals", rental.getCode());
            rental.setNameOfPlace(name);
            rental.setLocation(location);
            rental.setSize(size);
            rental.setPrice(price);
            rental.setTypeOfRental(type);
            FileHandler.saveFile("rentals",rental);
            /* we take the timestamp and save it to the activities*/
            Activity a= new Activity();
            a.saveAct(user, " edited rental "+rental.getCode());
            return true;
        }
        else
            return false;
    }

    public void addRental(Account user, String name, String location, String type, double size, double price, int code) throws IOException, ClassNotFoundException {
        Rental r = new Rental(user.getUsername(),name,type,location,size,price,code);
        FileHandler.saveFile("rentals", r);
        Activity a = new Activity();
        a.saveAct(user, " added rental "+code);
    }

    public int addImage() throws IOException, ClassNotFoundException {
        ArrayList<Rental> rentals = FileHandler.loadFile("rentals",Rental.class);
        Rental lastRental = rentals.get(rentals.size()-1);
        int newCode=(lastRental.getCode()+1);
        UploadImage im = new UploadImage();
        im.initialize(String.valueOf(newCode));
        return newCode;
    }

    public boolean deleteRental(Account user, int code) throws IOException, ClassNotFoundException {
        ArrayList<Rental> array = FileHandler.loadFile("rentals", Rental.class);
        Rental rental=null;
        for(Rental re: array){
            if (re.getCode()==code){
                rental=re;
                break;
            }
        }
        if ((rental!=null) && (rental.getOwnerUsername().equals(user.getUsername()))){
            FileHandler.deleteObj("rentals", code);
            /* we take the timestamp and save it to the activities*/
            Activity a= new Activity();
            a.saveAct(user, " deleted place "+code);
            return true;
        }
        else
            return false;
    }

    public boolean ViewMyReservations(Account user, JPanel panel2) throws IOException, ClassNotFoundException {
        int i=0;
        ArrayList<Reservation> r = FileHandler.loadFile("reservations", Reservation.class);
        for (Reservation re: r){
            if (re.getOwner().equals(user.getUsername())) {
                ReservationGUI res = new ReservationGUI();
                res.ReservationProGUI(i, panel2, re);
                i++;
            }
        }
        /*we take the timestamp and save it to the activities*/
        Activity a= new Activity();
        a.saveAct(user, " viewed all their cancelled reservations");
        return i != 0;
    }

    public boolean ViewPartReservations(Account user, JPanel panel2, boolean active) throws IOException, ClassNotFoundException {
        int i=0;
        ArrayList<Reservation> r = FileHandler.loadFile("reservations", Reservation.class);
        for (Reservation re: r){
            if ((re.getOwner().equals(user.getUsername())) && (re.getActivity()==active)) {
                ReservationGUI res = new ReservationGUI();
                res.ReservationProGUI(i, panel2, re);
                i++;
            }
        }
        /*we take the timestamp and save it to the activities*/
        Activity a= new Activity();
        a.saveAct(user, " viewed all their cancelled reservations");
        return i != 0;
    }
}
