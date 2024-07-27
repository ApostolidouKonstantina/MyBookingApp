import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

/**
 * Every reservation includes the names of the customer and the owner(provider),
 * the checkIn and checkOut dates, and if the reservation is active
 * The reservation is active when active==true, but if it's cancelled active==false
 * */

public class Reservation implements Serializable {

    @Serial
    private static final long serialVersionUID = 99852L;


    private String customer, owner; //usernames
    private Date checkIn;
    private Date checkOut;
    private boolean active;
    private int code_of_rental, code_of_reservation;

    public Reservation(String customer, String owner, Date checkIn, Date checkOut, boolean active, int code_of_rental, int code_of_reservation) {
        setCustomer(customer);
        setOwner(owner);
        setCheckIn(checkIn);
        setCheckOut(checkOut);
        setActivity(active);
        setCodeOfRental(code_of_rental);
        setCodeOfReservation(code_of_reservation);
    }

    public void setCodeOfReservation(int code_of_reservation){ this.code_of_reservation=code_of_reservation;}

    public int getCodeOfReservation(){
        return code_of_reservation;
    }

    public void setCodeOfRental(int code_of_rental){
        this.code_of_rental=code_of_rental;
    }

    public int getCodeOfRental(){ return code_of_rental; }

    public void setOwner(String owner){
        this.owner=owner;
    }

    public String getOwner(){
        return this.owner;
    }

    public void setCustomer(String customer){
        this.customer=customer;
    }

    public String getCustomer(){return this.customer;}

    public Date getCheckIn() {
        return this.checkIn;
    }

    public void setCheckIn(Date checkIn) {
        this.checkIn=checkIn;
    }

    public Date getCheckOut() {
        return this.checkOut;
    }

    public void setCheckOut(Date checkOut) {
        this.checkOut=checkOut;
    }

    public boolean getActivity(){
        return this.active;
    }

    public void setActivity(boolean active){
        this.active=active;
    }
}