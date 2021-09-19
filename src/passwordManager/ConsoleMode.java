package passwordManager;

import Exceptions.NoLoginFoundException;
import Exceptions.NotAuthorizedException;
import java.util.List;
import java.util.Scanner;

public class ConsoleMode extends Mode {
    
    private final Scanner sc;

    public ConsoleMode() {
        this.sc = new Scanner(System.in);
    }
    @Override
    public void run(){
        if(!authorize(safe)) System.exit(-1);
        
        while (true){
            
            System.out.println("Enter command: (new | gen | remove | change | get");
            String command = sc.next();
            switch(command){
                case "new" -> {
                    System.out.println("new: ");
                    Login pw = newLogin();
                    System.out.println(pw.toString());
                    System.out.println("Save password ? (true/false)");
                    boolean save = sc.nextBoolean();
                    if(save){
                        System.out.println("Saving...");
                        try {
                            safe.addPw(pw);
                        } catch (NotAuthorizedException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                    else System.out.println("Abort");
                }
                case "gen" -> {
                    System.out.println("gen: ");
                    Login pw1 = newLoginGen();
                    System.out.println(pw1.toString());
                    System.out.println("Save password ? (true/false)");
                    boolean save1 = sc.nextBoolean();
                    if(save1){
                        System.out.println("Saving...");
                        try {
                            safe.addPw(pw1);
                        } catch (NotAuthorizedException ex) {
                            System.out.println(ex.getMessage());
                        }
                    }
                    else System.out.println("Abort");
                }
                case "remove" -> {
                    System.out.println("Enter service:");
                    String service = sc.nextLine();
                    System.out.println("Enter username");
                    String username = sc.nextLine();
                    try {
                        safe.removePw(service, username);
                        System.out.println("Login removed");
                    } catch (NotAuthorizedException | NoLoginFoundException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                case "change" -> {
                    sc.nextLine();
                    System.out.println("Enter service:");
                    String service1 = sc.nextLine();
                    System.out.println("Enter username");
                    String username1 = sc.nextLine();
                    Login login = newLogin();
                    try {
                        safe.removePw(service1, username1);
                        safe.addPw(login);
                        System.out.println("Login changed");
                    } catch (NotAuthorizedException | NoLoginFoundException ex) {
                        System.out.println(ex.getMessage());
                    }
                }
                case "get" -> {
                    System.out.println("Specify: (all | service | username)");
                    sc.nextLine();
                    String spec = sc.nextLine();
                    switch(spec){
                        case "all":
                            List<Login> list = safe.getAllPasswords();
                            list.forEach(login1 -> {
                                System.out.println(login1.toString());
                            });
                            break;

                        case "service":
                            System.out.println("Enter service:");
                            String service2 = sc.nextLine();
                            try{
                                List<Login> list1 = safe.getPwByService(service2);
                                list1.forEach(login1 -> {
                                    System.out.println(login1.toString());
                                });
                            } catch (NoLoginFoundException ex) {
                                System.out.println(ex.getMessage());
                            }
                            break;
                        case "username":
                            System.out.println("Enter username:");
                            String username2 = sc.nextLine();
                            try{
                                List<Login> list1 = safe.getPwByUsername(username2);
                                for(Login login1:list1){
                                    System.out.println(login1.toString());
                                }
                            }catch(NoLoginFoundException ex){
                                    ex.getMessage();
                            }
                            break;
                    }
                }
            }
        }
    }
    public boolean authorize(Safe safe){
        System.out.println("Enter your masterpassword: ");
        int attempts = 3;
        boolean audit;
        while(attempts > 0){
            String masterpw = sc.nextLine();
            audit = safe.authorize(masterpw);
            if(audit){
                return true;
            }
            else{
                attempts--;
                System.out.println("Password incorrect!\n" + attempts+ " attempts left:");
            }
        }
        return false;
    }
    private Login newLogin(){
            sc.nextLine();
            System.out.println("Service: ");
            String service = sc.nextLine();
            System.out.println("Username: ");
            String username = sc.nextLine();
            System.out.println("Password: ");
            String password = sc.nextLine();
            System.out.println("Tag: ");
            String tag = sc.nextLine();
            System.out.println("Url: (can be skipped)");
            String url = sc.nextLine();
            Login pw;
            if(url.length() > 0){
                pw = new OnlineLogin(service, username, password, tag, url);
            }
            else pw = new Login(service, username, password, tag);
            return pw;
    }
    private Login newLoginGen(){
            sc.nextLine();
            System.out.println("Service: ");
            String service = sc.nextLine();
            System.out.println("Username: ");
            String username = sc.nextLine();
            System.out.println("Tag: ");
            String tag = sc.nextLine();
            System.out.println("Url: (can be skipped)");
            String url = sc.nextLine();
            System.out.println("Password length: ");
            int length = sc.nextInt();
            PasswordGenerator pwGen = new PasswordGenerator();
            String password;
            try{
                password = pwGen.fertigGeneriertesPasswort(length);
            }catch (NumberFormatException e){
                   password = pwGen.fertigGeneriertesPasswort(16);
            }
            Login pw;
            if(url.length() > 0){
                pw = new OnlineLogin(service, username, password, tag, url);
            }
            else pw = new Login(service, username, password, tag);
            return pw;
    }
}
