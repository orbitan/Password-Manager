package passwordManager;

public class Password {
    
    protected final String service;
    protected final String username;
    protected final String password;
    
    public Password(String service, String username, String password){
        this.service = service;
        this.username = username;
        this.password = password;
    }
    public String getService(){
        return this.service;
    }
    public String getUsername(){
        return this.username;
    }
    public String getPasswort(){
        return this.password;
    }
    @Override
    public String toString(){
        String result = "";
        result += this.service + " "
                + this.username + " " 
                + this.password;
        return result;
    }
}
