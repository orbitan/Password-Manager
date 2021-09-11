package passwordManager;

import Exceptions.NotAuthorizedException;
import java.util.Scanner;
        
public class PasswordManager{
    
    public static void main(String[] args) throws NotAuthorizedException{
        PasswordManager pwMan = new PasswordManager();
        Safe safe = new Safe();
        Scanner sc = new Scanner(System.in);
        System.out.println("Enter your masterpassword: ");
        int attempts = 3;
        boolean audit = false;
        while(attempts > 0 && audit == false){
            String masterpw = sc.nextLine();
            audit = safe.authorize(masterpw);
            if(audit){
                break;
            }
            else{
                attempts--;
                System.out.println("Password incorrect!\n" + attempts+ " attempts left:");
                //quit application
                if(attempts <= 0) System.exit(-1);
            }
        }
        
        while (true){
            System.out.println("Enter command: ");
            String command = sc.next();
            switch(command){
                case "add":
                    System.out.print("add: ");
                switch (sc.next()) {
                    case "new" ->                         {
                            System.out.print("new: ");
                            Password pw = pwMan.newPassword(sc);
                            System.out.println(pw.toString());
                            System.out.println("Save password ? (true/false)");
                            boolean save = sc.nextBoolean();
                            if(save){
                                System.out.println("Saving...");
                                safe.addPw(pw);
                            }
                            else System.out.println("Abort");
                            break;
                        }
                    case "gen" ->                        {
                            System.out.print("gen: ");
                            Password pw = pwMan.newPasswordGen(sc);
                            System.out.println(pw.toString());
                            System.out.println("Save password ? (true/false)");
                            boolean save = sc.nextBoolean();
                            if(save){
                                System.out.println("Saving...");
                                safe.addPw(pw);
                            }
                            else System.out.println("Abort");
                            break;
                        }
                    default -> {
                        System.out.println("'new' to create a new login\n" 
                                + "'gen' to create a new login with generated password\n" 
                                + "'quit' to leave current mode\n" 
                                + "'exit' to quit programm");
                        break;
                    }
                }
                case "remove":
                case "change":
                case "get":
                default: 
                    System.out.println("'add' to create new login\n"
                                           +"'remove' to delete login\n"
                                           + "'change' to change login\n"
                                            +"'get to show login(s)'");
                    break;
            }
        }
    }
    private Password newPassword(Scanner sc){
            sc.nextLine();
            System.out.println("Service: ");
            String service = sc.nextLine();
            System.out.println("Username: ");
            String username = sc.nextLine();
            System.out.println("Password: ");
            String password = sc.nextLine();
            System.out.println("Tag: ");
            String tag = sc.nextLine();
            Password pw = new Password(service, username, password, tag);
            return pw;
    }
    private Password newPasswordGen(Scanner sc){
            sc.nextLine();
            System.out.println("Service: ");
            String service = sc.nextLine();
            System.out.println("Username: ");
            String username = sc.nextLine();
            System.out.println("Tag: ");
            String tag = sc.nextLine();
            System.out.println("Password length: ");
            int length = sc.nextInt();
            System.out.println("lowercase ?: (true/false)");
            boolean lowerCase = sc.nextBoolean();
            System.out.println("Uppercase ?: (true/false)");
            boolean upperCase = sc.nextBoolean();
            System.out.println("Numbers ?: (true/false)");
            boolean numbers = sc.nextBoolean();
            System.out.println("Special characters ?: (true/false)");
            boolean spezial = sc.nextBoolean();
            PasswordGenerator pwGen = new PasswordGenerator();
            String password;
            try{
                password = pwGen.fertigGeneriertesPasswort(length, lowerCase, spezial, numbers, upperCase);
            }catch (NumberFormatException e){
                   password = pwGen.fertigGeneriertesPasswort(16, true, true, true, true);
            }
            Password pw = new Password(service, username, password, tag);
            return pw;
    }
}