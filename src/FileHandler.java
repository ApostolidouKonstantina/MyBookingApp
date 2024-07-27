import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;


/** This class is handling the files accounts, rentals, reservations, activities.
 * It saves, deletes and loads objects from each and to each file.
 * All methods are static and files used are in binary form     */


public class FileHandler {

    public FileHandler()throws IOException{
        Path pathOfFile=Paths.get("accounts");
        File file=pathOfFile.toFile();
        file.createNewFile();

        pathOfFile=Paths.get("activities");
        file=pathOfFile.toFile();
        file.createNewFile();

        pathOfFile=Paths.get("rentals");
        file=pathOfFile.toFile();
        file.createNewFile();

        pathOfFile=Paths.get("reservations");
        file=pathOfFile.toFile();
        file.createNewFile();


    }



    public static<T> void saveFile(String path,T toSave) throws IOException{
        Path pathOfFile=Paths.get(path);
        File file=pathOfFile.toFile();
        file.createNewFile();
        if(!file.exists()){throw new FileNotFoundException();}
        FileOutputStream fileOutputStream = new FileOutputStream(file,true);
        ObjectOutputStream objectOutputStream=new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(toSave);
        objectOutputStream.flush();
        objectOutputStream.close();
    }



    public static void deleteObj(String path, double code)throws IOException, ClassNotFoundException{
        Path pathOfFile=Paths.get(path);
        File file=pathOfFile.toFile();

        ArrayList<Rental> array = FileHandler.loadFile("rentals", Rental.class);
        for(Rental re: array){
            if (!(re.getCode()==code)){
                saveFile("newRentals", re);
            }
        }


        file.delete();
        pathOfFile=Paths.get("newRentals");
        File newFile= pathOfFile.toFile();
        newFile.renameTo(file);
    }

    public static void deleteObj(String path, Reservation r)throws IOException, ClassNotFoundException{
        Path pathOfFile=Paths.get(path);
        File file=pathOfFile.toFile();

        ArrayList<Reservation> array = FileHandler.loadFile("reservations", Reservation.class);
        for(Reservation re: array){
            if (!(re.equals(r))){
                saveFile("reservations", re);
            }
        }

        file.delete();
        pathOfFile=Paths.get("newReservations");
        File newFile= pathOfFile.toFile();
        newFile.renameTo(file);
    }

    public static void deleteObj(String path, Account r)throws IOException, ClassNotFoundException{
        Path pathOfFile=Paths.get(path);
        File file=pathOfFile.toFile();

        ArrayList<Account> array = FileHandler.loadFile("accounts", Account.class);
        for(Account t: array){
            if (!(t.getUsername().equals(r.getUsername()))){

                saveFile("newAccounts", t);
            }
        }


        file.delete();
        pathOfFile=Paths.get("newAccounts");
        File newFile= pathOfFile.toFile();
        newFile.renameTo(file);
    }


    public static <T> ArrayList<T> loadFile(String path ,Class<T> targetType)throws IOException,ClassNotFoundException{
        Path pathOfFile=Paths.get(path);
        File file=pathOfFile.toFile();
        FileInputStream inputStream=new FileInputStream(file);
        ObjectInputStream objectIN=null;
        T tmpres;
        boolean x =true;
        ArrayList<T> array = new ArrayList<>();
        while(x){
            try{

                objectIN= new ObjectInputStream(inputStream);
                Object tmpObject= objectIN.readObject();
                tmpres= targetType.cast(tmpObject);
                array.add(tmpres);

            }catch (EOFException eof){
                x=false;
            }
        }
        if(objectIN!=null){objectIN.close();}
        return array;
    }




}
