import java.io.IOException;
import java.io.Serial;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;
import java.io.Serializable;

/**
 * we save every activity in the app
 * we keep the username of the person/account,a description of the action and the time*/

public class Activity implements Serializable{

    private String username, action, time;
    @Serial
    private static final long serialVersionUID = 95569L;



    public Activity(){
        setUsername(null);
        setAction(null);
        setTime(null);
    }

    public Activity(String username, String action, String time){
        setUsername(username);
        setAction(action);
        setTime(time);
    }


    public void saveAct(Account user, String action){
        String format = "dd/MM/yyyy HH:mm:ss" ;
        final SimpleDateFormat sdf = new SimpleDateFormat(format);
        sdf.setTimeZone(TimeZone.getTimeZone("GMT+2"));
        String time =  sdf.format(new Date());
        Activity a= new Activity(user.getUsername(), action, time);
        try{
            FileHandler.saveFile("activities", a);
        } catch (IOException fileNotFoundException) {
            System.err.println(fileNotFoundException);
        }
    }


    public String getUsername(){
        return this.username;
    }

    public void setUsername(String username){
        this.username=username;
    }
    public String getAction(){
        return this.action;
    }

    public void setAction(String action){
        this.action=action;
    }
    public String getTime(){
        return this.time;
    }

    public void setTime(String time){
        this.time=time;
    }

}