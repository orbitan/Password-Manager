package passwordManager;

import Exceptions.NoPasswordFoundException;
import Exceptions.IncorrectPasswordException;
import Exceptions.NotAuthorizedException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Safe implements Serializable{
    
    private List<Password> list;
    private String masterPw;
    private boolean authorized;
    
    public Safe(){
        this.list = new ArrayList();
        this.masterPw = loadMasterPw();
        this.authorized = false;
    }
    //load masterpassword from file/database
    private String loadMasterPw(){
        String masterpw = "1234";       //dummy       
        
        //TODO: load masterpassword from file/database
        
        return masterpw;
    }
    //save masterpassword into file/database
    private void saveMasterPw(){
        
        //TODO: save implementation
        
    }
    //change the masterpassword for the Safe object; also used to set the masterpassword for the first time
    public void changeMasterPw(String oldPw, String newPw) throws IncorrectPasswordException{
        if(oldPw.equals(this.masterPw)){
            this.masterPw = newPw;
            saveMasterPw();
        }
        else throw new Exceptions.IncorrectPasswordException();
    }
    //authorize the access to the Safe object
    public void authorize(String password) throws IncorrectPasswordException{
        if(password.equals(masterPw)){
            this.authorized = true;
            load();
        }
        else throw new IncorrectPasswordException();
    }
    //loads the list of passwords from file/database
    private void load(){
        
    //TODO
    
    }
    //saves the list object to a file/database
    private boolean save(){
        try{
            
            //TODO: save implementation
            
        }catch(Exception e){
            return false;
        }
            return true;
    }
    //add a password to the list
    public void addPw(Password password) throws NotAuthorizedException{
        if(this.authorized){
            this.list.add(password);
            save();
        }
        else throw new Exceptions.NotAuthorizedException();
    }
    //remove a password from the list
    public void removePw(Password password) throws NotAuthorizedException{
        if(this.authorized){
            this.list.remove(password);
            save();
        }
        else throw new Exceptions.NotAuthorizedException();
    }
    //change a password from the list; since Passwords are final, it needs to be removed and added again
    public void changePw(Password password, Password newPassword) throws NotAuthorizedException{
        if(this.authorized){
            removePw(password);
            addPw(newPassword);
            save();
        }
        else throw new Exceptions.NotAuthorizedException();
    }
    //return a list of passwords which matches the given service
    public List<Password> getPwByService(String service) throws NoPasswordFoundException{
        List<Password> resultList = new ArrayList();
        
        //TODO: fill list from ResultSet via file/database
        
        if(list.isEmpty()) throw new NoPasswordFoundException("No password stored with service: " + service);
        return resultList;
    }
    //return a list of passwords which matches the given username
    public List<Password> getPwByUsername(String username) throws NoPasswordFoundException{
        List<Password> resultList = new ArrayList();
        
        //TODO: fill list from ResultSet via file/database
        
        if(resultList.isEmpty()) throw new NoPasswordFoundException("No password stored with username: " + username);
        return resultList;
    }
    //return a list of passwords which matches the given tag
    public List<Password> getPwByTag(String tag) throws NoPasswordFoundException{
        List<Password> resultList = new ArrayList();
        
        //TODO: fill list from ResultSet via file/database
        
        if(resultList.isEmpty()) throw new NoPasswordFoundException("No password stored with tag: " + tag);
        return resultList;
    }
    //returns all stored passwords
    public List<Password> getAllPasswords(){
        return this.list;
    }
}
