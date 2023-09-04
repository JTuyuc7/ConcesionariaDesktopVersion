package Utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations {

    public boolean isValidField(String input){
        return input.isEmpty();
    }

    public boolean validateRegisterFields(String name, String lastName, String email, String password){
        return name.isEmpty() || lastName.isEmpty() || email.isEmpty() || password.isEmpty();
    }

    public boolean validatePasswordLength(String password){
        return password.length() < 6;
    }

    public boolean validateEmailAddress(String email){
        String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\\\.[A-Za-z0-9_-]+)*@[^-][A-Za-z0-9-]+(\\\\.[A-Za-z0-9-]+)*(\\\\.[A-Za-z]{2,})$";
        String patterRx = "^[A-Za-z0-9+_.-]+@(.+)$";
        Pattern pattern = Pattern.compile(patterRx);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }
}
