package passwordManager;
        
public class PasswordManager{
    
    private  Mode mode;
    
    public static void main(String[] args){
        PasswordManager pwMan = new PasswordManager();
        pwMan.mode = Mode.get(args);
        pwMan.mode.run();  
    }
}