import java.io.Serial;
import java.util.ArrayList;
import java.io.IOException;
import java.io.Serializable;


/**
 * Every rental includes its name, the name of the owner(provider), it's type(House/Hotel/Apartment),
 * it's location, the price and the space
 * It also includes "getImage()" which return the path to the image of each rental.
 * Note: Each rental's image has the code of it as a name +.jpg
 * */

public class Rental implements Serializable{

    @Serial
    private static final long serialVersionUID = 9178661439383356177L;
    
    private String OwnerUsername, NameOfPlace, TypeOfRental, Location;
    private double Price, Size;
    int code;

    public Rental(){
        setOwnerUsername(null);
        setNameOfPlace(null);
        setTypeOfRental(null);
        setLocation(null);
        setPrice(0);
        setSize(0);
        setCode(0);
    }

    public Rental(String OwnerUsername, String NameOfPlace, String TypeOfRental, String Location, double Size, double Price, int code){
        setOwnerUsername(OwnerUsername);
        setNameOfPlace(NameOfPlace);
        setTypeOfRental(TypeOfRental);
        setLocation(Location);
        setPrice(Price);
        setSize(Size);
        setCode(code);
    }

    public String getImage() { return ("images/"+this.code+".jpg"); }

    public void setCode(int code){
        this.code=code;
    }

    public int getCode(){
        return this.code;
    }

    public void setNameOfPlace(String NameOfPlace){
        this.NameOfPlace=NameOfPlace;
    }

    public String getNameOfPlace(){
        return NameOfPlace;
    }

    public void setOwnerUsername(String OwnerUsername){
        this.OwnerUsername=OwnerUsername;
    }

    public String getOwnerUsername(){
        return OwnerUsername;
    }

    public ArrayList<Reservation> getReservations(){
        ArrayList<Reservation> reservations = new ArrayList<>();
        try {
            reservations = FileHandler.loadFile("reservations",Reservation.class);
            reservations.removeIf(x -> x.getCodeOfRental() != code);
        }catch(ClassNotFoundException exception){
            System.err.println(exception);
        }catch(IOException exc){
            System.out.println("File not found");
        }
        return reservations;
    }

    public void setTypeOfRental(String TypeOfRental){
        this.TypeOfRental=TypeOfRental;
    }

    public String getTypeOfRental(){
        return this.TypeOfRental;
    }

    public void setLocation(String Location){
        this.Location=Location;
    }

    public String getLocation(){
        return this.Location;
    }

    public void setPrice(double Price){
        this.Price=Price;
    }

    public double getPrice(){
        return this.Price;
    }

    public void setSize(double Size){
        this.Size=Size;
    }

    public double getSize(){
        return this.Size;
    }

}