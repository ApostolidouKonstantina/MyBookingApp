import javax.swing.*;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;

/**
 * After the admin makes their choice in their main menu, the appropriate method is called.
 * After every action we write it down to "activities"
 * */

public class AdminMainMenu {

    /** "ViewProfiles" reads every approved account there is in the file "accounts" and sends each one
     * to "ProfileGUI1" which will edit them properly in the frame. Methods "ViewRentals", "ViewActivity"
     * and "ViewReservations" work in a similar way*/
    public void ViewProfiles(Account user, JPanel panel2) throws IOException, ClassNotFoundException {
        ArrayList<Account> accounts = FileHandler.loadFile("accounts", Account.class);
        int i=0;
        for(Account t: accounts) {
            if (t.getApproved()) {
                ProfileGUI p = new ProfileGUI();
                p.ProfileGUI2(i, panel2, t);
                i++;
            }
        }
        //we take the timestamp and save it to the activities
        Activity a= new Activity();
        a.saveAct(user, " viewed all accounts");
    }

    public void ViewRentals(Account user, JPanel panel2) throws IOException, ClassNotFoundException, URISyntaxException {
        ArrayList<Rental> rentals = FileHandler.loadFile("rentals", Rental.class);
        int i=0;
        for(Rental r: rentals) {
            RentalGUI p = new RentalGUI();
            p.RentalAdminGUI(i, panel2, r, user);
            i++;
        }
        //we take the timestamp and save it to the activities
        Activity a= new Activity();
        a.saveAct(user, " viewed all rentals");
    }

    public void ViewActivity(Account user, JPanel panel2) throws IOException, ClassNotFoundException {
        ArrayList<Activity> activities = FileHandler.loadFile("activities", Activity.class);
        int i=0;
        for(Activity ac: activities) {
            ActivityGUI p = new ActivityGUI();
            p.ActivityGUI1(i, panel2, ac);
            i++;
        }
        //we take the timestamp and save it to the activities
        Activity a= new Activity();
        a.saveAct(user, " viewed all app activity");
    }

    public boolean ViewReservations(Account user, JPanel panel2) throws IOException, ClassNotFoundException {
        int i=0;
        ArrayList<Reservation> res = FileHandler.loadFile("reservations", Reservation.class);
        for (Reservation r: res){
            ReservationGUI re = new ReservationGUI();
            re.ReservationAdminGUI(i, panel2, r);
            i++;
        }
        //we take the timestamp and save it to the activities*/
        Activity a= new Activity();
        a.saveAct(user, " viewed all reservations");
        return i != 0;
    }

    /**"ViewPartReservations" also works like the previous, with the only difference being it takes one more
     * parameter (boolean active). If active==true, then we show only the active reservations. In case
     * active==false, we show only the cancelled reservations*/
    public boolean ViewPartReservations(Account user, JPanel panel2, boolean active) throws IOException, ClassNotFoundException {
        int i=0;
        ArrayList<Reservation> res = FileHandler.loadFile("reservations", Reservation.class);
        for (Reservation r: res){
            if (r.getActivity()==active) {
                ReservationGUI re = new ReservationGUI();
                re.ReservationAdminGUI(i, panel2, r);
                i++;
            }
        }
        Activity a=new Activity();
        if (active)
            a.saveAct(user, " viewed all active reservations");
        else
            a.saveAct(user, " viewed all cancelled reservations");
        return i!=0;
    }

    public boolean searchUser(Account user, JPanel panel2, String username) throws IOException, ClassNotFoundException {
        ArrayList<Account> accounts = FileHandler.loadFile("accounts", Account.class);
        for(Account ac: accounts) {
            if (ac.getUsername().equals(username)){
                ProfileGUI p = new ProfileGUI();
                p.ProfileGUI2(0, panel2, ac);
                Activity a= new Activity();
                a.saveAct(user, " searched for user "+username);
                return true;
            }
        }
        return false;
    }

    public boolean searchRental(Account user, JPanel panel2, String rentalName) throws IOException, ClassNotFoundException {
        ArrayList<Rental> rentals = FileHandler.loadFile("rentals", Rental.class);
        int i=0;
        for(Rental r: rentals) {
            if (r.getNameOfPlace().equals(rentalName)){
                RentalGUI p = new RentalGUI();
                p.RentalAdminGUI(i, panel2, r, user);
                i++;
            }
        }
        Activity a= new Activity();
        a.saveAct(user, " searched for rental "+rentalName);
        return i!=0;
    }

    /**"EditRequests" goes through the "accounts" file and looks for the
     * requests(=the accounts that haven't been approved). Then it sends them to
     * "ProfileGUIForApproval" for further editing*/
    public boolean EditRequests(Account user, JPanel panel2) throws IOException, ClassNotFoundException {
        ArrayList<Account> accounts = FileHandler.loadFile("accounts", Account.class);
        int i=0;
        for(Account t: accounts) {
            if (!t.getApproved()){
                ProfileGUI p = new ProfileGUI();
                p.ProfileGUIForApproval(i, panel2, t);
                i++;
            }
        }
        //we take the timestamp and save it to the activities
        Activity a= new Activity();
        a.saveAct(user, " viewed/edited sign up requests");
        return i!=0;
    }

    public void deleteRental(Account user, int code) throws IOException, ClassNotFoundException {
        FileHandler.deleteObj("rentals", code);
        /* we take the timestamp and save it to the activities*/
        Activity a = new Activity();
        a.saveAct(user, " deleted rental "+code);
    }
}