package passwordManager;

public class Password {
    
    protected final String service;
    protected final String username;
    protected final String password;
    protected final String tag;
    
    public Password(String service, String username, String password){
        this.service = service;
        this.username = username;
        this.password = password;
        this.tag = "";
    }
    public Password(String service, String username, String password, String tag){
        this.service = service;
        this.username = username;
        this.password = password;
        this.tag = tag;
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
    public String getTag(){
        return this.tag;
    }
    @Override
    public String toString(){
        String result = "";
        result += this.service + " "
                + this.username + " " 
                + this.password + " "
                + this.tag;
        return result;
    }
}
