package com.driver;

public class Email {

    private String emailId;
    private String password;

    public Email(String emailId){
        this.emailId = emailId;
        this.password = "Accio@123";
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailId() {
        return emailId;
    }

    public String getPassword() {
        return password;
    }

    public void changePassword(String oldPassword, String newPassword){
        //Change password only if the oldPassword is equal to current password and the new password meets all of the following:
        // 1. It contains at least 8 characters
        // 2. It contains at least one uppercase letter
        // 3. It contains at least one lowercase letter
        // 4. It contains at least one digit
        // 5. It contains at least one special character. Any character apart from alphabets and digits is a special character

        if(oldPassword.equals(getPassword()))
        {
            boolean checkForUpperCase=false;
            boolean checkForLowerCase=false;
            boolean checkForDigit=false;
            boolean checkForSpecialChar=false;
            if(newPassword.length()==8)
            {
                for(int ch : newPassword.toCharArray()){
                    if(Character.isUpperCase(ch)){
                        checkForUpperCase = true;
                    } else if (Character.isLowerCase(ch)) {
                        checkForLowerCase = true;
                    }else if(Character.isDigit(ch)){
                        checkForDigit = true;
                    }else{
                        checkForSpecialChar = true;
                    }
                }
            }
            if(checkForUpperCase && checkForLowerCase && checkForDigit && checkForSpecialChar)
            {
                setPassword(newPassword);
            }
        }
    }
}
