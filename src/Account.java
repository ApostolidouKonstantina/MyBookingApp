import java.io.Serial;
import java.io.Serializable;
/**
 * every account contains a name, a username, a password, the type of it(admin, provider, customer)
 * and a boolean that let us know if the account is approved or not.
 * If approved==true then the account is approved by admin and active
 * If approved==false then the account isn't approved and is considered a request*/

public class Account implements Serializable{

    @Serial
    private static final long serialVersionUID = 92638L;

    private String username, password, name, type;
    private boolean approved;


    public Account(){
        setName(null);
        setUsername(null);
        setPassword(null);
        setType(null);
        setApproved(false);
    }

    public Account(String name, String username, String password, String type, boolean approved){
        setName(name);
        setUsername(username);
        setPassword(password);
        setType(type);
        setApproved(approved);
    }

    public void setApproved(boolean approved){
        this.approved=approved;
    }

    public boolean getApproved(){
        return this.approved;
    }

    public void setName(String name){
        this.name=name;
    }

    public String getName(){
        return this.name;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public String getUsername(){
        return this.username;
    }

    public void setPassword(String password){
        this.password=password;
    }

    public String getPassword(){
        return this.password;
    }

    public void setType(String type){
        this.type=type;
    }

    public String getType(){
        return this.type;
    }

}