import javax.swing.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;


/**
 * After the customer makes their choice in their main menu, the appropriate method is called.
 * After every action we write it down to "activities""*/

public class CustomerMainMenu extends JPanel{

    /** After "Reserve" is called with all the information about the reservation that the customer
     * desires to make, it scans through the file "rentals" and saves in the array "matches" the ones
     * that fit all the criteria. Then with the help of "RentalCustomerGUI" shows the list
     * to the customer to make their reservation*/
    public boolean Reserve(Account user, JPanel panel2, String location, String type, Date cin, Date cout, double minPrice, double maxPrice, double minSpace, double maxSpace){
        int m=0;
        ArrayList<Rental> rentals;
        try {
            rentals = FileHandler.loadFile("rentals",Rental.class);
            Rental[] matches = new Rental[rentals.size()];
            for (Rental r:rentals) {
                if (r.getLocation().equals(location) && r.getTypeOfRental().equals(type) && r.getPrice() >= minPrice && r.getPrice() <= maxPrice && r.getSize() >= minSpace && r.getSize() <= maxSpace) {
                    //if we find a place that matches the criteria, we check if it's available in the requested check in and check out time
                    if (r.getReservations().isEmpty()){
                        // if the reservations are empty...
                        matches[m]=r;
                        m++;
                    }
                    else{
                        for (Reservation re: r.getReservations()){
                            if (!((re.getActivity()) && !(re.getCheckIn().after(cin) && re.getCheckIn().after(cout)) || (re.getCheckOut().before(cin) && re.getCheckOut().before(cout)))){
                                try{
                                    matches[m]=r;
                                    m++;
                                }catch(ArrayIndexOutOfBoundsException e){break;}
                            }
                        }
                    }
                }
            }
            if (m==0)
                return false;
            else{
                for (int i=0;i<m;i++){
                    RentalGUI r = new RentalGUI();
                    r.RentalCostumerGUI(i,user,panel2,matches[i],cin,cout);
                }
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return true;
    }

    /** Once the customer choices which Rental they want to reserve, "makeReservation" is called
     * and saves the reservation in the "reservations" file. The codeOfReservation is +1
     * from the last reservation in the file, no matter if it is cancelled or active*/
    public void makeReservation(Account user, Rental r, Date cin, Date cout) throws IOException, ClassNotFoundException {
        ArrayList<Reservation> res = FileHandler.loadFile("reservations",Reservation.class);
        Reservation lastReservation = res.get(res.size()-1);
        int newCode=(lastReservation.getCodeOfReservation())+1;
        Reservation reservation=new Reservation(user.getUsername(),r.getOwnerUsername(),cin,cout,true,r.getCode(),newCode);
        FileHandler.saveFile("reservations", reservation);
        Activity a = new Activity();
        a.saveAct(user," made reservation "+newCode);
    }

    /** "ViewReservations" goes through all reservations in the file, and shows
     * the ones that belong to this particular user (re.getCustomer().equals(user.getUsername())).
     * Note: The username can't exist twice*/
    public boolean ViewReservations(Account user, JPanel panel2) throws IOException, ClassNotFoundException {
        int i=0;
        ArrayList<Reservation> r = FileHandler.loadFile("reservations", Reservation.class);
        for (Reservation re: r){
            if ((re.getCustomer().equals(user.getUsername())) && (re.getActivity())) {
                ReservationGUI res = new ReservationGUI();
                res.ReservationCustomerGUI(i, panel2, re, user);
                i++;
            }
        }
        /*we take the timestamp and save it to the activities*/
        Activity a = new Activity();
        a.saveAct(user, " viewed all their reservations");
        return i != 0;
    }

    /** "CancelReservation" takes the codeOfReservation that they want to
     * cancel from the user and if that reservation does belong to them, deletes it.
     * Note: The user can view the codeOfReservation for each of their reservations
     * when they view their reservations*/
    public boolean CancelReservation(Account user, int code) throws IOException, ClassNotFoundException {
        ArrayList<Reservation> r = FileHandler.loadFile("reservations", Reservation.class);
        Reservation res=null;
        for(Reservation re: r){
            if (re.getCodeOfReservation()==code){
                res=re;
                break;
            }
        }
        if ((res!=null) && (res.getCustomer().equals(user.getUsername())) && (res.getActivity())) {
            FileHandler.deleteObj("reservations", res);
            res.setActivity(false);
            FileHandler.saveFile("reservations", res);
            Activity a = new Activity();
            a.saveAct(user, " cancelled reservation "+code);
            return true;
        }
        else
            return false;
    }
}
