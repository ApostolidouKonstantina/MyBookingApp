
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import java.util.ArrayList;
import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;


public class AccountsTest{
    public static Reservation res;
    public static Rental r;
    public static Account c,p,a;
    public static String date1,date2;
    

    @BeforeAll
    static void setUp(){
        try{
        FileHandler h = new FileHandler();
        c = new Account("Jacob", "jack123", "abc123", "customer", false);
        p = new Account("Tom", "nick12", "12345678", "provider", true);
        a=new Account("Andreas", "drew89", "helloworld", "admin", true);
        r = new Rental("nick12", "skghotel", "Hotel", "Thessaloniki", 40, 100, 1);
        date1="12/05/2022";
        date2="15/05/2022";
        res = new Reservation("jack123", "nick12", (new SimpleDateFormat(date1)).parse(date1),(new SimpleDateFormat(date2)).parse(date2) , true, r.getCode(),1);
        FileHandler.saveFile("accounts", c);
        FileHandler.saveFile("accounts", p);
        FileHandler.saveFile("accounts", a);
        FileHandler.saveFile("rentals", r);
        FileHandler.saveFile("reservations", res);
        }catch (ParseException e){
            System.err.println(e);
        }catch(IOException exc){
            System.err.println(exc);
        }
    }

    @Test
    @DisplayName("testing account class methods")
    void testAccount(){
        assertEquals("Jacob",c.getName());
        assertEquals("nick12",p.getUsername());
        p.setPassword("123456789");
        assertEquals("123456789",p.getPassword());
        assertEquals("admin",a.getType());
        assertEquals(true,a.getApproved());

    }
    
    @Test
    @DisplayName("testing rental class methods")
    void testRental(){
        assertEquals(1,r.getCode());
        assertEquals("images/"+r.getCode()+".jpg",r.getImage());
        assertEquals("skghotel",r.getNameOfPlace());
        assertEquals("nick12",r.getOwnerUsername());
        try{FileHandler.saveFile("reservations", res);}catch (IOException io){System.err.println(io);}
        assertTrue(r.getReservations().size()>0);
    }

    @Test
    @DisplayName("testing reservation class methods")
    void testReservation(){
        assertEquals(r.getCode(),res.getCodeOfRental());
        assertEquals(r.getOwnerUsername(),res.getOwner());
        assertEquals(1, res.getCodeOfReservation());
        try{
        assertEquals((new SimpleDateFormat(date2)).parse(date2),res.getCheckOut());
        }catch (ParseException e){
            System.err.println(e);
        }
    }

    @Test
    @DisplayName("testing activity class methods")
    void testActivity(){
        ArrayList<Activity> acts = new ArrayList<>();
        Activity act = new Activity("drew89", "wanna test the activity", "12:40");
        try{
        assertEquals("wanna test the activity", act.getAction());
        assertEquals("12:40", act.getTime());

        act.saveAct(a, act.getAction());
        acts = FileHandler.loadFile("activities", Activity.class);

        assertEquals("wanna test the activity", acts.get(acts.size()-1).getAction());




        }catch (ClassNotFoundException exc) {
            System.err.println(exc);
        } catch (IOException ioException) {
            System.err.println(ioException);
        }
    }


    @Test
    @DisplayName("Customer succesful sign up /same username/ username with special characters /invalid password length / provider succesful sign up")
    void testSignUp(){
        ArrayList<Account> acc = new ArrayList<>();
        Account a;
        SignUp s = new SignUp();
        try{
        a = s.signUp("Manolis", "manter9", "132457689", "customer");
        FileHandler.deleteObj("accounts", a);
        assertEquals("Manolis", a.getName());
        a=s.signUp("Nick", "nick12", "12345678", "provider");
        assertEquals("!taken", a.getName());
        a=s.signUp("Nick", "<nick12>", "12345678", "provider");
        assertEquals("!character", a.getName());
        a=s.signUp("Nick", "nikos12", "1234", "provider");
        assertEquals("!password", a.getName());
        a=s.signUp("Tasos", "admin23", "12345678", "provider");
        FileHandler.deleteObj("accounts", a);
        assertEquals("provider", a.getType());

        }catch (ClassNotFoundException exc) {
            System.err.println(exc);
        } catch (IOException ioException) {
            System.err.println(ioException);
        }


    }



    @Test
    @DisplayName("Provider and admin succesful log in / unsuccesful login: customer not yet approved / unsuccesful login: no username found / unsuccesful login: wrong password")
    void testLogIn(){
        Account a;
        LogIn l = new LogIn();
        
        a=l.logIn("nick12", "12345678");
        assertEquals("nick12",a.getUsername());
        a=l.logIn("jack123", "abc123");
        assertEquals("!approved",a.getName());
        a=l.logIn("drew89", "helloworld");
        assertEquals("drew89",a.getUsername());
        a=l.logIn("random", "12345678");
        assertEquals("!username",a.getName());
        a=l.logIn("drew89", "12345678");
        assertEquals("!password",a.getName());

        

    }


    @Test
    @DisplayName("Customer Test  / 2 new customers make a reservation / then they cancell it")
    void testingCustomer(){
        CustomerMainMenu menu=new CustomerMainMenu();
        ArrayList<Activity> act = new ArrayList<>();
        Account acc = new Account("akis", "ak47", "12345678", "customer", true);
        Account acc2 = new Account("george", "ggg89", "12345678", "customer", true);
        try{
        FileHandler.saveFile("accounts", acc);
        FileHandler.saveFile("accounts", acc2);
        menu.makeReservation(acc, r, (new SimpleDateFormat(date1)).parse(date1), (new SimpleDateFormat(date2)).parse(date2));
        act = FileHandler.loadFile("activities", Activity.class);
        date1="28/07/2022";
        date2="28/09/2022";
        
        
        
        assertEquals(" made reservation 2",act.get(act.size()-1).getAction());
        assertTrue(menu.CancelReservation(acc, 2));
        
        menu.makeReservation(acc2, r, (new SimpleDateFormat(date1)).parse(date1), (new SimpleDateFormat(date2)).parse(date2));
        act = FileHandler.loadFile("activities", Activity.class);

        assertEquals(" made reservation 3", act.get(act.size()-1).getAction());
        assertTrue(menu.CancelReservation(acc2, 3));

        FileHandler.deleteObj("reservations", res);
        FileHandler.deleteObj("accounts", acc);
        FileHandler.deleteObj("accounts", acc2);
        }catch (ParseException e){
            System.err.println(e);
        }catch(IOException exc){
            System.err.println(exc);
        }catch(ClassNotFoundException cnfe){
            System.err.println(cnfe);
        }
    }


    @Test
    @DisplayName("Provider Test / existing provider adds new rental / then edits it / then deletes it / then unsuccesfully edits and deletes it again")
    void testingProvider(){
        ProviderMainMenu menu = new ProviderMainMenu();
        ArrayList<Activity> act = new ArrayList<>();
        Rental r = new Rental("nick12", "testRental", "House", "Irakleio", 90 , 150 , 2);
        try{
        menu.addRental(p, "testRental", "Irakleio", "House", 90 , 150 , 2);
        act=FileHandler.loadFile("activities", Activity.class);

        assertEquals(" added rental 2", act.get(act.size()-1).getAction());
        assertTrue(menu.editRental(p, "newTestRental", "Xania", 90, 150, "House", r));
        assertTrue(menu.deleteRental(p, 2));
        assertFalse(menu.editRental(p, "newTestRental", "Xania", 90, 150, "House", r));
        assertFalse(menu.deleteRental(p, 2));

        
        }catch(IOException exc){
            System.err.println(exc);
        }catch(ClassNotFoundException cnfe){
            System.err.println(cnfe);
        }


    }


    @Test
    @DisplayName("Admin Test / existing provider adds rental and then admin deletes it")
    void testingAdmin(){
        AdminMainMenu menu = new AdminMainMenu();
        ArrayList<Activity> act = new ArrayList<>();
        ArrayList<Account> acc = new ArrayList<>();
        try{
        (new ProviderMainMenu()).addRental(p, "thalassa", "Leukada", "Hotel", 35, 70, 3);
        menu.deleteRental(p, 3);
        act = FileHandler.loadFile("activities", Activity.class);
        
        assertEquals(" deleted rental 3", act.get(act.size()-1).getAction());




        }catch(IOException exc){
            System.err.println(exc);
        }catch(ClassNotFoundException cnfe){
            System.err.println(cnfe);
        }
        
    }

    
    
}