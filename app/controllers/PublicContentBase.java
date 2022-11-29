package controllers;


import helpers.HashUtils;
import models.User;
import play.mvc.Controller;

public class PublicContentBase extends Controller {

    public static void register(){
        render();
    }

 public static void processRegister(String username, String password, String passwordCheck, String type){
        
        
        if(validPassword(password) && validUser()){
          User u = new User(username, HashUtils.getMd5(password), type, -1);
          u.save();
        }else{
            System.out.println("User invalid");
        }
        
        registerComplete();
    }
    public static boolean validPassword(String password){
            boolean valid = false;
            if (password.length()<8) 
            {
                valid = false;
                System.out.println("Password must have at least 8 characters");
                continue; // skip to next iteration
            }
            for (int i = 0; i < password.length(); i++){
                char c = password.charAt(i);
                if (       ('a' <= c && c <= 'z') // Checks if it is a lower case letter
                        || ('A' <= c && c <= 'Z') //Checks if it is an upper case letter
                        || ('0' <= c && c <= '9') //Checks to see if it is a digit
                ) 
                {
                    valid = true;
                }
                else 
                {
                    // tells the user that only letters & digits are allowed
                    System.out.println("Only letter & digits are acceptable.");
                    valid = false;
                    break;
                }

            }
        } while(!valid);
        return valid;
    }

       public boolean validUser(String user) {
           String ePattern = "^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@((\\[[0-9]{1,3}\\
           .[0-9]{1,3}\\.[0-9]{1,3}\\.[0-9]{1,3}\\])|(([a-zA-Z\\-0-9]+\\.)+[a-zA-Z]{2,}))$";
           java.util.regex.Pattern p = java.util.regex.Pattern.compile(ePattern);
           java.util.regex.Matcher m = p.matcher(email);
           return m.matches();
    }

    public static void registerComplete(){
        render();
    }
}

