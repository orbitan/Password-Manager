package passwordManager;

import Exceptions.NoLoginFoundException;
import Exceptions.IncorrectPasswordException;
import Exceptions.NotAuthorizedException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Safe implements Serializable{
    
    private List<Login> list;
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
    public boolean authorize(String password){
        if(password.equals(masterPw)){
            this.authorized = true;
            load();
            return true;
        }
        else return false;
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
    public void addPw(Login password) throws NotAuthorizedException{
        if(this.authorized){
            this.list.add(password);
            save();
        }
        else throw new Exceptions.NotAuthorizedException();
    }
    //remove a password from the list
    public void removePw(String service, String username) throws NotAuthorizedException,NoLoginFoundException{
        if(this.authorized){
            boolean found = false;
            for(Login login:list){
                if(login.service.equals(service) && login.username.equals(username)){
                    found = true;
                    list.remove(login);
                    save();
                }
            }
            if(!found){
                throw new NoLoginFoundException();
            }
        }
        else throw new Exceptions.NotAuthorizedException();
    }
    //change a password from the list; since Passwords are final, it needs to be removed and added again
    public void changePw(String service, String username, Login newPassword) throws NotAuthorizedException, NoLoginFoundException{
        if(this.authorized){
            removePw(service, username);
            addPw(newPassword);
            save();
        }
        else throw new Exceptions.NotAuthorizedException();
    }
    //return a list of passwords which matches the given service
    public List<Login> getPwByService(String service) throws NoLoginFoundException{
        List<Login> resultList = new ArrayList();
        
        //TODO: fill list from ResultSet via file/database
        
        if(list.isEmpty()) throw new NoLoginFoundException("No password stored with service: " + service);
        return resultList;
    }
    //return a list of passwords which matches the given username
    public List<Login> getPwByUsername(String username) throws NoLoginFoundException{
        List<Login> resultList = new ArrayList();
        
        //TODO: fill list from ResultSet via file/database
        
        if(resultList.isEmpty()) throw new NoLoginFoundException("No password stored with username: " + username);
        return resultList;
    }
    //return a list of passwords which matches the given tag
    public List<Login> getPwByTag(String tag) throws NoLoginFoundException{
        List<Login> resultList = new ArrayList();
        
        //TODO: fill list from ResultSet via file/database
        
        if(resultList.isEmpty()) throw new NoLoginFoundException("No password stored with tag: " + tag);
        return resultList;
    }
    //returns all stored passwords
    public List<Login> getAllPasswords(){
        return this.list;
    }
}
